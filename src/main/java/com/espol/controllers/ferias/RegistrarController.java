package com.espol.controllers.ferias;

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
        Feria feria = new Feria(App.datos.getFerias().getLast().getCodigo() + 1, null, null, null, null, null, null);
    }

}
