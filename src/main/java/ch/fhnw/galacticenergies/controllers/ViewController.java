package ch.fhnw.galacticenergies.controllers;

import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.ui.UIController;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;

public class ViewController implements UIController {

    private final List<Entity> lives = new ArrayList<>();

    private final GameScene gameScene;

    public ViewController (GameScene gameScene) {
        this.gameScene = gameScene;
    }

    @Override
    public void init () {

    }

    public void addLife () {
        Entity life = spawn("life");
        lives.add(life);
        life.setX(getAppWidth() - lives.size() * 30);
    }

    public void loseLife () {
        if (lives.size() == 1) {
            GameOverController.showGameOver();
        }
        Entity life = lives.get(lives.size() - 1);
        life.removeFromWorld();
        lives.remove(life);


        Viewport viewport = gameScene.getViewport();

        Node flash = new Rectangle(viewport.getWidth(), viewport.getHeight(), Color.rgb(190, 10, 15, 0.5));

        gameScene.addUINode(flash);

        runOnce(() -> gameScene.removeUINode(flash), Duration.seconds(1));
    }


}
