package com.espol.controllers.ferias;

import java.time.LocalDate;
import java.util.Optional;

import com.espol.App;
import com.espol.models.Feria;
import com.espol.models.Seccion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class EditarController {

    private Feria feria;

    @FXML
    private Label tituloLabel;

    @FXML
    private TextField nameTxtField;

    @FXML
    private TextField descTxtField;

    @FXML
    private TextField lugarTxtField;

    @FXML
    private DatePicker inicioDate;

    @FXML
    private DatePicker finDate;

    @FXML
    private TextField horarioTxtField;

    @FXML
    private Spinner<Integer> sec1Spinner;

    @FXML
    private Spinner<Integer> sec2Spinner;

    @FXML
    private Spinner<Integer> sec3Spinner;

    @FXML
    private Spinner<Integer> sec4Spinner;

    @FXML
    private Button actualizarButton;

    @FXML
    private Button backButton;

    @FXML
    void handleActualizarButtonAction(ActionEvent event) {
        String name = nameTxtField.getText();
        String descripcion = descTxtField.getText();
        String lugar = lugarTxtField.getText();
        LocalDate fechaInicio = inicioDate.getValue();
        LocalDate fechaFin = finDate.getValue();
        if (editValidation(name, descripcion, lugar, fechaInicio, fechaFin, lugar)) {
            feria.setNombre(name);
            feria.setDescripcion(descripcion);
            feria.setLugar(lugar);
            feria.setFechaInicio(fechaInicio);
            feria.setFechaFin(fechaFin);
            modifyStandsNumber();
            App.datos.generarArchivo();
        }
        App.setScreen("ferias/tabla", event);
    }

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        App.setScreen("ferias/tabla", event);
    }

    private void modifyStandsNumber() {
        Spinner[] spinners = { sec1Spinner, sec2Spinner, sec3Spinner, sec4Spinner };

        for (int i = 0; i < spinners.length; i++) {
            Seccion seccion = feria.getSecciones()[i];
            int numStandsOriginal = seccion.getStands().size();
            int numStandsNuevo = (Integer) spinners[i].getValue();
            if (numStandsOriginal < numStandsNuevo) {
                seccion.addStands(numStandsNuevo - numStandsOriginal);
            } else if (numStandsOriginal > numStandsNuevo) {
                Optional<ButtonType> result = App
                        .mostrarAlertaAdvert("Seguro que desea borrar los stands de la seccion "+seccion.getId()+"?\nSe perdera toda la informacion");
                if (!result.isPresent()) {

                } else if (result.get() == ButtonType.OK) {

                    seccion.removeStands(numStandsOriginal - numStandsNuevo);
                } else if (result.get() == ButtonType.CANCEL) {

                }

            }

        }
    }

    private boolean editValidation(String nombre, String descripcion, String lugar, LocalDate fechaInicio,
            LocalDate fechaFin, String horario) {
        if (nombre.isBlank()) {
            App.mostrarAlertaError("Debe ingresar un nombre");
        } else if (descripcion.isBlank()) {
            App.mostrarAlertaError("Debe ingresar un descripcion");
        } else if (lugar.isBlank()) {

            App.mostrarAlertaError("Debe ingresar un lugar");
        } else if (fechaInicio == null) {
            App.mostrarAlertaError("Debe ingresar una fecha valida de inicio");

        } else if (fechaFin == null) {
            App.mostrarAlertaError("Debe ingresar una fecha valida de fin");

        }

        return true;

    }

    /**
     * Initializes the controller class.
     */

    @FXML
    public void initialize(Feria feria) {
        this.feria = feria;
        tituloLabel.setText("Editar Feria\n" + feria.getNombre());
        nameTxtField.setText(feria.getNombre());
        descTxtField.setText(feria.getDescripcion());
        lugarTxtField.setText(feria.getLugar());
        inicioDate.setValue(feria.getFechaInicio());
        finDate.setValue(feria.getFechaFin());
        horarioTxtField.setText(feria.getHorario());
        Seccion[] secciones = feria.getSecciones();

        sec1Spinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, secciones[0].getStands().size()));
        sec2Spinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, secciones[1].getStands().size()));
        sec3Spinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, secciones[2].getStands().size()));
        sec4Spinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, secciones[3].getStands().size()));
    }

}
