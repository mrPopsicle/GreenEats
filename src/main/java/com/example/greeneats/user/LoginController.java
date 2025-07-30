package com.example.greeneats.user;

import com.example.greeneats.HelloController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginController {
    private final UserService userService = new CsvUserService();

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private StackPane stackPane;

    // Sign Up fields
    @FXML private TextField signUpUsernameField;
    @FXML private PasswordField signUpPasswordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;

    @FXML
    protected void onSignInClicked(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Username and password must not be empty.");
            return;
        }

        if (!userService.validateUser(username, password)) {
            showAlert("Invalid credentials.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/greeneats/SideBar.fxml"));
            BorderPane sidebar = loader.load();
            Parent content = FXMLLoader.load(getClass().getResource("/com/example/greeneats/HomePage.fxml"));

            HelloController controller = loader.getController();
            controller.setContent(content);

            stackPane.getChildren().clear();
            stackPane.getChildren().add(sidebar);

        } catch (IOException e) {
            showAlert("Error loading home screen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSignUpConfirmClicked(ActionEvent event) {
        String username = signUpUsernameField.getText().trim();
        String password = signUpPasswordField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        if (password.length() < 6) {
            showAlert("Password must be at least 6 characters long.");
            return;
        }

        User newUser = new User(username, password, firstName, lastName);
        if (userService.addUser(newUser)) {
            showAlert("Account created successfully! Please sign in.");
            switchToSignIn();
        } else {
            showAlert("Username already exists. Please choose a different username.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void switchToSignUp() {
        try {
            Parent signUp = FXMLLoader.load(getClass().getResource("/com/example/greeneats/CreateAnAccount.fxml"));
            stackPane.getChildren().setAll(signUp);
        } catch (IOException e) {
            showAlert("Error loading SignUp page.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void switchToSignIn() {
        try {
            userService.loadUsers();
            Parent signIn = FXMLLoader.load(getClass().getResource("/com/example/greeneats/LogInPage.fxml"));
            stackPane.getChildren().setAll(signIn);
        } catch (IOException e) {
            showAlert("Error loading SignIn page.");
            e.printStackTrace();
        }
    }
}