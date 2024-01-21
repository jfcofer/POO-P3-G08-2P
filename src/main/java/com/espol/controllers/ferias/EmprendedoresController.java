package com.espol.controllers.ferias;

import java.util.ArrayList;

import com.espol.App;
import com.espol.models.Emprendedor;
import com.espol.models.Feria;
import com.espol.models.Seccion;
import com.espol.models.Stand;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmprendedoresController {

    @FXML
    private TableView<Emprendedor> tabla;

    @FXML
    private TableColumn<Emprendedor, String> nombreColumn;

    @FXML
    private TableColumn<Emprendedor, String> descColumn;

    @FXML
    private TableColumn<Emprendedor, String> seccionColumn;

    @FXML
    private TableColumn<Emprendedor, String> standColumn;

    @FXML
    private Button backButton;

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        App.setScreen("ferias/tabla", event);
    }

    @FXML
    void initialize(Feria feria) {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descripcionServicios"));
        seccionColumn.setCellFactory(tc -> new TableCell<Emprendedor, String>() {
            @Override
            protected void updateItem(String string, boolean empty) {
                super.updateItem(string, empty);
                System.out.println(getIndex());
                Emprendedor emprendedor = tc.getTableView().getItems().get((getIndex() * -1) - 1);

                for (Seccion seccion : feria.getSecciones()) {
                    for (Stand stand : seccion.getStands()) {
                        if (stand.getPersonaAsignada() instanceof Emprendedor) {
                            if (emprendedor.equals((Emprendedor) stand.getPersonaAsignada()))
                                setText(Integer.toString(seccion.getId()));
                            break;
                        }
                    }
                }

            }
        });
        standColumn.setCellFactory(tc -> new TableCell<Emprendedor, String>() {
            @Override
            protected void updateItem(String string, boolean empty) {
                super.updateItem(string, empty);
                Emprendedor emprendedor = tc.getTableView().getItems().get(getIndex());
                for (Seccion seccion : feria.getSecciones()) {
                    for (Stand stand : seccion.getStands()) {
                        if (stand.getPersonaAsignada() instanceof Emprendedor) {
                            if (emprendedor.equals((Emprendedor) stand.getPersonaAsignada()))
                                setText(stand.getCodigo());
                            break;
                        }
                    }
                }

            }
        });
        ArrayList<Emprendedor> emprendedores = feria.getEmprendedores();
        ObservableList<Emprendedor> observableListEmprendedores = FXCollections.observableArrayList(emprendedores);
        tabla.setItems(observableListEmprendedores);
    }

}
