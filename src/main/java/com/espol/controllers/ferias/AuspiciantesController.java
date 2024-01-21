package com.espol.controllers.ferias;

import com.espol.App;
import com.espol.models.AuspicianteEnFeria;
import com.espol.models.Feria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AuspiciantesController {

    @FXML
    private Label titulo;

    @FXML
    private TableView<AuspicianteEnFeria> tabla;

    @FXML
    private TableColumn<AuspicianteEnFeria, String> nombreColumn;

    @FXML
    private TableColumn<AuspicianteEnFeria, String> descColumn;

    @FXML
    private TableColumn<AuspicianteEnFeria, String> tieneStandColumn;

    @FXML
    private TableColumn<AuspicianteEnFeria, String> standAsignadoColumn;

    @FXML
    private Button backButton;

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        App.setScreen("ferias/tabla", event);
    }

    @FXML
    public void initialize(Feria feria) {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tieneStandColumn.setCellFactory(tableCol -> new TableCell<AuspicianteEnFeria, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                boolean tieneStand = tableCol.getTableView().getItems().get(getIndex()).getTieneStand();
                if (tieneStand) {
                    setText("Si");
                } else {
                    setText("No");
                }
            }
        });
    }

}
