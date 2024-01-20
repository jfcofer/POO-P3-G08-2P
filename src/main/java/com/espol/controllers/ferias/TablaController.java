package com.espol.controllers.ferias;

import java.time.LocalDate;
import java.util.ArrayList;

import com.espol.App;
import com.espol.models.*;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TablaController {

    @FXML
    private Button nuevaFeriaButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Feria> tabla;

    @FXML
    private TableColumn<Feria, String> codigoColumn;

    @FXML
    private TableColumn<Feria, String> nombreColumn;
    
    @FXML
    private TableColumn<Feria, LocalDate> fechaInicioColumn;
    
    @FXML
    private TableColumn<Feria, String> lugarColumn;
    
    @FXML
    private TableColumn<Feria, Integer> auspiciantesColumn;

    @FXML
    private TableColumn<Feria, Integer> emprendedoresColumn;

    @FXML
    private TableColumn<Feria, String> opcionesColumn;
    
    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        App.setScreen("inicio", event);
    }

    @FXML
    public void handleNuevaFeriaButtonAction(ActionEvent event) {
        App.setScreen("ferias/registrar", event);
    }
    
    @FXML
    public void initialize() {
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        lugarColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        auspiciantesColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAuspiciantes().size()).asObject() );
        emprendedoresColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEmprendedores.size()).asObject());
        opcionesColumn.setCellFactory(tableCol -> new TableCell<Feria,String>(){
            final Button detallesButtonn = new Button("Ver")
            @Override
            protected void updateItem(String string, boolean empty){
                super.updateItem(string, empty);

            }
        });


    }
}
