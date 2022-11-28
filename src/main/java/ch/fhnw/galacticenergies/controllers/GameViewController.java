package ch.fhnw.galacticenergies.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameViewController {

    @FXML
    private ImageView spaceshipimg;

    @FXML
    public void updateSpaceShipY(int y)
    {
        spaceshipimg.setY(y);
    }
}
