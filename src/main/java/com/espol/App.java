package com.espol;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

import com.espol.models.Datos;

/**
 * JavaFX App
 */
public class App extends Application {
    public static Datos datos;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        datos = Datos.leerArchivo();
        if (datos == null) {
            datos = Datos.defaultDatos();
            datos.generarArchivo();
        }
        scene = new Scene(loadFXML("inicio"), 700, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static FXMLLoader getLoader(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setScreen(String fxml, ActionEvent event) {
        Parent root = null;
        try {
            root = loadFXML(fxml);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                root = loadFXML("error");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void setScreen(Parent root, ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void setNewScreen(Parent root, ActionEvent event) {
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void closeScreen(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    };

    public static void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void mostrarAlertaInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public static Optional<ButtonType> mostrarAlertaAdvert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Advertencia");
        alert.setContentText(mensaje);
        return alert.showAndWait();
    }


}