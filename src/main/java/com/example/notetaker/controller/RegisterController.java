package com.example.notetaker.controller;

import com.example.notetaker.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserService userService = UserService.getInstance();

    @FXML
    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userService.register(username, password)) {
            showAlert("Registration Successful", "Returning to login");
            goToLogin();
        } else {
            showAlert("Registration Failed", "Username already exists!");
        }
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        goToLogin();
    }

    private void goToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/notetaker/Login.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
