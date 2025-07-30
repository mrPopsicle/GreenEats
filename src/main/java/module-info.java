module com.example.greeneats {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.greeneats to javafx.fxml;
    exports com.example.greeneats;
    exports com.example.greeneats.user;
    opens com.example.greeneats.user to javafx.fxml;
}