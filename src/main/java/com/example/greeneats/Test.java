package com.example.greeneats;

import com.example.greeneats.model.Cart;
import com.example.greeneats.model.MenuItem;
import com.example.greeneats.model.Order;

public class Test {
    public static void main(String[] args) {
// create menu items
        MenuItem burger = new MenuItem("Burger", 99.99, "Tasty burger", 500, "Fast Food", "burger.png");
        MenuItem fries = new MenuItem("Fries", 49.99, "Crispy fries", 300, "Fast Food", "fries.png");

// create cart and add items
        Cart cart = new Cart();
        cart.addItem(burger, 2);
        cart.addItem(fries, 1);

// generate summary for order (attach sa Fx:Id)
        String itemsSummary = Cart.generateItemsSummary(cart.getItems());

// create order
        Order order = new Order("john_doe", itemsSummary, cart.getTotal());

        System.out.println(order);


    }
}
