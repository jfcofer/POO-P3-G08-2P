package com.espol.controllers;

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
}
