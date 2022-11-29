package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.GameView;
import ch.fhnw.galacticenergies.models.Player;
import ch.fhnw.galacticenergies.services.WindowService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class GameViewController {
    @FXML
    private ImageView spaceshipimgview;
    @FXML
    private Image spaceshipimg;
    @FXML
    private AnchorPane spaceanchor;

    @FXML
    private AnchorPane rootAnchor;

    public void moveDown()
    {
        final Bounds bounds = rootAnchor.getBoundsInLocal();
        final boolean atBottomBorder = spaceshipimgview.getLayoutY() >= (bounds.getMaxY());

        if(atBottomBorder) return;
        spaceshipimgview.setLayoutY(spaceshipimgview.getLayoutY() + 5);
    }

    public void moveUp()
    {
        if(spaceshipimgview.getLayoutY() - 5 < 0) spaceshipimgview.setLayoutY(0);
        spaceshipimgview.setLayoutY(spaceshipimgview.getLayoutY() - 5);
        System.out.println(spaceshipimgview.getLayoutY());
    }

    public void resizeImg()
    {

    }
}
