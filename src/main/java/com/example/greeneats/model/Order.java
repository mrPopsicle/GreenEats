package com.example.greeneats.model;
//Receipt
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//For OrderHistory and Receipt
public class Order {
    private String username;
    private String orderId;
    private String orderDate;
    private String itemsSummary;
    private double totalAmount;

    // Constructor for creating new orders
    public Order(String username, String itemsSummary, double totalAmount) {
        this.username = username;
        this.orderId = "ORD" + System.currentTimeMillis();
        this.orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.itemsSummary = itemsSummary;
        this.totalAmount = totalAmount;
    }

    // Constructor for reading from CSV
    public Order(String username, String orderId, String orderDate, String itemsSummary, double totalAmount) {
        this.username = username;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.itemsSummary = itemsSummary;
        this.totalAmount = totalAmount;
    }



    // Getters
    public String getUsername() { return username; }
    public String getOrderId() { return orderId; }
    public String getOrderDate() { return orderDate; }
    public String getItemsSummary() { return itemsSummary; }
    public double getTotalAmount() { return totalAmount; }

    // For display
    public String getFormattedTotal() {
        return String.format("₱%.2f", totalAmount);
    }

    // Convert to CSV line
    public String toCsvLine() {
        return username + "," + orderId + "," + orderDate + "," +
                "\"" + itemsSummary + "\"," + totalAmount;
    }

    @Override
    public String toString() {
        return orderDate + " - " + getFormattedTotal() + " - " + itemsSummary;
    }
}
