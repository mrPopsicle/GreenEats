package com.example.greeneats.model;


import java.util.Objects;

public class MenuItem {
    private String name;
    private double price;
    private String description;
    private double calories;
    private String category;
    private String image;


    public MenuItem(String name, double price, String description, double calories, String category, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.calories = calories;
        this.category = category;
        this.image = image;

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

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    // For price format
    public String getFormattedPrice() {
        return String.format("₱%.2f", price);
    }

    public boolean equals(MenuItem other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MenuItem)) return false;
        return equals((MenuItem) obj); // reuse your own method
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