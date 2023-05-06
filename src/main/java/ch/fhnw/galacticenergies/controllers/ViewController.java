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

import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.runOnce;
import static com.almasb.fxgl.dsl.FXGL.spawn;

/**
 * Defines all visuals of the game
 *
 * @version 1.0
 */

public class ViewController implements UIController {

    private final List<Entity> lives = new ArrayList<>();

    private final GameScene gameScene;

    private boolean paused = false;
    public ViewController(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    @Override
    public void init() {
    }

    /**
     * Adds a life to the life-dashboard
     */
    public void addLife() {
        Entity life = spawn("life");
        lives.add(life);
        life.setX(getAppWidth() - lives.size() * 30);
    }

    /**
     * Removes a life on the life-dashboard
     */
    public void loseLife() {

        Entity life = lives.get(lives.size() - 1);
        life.removeFromWorld();
        lives.remove(life);
        if (lives.size() == 0) {
            //TODO
            //Gameover screen einfügen
        }

        Viewport viewport = gameScene.getViewport();

        Node flash = new Rectangle(viewport.getWidth(), viewport.getHeight(), Color.rgb(190, 10, 15, 0.5));

        gameScene.addUINode(flash);

        runOnce(() -> gameScene.removeUINode(flash), Duration.seconds(1));
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
