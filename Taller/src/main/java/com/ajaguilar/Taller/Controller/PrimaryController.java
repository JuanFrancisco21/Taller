package com.ajaguilar.Taller.Controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ajaguilar.Taller.Modelo.Client;
import com.ajaguilar.Taller.Modelo.Reparacion;
import com.ajaguilar.Taller.Modelo.DAO.ClientDAO;
import com.ajaguilar.Taller.Modelo.DAO.ReparacionDAO;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

public class PrimaryController {
	@FXML
	public ObservableList<Client> clientes;
	@FXML
	public ObservableList<Reparacion> Reparaciones;
	
	
	@FXML
	private TextField DniCliente;
	@FXML
	private TextField IDRepa;
	
	@FXML
	private TableView<Client> tablaClientes;
	@FXML
	private TableColumn<Client, String> dniColumna;
	@FXML
	private TableColumn<Client,String> nombreColumna;
	@FXML
	private TableColumn<Client,String> direccionColumna;
	
	@FXML
	private TableView<Reparacion> tablaReparaciones;
	@FXML
	private TableColumn<Reparacion, Integer> idColumna;
	@FXML
	private TableColumn<Reparacion,Double> precioColumna;
	@FXML
	private TableColumn<Reparacion,String> matriculaColumna;
	
	@FXML
	private Button deletePerson;
	@FXML
	private Button Search;
	@FXML
	private Button SearchAll;
	@FXML
	private Button Searchid;
	
	@FXML
	protected void initialize() {
		System.out.println("Cargando...");
		configuraTablas();
		
		
		
	}
	@FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
	@FXML
    private void save() {
       // Utiles.saveFile(PersonaDAO.mockList);
    }
	@FXML
	private void addPersona() {
		
		Client a=new Client();
		ClientDAO b=new ClientDAO(a);
		
		b.guardar();
		clientes.add(a);
	}
	
	@FXML
	private void deletePersona() {
	    Client selected = tablaClientes.getSelectionModel().getSelectedItem();
	    if (selected != null) {
	    	try {
	    		deletePerson.setDisable(false);
			    clientes.remove(selected);
			    ClientDAO a=new ClientDAO(selected);
			    a.eliminar();
			    
			} catch (Exception e) {
	    		deletePerson.setDisable(true);
				System.out.println("Error al borrar persona");			
			}
	    
	    }
	}
	
	
	@FXML
	private void searchAll() {
        clientes.clear();
        List<Client> newC;
            newC=ClientDAO.TodosClient();
        clientes.addAll(newC);
	}
	@FXML
	private void search() {
		String pattern=DniCliente.getText();
        pattern=pattern.trim();
        clientes.clear();
        
        
        Client newC=new Client();
        if(!pattern.equals("")){
        	newC=ClientDAO.buscaPorDni(pattern);
            List<Reparacion> lc=ReparacionDAO.getReparacionByClient( pattern);
            Reparaciones.clear();
            Reparaciones.addAll(lc);
        }else {
        	
        }
        clientes.addAll(newC);
        

	}
	@FXML
	private void searchRepa() {
		String pattern=IDRepa.getText();
		int i=Integer.parseInt(pattern);  

        pattern=pattern.trim();
        clientes.clear();
        
        List<Client> newC=new ArrayList<>();
        if(i>0){
            List<Reparacion> lc=new ArrayList<Reparacion>();
            lc.addAll(ReparacionDAO.getReparacionById(i));
            Reparaciones.clear();
            Reparaciones.addAll(lc);
        	lc.forEach(item->newC.add(item.getMiclient()));
        }else {
        	
        }
        clientes.addAll(newC);
        

	}
	private void configuraTablas() {
		this.clientes=FXCollections.observableArrayList();
		this.clientes.setAll(ClientDAO.TodosClient());
		
		dniColumna.setCellValueFactory(cellData->{
			return new SimpleObjectProperty<>(cellData.getValue().getDni());
		});
		dniColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        dniColumna.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Client, String> t) {

            	Client selected = (Client) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                selected.setDni(t.getNewValue());
                ClientDAO cc = new ClientDAO(selected);
                cc.guardar();
            }
        }
        );
		nombreColumna.setCellValueFactory(cellData->{
			return new SimpleObjectProperty<>(cellData.getValue().getNombre());
		});
		nombreColumna.setCellFactory(TextFieldTableCell.forTableColumn());
		nombreColumna.setOnEditCommit(
	                new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
	            @Override
	            public void handle(TableColumn.CellEditEvent<Client, String> t) {
	
	            	Client selected = (Client) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow());
	                selected.setNombre(t.getNewValue());
	                ClientDAO cc = new ClientDAO(selected);
	                cc.guardar();
	            }
	        }
	        );
		direccionColumna.setCellValueFactory(cellData->{
			return new SimpleObjectProperty<>(cellData.getValue().getDireccion());
		});
		direccionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
		direccionColumna.setOnEditCommit(
	                new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
	            @Override
	            public void handle(TableColumn.CellEditEvent<Client, String> t) {

	            	Client selected = (Client) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow());
	                selected.setDireccion(t.getNewValue());
	                ClientDAO cc = new ClientDAO(selected);
	                cc.guardar();
	            }
	        }
	        );
		
		tablaClientes.setEditable(true);
		tablaClientes.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldvalue, newvalue)-> showRepara(newvalue));
		tablaClientes.setItems(clientes);
		
		//configuraTablareparaciones
		
		this.Reparaciones=FXCollections.observableArrayList();
		this.Reparaciones.setAll(ReparacionDAO.getTodasRepas());
		
		idColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getId());
        });
        
        precioColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getPrecio());
        });
        matriculaColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getMatricula());
        });
        
		
        tablaReparaciones.setItems(Reparaciones);
        showRepara(null);
		
	}
	private void showRepara(Client c) {
		Reparaciones.clear();
		if (c != null) {
			List<Reparacion> r=ReparacionDAO.getReparacionByClient(c.getDni());
			Reparaciones.addAll(r);
			deletePerson.setDisable(false);


		} else {
			deletePerson.setDisable(true);
		}

		
	}
}