package com.espol.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.espol.App;

public class SecondaryController {

    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}