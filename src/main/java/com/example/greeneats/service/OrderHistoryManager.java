package com.example.greeneats.service;

import com.example.greeneats.model.Order;
import com.example.greeneats.model.CartItem;

import java.io.*;
import java.util.*;

public class OrderHistoryManager {
    private static final String ORDERS_FILE = "orders.csv";

    public OrderHistoryManager() {
        createFileIfNotExists();
    }

    // Add a new order
    public void addOrder(String username, List<CartItem> cartItems, double total) {
        // Create summary of items
        String itemsSummary = createItemsSummary(cartItems);

        Order order = new Order(username, itemsSummary, total);

        // Append to CSV file
        try (FileWriter fw = new FileWriter(ORDERS_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(order.toCsvLine());

        } catch (IOException e) {
            System.out.println("Could not save order: " + e.getMessage());
        }
    }

    // Get all orders for a user
    public List<Order> getUserOrders(String username) {
        List<Order> userOrders = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ORDERS_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                Order order = parseOrderFromCsv(line);
                if (order != null && order.getUsername().equals(username)) {
                    userOrders.add(order);
                }
            }

        } catch (IOException e) {
            System.out.println("Could not read orders: " + e.getMessage());
        }

        return userOrders;
    }

    // Helper method to create items summary
    private String createItemsSummary(List<CartItem> cartItems) {
        if (cartItems.isEmpty()) return "No items";

        StringBuilder summary = new StringBuilder();
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            summary.append(item.getMenuItem().getName()).append(" x").append(item.getQuantity());
            if (i < cartItems.size() - 1) {
                summary.append("; ");
            }
        }
        return summary.toString();
    }
    private Order parseOrderFromCsv(String csvLine) {
        try {

            String[] parts = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            if (parts.length >= 5) {
                String username = parts[0].trim();
                String orderId = parts[1].trim();
                String orderDate = parts[2].trim();
                String itemsSummary = parts[3].replace("\"", "").trim();
                double totalAmount = Double.parseDouble(parts[4].trim());

                return new Order(username, orderId, orderDate, itemsSummary, totalAmount);
            }
        } catch (Exception e) {
            System.out.println("Error parsing CSV line: " + csvLine);
        }
        return null;
    }

    // Create CSV file with headers if it doesn't exist
    private void createFileIfNotExists() {
        File file = new File(ORDERS_FILE);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(ORDERS_FILE))) {
                pw.println("username,order_id,order_date,items_summary,total_amount");
            } catch (IOException e) {
                System.out.println("Could not create orders file: " + e.getMessage());
            }
        }
    }
}