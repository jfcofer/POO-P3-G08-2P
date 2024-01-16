/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.controllers.auspiciantes;

import com.espol.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import com.espol.models.*;
import com.espol.App;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;

/**
 *
 * @author Cristhian
 */
public class RegistrarController {

    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnRegistrar;
    @FXML
    private TextField CedulaTextField;
    @FXML
    private TextField NombreTextField;
    @FXML
    private TextField PersonaResponsableTextField;
    @FXML
    private TextField TelefonoTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField DireccionTextField;
    @FXML
    private TextField SitioWebTextField;
    @FXML
    private CheckBox AlimentacionCheckBox;
    @FXML
    private CheckBox EducacionCheckBox;
    @FXML
    private CheckBox SaludCheckBox;
    @FXML
    private CheckBox VestimentaCheckBox;
    @FXML
    private TabPane RedesSocialesTabPane;
    @FXML
    private TextField UserTwitterTextField;
    @FXML
    private TextField EmailTwitterTextField;
    @FXML
    private TextField UserFacebookTextField;
    @FXML
    private TextField EmailFacebookTextField;
    @FXML
    private TextField UserInstagramTextField;
    @FXML
    private TextField EmailInstagramTextField;
    @FXML
    private TextField UserYoutubeTextField;
    @FXML
    private TextField EmailYoutubeTextField;
    @FXML
    private TextField UserLinkedinTextField;
    @FXML
    private TextField EmailLinkedinTextField;
    @FXML
    private TextField UserPinterestTextField;
    @FXML
    private TextField EmailPinterestTextField;
    @FXML
    private TextField UserTiktokTextField;
    @FXML
    private TextField EmailTiktokTextField;

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        App.setScreen("auspiciantes/tabla", event);
    }

    @FXML
    private void handleRegistrarAuspicianteButtonAction(ActionEvent event) {

        String cedula = CedulaTextField.getText();
        String nombre = NombreTextField.getText();
        String responsable = PersonaResponsableTextField.getText();
        String telefono = TelefonoTextField.getText();
        String email = EmailTextField.getText();
        String direccion = DireccionTextField.getText();
        String sitioWeb = SitioWebTextField.getText();
        Boolean condicion = false;

        // Verificar que no exista otro auspiciante con el mismo número de cédula o RUC
        for (Auspiciante auspiciante : App.datos.getListaAuspiciantes()) {
            if (auspiciante.getRuc().equals(cedula)) {
                condicion = true;
                return;
            }
        }
        
        if (condicion == true || cedula.isBlank()) {
            mostrarAlertaError("La cedula no puede quedar vacía o ya existe un auspiciante registrado con esa cédula o ruc");
        } 
        if(nombre.isBlank()) {
            mostrarAlertaError("El nombre no puede quedar vacía");
        }
        else if(responsable.isBlank()){
            mostrarAlertaError("El nombre de la persona responsable no puede quedar vacío");
        }
        else if(telefono.isBlank()){
            mostrarAlertaError("El número de teléfono no puede quedar vacío");
        }
        else if(email.isBlank()){
            mostrarAlertaError("El email no puede quedar vacío");
        }
        else{
            // Crear un nuevo objeto Auspiciante y agregarlo a la lista
            Auspiciante nuevoAuspiciante = new Auspiciante(cedula, nombre, telefono, email, direccion, sitioWeb, responsable);

            // Agregar los sectores cubiertos
            if (AlimentacionCheckBox.isSelected()) {
                nuevoAuspiciante.agregarSectores(1);
            }
            if (EducacionCheckBox.isSelected()) {
                nuevoAuspiciante.agregarSectores(2);
            }
            if (SaludCheckBox.isSelected()) {
                nuevoAuspiciante.agregarSectores(3);
            }
            if (VestimentaCheckBox.isSelected()) {
                nuevoAuspiciante.agregarSectores(4);
            }
            
            if(nuevoAuspiciante.getLstTipoSectores().isEmpty()){
                mostrarAlertaError("Debe seleccionar al menos un sector cubierto");
            }
            else {

            // Agregar las redes , leyendo los text fields de cada campo
            if (!UserTwitterTextField.getText().isEmpty()) {
                nuevoAuspiciante.agregarRedSocial("Twitter", UserTwitterTextField.getText(), EmailTwitterTextField.getText());
            }
            if (!UserTiktokTextField.getText().isEmpty()) {
                nuevoAuspiciante.agregarRedSocial("TikTok", UserTiktokTextField.getText(), EmailTiktokTextField.getText());
            }
            if (!UserFacebookTextField.getText().isEmpty()) {
                nuevoAuspiciante.agregarRedSocial("Facebook", UserFacebookTextField.getText(), EmailFacebookTextField.getText());
            }
            if (!UserInstagramTextField.getText().isEmpty()) {
                nuevoAuspiciante.agregarRedSocial("Instagram", UserInstagramTextField.getText(), EmailInstagramTextField.getText());
            }
            if (!UserYoutubeTextField.getText().isEmpty()) {
                nuevoAuspiciante.agregarRedSocial("Youtube", UserYoutubeTextField.getText(), EmailYoutubeTextField.getText());
            }
            if (!UserLinkedinTextField.getText().isEmpty()) {
                nuevoAuspiciante.agregarRedSocial("LinkedIn", UserLinkedinTextField.getText(), EmailLinkedinTextField.getText());
            }
            if (!UserPinterestTextField.getText().isEmpty()) {
                nuevoAuspiciante.agregarRedSocial("Pinterest", UserPinterestTextField.getText(), EmailPinterestTextField.getText());
            }
            App.datos.getListaAuspiciantes().add(nuevoAuspiciante);
            mostrarAlertaInfo("El registro se ha realizado correctamente");
            App.setScreen("auspiciantes/tabla", event);
            }
        }

    }

    @FXML
    private void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void mostrarAlertaInfo(String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
