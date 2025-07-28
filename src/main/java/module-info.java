module com.example.greeneats {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.greeneats to javafx.fxml;
    exports com.example.greeneats;
}