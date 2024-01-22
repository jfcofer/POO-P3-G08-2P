package com.espol.controllers.emprendedores;

import com.espol.App;
import com.espol.models.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TablaController {

    @FXML
    private Button backButton;

    @FXML
    private Button registerButton;
    
    @FXML
    private TableView<Emprendedor> tabla;
    @FXML
    private TableColumn<Emprendedor, String> columnaCedula;
    @FXML
    private TableColumn<Emprendedor,String> columnaNombre;
    @FXML
    private TableColumn<Emprendedor,String> columnaTelefono;
    @FXML
    private TableColumn<Emprendedor,String> columnaEmail;
    @FXML
    private TableColumn<Emprendedor,String> columnaOpciones;

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

    @FXML
    public void handleRegisterButtonAction(ActionEvent event) {
        App.setScreen("emprendedores/registrar", event);
    }
    
    @FXML
    public void initialize(){
        ArrayList<Emprendedor> emprendedores = App.datos.getEmprendedores();
        
        // Definir cómo se llenarán las columnas
        columnaCedula.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        columnaOpciones
                .setCellFactory(new Callback<TableColumn<Emprendedor, String>, TableCell<Emprendedor, String>>() {
                    @Override
                    public TableCell<Emprendedor, String> call(TableColumn<Emprendedor, String> param) {
                        return new TableCell<Emprendedor, String>() {
                            final Button btn = new Button("Editar");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    btn.setOnAction((ActionEvent event) -> {
                                        Emprendedor emprendedor = getTableView().getItems().get(getIndex());
                                        FXMLLoader loader = App.getLoader("emprendedores/editar");
                                        Parent root = null;
                                        try {
                                            root = loader.load();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                        EditarController editarController = loader.getController();
                                        editarController.cargarEmprendedor(emprendedor.getRuc());
                                        App.setScreen(root, event);
                                    });
                                    setGraphic(btn);
                                }
                            }
                        };
                    }
                });


        // Convertir la lista de auspiciantes a una lista observable
        ObservableList<Emprendedor> listaObservable = FXCollections.observableArrayList(emprendedores);

        // Añadir los datos a la tabla
        tabla.setItems(listaObservable);
    }

}
