module com.espol {
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires transitive javafx.controls;

    opens com.espol to javafx.fxml;
    opens com.espol.models to javafx.base;
    opens com.espol.controllers to javafx.fxml;
    opens com.espol.controllers.auspiciantes to javafx.fxml;
    opens com.espol.controllers.emprendedores to javafx.fxml;
    opens com.espol.controllers.ferias to javafx.fxml;
    opens com.espol.controllers.stands to javafx.fxml;

    exports com.espol;
    exports com.espol.controllers;
    exports com.espol.controllers.auspiciantes;
    exports com.espol.controllers.emprendedores;
    exports com.espol.controllers.ferias;
    exports com.espol.controllers.stands;
}
