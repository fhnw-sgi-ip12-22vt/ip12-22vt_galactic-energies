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

    private double maxBorder;
    public void initPlayer(){
        PlayerController.initPlayer(spaceshipimgview.getLayoutX(),spaceshipimgview.getLayoutY());
        final Bounds bounds = rootAnchor.getBoundsInLocal();
        maxBorder = bounds.getMaxY();
        System.out.println("maxinit:" + maxBorder);
    }

    public void moveDown()
    {
        final Bounds bounds = rootAnchor.getBoundsInLocal();
        final boolean atBottomBorder = spaceshipimgview.getLayoutY() >= (bounds.getMaxY());

        //if(atBottomBorder) return;
        System.out.println("ship: "+"hoch: "+ spaceshipimg.getWidth());
        PlayerController.moveDown(maxBorder- spaceshipimg.getWidth());
        spaceshipimgview.setLayoutY(PlayerController.getPlayer().getY_Pos());
       // spaceshipimgview.setLayoutY(spaceshipimgview.getLayoutY() + 5);
       // System.out.println("max: "+ bounds.getMaxY());
        //System.out.println("raumschiff: "+spaceshipimgview.getLayoutY());
    }

    public void moveUp()
    {
        PlayerController.moveUp();
        spaceshipimgview.setLayoutY(PlayerController.getPlayer().getY_Pos());
        //if(spaceshipimgview.getLayoutY() - 5 < 0) spaceshipimgview.setLayoutY(0);
        //spaceshipimgview.setLayoutY(spaceshipimgview.getLayoutY() - 5);

    }
}
