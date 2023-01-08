package ch.fhnw.galacticenergies.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BackgroundSize;

public class ImagePaneSpeed extends Pane {
    public ImagePaneSpeed(String imageLoc, String style) {
        this(new SimpleStringProperty(imageLoc), new SimpleStringProperty(style));
    }

    ImagePaneSpeed(StringProperty imageLocProperty, StringProperty styleProperty) {
        styleProperty().bind(
                new SimpleStringProperty("-fx-background-image: url(\"")
                        .concat(imageLocProperty)
                        .concat(new SimpleStringProperty("\");"))
                        .concat(styleProperty)
        );
    }
}
