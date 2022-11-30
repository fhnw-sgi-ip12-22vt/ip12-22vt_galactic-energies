package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.views.ImagePaneSpeed;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;

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
    private StackPane speedAnchor;

    @FXML
    private ImageView velocityindicator;

    @FXML
    private GridPane root;

    @FXML
    private Pane grid_c1_r1;

    @FXML
    private Image speedPNG;

    @FXML
    private ImageView speedImage;
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
    public void showSpeed(int speedLevel) {
        System.out.println(speedLevel);
        ImagePaneSpeed p = new ImagePaneSpeed(getClass().getResource("/ch/fhnw/galacticenergies/images/SteuerboardLevel"+speedLevel+".png").toString(), "-fx-background-size: contain; -fx-background-repeat: no-repeat;");
        speedAnchor.getChildren().add(p);
    }
    public void showArrow(int direction) {
        System.out.println(direction);
        ImagePaneSpeed p = new ImagePaneSpeed(getClass().getResource("/ch/fhnw/galacticenergies/images/Arrow"+direction+".png").toString(), "-fx-background-size: contain; -fx-background-repeat: no-repeat;");
        speedAnchor.getChildren().add(p);
    }
    public void speedStop(){

    }

    public void resizeImg()
    {

    }


}
