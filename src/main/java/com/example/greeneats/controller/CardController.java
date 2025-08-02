package com.example.greeneats.controller;

import com.example.greeneats.model.MenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Button addToCartButton;

    public void setData(MenuItem item) {
        nameLabel.setText(item.getName());
        descLabel.setText(item.getDescription());
        priceLabel.setText(item.getFormattedPrice());
        //imageView.setImage(new Image(item.getImage()));
    }
}
