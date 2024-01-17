package com.espol.controllers.stands;

import com.espol.App;
import com.espol.models.*;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TablaController {

    private Feria feria;

    @FXML
    private Button backButton;

    @FXML
    private TextArea infoTxtArea;

    @FXML
    private HBox sec1HBox;

    @FXML
    private HBox sec2HBox;

    @FXML
    private HBox sec3HBox;

    @FXML
    private HBox sec4HBox;

    public void setFeria(Feria feria){
        
    }

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

    @FXML
    public void initialize(){
        Seccion seccion1 = feria.getSecciones()[0];
        Seccion seccion2 = feria.getSecciones()[1];
        Seccion seccion3 = feria.getSecciones()[2];
        Seccion seccion4 = feria.getSecciones()[3];


            
    }
}
