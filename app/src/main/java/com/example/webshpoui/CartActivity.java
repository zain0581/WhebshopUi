package com.example.webshpoui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Models.CartAdapter;
import Models.CartManager;
import Models.Items;

public class CartActivity extends AppCompatActivity {
    private ListView listView;
    private CartAdapter cartAdapter;
    private TextView totalPriceTextView;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = findViewById(R.id.cart_list_view);
        totalPriceTextView = findViewById(R.id.total_price);
        checkoutButton = findViewById(R.id.checkout_button);

        // Set up adapter with items from CartManager
        cartAdapter = new CartAdapter(this, R.layout.cart_item_row, CartManager.getInstance().getCartItems());
        listView.setAdapter(cartAdapter);

        // Update total price
        updateTotalPrice();

        // Setup the checkout button functionality
        checkoutButton.setOnClickListener(v -> checkout());
    }

    private void updateTotalPrice() {
        int totalPrice = CartManager.getInstance().getTotalPrice();
        totalPriceTextView.setText("Total: $" + totalPrice);
    }

    private void checkout() {
        // Check if the cart is empty
        if (CartManager.getInstance().getCartItems().isEmpty()) {
            // Show a message indicating that the cart is empty
            Toast.makeText(this, "Cart is empty. No order placed.", Toast.LENGTH_SHORT).show();
        } else {
            // Clear the cart items
            CartManager.getInstance().clearCart();

            // Notify the adapter about the data change
            cartAdapter.notifyDataSetChanged();

            // Show a toast message
            Toast.makeText(this, "Order has been placed!", Toast.LENGTH_SHORT).show();

            // Navigate back to MainActivity
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear all activities on top of MainActivity
            startActivity(intent);

            // Optionally, finish this activity
            finish();
        }
    }
}
