module com.ajaguilar.Taller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;
    requires javafx.base;

    opens com.ajaguilar.Taller.Controller to javafx.fxml;
    opens com.ajaguilar.Taller.Modelo to java.xml.bind;
    opens Utiles to java.xml.bind;
    
    exports com.ajaguilar.Taller.Modelo;
    exports com.ajaguilar.Taller.Controller;
}