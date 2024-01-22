package com.espol.controllers.ferias;

import java.util.ArrayList;

import com.espol.App;
import com.espol.models.Emprendedor;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmprendedoresController {

    @FXML
    private TableView<EmprendedorTabla> tabla;

    @FXML
    private TableColumn<EmprendedorTabla, String> nombreColumn;

    @FXML
    private TableColumn<EmprendedorTabla, String> descColumn;

    @FXML
    private TableColumn<EmprendedorTabla, String> seccionColumn;

    @FXML
    private TableColumn<EmprendedorTabla, String> standColumn;

    @FXML
    private Button backButton;

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        App.setScreen("ferias/tabla", event);
    }

    @FXML
    void initialize(Feria feria) {
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        descColumn.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        seccionColumn.setCellValueFactory(cellData -> cellData.getValue().idSeccionProperty());
        standColumn.setCellValueFactory(cellData -> cellData.getValue().codigoStandProperty());

        ArrayList<EmprendedorTabla> emprendedores = EmprendedorTabla.generarEmprendedores(feria);
        ObservableList<EmprendedorTabla> observableListEmprendedores = FXCollections.observableArrayList(emprendedores);
        tabla.setItems(observableListEmprendedores);
    }

    private static class EmprendedorTabla {
        StringProperty nombre;
        StringProperty descripcion;
        StringProperty idSeccion;
        StringProperty codigoStand;


        public String getNombre() {
            return nombre.get();
        }

        public void setNombre(String nombre) {
            this.nombre = new SimpleStringProperty(nombre);
        }

        public StringProperty nombreProperty(){
            return nombre;
        }

        public String getDescripcion() {
            return descripcion.get();
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = new SimpleStringProperty(descripcion);
        }

        public StringProperty descripcionProperty(){
            return descripcion;
        }

        public String getIdSeccion() {
            return idSeccion.get();
        }

        public void setIdSeccion(String idSeccion) {
            this.idSeccion = new SimpleStringProperty(idSeccion);
        }
        public StringProperty idSeccionProperty(){
            return idSeccion;
        }

        public String getCodigoStand() {
            return codigoStand.get();
        }

        public void setCodigoStand(String codigoStand) {
            this.codigoStand = new SimpleStringProperty(codigoStand);
        }
        public StringProperty codigoStandProperty(){
            return codigoStand;
        }

        public EmprendedorTabla(String nombre,
                String descripcion,
                String idSeccion,
                String codigoStand) {
            this.nombre = new SimpleStringProperty( nombre);
            this.descripcion = new SimpleStringProperty(descripcion);
            this.idSeccion = new SimpleStringProperty(idSeccion);
            this.codigoStand = new SimpleStringProperty(codigoStand);

        }

        private static ArrayList<EmprendedorTabla> generarEmprendedores(Feria feria) {
            ArrayList<EmprendedorTabla> arr = new ArrayList<>();
            ArrayList<Emprendedor> emprendedores = feria.getEmprendedores();
            for (Emprendedor emprendedor : emprendedores) {
                for (Seccion seccion : feria.getSecciones()) {
                    for (Stand stand : seccion.getStands()) {
                        if (stand.getPersonaAsignada() != null && stand.getPersonaAsignada().equals(emprendedor)) {
                            arr.add(new EmprendedorTabla(emprendedor.getNombre(), emprendedor.getDescripcionServicios(),
                                    Integer.toString(seccion.getId()), stand.getCodigo()));
                        }
                    }
                }

            }

            return arr;
        }

    }

}
