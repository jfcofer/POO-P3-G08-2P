package com.espol.controllers.ferias;

import java.time.LocalDate;

import com.espol.App;
import com.espol.models.Feria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    private Spinner<Integer> sec1Spinner;

    @FXML
    private Spinner<Integer> sec2Spinner;

    @FXML
    private Spinner<Integer> sec3Spinner;

    @FXML
    private Spinner<Integer> sec4Spinner;

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
        int numSec1 = sec1Spinner.getValue();
        int numSec2 = sec2Spinner.getValue();
        int numSec3 = sec3Spinner.getValue();
        int numSec4 = sec4Spinner.getValue();

        if (registerValidation(name, descripcion, lugar, fechaInicio, fechaFin, lugar)) {
            Feria feria = new Feria(App.datos.getFerias().getLast().getCodigo() + 1, name, descripcion, lugar,
                    fechaInicio, fechaFin, lugar);
            feria.asignarNumeroStands(numSec1,numSec2,numSec3,numSec4);
            App.datos.getFerias().add(feria);
            App.datos.generarArchivo();
            App.mostrarAlertaInfo("Registro realizado Correctamente");
            App.setScreen("ferias/tabla", event);
        }

    }

    private boolean registerValidation(String nombre, String descripcion, String lugar, LocalDate fechaInicio,
            LocalDate fechaFin, String horario) {
        for (Feria feria : App.datos.getFerias()) {
            if (feria.getNombre().equals(nombre)) {
                App.mostrarAlertaError("Ya existe una feria registrada con ese nombre");
                return false;
            }
        }
        if (nombre.isBlank()) {
            App.mostrarAlertaError("Debe ingresar un nombre");
        } else if (descripcion.isBlank()) {
            App.mostrarAlertaError("Debe ingresar un descripcion");
        } else if (lugar.isBlank()) {

            App.mostrarAlertaError("Debe ingresar un lugar");
        } else if (fechaInicio == null) {
            App.mostrarAlertaError("Debe ingresar una fecha valida de inicio");

        } else if (fechaFin == null) {
            App.mostrarAlertaError("Debe ingresar una fecha valida de fin");

        } 

        return true;

    }

    @FXML
    public void initialize(){
        sec1Spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        sec2Spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        sec3Spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        sec4Spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
    }

}
