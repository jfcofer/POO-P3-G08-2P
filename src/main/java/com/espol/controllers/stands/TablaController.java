package com.espol.controllers.stands;

import com.espol.App;
import com.espol.models.*;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

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
                if (stand.getPersonaAsignada() == null) {
                    Button reservable = new Button("Reservar");
                    reservable.setOnAction(event -> {
                        FXMLLoader loader = App.getLoader("stands/reservar");
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            App.setScreen("error", event);
                            e.printStackTrace();
                        }
                        ReservarController controller = loader.getController();
                        controller.initialize(feria, stand);
                        App.setScreen(root, event);
                    });
                    standVBox.getChildren().add(reservable);
                }

                HBoxes[i].getChildren().add(standVBox);
            }
        }
    }
}
