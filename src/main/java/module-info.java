module com.example.notetaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.sql;
    requires com.google.gson;
    opens com.example.notetaker to javafx.fxml;
    opens com.example.notetaker.controller to javafx.fxml;
    exports com.example.notetaker;
    exports com.example.notetaker.controller;
    exports com.example.notetaker.service;
    exports com.example.notetaker.model;

}
