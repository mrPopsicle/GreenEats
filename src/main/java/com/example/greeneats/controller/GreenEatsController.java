package com.example.greeneats.controller;

import com.example.greeneats.GreenEatsApp;
import com.example.greeneats.model.MenuItem;
import com.example.greeneats.user.UserSession;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.greeneats.user.LoginController.showAlert;

public class GreenEatsController implements Initializable {
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

    //SignIn Page
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;


    //SignUpPage
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;


    private BorderPane sidebarPane;  // keep reference
    // For tracking active button
    private Button activeButton;

    @FXML
    private VBox menuContainer;

    @FXML
    private GridPane grid;
    //for testing
    private final List<MenuItem> menuItems = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {


//
//        if (pieChart != null) {
//            ObservableList<PieChart.Data> pieChartData =
//                    FXCollections.observableArrayList(
//                            new PieChart.Data("Chicken", 14),
//                            new PieChart.Data("Pizza", 14),
//                            new PieChart.Data("Beef", 20),
//                            new PieChart.Data("Salads", 50)
//                    );
//            pieChart.setData(pieChartData);
//            pieChart.setTitle("Food Distribution");


    }

    public void postInitialize() {
        loadTestMenuItems();
        try {
            populateGrid();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTestMenuItems(){
        menuItems.add(new MenuItem("Chicken", 150.0, "Crispy fried chicken", 500, "Best Sellers", "/image/bowld/2pc_chicken.jpg"));
        menuItems.add(new MenuItem("Salad", 120.0, "Fresh veggie salad", 200, "Healthy Options", "/image/lettuce.jpg"));
    }
    private void populateGrid() throws IOException {
        int column = 0;
        int row = 0;

        for (MenuItem item : menuItems) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greeneats/MenuItemCard.fxml"));
            StackPane card = loader.load();

            CardController controller = loader.getController();
            controller.setData(item);

            grid.add(card, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    @FXML
    protected void onLogOutClick(ActionEvent  event) throws IOException {
        UserSession.getInstance().isLoggedIn();
        UserSession.getInstance().getCurrentUsername();

        UserSession.getInstance().logout();

        UserSession.getInstance().isLoggedIn();

        try {
            Parent loginScreen = FXMLLoader.load(getClass().getResource("/com/example/greeneats/LogInPage.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(loginScreen);

            System.out.println("Successfully navigated back to login screen.");
        } catch (IOException e) {
            showAlert("Error loading login screen: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void setContent(Parent pane) {
        contentPane.setContent(pane);
    }



    @FXML
    protected void onExitClicked(ActionEvent event) {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greeneats/Menu.fxml"));
        Parent pane = loader.load();

        GreenEatsController controller = loader.getController();
        controller.postInitialize();

        Button sourceButton = (Button) event.getSource();
        ScrollPane scrollPane = (ScrollPane) sourceButton.getScene().lookup("#contentPane");
        if (scrollPane != null) {
            scrollPane.setContent(pane);
        }
    }


    @FXML
    protected void addToCartClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GreenEatsApp.class.getResource("AddToCart.fxml"));
        Parent root = fxmlLoader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Add to Cart");
        popupStage.setScene(new Scene(root));
        popupStage.setResizable(false);
        popupStage.showAndWait();

    }

    @FXML
    protected void onCartClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GreenEatsApp.class.getResource("CheckOutPage.fxml"));
        Parent root = fxmlLoader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Check out");
        popupStage.setScene(new Scene(root));
        popupStage.setResizable(false);
        popupStage.showAndWait();
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