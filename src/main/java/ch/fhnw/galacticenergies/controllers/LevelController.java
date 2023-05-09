package ch.fhnw.galacticenergies.controllers;

import com.almasb.fxgl.animation.Interpolators;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Defines the level of the current game
 *
 * @version 1.0
 */
public class LevelController {

    private static final int MAX_LEVEL = 10;

    private Text uiTextLevel;

    /**
     * Show the next level in the game
     */
    public void nextLevel() {
        inc("level", +1);
        var levelNum = geti("level");

        if (levelNum > MAX_LEVEL) {
            getDialogService().showMessageBox("GAME END", getGameController()::exit);
            return;
        }

        uiTextLevel.setVisible(false);

        inc("level", +1);

        Text textLevel = getUIFactoryService().newText("Level " + geti("level"), Color.WHITE, 22);
        textLevel.setEffect(new DropShadow(7, Color.BLACK));
        textLevel.setOpacity(0);

        centerText(textLevel);
        textLevel.setTranslateY(250);

        addUINode(textLevel);

        animationBuilder()
                .interpolator(Interpolators.SMOOTH.EASE_OUT())
                .duration(Duration.seconds(1.66))
                .onFinished(() -> {
                    animationBuilder()
                            .duration(Duration.seconds(1.66))
                            .interpolator(Interpolators.EXPONENTIAL.EASE_IN())
                            .onFinished(() -> {
                                removeUINode(textLevel);
                                uiTextLevel.setVisible(true);
                            })
                            .translate(textLevel)
                            .from(new Point2D(textLevel.getTranslateX(), textLevel.getTranslateY()))
                            .to(new Point2D(330, 540))
                            .build();
                })
                .fadeIn(textLevel)
                .build();
    }

    public static void playInCutscene(Runnable onFinished) {

    }

    public static void setLevel(int level) {
        spawn("rocket");
    }

    public Text getUiTextLevel() {
        return uiTextLevel;
    }

    public void setUiTextLevel(Text uiTextLevel) {
        this.uiTextLevel = uiTextLevel;
    }
}
