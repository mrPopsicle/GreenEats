package com.example.greeneats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.greeneats.config.AppConfig.CSS_PATH;

public class GreenEatsApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GreenEatsApp.class.getResource("LogInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(GreenEatsApp.class.getResource(CSS_PATH).toExternalForm());
        stage.setTitle("GreenEats");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}