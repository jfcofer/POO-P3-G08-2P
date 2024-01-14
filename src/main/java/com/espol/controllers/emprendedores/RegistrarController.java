/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.controllers.emprendedores;

import com.espol.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author William
 */
public class RegistrarController {

    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    public void initialize() {

    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        App.setScreen("emprendedores/tabla", event);
    }

}
