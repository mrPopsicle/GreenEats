package com.example.greeneats.controller;

import com.example.greeneats.model.CartItem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CheckoutController {

    @FXML
    private TableColumn itemColumn;
    @FXML
    private TableColumn quantityColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TableColumn caloriesColumn;
    @FXML
    private TableView<CartItem> checkoutTable;
}
