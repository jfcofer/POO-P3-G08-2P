package com.espol.controllers.ferias;

import com.espol.App;

import com.espol.models.Feria;
import com.espol.models.Seccion;
import com.espol.models.Stand;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class StandsController {

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
    public void initialize(Feria feria) {
        HBox[] HBoxes = { sec1HBox, sec2HBox, sec3HBox, sec4HBox };
        Seccion[] secciones = feria.getSecciones();
        for (int i = 0; i < HBoxes.length; i++) {

            for (Stand stand : secciones[i].getStands()) {
                Label titulo = new Label("Stand");
                Label codigo = new Label(stand.getCodigo());
                VBox standVBox = new VBox(3);
                VBox.setVgrow(standVBox, Priority.ALWAYS);
                standVBox.setAlignment(Pos.CENTER);
                standVBox.setPrefWidth(100);
                standVBox.getChildren().addAll(titulo, codigo);
                standVBox.setOnMouseClicked(event -> {
                    infoTxtArea.setText(stand.toString());
                });

                HBoxes[i].getChildren().add(standVBox);
            }
        }
    }

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

}
