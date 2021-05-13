package com.ajaguilar.Taller.Controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.ajaguilar.Taller.Modelo.Reparacion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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
        ejeX.setLabel("Mes del año");
        ejeY.setLabel("Dinero");
    }
    /**
     * Metodo para setear datos de las reparaciones en la gráfica.
     * @param reparacion, lista de reparaciones
     */
    public void setDatosDinero(List<Reparacion> reparacion) {
        
        int[] numMes = new int[12];
        for (Reparacion p : reparacion) {
        	String[] parts = p.getFecha().split("-");
        	if (Integer.parseInt(parts[0])==LocalDate.now().getYear()) {
        		int mes = Integer.parseInt(parts[1]) ;
                //numMes[mes]++; Cuenta el numero de reparaciones por mes.
                numMes[mes]=(int) (numMes[mes]+p.getPrecio()); //Cuenta dinero ganado por mes.
			}
        	
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Ganancias del mes");
        for (int i = 0; i < numMes.length; i++) {
            series.getData().add(new XYChart.Data<>(nombreMeses.get(i), numMes[i]));
        }

        graficoBarras.getData().add(series);
        
    }
}
