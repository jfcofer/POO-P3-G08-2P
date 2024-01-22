package com.espol.controllers.ferias;

import java.util.ArrayList;

import com.espol.App;
import com.espol.models.AuspicianteEnFeria;
import com.espol.models.Feria;
import com.espol.models.Seccion;
import com.espol.models.Stand;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tieneStandColumn.setCellValueFactory(new PropertyValueFactory<>("tieneStand"));
        standAsignadoColumn.setCellValueFactory(new PropertyValueFactory<>("standAsignado"));

        ArrayList<AuspicianteTabla> auspiciantes = AuspicianteTabla.generarAuspiciantes(feria);
        ObservableList<AuspicianteTabla> observableListAuspiciantes = FXCollections.observableArrayList(auspiciantes);
        tabla.setItems(observableListAuspiciantes);

    }
    private static class AuspicianteTabla{
        String nombre;
        String descripcion;
        String tieneStand;
        String standAsignado;

        private AuspicianteTabla(String nombre, String descripcion, String tieneStand, String standAsignado){
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.tieneStand = tieneStand;
            this.standAsignado = standAsignado;
        }

        private static ArrayList<AuspicianteTabla> generarAuspiciantes(Feria feria){
            ArrayList<AuspicianteTabla> arr = new ArrayList<>();
            ArrayList<AuspicianteEnFeria> auspiciantes = feria.getAuspiciantes();
            for (AuspicianteEnFeria auspiciante : auspiciantes){
                for (Seccion seccion : feria.getSecciones()){
                    for (Stand stand : seccion.getStands()){
                        if (stand.getPersonaAsignada().equals(auspiciante)){
                            arr.add(new AuspicianteTabla(auspiciante.getNombre(),auspiciante.getDescripcion(),(auspiciante.getTieneStand() ? "Si" : "No"), stand.getCodigo()));
                        }
                    }
                }
            }
            return arr;
        }
    } 

}
