package com.example.greeneats;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private ScrollPane contentPane;
    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox vBox;


    private BorderPane sidebarPane;  // keep reference
    // For tracking active button
    private Button activeButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        // Initialize pie chart only if it exists
        if (pieChart != null) {
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Chicken", 14),
                            new PieChart.Data("Pizza", 14),
                            new PieChart.Data("Beef", 20),
                            new PieChart.Data("Salads", 50)
                    );
            pieChart.setData(pieChartData);
            pieChart.setTitle("Food Distribution");
        }



    }

    private void loadModernStyles() {
        try {
            String css = getClass().getResource("/com/example/greeneats/styles.css").toExternalForm();

            if (stackPane != null) {
                stackPane.getStylesheets().add(css);
                stackPane.getStyleClass().add("main-container");
            }

            if (borderPane != null) {
                borderPane.getStylesheets().add(css);
                borderPane.getStyleClass().add("main-container");
            }
        } catch (Exception e) {
            System.out.println("Could not load modern styles: " + e.getMessage());
        }
    }

    @FXML
    protected void onSignInClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greeneats/SideBar.fxml"));
            BorderPane sidebar = loader.load();


            HelloController controller = loader.getController();

            Parent content = FXMLLoader.load(getClass().getResource("/com/example/greeneats/HomePage.fxml"));
            controller.contentPane.setContent(content);


            String css = getClass().getResource("/styles.css").toExternalForm();
            sidebar.getStylesheets().add(css);

            stackPane.getChildren().clear();
            stackPane.getChildren().add(sidebar);

            sidebarPane = sidebar;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void onSignUpClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greeneats/CreateAnAccount.fxml"));
            Parent sidebarPane = loader.load();


            stackPane.getChildren().clear();
            stackPane.getChildren().add(sidebarPane);
        } catch (IOException e) {
            System.err.println("Error loading SideBar.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void onExitClicked(ActionEvent event) {
        // Add smooth exit animation if desired
        Platform.exit();
    }

    @FXML
    protected void onHomeClicked(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/com/example/greeneats/HomePage.fxml"));
        contentPane.setContent(pane);

    }

    @FXML
    protected void onFindClicked(ActionEvent event) {

        setActiveButton((Button) event.getSource());
    }

    @FXML
    protected void onCuisinesClicked(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/com/example/greeneats/Resto.fxml"));
        contentPane.setContent(pane);
    }
    @FXML
    protected void onRestoClicked(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/com/example/greeneats/Menu.fxml"));
        Button sourceButton = (Button) event.getSource();
        ScrollPane scrollPane = (ScrollPane) sourceButton.getScene().lookup("#contentPane");
        if (scrollPane != null) {
            scrollPane.setContent(pane);
        }
    }

    @FXML
    protected void onNutritionTrackerClicked(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/com/example/greeneats/DashBoard.fxml"));
        contentPane.setContent(pane);


    }

    @FXML
    protected void onSettingsClicked(ActionEvent event) {

        setActiveButton((Button) event.getSource());
    }

    private void setActiveButton(Button clickedButton) {

        if (activeButton != null) {
            activeButton.getStyleClass().removeAll("sidebar-button-active");
            activeButton.getStyleClass().add("sidebar-button");
        }

        // Set new active button
        activeButton = clickedButton;
        if (activeButton != null) {
            activeButton.getStyleClass().removeAll("sidebar-button");
            activeButton.getStyleClass().add("sidebar-button-active");
        }
    }

}