package com.ajaguilar.Taller.Controller;

import java.io.IOException;
import java.time.LocalDate;

import com.ajaguilar.Taller.Modelo.Client;
import com.ajaguilar.Taller.Modelo.Reparacion;
import com.ajaguilar.Taller.Modelo.DAO.ClientDAO;
import com.ajaguilar.Taller.Modelo.DAO.ReparacionDAO;

import Utiles.Dialog;
import Utiles.GUI;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;


public class SecondaryController {

	@FXML
	public static Client cliente;
	@FXML
	public static ObservableList<Reparacion> Reparaciones;
	
	
	@FXML
	private TextField textPrecio;
	@FXML
	private TextField textMatricula;
	@FXML
	private TextField textDescripcion;
	@FXML
	private TextArea textoDescripcion;
	@FXML
	private DatePicker fecha =new DatePicker();
	
	@FXML
	private TableView<Reparacion> tablaReparaciones;
	@FXML
	private TableColumn<Reparacion,Double> precioColumna;
	@FXML
	private TableColumn<Reparacion,String> matriculaColumna;
	@FXML
	private TableColumn<Reparacion,String> descripcionColumna;
	@FXML
	private TableColumn<Reparacion,String> fechaColumna;

	
	@FXML
	private Button deleteReparacion;
	@FXML
	private Button addReparacion;
	
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        
    }
    /**
     * Método para pasar listas de la 1º vista a la actual.
     * @param clientObj (cliente).
     * @param reparacionList (lista de reparaciones del cliente).
     */
    public static void initList(Client clientObj, ObservableList<Reparacion> reparacionList	) {
    	Reparaciones=FXCollections.observableArrayList();
    	cliente=new Client();

    	cliente=clientObj;
    	Reparaciones.setAll(reparacionList);
    }
    
    @FXML
    private void initialize() {

    	ConfidRepa();
    }
    
    /**
     * Método para añadir una reparacion a la BBDD y a la ObservableList.
     */
    @FXML
    private void addRepa() {
    
    	try {
    		LocalDate h=fecha.getValue();
    		double p=Double.parseDouble(textPrecio.getText());
    		String m=textMatricula.getText();
    		String d=textDescripcion.getText();
    		String f =h.getYear()+"-"+h.getMonthValue()+"-"+h.getDayOfMonth();
    		Client c=cliente;
    		
    		if (GUI.validaMatricula(textMatricula.getText()) && GUI.isDecimal(textPrecio.getText())) {
    			
    			Reparacion x =new Reparacion(2,p,m,d,f,c);
        		ReparacionDAO a=new ReparacionDAO(x);
            	
            	a.guardar();
            	Reparaciones.add(a);
			}else if (!GUI.isDecimal(textPrecio.getText())) {
				Dialog.showWarning("Precio", "Precio no válido", "Debe ser un numero (con decimales)");
			}else if (!GUI.validaMatricula(textMatricula.getText())) {
				Dialog.showWarning("Matricula", "Matricula no válida", "Formato= (1234 YYY)/(1234-YYY)/(1234 YY)");
			}else if (!GUI.validarFecha(f)) {
				Dialog.showWarning("Fecha", "Fecha no válida", "Formato= (YYYY-MM-DD)");
			}
    		

		} catch (Exception e) {
			System.out.println("Error al crear reparacion");
		}
    	
    }
    /**
     * Método para borrar una reparacion de la BBDD y de la ObservableList.
     */
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
    
    /**
     * Método para configurar datos de la tabla.
     */
    private void ConfidRepa() {    
        
        precioColumna.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getPrecio());
        });
        precioColumna.setCellFactory(TextFieldTableCell.<Reparacion, Double>forTableColumn(new DoubleStringConverter()));
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
        
        tablaReparaciones.setEditable(true);
        tablaReparaciones.setItems(Reparaciones);
        tablaReparaciones.getSelectionModel()
		.selectedItemProperty()
		.addListener((observable,oldValue,newValue)->{
			muestraInfo(newValue);
		});
        
    }
    
    /**
     * Muestra la descripcion de la reparacion seleccionada.
     * @param p, reparacion a mostrar.
     */
    private void muestraInfo(Reparacion p) {
		if(p!=null) {
			textoDescripcion.setText(p.getDescripcion());
			textoDescripcion.setWrapText(true);
			
		}else {
			textoDescripcion.setText("No selecionado");
		}
	}
    
    
    
    
   }