module ch.fhnw.galacticenergies {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires com.pi4j;
    requires com.pi4j.plugin.pigpio;
    requires com.pi4j.plugin.raspberrypi;
    requires com.pi4j.plugin.mock;
    requires com.pi4j.library.pigpio;
    uses com.pi4j.extension.Extension;
    uses com.pi4j.provider.Provider;
    //for LCD Display
    requires com.pi4j.plugin.linuxfs;
    requires java.logging;

    exports ch.fhnw.galacticenergies;
    exports ch.fhnw.galacticenergies.controllers;
    exports ch.fhnw.galacticenergies.models;
    opens ch.fhnw.galacticenergies.controllers to javafx.fxml;
    opens ch.fhnw.galacticenergies to javafx.fxml, javafx.graphics;
    opens ch.fhnw.galacticenergies.views to javafx.graphics;
}