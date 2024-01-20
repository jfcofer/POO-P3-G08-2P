package com.espol.controllers.ferias;

import com.espol.models.Feria;
import com.espol.models.Seccion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    }

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        
    }


    /**
     * Initializes the controller class.
     */

    @FXML
    public void initialize(Feria feria) {
        this.feria = feria;
        tituloLabel.setText("Editar Feria\n"+feria.getNombre());
        nameTxtField.setText(feria.getNombre());
        descTxtField.setText(feria.getDescripcion());
        lugarTxtField.setText(feria.getLugar());
        inicioDate.setValue(feria.getFechaInicio());
        finDate.setValue(feria.getFechaFin());
        horarioTxtField.setText(feria.getHorario());
        Seccion[] secciones = feria.getSecciones();

        sec1Spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, secciones[0].getStands().size()));
        sec2Spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, secciones[1].getStands().size()));
        sec3Spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, secciones[2].getStands().size()));
        sec4Spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, secciones[3].getStands().size()));
    }

}
