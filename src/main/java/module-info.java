module com.espol {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.espol to javafx.fxml;
    exports com.espol.controllers;
    exports com.espol;
}
