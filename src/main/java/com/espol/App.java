package com.espol;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
  
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
    }


}