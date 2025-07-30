package com.example.greeneats.model;

public abstract class MenuItem {
    private double price;
    private String name;
    private String description;
    private String image;
    private String category;
    private String calories;

    public MenuItem(double price, String name, String description, String image, String category, String calories) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
