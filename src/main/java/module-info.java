module com.espol {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.espol to javafx.fxml;
    exports com.espol;
    exports com.espol.controllers;
    exports com.espol.controllers.auspiciantes;
    exports com.espol.controllers.emprendedores;
    exports com.espol.controllers.ferias;
    exports com.espol.controllers.stands;
}
