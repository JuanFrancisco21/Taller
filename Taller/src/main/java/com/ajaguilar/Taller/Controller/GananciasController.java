package com.ajaguilar.Taller.Controller;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class GananciasController {
	@FXML
    private BarChart<String, Integer> graficoBarras;
    @FXML
    private CategoryAxis ejeX;
    @FXML
    private NumberAxis ejeY;
    
    private ObservableList<String> nombreMeses = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        String[] meses = {"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
        nombreMeses.addAll(Arrays.asList(meses));
        
        ejeX.setCategories(nombreMeses);
        //Etiquetas de los ejes
        ejeX.setLabel("Mes de nacimiento");
        ejeY.setLabel("Número de personas");
    }
}
