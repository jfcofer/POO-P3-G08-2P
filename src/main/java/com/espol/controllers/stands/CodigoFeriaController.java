package com.espol.controllers.stands;

import java.util.ArrayList;

import com.espol.App;
import com.espol.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CodigoFeriaController {

    @FXML
    private Button btnRegresar;

    @FXML
    private ComboBox<Feria> feriasCmbBox;

    @FXML
    private Button ingresarButton;

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

    @FXML
    public void handleIngresarButtonAction(ActionEvent event){
        TablaController controller = App.getLoader("stands/tabla").getController();
        
    }

    @FXML
    public void initialize(){
        Callback<ListView<Feria>, ListCell<Feria>> factory = lv -> new ListCell<Feria>(){
            @Override
            protected void updateItem(Feria feria, boolean empty){
                super.updateItem(feria,empty);
                setText(empty?"":feria.getNombre());
            }
        }
        // ArrayList<Feria> ferias = App.datos.getFerias();
        // ObservableList<Feria> listaObservable = new ObservableList(ferias);
        feriasCmbBox.setCellFactory(factory);
        feriasCmbBox.setButtonCell(factory.call(null));



    }

}
