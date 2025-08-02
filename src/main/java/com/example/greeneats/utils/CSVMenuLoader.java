package com.example.greeneats.utils;

import com.example.greeneats.model.MenuItem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVMenuLoader {

    public static List<MenuItem> loadMenuFromCSV(String csvFilePath) {
        List<MenuItem> menuItems = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] values = line.split(",");

                if (values.length >= 4) {
                    try {
                        String name = values[0].trim();
                        double price = Double.parseDouble(values[1].trim());
                        String description = values[2].trim();
                        double calories = Double.parseDouble(values[3].trim());

                        menuItems.add(new MenuItem(name, price, description, calories));

                    } catch (NumberFormatException e) {
                        System.err.println("Invalid price in line: " + line);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }

        return menuItems;
    }
}