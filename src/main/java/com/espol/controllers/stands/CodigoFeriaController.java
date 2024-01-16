package com.espol.controllers.stands;

import java.util.ArrayList;

import com.espol.App;
import com.espol.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class CodigoFeriaController {

    @FXML
    private Button btnRegresar;

    @FXML
    private ComboBox<Feria> feriasCmbBox;

    @FXML
    private Button ingresarButton;

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

    @FXML
    public void handleIngresarButtonAction(ActionEvent event){
        TablaController controller = App.getLoader("stands/tabla").getController();
        controller.setFeria(feriasCmbBox.getValue());
        App.setScreen("stands/tabla", event);
    }

    @FXML
    public void initialize(){
        ArrayList<Feria> ferias = App.datos.getFerias();
        feriasCmbBox.getItems().clear();
        feriasCmbBox.getItems().addAll(ferias);
    }

}
