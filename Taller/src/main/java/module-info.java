module com.ajaguilar.Taller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;
    requires javafx.base;

    opens com.ajaguilar.Taller to javafx.fxml;
    exports com.ajaguilar.Taller;
}