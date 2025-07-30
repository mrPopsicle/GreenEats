module com.example.greeneats {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.greeneats to javafx.fxml;
    exports com.example.greeneats;
    exports com.example.greeneats.user;
    opens com.example.greeneats.user to javafx.fxml;
    exports com.example.greeneats.model;
    opens com.example.greeneats.model to javafx.fxml;
    exports com.example.greeneats.view;
    opens com.example.greeneats.view to javafx.fxml;
    exports com.example.greeneats.controller;
    opens com.example.greeneats.controller to javafx.fxml;
    exports com.example.greeneats.utils;
    opens com.example.greeneats.utils to javafx.fxml;
}