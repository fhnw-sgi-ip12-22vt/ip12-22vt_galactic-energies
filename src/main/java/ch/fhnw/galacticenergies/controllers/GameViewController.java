package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.models.Player;
import ch.fhnw.galacticenergies.services.WindowService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
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
    public void updateSpaceShipY()
    {
        System.out.println("s " + spaceshipimg.getHeight());
        Player p = PlayerController.p;
        spaceshipimgview.yProperty().bind(new SimpleIntegerProperty(p.getPos_y()));
    }
}
