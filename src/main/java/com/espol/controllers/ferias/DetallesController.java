/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.controllers.ferias;

import com.espol.App;
import com.espol.models.Feria;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author William
 */
public class DetallesController {

    private Feria feria;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField descripcionTextField;
    @FXML
    private TextField lugarTextField;
    @FXML
    private TextField fechaStartTextField;
    @FXML
    private TextField fechaEndTextField;
    @FXML
    private TextField horarioTextField;
    @FXML
    private TextField standS1TextField;
    @FXML
    private TextField standS2TextField;
    @FXML
    private TextField standS3TextField;
    @FXML
    private TextField standS4TextField;
    @FXML
    private TextArea auspiciantesTextArea;
    @FXML
    private TextArea emprendedoresTextArea;
    
    @FXML
    public void initialize(Feria feria) {
        nombreTextField.setText(feria.getNombre());
        descripcionTextField.setText(feria.getDescripcion());
        lugarTextField.setText(feria.getLugar());
        fechaStartTextField.setText(String.valueOf(feria.getFechaInicio()));
        fechaEndTextField.setText(String.valueOf(feria.getFechaFin()));
        horarioTextField.setText(feria.getHorario());
        standS1TextField.setText(String.valueOf(feria.getSecciones()[0].getStands().size()));
        standS2TextField.setText(String.valueOf(feria.getSecciones()[1].getStands().size()));
        standS3TextField.setText(String.valueOf(feria.getSecciones()[2].getStands().size()));
        standS4TextField.setText(String.valueOf(feria.getSecciones()[3].getStands().size()));
        auspiciantesTextArea.setText(feria.consultarAuspiciantes());
        emprendedoresTextArea.setText(feria.consultarEmprendedores());
        
    }
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        App.setScreen("ferias/tabla", event);
    }
}
