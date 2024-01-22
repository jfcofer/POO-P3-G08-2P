package com.espol.controllers.ferias;

import java.util.ArrayList;

import com.espol.App;
import com.espol.models.AuspicianteEnFeria;
import com.espol.models.Feria;
import com.espol.models.Seccion;
import com.espol.models.Stand;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AuspiciantesController {

    @FXML
    private Label titulo;

    @FXML
    private TableView<AuspicianteTabla> tabla;

    @FXML
    private TableColumn<AuspicianteTabla, String> nombreColumn;

    @FXML
    private TableColumn<AuspicianteTabla, String> descColumn;

    @FXML
    private TableColumn<AuspicianteTabla, String> tieneStandColumn;

    @FXML
    private TableColumn<AuspicianteTabla, String> standAsignadoColumn;

    @FXML
    private Button backButton;

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        App.setScreen("ferias/tabla", event);
    }

    @FXML
    public void initialize(Feria feria) {
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        descColumn.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        tieneStandColumn.setCellValueFactory(cellData -> cellData.getValue().tieneStandProperty());
        standAsignadoColumn.setCellValueFactory(cellData -> cellData.getValue().standAsignadoProperty());

        ArrayList<AuspicianteTabla> auspiciantes = AuspicianteTabla.generarAuspiciantes(feria);
        ObservableList<AuspicianteTabla> observableListAuspiciantes = FXCollections.observableArrayList(auspiciantes);
        tabla.setItems(observableListAuspiciantes);

    }

    private static class AuspicianteTabla {
        StringProperty nombre;
        StringProperty descripcion;
        StringProperty tieneStand;
        StringProperty standAsignado;

        public StringProperty nombreProperty() {
            return nombre;
        }

        public StringProperty descripcionProperty() {
            return descripcion;
        }

        public StringProperty tieneStandProperty() {
            return tieneStand;
        }

        public StringProperty standAsignadoProperty() {
            return standAsignado;
        }

        public AuspicianteTabla(String nombre, String descripcion, String tieneStand, String standAsignado) {
            this.nombre = new SimpleStringProperty(nombre);
            this.descripcion = new SimpleStringProperty(descripcion);
            this.tieneStand = new SimpleStringProperty(tieneStand);
            this.standAsignado = new SimpleStringProperty(standAsignado);
        }

        private static ArrayList<AuspicianteTabla> generarAuspiciantes(Feria feria) {
            ArrayList<AuspicianteTabla> arr = new ArrayList<>();
            ArrayList<AuspicianteEnFeria> auspiciantes = feria.getAuspiciantes();
            for (AuspicianteEnFeria auspiciante : auspiciantes) {
                String codigoStand = "No tiene";
                for (Seccion seccion : feria.getSecciones()) {
                    for (Stand stand : seccion.getStands()) {
                        if (stand.getPersonaAsignada() != null && stand.getPersonaAsignada().equals(auspiciante)) {
                            codigoStand = stand.getCodigo();
                        }
                    }
                }

                arr.add(new AuspicianteTabla(auspiciante.getNombre(), auspiciante.getDescripcion(),
                        auspiciante.getTieneStand() ? "Si" : "No", codigoStand));
            }
            return arr;
        }
    }

}
