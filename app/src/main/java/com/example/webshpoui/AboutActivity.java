package com.example.webshpoui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Models.CartManager;
import Models.Items;

public class AboutActivity extends AppCompatActivity {

    private TextView itemName, itemDescription, itemPrice, itemQty, itemCategory;
    private Button addToCartButton;  // Add this line to declare the button
    private Items currentItem;       // Declare the currentItem variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Initialize views
        itemName = findViewById(R.id.item_name);
        itemDescription = findViewById(R.id.item_description);
        itemPrice = findViewById(R.id.item_price);
        itemQty = findViewById(R.id.item_qty);
        itemCategory = findViewById(R.id.item_category);
        addToCartButton = findViewById(R.id.add_to_cart_button);  // Initialize the button

        // Retrieve the item details passed from the MainActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        int price = intent.getIntExtra("price", 0);
        int qty = intent.getIntExtra("qty", 0);
        String category = intent.getStringExtra("category");

        // Set the text to the respective views
        itemName.setText(name);
        itemDescription.setText(description);
        itemPrice.setText("Price: $" + price);
        itemQty.setText("Quantity: " + qty);
        itemCategory.setText("Category: " + category);

        // Create an item object with the received data
        currentItem = new Items(0, name, description, price, qty, category);

        // Handle "Add to Cart" button click
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the current item to the cart using CartManager
                CartManager.getInstance().addItemToCart(currentItem);

                // Show confirmation message
                Toast.makeText(AboutActivity.this, "Added to cart: " + currentItem.getName(), Toast.LENGTH_SHORT).show();

                // Navigate to CartActivity automatically after adding item to cart
                Intent cartIntent = new Intent(AboutActivity.this, CartActivity.class);
               startActivity(cartIntent);
            }
        });
        // FloatingActionButton Click to Navigate to CartActivity

    }



}
