package com.espol.controllers.ferias;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.espol.App;
import com.espol.models.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class TablaController {

    @FXML
    private Button nuevaFeriaButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Feria> tabla;

    @FXML
    private TableColumn<Feria, String> codigoColumn;

    @FXML
    private TableColumn<Feria, String> nombreColumn;

    @FXML
    private TableColumn<Feria, LocalDate> fechaInicioColumn;

    @FXML
    private TableColumn<Feria, String> lugarColumn;

    @FXML
    private TableColumn<Feria, Integer> auspiciantesColumn;

    @FXML
    private TableColumn<Feria, Integer> emprendedoresColumn;

    @FXML
    private TableColumn<Feria, String> opcionesColumn;

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

    @FXML
    public void handleNuevaFeriaButtonAction(ActionEvent event) {
        App.setScreen("ferias/registrar", event);
    }

    @FXML
    public void initialize() {
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        lugarColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        auspiciantesColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getAuspiciantes().size()).asObject());
        emprendedoresColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getEmprendedores().size()).asObject());
        opcionesColumn.setCellFactory(tableCol -> new TableCell<Feria, String>() {
            Button detallesButton = new Button("Detalles");
            Button emprendedoresButton = new Button("Emprendedores");
            Button auspiciantesButton = new Button("Auspiciantes");
            Button standsButton = new Button("Stands");
            Button editarButton = new Button("Editar");
            HBox botones = new HBox();

            @Override
            protected void updateItem(String string, boolean empty) {
                super.updateItem(string, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {

                    botones.setAlignment(Pos.CENTER);
                    detallesButton.setFont(Font.font(8));
                    auspiciantesButton.setFont(Font.font(8));
                    emprendedoresButton.setFont(Font.font(8));
                    standsButton.setFont(Font.font(8));
                    editarButton.setFont(Font.font(8));
                    detallesButton.setOnAction(event -> {
                        FXMLLoader loader = App.getLoader("ferias/detalles");
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            App.setScreen("error", event);
                            e.printStackTrace();
                        }
                        DetallesController controller = loader.getController();
                        Feria feria = getTableView().getItems().get(getIndex());
                        controller.initialize(feria);
                        App.setScreen(root, event);
                    });
                    emprendedoresButton.setOnAction(event -> {
                        FXMLLoader loader = App.getLoader("ferias/emprendedores");
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            App.setScreen("error", event);
                            e.printStackTrace();
                        }
                        EmprendedoresController controller = loader.getController();
                        Feria feria = getTableView().getItems().get(getIndex());
                        controller.initialize(feria);
                        App.setScreen(root, event);
                    });
                    auspiciantesButton.setOnAction(event -> {
                        FXMLLoader loader = App.getLoader("ferias/auspiciantes");
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            App.setScreen("error", event);
                            e.printStackTrace();
                        }
                        AuspiciantesController controller = loader.getController();
                        Feria feria = getTableView().getItems().get(getIndex());
                        controller.initialize(feria);
                        App.setScreen(root, event);
                    });
                    standsButton.setOnAction(event -> {
                        FXMLLoader loader = App.getLoader("ferias/stands");
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            App.setScreen("error", event);
                            e.printStackTrace();
                        }
                        StandsController controller = loader.getController();
                        Feria feria = getTableView().getItems().get(getIndex());
                        controller.initialize(feria);
                        App.setScreen(root, event);
                    });
                    editarButton.setOnAction(event -> {
                        FXMLLoader loader = App.getLoader("ferias/editar");
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            App.setScreen("error", event);
                            e.printStackTrace();
                        }
                        EditarController controller = loader.getController();
                        Feria feria = getTableView().getItems().get(getIndex());
                        controller.initialize(feria);
                        App.setScreen(root, event);
                    });
                    botones.getChildren().add(detallesButton);
                    botones.getChildren().add(emprendedoresButton);
                    botones.getChildren().add(auspiciantesButton);
                    botones.getChildren().add(standsButton);
                    botones.getChildren().add(editarButton);



                    setGraphic(botones);
                    setText(null);
                }
            }
        });
        ArrayList<Feria> ferias = App.datos.getFerias();
        ObservableList<Feria> observableArray = FXCollections.observableArrayList(ferias);
        tabla.setItems(observableArray);
    }
}
