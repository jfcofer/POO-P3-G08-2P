package com.espol.controllers.stands;

import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ReservarController {

    private Feria feria;

    private Stand stand;

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
        if (App.datos.getEmprendedores().contains(personaCmbBox.getValue())) {
            Emprendedor emprendedor = (Emprendedor) personaCmbBox.getValue();
            if (conteoEmprendedor(emprendedor, feria) >= 2) {
                mostrarAlertaError("El emprendedor no debe tener mas de dos stands reservados");
            } else {
                stand.setPersonaAsignada(personaCmbBox.getValue());
                stand.setFechaAsignacion(LocalDate.now());
                App.datos.generarArchivo();
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
        } else {
            stand.setPersonaAsignada(personaCmbBox.getValue());
            stand.setFechaAsignacion(LocalDate.now());
            App.datos.generarArchivo();
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
    }

    @FXML
    void handleRolChoiceBoxAction(ActionEvent event) {
        String rol = rolChoiceBox.getValue();
        personaCmbBox.getItems().clear();
        updatePersonaCmbBox(rol);
    }

    private void updatePersonaCmbBox(String rol) {
        if ("Auspiciante".equals(rol)) {
            Callback<ListView<Persona>, ListCell<Persona>> factory = lv -> new ListCell<Persona>() {
                @Override
                protected void updateItem(Persona auspiciante, boolean empty) {
                    super.updateItem(auspiciante, empty);
                    setText(empty ? "" : auspiciante.getNombre());
                }
            };
            personaCmbBox.setCellFactory(factory);
            personaCmbBox.setButtonCell(factory.call(null));
            ArrayList<Persona> auspiciantes = convertToParentList(App.datos.getAuspiciantes());
            ObservableList<Persona> observableList = FXCollections.observableArrayList(auspiciantes);
            personaCmbBox.setItems(observableList);
        } else {
            Callback<ListView<Persona>, ListCell<Persona>> factory = lv -> new ListCell<Persona>() {
                @Override
                protected void updateItem(Persona emprendedor, boolean empty) {
                    super.updateItem(emprendedor, empty);
                    setText(empty ? "" : emprendedor.getNombre());
                }
            };
            personaCmbBox.setCellFactory(factory);
            personaCmbBox.setButtonCell(factory.call(null));
            ArrayList<Persona> emprendedores = convertToParentList(App.datos.getEmprendedores());
            ObservableList<Persona> observableList = FXCollections.observableArrayList(emprendedores);
            personaCmbBox.setItems(observableList);
        }
    }

    @FXML
    public void initialize(Feria feria, Stand stand) {
        this.feria = feria;
        this.stand = stand;
        List<String> list = new ArrayList<String>(Arrays.asList("Auspiciante", "Emprendedor"));
        ObservableList<String> roles = FXCollections.observableArrayList(list);
        rolChoiceBox.setItems(roles);
    }

    private static <T extends Persona> ArrayList<Persona> convertToParentList(List<T> childList) {
        ArrayList<Persona> parentList = new ArrayList<>();
        parentList.addAll(childList);
        return parentList;
    }

    private static int conteoEmprendedor(Emprendedor emprendedor, Feria feria) {
        int standsEmprendedor = 0;
        for (Seccion seccion : feria.getSecciones()) {
            for (Stand stand : seccion.getStands()) {
                if (stand.getPersonaAsignada() instanceof Emprendedor) {
                    Emprendedor personaEmp = (Emprendedor) stand.getPersonaAsignada();
                    if (personaEmp.equals(emprendedor)) {
                        standsEmprendedor++;
                    }

                }
            }
        }
        return standsEmprendedor;
    }

    private static void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
