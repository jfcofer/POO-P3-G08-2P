/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.controllers.auspiciantes;

import com.espol.App;
import com.espol.models.Auspiciante;
import com.espol.models.AuspicianteEnFeria;
import com.espol.models.Feria;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 *
 * @author Cris
 */
public class AsignarController {

    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnValidar;
    @FXML
    private Button btnAsignar;
    @FXML
    private ComboBox feriasComboBox;
    @FXML
    private CheckBox standCheckBox;
    @FXML
    private TextField cedulaTextField;
    @FXML
    private TextField descripcionTextField;

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
        feriasComboBox.setCellFactory(factory);
        feriasComboBox.setButtonCell(factory.call(null));
        ArrayList<Feria> ferias = App.datos.getFerias();
        ObservableList<Feria> feriasObservableList = FXCollections.observableArrayList(ferias);
        feriasComboBox.setItems(feriasObservableList);

        descripcionTextField.setEditable(false);
        standCheckBox.setVisible(false);
        btnAsignar.setDisable(true);

        App.mostrarAlertaInfo(
                "Primero deberá seleccionar una feria activa, luego digitar la cédula/ruc del auspiciante y validarlo antes de continuar llenando los otros campos.\n Una vez validado estos datos no podrá cambiarlos a menos que regrese y vuelva a ingresar a esta ventana.");
    }

    @FXML
    private void handleValidarAuspicianteButtonAction(ActionEvent event) {
        String cedula = cedulaTextField.getText();
        ArrayList<Auspiciante> auspiciantes = App.datos.getAuspiciantes();

        if (cedula.equals(null)) {
            App.mostrarAlertaError(
                    "La cedula no puede quedar vacía");
        } else {
            Boolean existe = false;
            for (Auspiciante a : auspiciantes) {
                if (a.getRuc().equals(cedula)) {
                    existe = true;
                }
            }
            if (existe == false) {
                App.mostrarAlertaError(
                        "La cedula que ingresó no corresponde a ningún auspiciante registrado.");
            } else {
                descripcionTextField.setEditable(true);
                standCheckBox.setVisible(true);
                App.mostrarAlertaInfo(
                        "La cedula que ingresó ha sido validada, complete los campos restantes.");
                cedulaTextField.setEditable(false);
                feriasComboBox.setEditable(false);
                btnAsignar.setDisable(false);
            }
        }

    }

    @FXML
    private void handleAsignarButtonAction(ActionEvent event) {
        String cedula = cedulaTextField.getText();
        Boolean stand = standCheckBox.isSelected();
        Feria feria = (Feria) feriasComboBox.getValue();
        String descripcion = descripcionTextField.getText();
        Auspiciante auspiciante = null;

        for (Auspiciante a : App.datos.getAuspiciantes()) {
            if (a.getRuc().equals(cedula)) {
                auspiciante = a;
            }
        }
        if (descripcion.isBlank()) {
            App.mostrarAlertaError(
                    "La descripción de lo que cubre el auspicio no puede quedar vacío.");
        } else {
            Boolean registrar = true;
            for (Auspiciante a : feria.getAuspiciantes()) {
                if (a.getRuc().equals(cedula)) {
                    App.mostrarAlertaError(
                            "Este auspiciante ya se encuentra asignado a esta feria.");
                    registrar = false;
                    App.setScreen("auspiciantes/tabla", event);
                }
            }
            if (registrar) {
                feria.asignarAuspiciante(auspiciante, descripcion, stand);
                App.mostrarAlertaInfo(
                        "El auspiciante ha sido asignado a la feria correctammente.");
                App.setScreen("auspiciantes/tabla", event);
            }
        }

    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        App.setScreen("auspiciantes/tabla", event);
    }

}
