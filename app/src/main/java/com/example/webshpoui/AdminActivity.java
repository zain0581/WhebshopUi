package com.example.webshpoui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Models.Items;
import Models.VersionAdapter; // Ensure this is the adapter you're using

public class AdminActivity extends AppCompatActivity {

    private ListView listView;
    private List<Items> listOfItems = new ArrayList<>();
    private VersionAdapter versionAdapter; // Your custom adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = findViewById(R.id.list_view);
        versionAdapter = new VersionAdapter(this, R.layout.item_row, listOfItems); // Ensure item_row is your layout for list items
        listView.setAdapter(versionAdapter);

        Button createButton = findViewById(R.id.create_button);
      //  Button editButton = findViewById(R.id.edit_button);
        //Button deleteButton = findViewById(R.id.delete_button);

        // Set button click listeners
        createButton.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, CreateProductActivity.class)));
       // editButton.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, EditProductActivity.class)));
        //deleteButton.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, DeleteProductActivity.class)));

        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        String url = "http://192.168.0.223:8080/webshop"; // Update with your API endpoint

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listOfItems.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String name = jsonObject.getString("name");
                                String description = jsonObject.getString("description");
                                int price = jsonObject.getInt("price");
                                int qty = jsonObject.getInt("qty");
                                String category = jsonObject.getString("category");

                                Items item = new Items(id, name, description, price, qty, category);
                                listOfItems.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        versionAdapter.notifyDataSetChanged(); // Notify adapter about the new data
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AdminActivity", "Error fetching data: " + error.getMessage());
                Toast.makeText(AdminActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonArrayRequest);
    }
}
