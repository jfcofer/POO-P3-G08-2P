package com.espol.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.espol.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
