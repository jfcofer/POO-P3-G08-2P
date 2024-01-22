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
        Persona persona = personaCmbBox.getValue();
        if (App.datos.getEmprendedores().contains(persona)) {
            /*
             * Si la persona elegida es Emprendedor, para aplicar el conteo de stands
             */
            Emprendedor emprendedor = (Emprendedor) persona;

            /*
             * Revisar conteo de emprendedor
             */
            if (validacionStandsEmprendedor(emprendedor, feria)) {
                stand.setPersonaAsignada(personaCmbBox.getValue());
                stand.setFechaAsignacion(LocalDate.now());
                if (!feria.getEmprendedores().contains(emprendedor)){
                    feria.getEmprendedores().add(emprendedor);
                }
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
            } else {

                App.mostrarAlertaError("El emprendedor no debe tener mas de un stand reservado");

            }
        } else if (feria.getAuspiciantes().contains(persona)) {

            if (validacionStandsAuspiciante(persona, feria)) {
                stand.setPersonaAsignada(persona);
                stand.setFechaAsignacion(LocalDate.now());
                for (AuspicianteEnFeria auspiciante : feria.getAuspiciantes()) {
                    if (auspiciante.equals(persona)) {
                        auspiciante.setTieneStand(true);
                    }
                }
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
            } else {
                App.mostrarAlertaError("El aupsiciantes no debe tener mas de dos stands reservados");

            }

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
            ArrayList<AuspicianteEnFeria> auspiciantesConStand = new ArrayList<>();
            for (AuspicianteEnFeria auspiciante : feria.getAuspiciantes()) {
                if (auspiciante.getTieneStand()) {
                    auspiciantesConStand.add(auspiciante);
                }
            }
            ObservableList<Persona> observableList = FXCollections
                    .observableArrayList(convertToParentList(auspiciantesConStand));
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

    private static int conteoPersona(Persona persona, Feria feria) {
        int conteoStands = 0;
        for (Seccion seccion : feria.getSecciones()) {
            for (Stand stand : seccion.getStands()) {
                if (stand.getPersonaAsignada() != null && stand.getPersonaAsignada().equals(persona)) {
                    conteoStands++;
                }
            }
        }
        return conteoStands;
    }

    private static boolean validacionStandsAuspiciante(Persona persona, Feria feria) {
        if (conteoPersona(persona, feria) >= 2) {
            return false;
        }
        return true;
    }

    private static boolean validacionStandsEmprendedor(Persona persona, Feria feria) {
        if (conteoPersona(persona, feria) >= 1) {
            return false;
        }
        return true;
    }

}
