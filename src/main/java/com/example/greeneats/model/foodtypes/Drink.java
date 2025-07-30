package com.example.greeneats.model.foodtypes;

import com.example.greeneats.model.MenuItem;

public class Drink extends MenuItem {

    public Drink(double price, String name, String description, String image, String category, String calories) {
        super(price, name, description, image, category, calories);
    }


    @Override
    public String getCategory() {
        return "Drink";
    }


}
