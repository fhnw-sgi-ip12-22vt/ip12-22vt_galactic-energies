module ch.fhnw.galacticenergies {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    exports ch.fhnw.galacticenergies;
    exports ch.fhnw.galacticenergies.controllers;
    exports ch.fhnw.galacticenergies.models;
    opens ch.fhnw.galacticenergies.controllers to javafx.fxml;
    opens ch.fhnw.galacticenergies to javafx.fxml, javafx.graphics;
    opens ch.fhnw.galacticenergies.views to javafx.graphics;
}