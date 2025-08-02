package com.example.greeneats.model;

import java.util.HashMap;
import java.util.Map;

public class CartSummary {
    private final Map<String, Integer> categoryCount = new HashMap<>();
    private double totalCalories;
    private double totalPrice;

    public void addItem(String category, double calories) {
        categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        totalCalories += calories;
    }

    public Map<String, Integer> getCategoryCount() {
        return categoryCount;
    }

    public double getTotalCalories() {
        return totalCalories;
    }
}
