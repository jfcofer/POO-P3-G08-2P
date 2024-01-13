package com.espol.controllers.ferias;

import com.espol.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class TablaController {

    @FXML
    private Button backButton;

    @FXML
    private Button nuevaFeriaButton;

    @FXML
    private TableView<?> tabla;

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

    @FXML
    public void handleNuevaFeriaButtonAction(ActionEvent event) {
        App.setScreen("ferias/registrar", event);
    }

    @FXML
    public void initialize() {

    }
}
