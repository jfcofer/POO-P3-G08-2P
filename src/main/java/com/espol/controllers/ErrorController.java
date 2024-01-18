package com.espol.controllers;

import com.espol.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ErrorController {

    @FXML
    private Button backButton;

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

}
