package com.example.greeneats.model;

import java.util.List;

public interface CartOperations {

    boolean addItem(MenuItem item, int quantity);

    boolean addItem(MenuItem item, String size, int quantity);

    boolean addCartItem(CartItem cartItem);

    boolean removeCartItem(CartItem cartItem);

    int removeMenuItem(MenuItem item);

    boolean updateQuantity(CartItem cartItem, int newQuantity);

    void clearCart();
    CartSummary getCartSummary();
    boolean isEmpty();


    int getItemCount();

    int getTotalQuantity();

    double getTotal();
}
