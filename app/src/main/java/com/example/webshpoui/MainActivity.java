package com.example.webshpoui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Models.Items;
import Models.VersionAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Items> listOfItems = new ArrayList<>();
    private VersionAdapter versionAdapter;
    private FloatingActionButton fabViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        listView = findViewById(R.id.list_view);
        versionAdapter = new VersionAdapter(MainActivity.this, R.layout.item_row, listOfItems);
        listView.setAdapter(versionAdapter);

        // Fetch data from the API
        fetchDataFromApi();

        // Initialize the FloatingActionButton
        fabViewCart = findViewById(R.id.fab_view_cart);

        // Set ClickListener to navigate to CartActivity
        fabViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to CartActivity
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        // Set up status bar color and system UI visibility for Android Lollipop and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decor = getWindow().getDecorView();
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        // Initialize admin button and set ClickListener to navigate to AdminActivity
        Button adminButton = findViewById(R.id.admin_button);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    // Fetch data from the API
    private void fetchDataFromApi() {
        String url = "http://192.168.0.223:8080/webshop"; // Replace with your actual API URL

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
                Log.e("MainActivity", "Error fetching data: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonArrayRequest);
    }
}
