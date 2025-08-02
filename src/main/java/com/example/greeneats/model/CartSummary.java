package com.example.greeneats.model;

import java.util.Map;

public class CartSummary {
    private int totalItems;
    private int totalQuantity;
    private double total;
    private Map<String, Integer> categoryCounts;
    private Map<String, Double> categoryTotals;

    public CartSummary(int totalItems, int totalQuantity, double total, Map<String, Integer> categoryCounts,
                       Map<String, Double> categoryTotals) {
        this.totalItems = totalItems;
        this.totalQuantity = totalQuantity;
        this.total = total;
        this.categoryCounts = categoryCounts;
        this.categoryTotals = categoryTotals;
    }

    // Getters
    public int getTotalItems() { return totalItems; }
    public int getTotalQuantity() { return totalQuantity; }
    public double getTotal() { return total; }
    public Map<String, Integer> getCategoryCounts() { return categoryCounts; }
    public Map<String, Double> getCategoryTotals() { return categoryTotals; }

    public String getFormattedTotal() { return String.format("₱%.2f", total); }
}