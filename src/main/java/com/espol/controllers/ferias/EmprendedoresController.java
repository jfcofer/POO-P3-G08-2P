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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        seccionColumn.setCellValueFactory(new PropertyValueFactory<>("idSeccion"));
        standColumn.setCellValueFactory(new PropertyValueFactory<>("codigoStand"));

        ArrayList<EmprendedorTabla> emprendedores = EmprendedorTabla.generarEmprendedores(feria);
        ObservableList<EmprendedorTabla> observableListEmprendedores = FXCollections.observableArrayList(emprendedores);
        tabla.setItems(observableListEmprendedores);
    }

    private static class EmprendedorTabla {
        String nombre;
        String descripcion;
        String idSeccion;
        String codigoStand;

        private EmprendedorTabla(String nombre,
                String descripcion,
                String idSeccion,
                String codigoStand) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.idSeccion = idSeccion;
            this.codigoStand = codigoStand;

        }

        private static ArrayList<EmprendedorTabla> generarEmprendedores(Feria feria) {
            ArrayList<EmprendedorTabla> arr = new ArrayList<>();
            ArrayList<Emprendedor> emprendedores = feria.getEmprendedores();
            for (Emprendedor emprendedor : emprendedores) {
                for (Seccion seccion : feria.getSecciones()) {
                    for (Stand stand : seccion.getStands()) {
                        if (stand.getPersonaAsignada().equals(emprendedor)) {
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
