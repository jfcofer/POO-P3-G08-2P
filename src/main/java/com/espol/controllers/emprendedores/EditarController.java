/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.controllers.emprendedores;

import com.espol.App;
import com.espol.models.Emprendedor;
import com.espol.models.RedSocial;
import com.espol.models.TipoRedSocial;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 *
 * @author Cris
 */
public class EditarController {

    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnEditar;
    @FXML
    private TextField CedulaTextField;
    @FXML
    private TextField NombreTextField;
    @FXML
    private TextField TelefonoTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField DireccionTextField;
    @FXML
    private TextField SitioWebTextField;
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
    private TextField NombreResponsableTextField;
    @FXML
    private TextField DescripcionServiciosTextField;

    public void cargarEmprendedor(String ruc) {
        Emprendedor emprendedor = null;
        for (Emprendedor a : App.datos.getEmprendedores()) {
            if (a.getRuc().equals(ruc)) {
                emprendedor = a;
            }
        }
        CedulaTextField.setText(emprendedor.getRuc());
        CedulaTextField.setEditable(false);
        NombreTextField.setText(emprendedor.getNombre());
        NombreResponsableTextField.setText(emprendedor.getPersonaResponsable());
        TelefonoTextField.setText(emprendedor.getTelefono());
        EmailTextField.setText(emprendedor.getEmail());
        DireccionTextField.setText(emprendedor.getDireccion());
        SitioWebTextField.setText(emprendedor.getSitioWeb());
        DescripcionServiciosTextField.setText(emprendedor.getDescripcionServicios());

        ArrayList<RedSocial> redes = emprendedor.getRedesSociales();
        for (Object r : redes) {
            RedSocial red = (RedSocial) r;
            if (red.getTipo().equals(TipoRedSocial.Twitter)) {
                UserTwitterTextField.setText(red.getUsuario());
                EmailTwitterTextField.setText(red.getEnlace());
            } else if (red.getTipo().equals(TipoRedSocial.Facebook)) {
                UserFacebookTextField.setText(red.getUsuario());
                EmailFacebookTextField.setText(red.getEnlace());
            } else if (red.getTipo().equals(TipoRedSocial.Instagram)) {
                UserInstagramTextField.setText(red.getUsuario());
                EmailInstagramTextField.setText(red.getEnlace());
            } else if (red.getTipo().equals(TipoRedSocial.LinkedIn)) {
                UserLinkedinTextField.setText(red.getUsuario());
                EmailLinkedinTextField.setText(red.getEnlace());
            } else if (red.getTipo().equals(TipoRedSocial.Pinterest)) {
                UserPinterestTextField.setText(red.getUsuario());
                EmailPinterestTextField.setText(red.getEnlace());
            } else if (red.getTipo().equals(TipoRedSocial.TikTok)) {
                UserTiktokTextField.setText(red.getUsuario());
                EmailTiktokTextField.setText(red.getEnlace());
            } else if (red.getTipo().equals(TipoRedSocial.Youtube)) {
                UserYoutubeTextField.setText(red.getUsuario());
                EmailYoutubeTextField.setText(red.getEnlace());
            }
        }
    }

    @FXML
    private void handleEditarEmprendedorButtonAction(ActionEvent event) {

        String cedula = CedulaTextField.getText();
        String nombre = NombreTextField.getText();

        String responsable = NombreResponsableTextField.getText();
        String telefono = TelefonoTextField.getText();
        String email = EmailTextField.getText();
        String direccion = DireccionTextField.getText();
        String sitioWeb = SitioWebTextField.getText();

        if (nombre.isBlank()) {
            App.mostrarAlertaError("El nombre no puede quedar vacío");
        } else if (responsable.isBlank()) {
            App.mostrarAlertaError("El nombre de la persona responsable no puede quedar vacío");
        } else if (telefono.isBlank()) {
            App.mostrarAlertaError("El número de teléfono no puede quedar vacío");
        } else if (email.isBlank()) {
            App.mostrarAlertaError("El email no puede quedar vacío");
        } else {

            ArrayList<Emprendedor> emprendedores = App.datos.getEmprendedores();
            Emprendedor emprendedor = null;
            for (Emprendedor a : App.datos.getEmprendedores()) {
                if (a.getRuc().equals(cedula)) {
                    emprendedor = a;
                }
            }

            emprendedores.remove(emprendedor);
            emprendedor.setNombre(nombre);
            emprendedor.setPersonaResponsable(responsable);
            emprendedor.setTelefono(telefono);
            emprendedor.setEmail(email);
            emprendedor.setDireccion(direccion);
            emprendedor.setSitioWeb(sitioWeb);
            emprendedor.borrarRedes();

            // Agregar las redes , leyendo los text fields de cada campo
            if (!UserTwitterTextField.getText().isEmpty()) {
                emprendedor.agregarRedSocial("Twitter", UserTwitterTextField.getText(),
                        EmailTwitterTextField.getText());
            }
            if (!UserTiktokTextField.getText().isEmpty()) {
                emprendedor.agregarRedSocial("TikTok", UserTiktokTextField.getText(), EmailTiktokTextField.getText());
            }
            if (!UserFacebookTextField.getText().isEmpty()) {
                emprendedor.agregarRedSocial("Facebook", UserFacebookTextField.getText(),
                        EmailFacebookTextField.getText());
            }
            if (!UserInstagramTextField.getText().isEmpty()) {
                emprendedor.agregarRedSocial("Instagram", UserInstagramTextField.getText(),
                        EmailInstagramTextField.getText());
            }
            if (!UserYoutubeTextField.getText().isEmpty()) {
                emprendedor.agregarRedSocial("Youtube", UserYoutubeTextField.getText(),
                        EmailYoutubeTextField.getText());
            }
            if (!UserLinkedinTextField.getText().isEmpty()) {
                emprendedor.agregarRedSocial("LinkedIn", UserLinkedinTextField.getText(),
                        EmailLinkedinTextField.getText());
            }
            if (!UserPinterestTextField.getText().isEmpty()) {
                emprendedor.agregarRedSocial("Pinterest", UserPinterestTextField.getText(),
                        EmailPinterestTextField.getText());
            }
            emprendedores.add(emprendedor);

            App.datos.setEmprendedores(emprendedores);
            App.datos.generarArchivo();
            App.mostrarAlertaInfo("La edicion se ha realizado correctamente");
            App.setScreen("emprendedores/tabla", event);
        }
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        App.setScreen("emprendedores/tabla", event);
    }

}
