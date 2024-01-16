package com.espol.controllers.auspiciantes;

import com.espol.App;
import com.espol.models.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;



public class TablaController {

    @FXML
    private Button asignarFeriaButton;
    @FXML
    private Button backButton;
    @FXML
    private Button registerButton;
    @FXML
    private TableView<Auspiciante> tabla;
    @FXML
    private TableColumn columnaCedula;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaTelefono;
    @FXML
    private TableColumn columnaEmail;

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

    @FXML
    public void handleRegisterButtonAction(ActionEvent event) {
        App.setScreen("auspiciantes/registrar", event);
    }

    @FXML
    public void handleAsignarFeriaButtonAction(ActionEvent event) {
        App.setScreen("auspiciantes/asignar", event);
    }

    @FXML
    public void initialize(){
        ArrayList<Auspiciante> auspiciantes = App.datos.getListaAuspiciantes();
        
        // Definir cómo se llenarán las columnas
        columnaCedula.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        // Convertir la lista de auspiciantes a una lista observable
        ObservableList<Auspiciante> listaObservable = FXCollections.observableArrayList(auspiciantes);

        // Añadir los datos a la tabla
        tabla.setItems(listaObservable);
    }

}
