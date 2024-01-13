package com.espol.controllers;

import com.espol.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private Button btnAdministrarAuspiciantes;

    @FXML
    private Button btnAdministrarEmprendedores;

    @FXML
    private Button btnAdministrarFeria;

    @FXML
    private Button btnAdministrarStands;

    @FXML
    private Button btnSalir;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAuspiciantesButton(ActionEvent event) {
        App.setScreen("auspiciantes/tabla", event);
    }

    @FXML
    public void handleEmprendedoresButton(ActionEvent event) {
        App.setScreen("emprendedores/tabla", event);
    }

    @FXML
    public void handleFeriasButton(ActionEvent event) {
        App.setScreen("ferias/tabla", event);
    }

    @FXML
    public void handleStandsButton(ActionEvent event) {
        App.setScreen("stands/tabla", event);
    }
}
