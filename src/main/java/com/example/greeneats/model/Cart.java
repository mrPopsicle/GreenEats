package com.example.greeneats.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem menuItem, int quantity) {
        for (CartItem cartItem : items) {
            if (cartItem.getMenuItem().equals(menuItem)) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(menuItem, quantity));
    }

    public void removeItem(MenuItem menuItem) {
        items.removeIf(cartItem -> cartItem.getMenuItem().equals(menuItem));
    }


    public void updateQuantity(MenuItem menuItem, int newQuantity) {
        if (newQuantity <= 0) {
            removeItem(menuItem);
            return;
        }

        for (CartItem cartItem : items) {
            if (cartItem.getMenuItem().equals(menuItem)) {
                cartItem.setQuantity(newQuantity);
                return;
            }
        }
    }

    // Clear cart
    public void clearCart() {
        items.clear();
    }

    // Get all items
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    // Calculate total
    public double getTotal() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public String getFormattedTotal() {
        return String.format("₱%.2f", getTotal());
    }

    // Check if empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Get item count
    public int getItemCount() {
        return items.size();
    }

    // Get total quantity
    public int getTotalQuantity() {
        return items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cart (").append(getItemCount()).append(" items):\n");
        for (CartItem item : items) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append("Total: ").append(getFormattedTotal());
        return sb.toString();
    }
}