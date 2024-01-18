package com.espol.controllers.stands;

import java.io.IOException;
import java.util.ArrayList;

import com.espol.App;
import com.espol.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CodigoFeriaController {

    @FXML
    private Button btnRegresar;

    @FXML
    private ComboBox<Feria> feriasCmbBox;

    @FXML
    private Button ingresarButton;

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

    @FXML
    public void handleIngresarButtonAction(ActionEvent event) {
        FXMLLoader loader = App.getLoader("stands/tabla");
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            App.setScreen("error", event);
        }
        TablaController controller = loader.getController();
        controller.initialize(feriasCmbBox.getValue());
        App.setScreen(root, event);
    }

    @FXML
    public void initialize() {
        Callback<ListView<Feria>, ListCell<Feria>> factory = lv -> new ListCell<Feria>() {
            @Override
            protected void updateItem(Feria feria, boolean empty) {
                super.updateItem(feria, empty);
                setText(empty ? "" : feria.getNombre());
            }
        }; // ArrayList<Feria> ferias = App.datos.getFerias();
                // ObservableList<Feria> listaObservable = new ObservableList(ferias);
        feriasCmbBox.setCellFactory(factory);
        feriasCmbBox.setButtonCell(factory.call(null));
        ArrayList<Feria> ferias = App.datos.getFerias();
        ObservableList<Feria> observableList = FXCollections.observableArrayList(ferias);
        feriasCmbBox.setItems(observableList);
    }

}
