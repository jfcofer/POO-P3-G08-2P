package com.espol.controllers.stands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.espol.App;
import com.espol.models.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class ReservarController {

    private Feria feria;
  
    @FXML
    private Button backButton;

    @FXML
    private ComboBox<Persona> personaCmbBox;

    @FXML
    private Button reservarButton;

    @FXML
    private ChoiceBox<String> rolChoiceBox;

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        FXMLLoader loader = App.getLoader("stands/tabla");
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            App.setScreen("error", event);
            e.printStackTrace();
        }
        TablaController tablaController = loader.getController();
        tablaController.initialize(feria);
        App.setScreen(root, event);
    }

    @FXML
    void handleReservarButtonAction(ActionEvent event) {

        App.setScreen("stands/codigoFeria", event);
    }

    @FXML
    void handleRolChoiceBoxAction(){
        String rol = rolChoiceBox.getValue();
        updatePersonaCmbBox(rol);
    }

    private void updatePersonaCmbBox(String rol){
        if ("Auspiciante".equals(rol)){
            Callback<ListView<Auspiciante>, ListCell<Auspiciante>> factory = lv -> new ListCell<Auspiciante>(){
                @Override
                protected void updateItem(Auspiciante auspiciante, boolean empty){
                    super.updateItem(feria,empty);
                    setText(empty ? "" : auspiciante.getNombre());
                }
            };
            personaCmbBox.setCellFactory(factory);
            personaCmbBox.setButtonCell(factory.call(null));
            ArrayList<Auspiciante> auspiciantes = App.datos.getAuspiciantes();
            ObservableList<Auspiciante> observableList = FXCollections.observableArrayList(auspiciantes);
            personaCmbBox.setItems(observableList);
        } else {
            Callback<ListView<Emprendedor>, ListCell<Emprendedor>> factory = lv -> new ListCell<Emprendedor>(){
                @Override
                protected void updateItem(Emprendedor emprendedor, boolean empty){
                    super.updateItem(feria,empty);
                    setText(empty ? "" : emprendedor.getNombre());
                }
            };
            personaCmbBox.setCellFactory(factory);
            personaCmbBox.setButtonCell(factory.call(null));
            ArrayList<Emprendedor> emprendedores = App.datos.getEmprendedores();
            ObservableList<Emprendedor> observableList = FXCollections.observableArrayList(emprendedores);
            personaCmbBox.setItems(observableList);
        }
    }

    @FXML
    public void initialize(Feria feria, Stand stand) {
        this.feria = feria;
        List<String> list = new ArrayList<String>(Arrays.asList("Auspiciante", "Emprendedor"));
        ObservableList<String> roles = FXCollections.observableArrayList(list);
        rolChoiceBox.setItems(roles);
    }

}
