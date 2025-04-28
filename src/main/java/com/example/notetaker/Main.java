package com.example.notetaker;
import com.example.notetaker.service.OllamaConnection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.http.HttpClient;

/**
 * Modified from CAB302 Address Book application https://github.com/cab302-qut/address-book.git
 */
public class Main extends Application {

    public static final String TITLE = "Note Taker Dashboard";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(loader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

