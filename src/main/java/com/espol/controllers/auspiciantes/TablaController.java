package com.espol.controllers.auspiciantes;

import com.espol.App;
import com.espol.models.*;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;

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
    private TableColumn<Auspiciante, String> columnaCedula;
    @FXML
    private TableColumn<Auspiciante, String> columnaNombre;
    @FXML
    private TableColumn<Auspiciante, String> columnaTelefono;
    @FXML
    private TableColumn<Auspiciante, String> columnaEmail;
    @FXML
    private TableColumn<Auspiciante, String> columnaOpciones;

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
    public void initialize() {
        ArrayList<Auspiciante> auspiciantes = App.datos.getAuspiciantes();

        // Definir cómo se llenarán las columnas
        columnaCedula.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        columnaOpciones
                .setCellFactory(new Callback<TableColumn<Auspiciante, String>, TableCell<Auspiciante, String>>() {
                    @Override
                    public TableCell<Auspiciante, String> call(TableColumn<Auspiciante, String> param) {
                        return new TableCell<Auspiciante, String>() {
                            final HBox container = new HBox();
                            final Button btn = new Button("Editar");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    container.setAlignment(Pos.CENTER);
                                    btn.setTextAlignment(TextAlignment.CENTER);
                                    btn.setOnAction((ActionEvent event) -> {
                                        Auspiciante auspiciante = getTableView().getItems().get(getIndex());
                                        FXMLLoader loader = App.getLoader("auspiciantes/editar");
                                        Parent root = null;
                                        try {
                                            root = loader.load();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                        EditarController editarController = loader.getController();
                                        editarController.cargarAuspiciante(auspiciante.getRuc());
                                        App.setScreen(root, event);
                                    });
                                    container.getChildren().add(btn);
                                    setGraphic(container);
                                }
                            }
                        };
                    }
                });

        // Convertir la lista de auspiciantes a una lista observable
        ObservableList<Auspiciante> listaObservable = FXCollections.observableArrayList(auspiciantes);

        // Añadir los datos a la tabla
        tabla.setItems(listaObservable);
    }

}
