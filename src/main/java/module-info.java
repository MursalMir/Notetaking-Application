module com.example.notetaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.notetaker.controller to javafx.fxml;
    exports com.example.notetaker;
}