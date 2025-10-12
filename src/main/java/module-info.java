module org.example.hms {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hms to javafx.fxml;
    exports org.example.hms;
}