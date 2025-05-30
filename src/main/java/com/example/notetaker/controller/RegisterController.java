package com.example.notetaker.controller;

import com.example.notetaker.Util.AlertUtils;
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
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            AlertUtils.showAlert("Missing Fields", "Please enter both username and password.");
            return;
        }

        if (username.isBlank() || password.isBlank()) {
            showAlert("Registration Error", "Username and password cannot be blank.");
            return;
        }

        if (userService.register(username, password)) {
            AlertUtils.showAlert("Registration Successful", "Welcome! Redirecting to your dashboard.");
            goToDashboard();
        } else {
            AlertUtils.showAlert("Registration Failed", "Username already exists!");
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

    private void goToDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/notetaker/MainView.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
