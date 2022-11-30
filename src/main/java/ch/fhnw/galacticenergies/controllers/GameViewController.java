package ch.fhnw.galacticenergies.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Controls the functionality of the view.
 *
 * @version 1.0
 */
public class GameViewController {
    @FXML
    private ImageView spaceshipimgview;
    @FXML
    private Image spaceshipimg;
    @FXML
    private AnchorPane rootAnchor;

    private double maxBorder;

    /**
     * Initialization of the player with the current position.
     */
    public void initPlayer() {
        PlayerController.initPlayer(spaceshipimgview.getLayoutX(), spaceshipimgview.getLayoutY());
        final Bounds bounds = rootAnchor.getBoundsInLocal();
        maxBorder = bounds.getMaxY();
    }

    /**
     * Move down the player.
     */
    public void moveDown() {
        PlayerController.moveDown(maxBorder - spaceshipimg.getWidth());
        spaceshipimgview.setLayoutY(PlayerController.getPlayer().getY_Pos());
    }

    /**
     * Move up the player.
     */
    public void moveUp() {
        PlayerController.moveUp();
        spaceshipimgview.setLayoutY(PlayerController.getPlayer().getY_Pos());

    }

    public void resizeImg()
    {

    }
}
