package ch.fhnw.galacticenergies.controllers;

import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.UIController;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;

public class ViewController implements UIController {

    private List<Entity> lives = new ArrayList<>();

    private GameScene gameScene;

    public ViewController(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    @Override
    public void init() {

    }

    public void addLife() {
        Entity life = spawn("life");
        lives.add(life);
        life.setX(getAppWidth() - lives.size() * 30);
    }

    public void loseLife() {
        Entity life = lives.get(lives.size() - 1);

        lives.remove(life);

//        Animation animation = getAnimationLoseLife(life);
//        animation.setOnFinished(e -> gameScene.removeUINode(life));
//        animation.play();

        Viewport viewport = gameScene.getViewport();

        Node flash = new Rectangle(viewport.getWidth(), viewport.getHeight(), Color.rgb(190, 10, 15, 0.5));

        gameScene.addUINode(flash);

        runOnce(() -> gameScene.removeUINode(flash), Duration.seconds(1));
    }

//    private Animation getAnimationLoseLife(Entity life) {
//        life.getTransformComponent().set(64);
//        e.setFitHeight(64);
//
//        Viewport viewport = gameScene.getViewport();
//
//        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.66), texture);
//        tt.setToX(viewport.getWidth() / 2 - texture.getFitWidth() / 2);
//        tt.setToY(viewport.getHeight() / 2 - texture.getFitHeight() / 2);
//
//        ScaleTransition st = new ScaleTransition(Duration.seconds(0.66), texture);
//        st.setToX(0);
//        st.setToY(0);
//
//        return new SequentialTransition(tt, st);
//    }
}
