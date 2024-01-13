package com.espol.controllers.auspiciantes;

import java.io.IOException;

import com.espol.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class TablaController {

    @FXML
    private Button asignarFeriaButton;

    @FXML
    private Button backButton;

    @FXML
    private Button registerButton;

    @FXML
    private TableView<?> tabla;

    @FXML
    public void handleBackButtonAction(ActionEvent event){
        App.setScreen("inicio", event);
    }

}
