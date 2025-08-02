package com.example.greeneats.model;

import java.util.Objects;

public class CartItem {
    private MenuItem menuItem;
    private int quantity;
    private double totalPrice;


    public CartItem() {
        this.quantity = 1;
        this.totalPrice = 0.0;
    }

    public CartItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.quantity = 1;
        calculateTotalPrice();
    }


    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = Math.max(1, quantity);
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        if (menuItem != null) {
            totalPrice = menuItem.getPrice() * quantity;
        } else {
            totalPrice = 0.0;
        }
    }

    // Getters and Setters
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        calculateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(0, quantity);
        calculateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Utility methods for display
    public String getFormattedPrice() {
        return String.format("₱%.2f", totalPrice);
    }

    public String getDisplayText() {
        if (menuItem == null) return "Unknown Item";
        return menuItem.getName() + " x" + quantity;
    }

    // Validation
    public boolean isValid() {
        return menuItem != null && quantity > 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        CartItem cartItem = (CartItem) obj;
        return quantity == cartItem.quantity &&
                Objects.equals(menuItem, cartItem.menuItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItem, quantity);
    }

    @Override
    public String toString() {
        return getDisplayText() + " - " + getFormattedPrice();
    }
}
