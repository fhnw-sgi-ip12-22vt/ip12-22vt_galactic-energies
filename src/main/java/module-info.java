module ch.fhnw.galacticenergies {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens ch.fhnw.galacticenergies to javafx.fxml;
    exports ch.fhnw.galacticenergies;
    exports ch.fhnw.galacticenergies.controllers;
    exports ch.fhnw.galacticenergies.models;
    //exports ch.fhnw.galacticenergies.views;
    //opens ch.fhnw.galacticenergies.views to javafx.fxml;
}