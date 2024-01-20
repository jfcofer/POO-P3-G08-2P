package com.espol.controllers.ferias;

import java.time.LocalDate;

import com.espol.App;
import com.espol.models.Feria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RegistrarController {

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameTxtField;

    @FXML
    private TextField descTxtField;

    @FXML
    private TextField lugarTxtField;

    @FXML
    private DatePicker inicioDate;

    @FXML
    private DatePicker finDate;

    @FXML
    private TextField horarioTxtField;

    @FXML
    private TextField numSec1TxtField;

    @FXML
    private TextField numSec2TxtField;

    @FXML
    private TextField numSec3TxtField;

    @FXML
    private TextField numSec4TxtField;

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        App.setScreen("ferias/tabla", event);
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String name = nameTxtField.getText();
        String descripcion = descTxtField.getText();
        String lugar = lugarTxtField.getText();
        LocalDate fechaInicio = inicioDate.getValue();
        LocalDate fechaFin = finDate.getValue();

        if (registerValidation(name, descripcion, lugar, fechaInicio, fechaFin, lugar)) {
            Feria feria = new Feria(App.datos.getFerias().getLast().getCodigo() + 1, name, descripcion, lugar,
                    fechaInicio, fechaFin, lugar);
            App.datos.getFerias().add(feria);
            App.datos.generarArchivo();
            App.mostrarAlertaInfo("Registro realizado Correctamente");
            App.setScreen("ferias/tabla", event);

        }

    }

    private boolean registerValidation(String nombre, String descripcion, String lugar, LocalDate fechaInicio,
            LocalDate fechaFin,
            String horario) {
        for (Feria feria : App.datos.getFerias()) {
            if (feria.getNombre().equals(nombre)) {
                return false;
            }
        }
        if (nombre.isBlank() || descripcion.isBlank() || lugar.isBlank() || fechaInicio == null || fechaFin == null) {
            return false;
        }

        return true;

    }

}
