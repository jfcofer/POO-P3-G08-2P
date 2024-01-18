/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.controllers.auspiciantes;

import com.espol.App;
import com.espol.models.Auspiciante;
import com.espol.models.RedSocial;
import com.espol.models.Sectores;
import com.espol.models.TipoRedSocial;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

    public void cargarAuspiciante(String ruc) {
        Auspiciante auspiciante = null;
        for (Auspiciante a : App.datos.getAuspiciantes()) {
            if (a.getRuc().equals(ruc)) {
                auspiciante = a;
            }
        }
        CedulaTextField.setText(auspiciante.getRuc());
        CedulaTextField.setEditable(false);
        NombreTextField.setText(auspiciante.getNombre());
        PersonaResponsableTextField.setText(auspiciante.getPersonaResponsable());
        TelefonoTextField.setText(auspiciante.getTelefono());
        EmailTextField.setText(auspiciante.getEmail());
        DireccionTextField.setText(auspiciante.getDireccion());
        SitioWebTextField.setText(auspiciante.getSitioWeb());

        ArrayList<Sectores> sectores = auspiciante.getLstTipoSectores();
        for (Object sector : sectores) {
            if (sector.equals(Sectores.ALIMENTACION)) {
                AlimentacionCheckBox.setSelected(true);
            } else if (sector.equals(Sectores.EDUCACION)) {
                EducacionCheckBox.setSelected(true);
            } else if (sector.equals(Sectores.SALUD)) {
                SaludCheckBox.setSelected(true);
            } else if (sector.equals(Sectores.VESTIMENTA)) {
                VestimentaCheckBox.setSelected(true);
            }
        }

        ArrayList<RedSocial> redes = auspiciante.getRedesSociales();
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
    private void handleEditarAuspicianteButtonAction(ActionEvent event) {
        
        String cedula = CedulaTextField.getText();
        String nombre = NombreTextField.getText();
        
        String responsable = PersonaResponsableTextField.getText();
        String telefono = TelefonoTextField.getText();
        String email = EmailTextField.getText();
        String direccion = DireccionTextField.getText();
        String sitioWeb = SitioWebTextField.getText();

        if (nombre.isBlank()) {
            mostrarAlertaError("El nombre no puede quedar vacío");
        } else if (responsable.isBlank()) {
            mostrarAlertaError("El nombre de la persona responsable no puede quedar vacío");
        } else if (telefono.isBlank()) {
            mostrarAlertaError("El número de teléfono no puede quedar vacío");
        } else if (email.isBlank()) {
            mostrarAlertaError("El email no puede quedar vacío");
        } else {

            ArrayList<Auspiciante> auspiciantes = App.datos.getAuspiciantes();
            Auspiciante auspiciante = null;
            for (Auspiciante a : App.datos.getAuspiciantes()) {
                if (a.getRuc().equals(cedula)) {
                    auspiciante = a;
                }
            }
            
            auspiciantes.remove(auspiciante);
            auspiciante.setNombre(nombre);
            auspiciante.setPersonaResponsable(responsable);
            auspiciante.setTelefono(telefono);
            auspiciante.setEmail(email);
            auspiciante.setDireccion(direccion);
            auspiciante.setSitioWeb(sitioWeb);
            auspiciante.borrarSectores();
            auspiciante.borrarRedes();
            
            // Agregar los sectores cubiertos
            if (AlimentacionCheckBox.isSelected()) {
                auspiciante.agregarSectores(1);
            }
            if (EducacionCheckBox.isSelected()) {
                auspiciante.agregarSectores(2);
            }
            if (SaludCheckBox.isSelected()) {
                auspiciante.agregarSectores(3);
            }
            if (VestimentaCheckBox.isSelected()) {
                auspiciante.agregarSectores(4);
            }
            
            if (auspiciante.getLstTipoSectores().isEmpty()) {
                mostrarAlertaError("Debe seleccionar al menos un sector cubierto");
            } else {

                // Agregar las redes , leyendo los text fields de cada campo
                if (!UserTwitterTextField.getText().isEmpty()) {
                    auspiciante.agregarRedSocial("Twitter", UserTwitterTextField.getText(), EmailTwitterTextField.getText());
                }
                if (!UserTiktokTextField.getText().isEmpty()) {
                    auspiciante.agregarRedSocial("TikTok", UserTiktokTextField.getText(), EmailTiktokTextField.getText());
                }
                if (!UserFacebookTextField.getText().isEmpty()) {
                    auspiciante.agregarRedSocial("Facebook", UserFacebookTextField.getText(), EmailFacebookTextField.getText());
                }
                if (!UserInstagramTextField.getText().isEmpty()) {
                    auspiciante.agregarRedSocial("Instagram", UserInstagramTextField.getText(), EmailInstagramTextField.getText());
                }
                if (!UserYoutubeTextField.getText().isEmpty()) {
                    auspiciante.agregarRedSocial("Youtube", UserYoutubeTextField.getText(), EmailYoutubeTextField.getText());
                }
                if (!UserLinkedinTextField.getText().isEmpty()) {
                    auspiciante.agregarRedSocial("LinkedIn", UserLinkedinTextField.getText(), EmailLinkedinTextField.getText());
                }
                if (!UserPinterestTextField.getText().isEmpty()) {
                    auspiciante.agregarRedSocial("Pinterest", UserPinterestTextField.getText(), EmailPinterestTextField.getText());
                }
                auspiciantes.add(auspiciante);
                
                App.datos.setAuspiciantes(auspiciantes);
                mostrarAlertaInfo("La edicion se ha realizado correctamente");
                FXMLLoader loader = App.getLoader("auspiciantes/tabla");
                                        Parent root = null;
                                        try {
                                            root = loader.load();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                        TablaController controller = loader.getController();
                                        controller.initialize();
                                        App.setScreen(root, event);
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
    private void mostrarAlertaInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        App.setScreen("auspiciantes/tabla", event);
    }

}
