package com.example.greeneats.controller;

import com.example.greeneats.model.CartItem;
import com.example.greeneats.model.MenuItem;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddToCartController {

    @FXML
    private Label itemNameLabel;

    @FXML
    private Label itemPriceLabel;

    @FXML
    private TextField quantityField;

    private MenuItem menuItem;

    public void setMenuItem(MenuItem item) {
        this.menuItem = item;
        itemNameLabel.setText(item.getName());
        itemPriceLabel.setText(String.format("₱%.2f", item.getPrice()));
    }

    @FXML
    private void onAddToCartClicked() {

        System.out.println("Item added!");
    }
    @FXML
    private void onCancelClicked() {
        Platform.exit();
    }
    @FXML
    private void onAddQuantityClicked() {

        System.out.println("To cart add quantity");
    }
}
