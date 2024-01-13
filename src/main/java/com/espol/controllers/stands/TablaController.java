package com.espol.controllers.stands;

import com.espol.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class TablaController {

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

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

}
