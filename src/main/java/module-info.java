module ch.fhnw.galacticenergies {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens ch.fhnw.galacticenergies to javafx.fxml;
    exports ch.fhnw.galacticenergies;
}