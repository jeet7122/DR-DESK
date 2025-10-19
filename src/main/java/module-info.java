module org.example.hms {
    requires javafx.controls;
    requires java.sql;
    requires org.example.hms;

    exports org.example.hms;
    exports org.example.hms.dto;
}