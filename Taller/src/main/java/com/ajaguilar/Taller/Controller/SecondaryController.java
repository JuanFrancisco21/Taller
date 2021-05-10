package com.ajaguilar.Taller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import com.ajaguilar.modelo.Client;
import com.ajaguilar.modelo.Reparacion;
import com.ajaguilar.modelo.DAO.ClientDAO;
import com.ajaguilar.modelo.DAO.ReparacionDAO;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LocalDateStringConverter;

public class SecondaryController {

	@FXML
	public ObservableList<Reparacion> Reparaciones;
	
	@FXML
	private TextField textPrecio;
	@FXML
	private TextField textMatricula;
	@FXML
	private TextField textDescripcion;
	@FXML
	private TextField textoDescripcion;
	@FXML
	private DatePicker fecha;
	
	@FXML
	private TableView<Reparacion> tablaReparaciones;
	@FXML
	private TableColumn<Reparacion, Integer> idColumna;
	@FXML
	private TableColumn<Reparacion,Double> precioColumna;
	@FXML
	private TableColumn<Reparacion,String> matriculaColumna;
	@FXML
	private TableColumn<Reparacion,String> descripcionColumna;
	@FXML
	private TableColumn<Reparacion,String> fechaColumna;
	@FXML
	private TableColumn<Reparacion,Integer> pagadoColumna;
	
	@FXML
	private Button deleteReparacion;
	@FXML
	private Button addReparacion;
	
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void initialize() {
    	ConfidRepa();
    }
    
    @FXML
    private void addRepa() {
    
    	try {
    		LocalDate h=fecha.getValue();
    		double p=Double.parseDouble(textPrecio.getText());
    		String m=textMatricula.getText();
    		String d=textDescripcion.getText();
    		String f =h.getYear()+"-"+h.getMonthValue()+"-"+h.getDayOfMonth();
    		Client c=ClientDAO.buscaPorDni("31319229p");
    		Reparacion x =new Reparacion(2,p,m,d,f,c);
    		
    		ReparacionDAO a=new ReparacionDAO(x);
    		System.out.println(c.getDni());
    		System.out.println(a);
    		
        	
        	a.guardar();
        	Reparaciones.add(a);
		} catch (Exception e) {
			System.out.println("Error al crear reparacion");
		}
    	
    }
    @FXML
	private void deleteRepa() {
	    Reparacion selected = tablaReparaciones.getSelectionModel().getSelectedItem();
	    if (selected != null) {
	    	try {
	    		deleteReparacion.setDisable(false);
			    Reparaciones.remove(selected);
			    ReparacionDAO a=new ReparacionDAO(selected);
			    a.eliminar();
			    
			    
			} catch (Exception e) {
	    		deleteReparacion.setDisable(true);
				System.out.println("Error al borrar reparacion");			
			}
	    
	    }
	}
    private void ConfidRepa() {
    	this.Reparaciones=FXCollections.observableArrayList();
		this.Reparaciones.setAll(ReparacionDAO.getTodasRepas());
		
		
		idColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getId());
        });
        
        precioColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getPrecio());
        });
        //precioColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        precioColumna.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Reparacion, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reparacion, Double> t) {

            	Reparacion selected = (Reparacion) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                selected.setPrecio(t.getNewValue());
                ReparacionDAO cc = new ReparacionDAO(selected);
                cc.guardar();
            }
        });
        matriculaColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getMatricula());
        });
        matriculaColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        matriculaColumna.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Reparacion, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reparacion, String> t) {

            	Reparacion selected = (Reparacion) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                selected.setMatricula(t.getNewValue());
                ReparacionDAO cc = new ReparacionDAO(selected);
                cc.guardar();
            }
        });
        descripcionColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getDescripcion());
        });
        descripcionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        descripcionColumna.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Reparacion, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reparacion, String> t) {

            	Reparacion selected = (Reparacion) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                selected.setMatricula(t.getNewValue());
                ReparacionDAO cc = new ReparacionDAO(selected);
                cc.guardar();
            }
        });
       fechaColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getFecha());
        });
        //fechaColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        /*fechaColumna.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Reparacion, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reparacion, String> t) {

            	Reparacion selected = (Reparacion) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                selected.setFecha(t.getNewValue());
                ReparacionDAO cc = new ReparacionDAO(selected);
                cc.guardar();
            }
        });*/
        tablaReparaciones.setEditable(true);
        tablaReparaciones.setItems(Reparaciones);
        tablaReparaciones.getSelectionModel()
		.selectedItemProperty()
		.addListener((observable,oldValue,newValue)->{
			muestraInfo(newValue);
		});
    }
    private void muestraInfo(Reparacion p) {
		if(p!=null) {
			textoDescripcion.setText(p.getDescripcion());
			
		}else {
			textoDescripcion.setText("No selecionado");
		}
	}
    
    
    
    
   }