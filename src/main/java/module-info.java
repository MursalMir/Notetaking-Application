module com.example.steve {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.steve to javafx.fxml;
    exports com.example.steve;
}