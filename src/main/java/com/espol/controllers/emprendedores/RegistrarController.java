/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.controllers.emprendedores;

import com.espol.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.espol.models.Emprendedor;

/**
 * FXML Controller class
 *
 * @author William
 */
public class RegistrarController {

    @FXML
    private TextField CedulaTextField;

    @FXML
    private TextField DescripcionServiciosTextField;

    @FXML
    private TextField DireccionTextField;

    @FXML
    private TextField EmailFacebookTextField;
    
    @FXML
    private TextField EmailTiktokTextField;

    @FXML
    private TextField EmailInstagramTextField;

    @FXML
    private TextField EmailLinkedinTextField;

    @FXML
    private TextField EmailPinterestTextField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField EmailTwitterTextField;

    @FXML
    private TextField EmailYoutubeTextField;

    @FXML
    private TextField NombreResponsableTextField;

    @FXML
    private TextField NombreTextField;

    @FXML
    private TextField SitioWebTextField;

    @FXML
    private TextField TelefonoTextField;

    @FXML
    private TextField UserFacebookTextField;

    @FXML
    private TextField UserInstagramTextField;

    @FXML
    private TextField UserLinkedinTextField;

    @FXML
    private TextField UserPinterestTextField;

    @FXML
    private TextField UserTiktokTextField;

    @FXML
    private TextField UserTwitterTextField;

    @FXML
    private TextField UserYoutubeTextField;

    @FXML
    private Button btnRegresar;
    
    @FXML
    private Button btnRegistrar;

    /**
     * Initializes the controller class.
     */
    public void initialize() {

    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        App.setScreen("emprendedores/tabla", event);
    }
    
    @FXML
    void handleRegistrarEmprendedorButtonAction(ActionEvent event) {
        
        String cedula = CedulaTextField.getText();
        String nombre = NombreTextField.getText();
        String responsable = NombreResponsableTextField.getText();
        String telefono = TelefonoTextField.getText();
        String email = EmailTextField.getText();
        String direccion = DireccionTextField.getText();
        String sitioWeb = SitioWebTextField.getText();
        String DescripcionServicio = DescripcionServiciosTextField.getText();
        Boolean condicion = false;

        // Verificar que no exista otro emprendedor con el mismo número de cédula o RUC
        for (Emprendedor emprendedor : App.datos.getEmprendedores()) {
            if (emprendedor.getRuc().equals(cedula)) {
                condicion = true;
                return;
            }
        }
        
        if (condicion == true || cedula.isBlank()) {
            App.mostrarAlertaError("La cedula no puede quedar vacía o ya existe un emprendedor registrado con esa cédula o ruc");
        } 
        if(nombre.isBlank()) {
            App.mostrarAlertaError("El nombre no puede quedar vacía");
        }
        else if(responsable.isBlank()){
            App.mostrarAlertaError("El nombre de la persona responsable no puede quedar vacío");
        }
        else if(telefono.isBlank()){
            App.mostrarAlertaError("El número de teléfono no puede quedar vacío");
        }
        else if(email.isBlank()){
            App.mostrarAlertaError("El email no puede quedar vacío");
        }
        else if(DescripcionServicio.isBlank()){
            App.mostrarAlertaError("La descripcion del servicio no puede quedar vacío");
        }
        else{
            // Crear un nuevo objeto Emprendedor y agregarlo a la lista
            Emprendedor nuevoEmprendedor = new Emprendedor(cedula, nombre, telefono, email, direccion, sitioWeb, responsable, DescripcionServicio);

            // Agregar las redes , leyendo los text fields de cada campo
            if (!UserTwitterTextField.getText().isEmpty()) {
                nuevoEmprendedor.agregarRedSocial("Twitter", UserTwitterTextField.getText(), EmailTwitterTextField.getText());
            }
            if (!UserTiktokTextField.getText().isEmpty()) {
                nuevoEmprendedor.agregarRedSocial("TikTok", UserTiktokTextField.getText(), EmailTiktokTextField.getText());
            }
            if (!UserFacebookTextField.getText().isEmpty()) {
                nuevoEmprendedor.agregarRedSocial("Facebook", UserFacebookTextField.getText(), EmailFacebookTextField.getText());
            }
            if (!UserInstagramTextField.getText().isEmpty()) {
                nuevoEmprendedor.agregarRedSocial("Instagram", UserInstagramTextField.getText(), EmailInstagramTextField.getText());
            }
            if (!UserYoutubeTextField.getText().isEmpty()) {
                nuevoEmprendedor.agregarRedSocial("Youtube", UserYoutubeTextField.getText(), EmailYoutubeTextField.getText());
            }
            if (!UserLinkedinTextField.getText().isEmpty()) {
                nuevoEmprendedor.agregarRedSocial("LinkedIn", UserLinkedinTextField.getText(), EmailLinkedinTextField.getText());
            }
            if (!UserPinterestTextField.getText().isEmpty()) {
                nuevoEmprendedor.agregarRedSocial("Pinterest", UserPinterestTextField.getText(), EmailPinterestTextField.getText());
            }
            App.datos.getEmprendedores().add(nuevoEmprendedor);
            App.mostrarAlertaInfo("El registro se ha realizado correctamente");
            App.setScreen("emprendedores/tabla", event);
            
        }
        

    }
   

}
