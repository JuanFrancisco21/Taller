package com.ajaguilar.Taller.Controller;

import java.io.IOException;

import com.ajaguilar.Taller.Modelo.Client;
import com.ajaguilar.Taller.Modelo.Reparacion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConexionController {
		
	@FXML
	private Label ServerLabel;
	@FXML
	private Label DatabaseLabel;
	@FXML
	private Label UsuarioLabel;
	@FXML
	private Label ContraseñaLabel;
	
	@FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        
    }
  
    
    @FXML
    private void initialize() {
    	ConfigCon();
    }
    
    private static void ConfigCon() {
    	
    }
}
