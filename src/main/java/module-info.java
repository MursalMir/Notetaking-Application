module com.example.notetaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Required for file parsing libraries (model/FileParser.java)
    requires org.apache.pdfbox;
    requires org.apache.poi.ooxml;

    opens com.example.notetaker.controller to javafx.fxml;
    exports com.example.notetaker;
}