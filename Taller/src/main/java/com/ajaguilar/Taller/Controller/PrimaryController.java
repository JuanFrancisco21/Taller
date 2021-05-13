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

import Utiles.Dialog;
import Utiles.GUI;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	private Button changeview;
	@FXML
	private Button Search;
	@FXML
	private Button SearchAll;
	@FXML
	private Button Searchid;
	
	@FXML
	protected void initialize() {
		try {
			configuraTablas();
			
		} catch (Exception e) {
		System.out.println("Error al configurar tablas");
		}
		
		
		
		
	}
	/**
	 * Método para cambiar de vista hacia las reparaciones del cliente selecionado.
	 * @throws IOException
	 */
	@FXML
    private void switchToSecondary() throws IOException {
	    Client selected = tablaClientes.getSelectionModel().getSelectedItem();
        SecondaryController.initList(selected, Reparaciones);

        App.setRoot("secondary");
    }
	
	/**
	 * Habre una ventana emergente, donde cambiar datos de la conexion.
	 * @throws IOException 
	 */
	@FXML
    private void switchToConexion() throws IOException {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(App.class.getResource("conexion.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Conexion");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	       // dialogStage.initOwner(PrimaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	
    }
	
	/**
	 * Habre una ventana hacia la grafica de ganacias del año.
	 * @throws IOException execpcion .
	 */
	@FXML
    private void switchToGanancias() throws IOException {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(App.class.getResource("ganancias.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Ganancias");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        GananciasController controller = loader.getController();
	        controller.setDatosDinero(ReparacionDAO.getTodasRepas());

	        dialogStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	
    }
	
	/**
	 * Método para añadir a un cliente en la base de datos, como tambien en la ObservableList.
	 */
	@FXML
	private void addPersona() {
		
		Client a=new Client();
		ClientDAO b=new ClientDAO(a);
		
		b.guardar();
		clientes.add(a);
	}
	
	/**
	 * Método para borrar a un cliente de la base de datos como de la ObservableList.
	 */
	@FXML
	private void deletePersona() {
	    Client selected = tablaClientes.getSelectionModel().getSelectedItem();
	    if (selected != null) {
	    	try {
	    		deletePerson.setDisable(false);
			    ClientDAO a=new ClientDAO(selected);
			    a.eliminar();
			    clientes.remove(selected);
			    
			} catch (Exception e) {
	    		deletePerson.setDisable(true);
				System.out.println("Error al borrar persona");			
			}
	    
	    }
	}
	
	/**
	 * Traer todos los clientes de la base de datos a la tabla.
	 */
	@FXML
	private void searchAll() {
        clientes.clear();
        List<Client> newC;
            newC=ClientDAO.TodosClient();
        clientes.addAll(newC);
	}
	/**
	 * Metodo para buscar un cliente mediante un trozo de dni.
	 */
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
	
	/**
	 * Metodo para configurar las columnas de las dos tablas.
	 */
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
	            	
	            	if (GUI.validaDNI(t.getNewValue())==true) {
		                selected.setDni(t.getNewValue());

					} else {
						Dialog.showWarning("Dni", "Dni no valido", "Formato= (12345678X)");
						configuraTablas();
					}
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
		this.Reparaciones.addAll(ReparacionDAO.getTodasRepas());
		
        
        precioColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getPrecio());
        });
        matriculaColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getMatricula());
        });
        
        
        tablaReparaciones.setItems(Reparaciones);
        showRepara(null);
		
	}
	/**
	 * Muestra la informacion de reparaciones de un cliente
	 * @param c cliente selecionado
	 */
	private void showRepara(Client c) {
		Reparaciones.clear();
		if (c != null) {
			List<Reparacion> r=ReparacionDAO.getReparacionByClient(c.getDni());
			Reparaciones.addAll(r);
			deletePerson.setDisable(false);
			changeview.setDisable(false);

		} else {
			changeview.setDisable(true);
			deletePerson.setDisable(true);
		}

		
	}
}
