module org.example.hms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.hms to javafx.fxml;
    exports org.example.hms;
}