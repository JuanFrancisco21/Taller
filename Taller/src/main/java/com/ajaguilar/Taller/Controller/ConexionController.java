package com.ajaguilar.Taller.Controller;


import com.ajaguilar.Taller.Modelo.Connection;

import Utiles.XMLUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConexionController {
		
	@FXML
	private TextField ServerText;
	@FXML
	private TextField DatabaseText;
	@FXML
	private TextField UsuarioText;
	@FXML
	private TextField ContraseñaText;
	
	@FXML
	public Button closeButton;
	
	
	@FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
  
    
    @FXML
    private void initialize() {
    	ConfigCon();
    }
    
    @FXML
    private void save() {
    	Connection a=new Connection();
    	a.setServer(ServerText.getText());
    	a.setDatabase(DatabaseText.getText());
    	a.setUserName(UsuarioText.getText());
    	a.setPassword(ContraseñaText.getText());
    	XMLUtil.writeDataXML(a);
    	
    	handleCloseButtonAction();
    }
    
    private void ConfigCon() {
    	ServerText.setText(XMLUtil.loadDataXML().getServer());
    	DatabaseText.setText(XMLUtil.loadDataXML().getDatabase());
    	UsuarioText.setText(XMLUtil.loadDataXML().getUserName());
    	ContraseñaText.setText(XMLUtil.loadDataXML().getPassword());
    }
}
