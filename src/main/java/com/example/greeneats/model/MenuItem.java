package com.example.greeneats.model;

import java.util.Objects;

public class MenuItem {
    private String name;
    private double price;
    private String description;
    private double calories;


    public MenuItem(String name, double price, String description, double calories) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.calories = calories;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    // For price format
    public String getFormattedPrice() {
        return String.format("₱%.2f", price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MenuItem menuItem = (MenuItem) obj;
        return Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " - " + getFormattedPrice() + " (" + calories + " cal)";
    }
}