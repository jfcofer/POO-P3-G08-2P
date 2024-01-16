/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.controllers.auspiciantes;

import com.espol.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author William
 */
public class AsignarController {

    @FXML
    private Button btnRegresar;

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        App.setScreen("auspiciantes/tabla", event);
    }

}
