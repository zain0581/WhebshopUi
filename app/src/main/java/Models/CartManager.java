package Models;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private List<Items> cartItems;

    // Private constructor for Singleton pattern
    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    // Add an item to the cart
    public void addItemToCart(Items item) {
        cartItems.add(item);
    }

    // Get all items in the cart
    public List<Items> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    // Clear the cart
    public void clearCart() {
        cartItems.clear();
    }

    // Get total price of items in the cart
    public int getTotalPrice() {
        int total = 0;
        for (Items item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }
}
