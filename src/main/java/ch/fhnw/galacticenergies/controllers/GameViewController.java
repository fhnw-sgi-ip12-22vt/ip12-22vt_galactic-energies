package ch.fhnw.galacticenergies.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.spreadsheet.Grid;

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
    @FXML
    private ImageView velocityindicator;

    @FXML
    private GridPane root;

    @FXML
    private Pane grid_c1_r1;
    private double maxBorder;


    /**
     * Initialization of the player with the current position.
     */
    public void initPlayer() {
        PlayerController.initPlayer(spaceshipimgview.getLayoutX(), spaceshipimgview.getLayoutY());
        final Bounds bounds = root.getBoundsInLocal();
        maxBorder = bounds.getMaxY();
        System.out.println(maxBorder);
        //spaceshipimgview.fitHeightProperty().bind(grid_c1_r1.heightProperty());
    }

    /**
     * Move down the player.
     */
    public void moveDown() {
        PlayerController.moveDown(maxBorder - spaceshipimgview.getFitWidth());
        spaceshipimgview.setLayoutY(PlayerController.getPlayer().getY_Pos());
        System.out.println("spaceship: "+spaceshipimgview.getLayoutY());
    }

    /**
     * Move up the player.
     */
    public void moveUp() {
        PlayerController.moveUp();
        spaceshipimgview.setLayoutY(PlayerController.getPlayer().getY_Pos());
    }
    public void speedUp() {

    }
    public void speedStop(){

    }

    public void resizeImg()
    {

    }
}
