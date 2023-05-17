package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.View;
import ch.fhnw.galacticenergies.components.CheckpointComponent;
import ch.fhnw.galacticenergies.data.DBConnection;
import com.almasb.fxgl.animation.Interpolators;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import static com.almasb.fxgl.dsl.FXGL.addUINode;
import static com.almasb.fxgl.dsl.FXGL.animationBuilder;
import static com.almasb.fxgl.dsl.FXGL.centerText;
import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.removeUINode;

/**
 * Defines the level of the current game
 *
 * @version 1.0
 */
public class LevelController {

    private static final int MAX_LEVEL = 10;

    private Text uiTextLevel;

    private double savedPower = 0;

    /**
     * Show the next level in the game
     */
    public void nextLevel() {
        inc("level", +1);
        savedPower = PowerController.getTotalPower();
        View.asteroidController.increaseMaxAsteroids();
        var levelNum = geti("level");

        if (levelNum > MAX_LEVEL) {
            getDialogService().showMessageBox("GAME END", getGameController()::exit);
            return;
        }

        uiTextLevel.setVisible(false);
        Text textLevel = getUIFactoryService().newText(getCheckpointText(), Color.WHITE, 22);
        textLevel.setEffect(new DropShadow(7, Color.BLACK));
        textLevel.setOpacity(0);

        centerText(textLevel);
        textLevel.setTranslateY(250);

        addUINode(textLevel);

        animationBuilder()

            .interpolator(Interpolators.SMOOTH.EASE_OUT())
            .duration(Duration.seconds(10))
            .onFinished(() -> {
                animationBuilder()
                    .duration(Duration.seconds(0.5))
                    .interpolator(Interpolators.LINEAR.EASE_IN())
                    .onFinished(() -> {
                        removeUINode(textLevel);
                        uiTextLevel.setVisible(true);
                        ViewController.setPaused(false);
                    })
                    .translate(textLevel)
                    .from(new Point2D(textLevel.getTranslateX(), textLevel.getTranslateY()))
                    .to(new Point2D(330, 540))
                    .buildAndPlay();
            })
            .fadeIn(textLevel)
            .buildAndPlay();
    }

    public Text getUiTextLevel() {
        return uiTextLevel;
    }

    public void setUiTextLevel(Text uiTextLevel) {
        this.uiTextLevel = uiTextLevel;
    }

    public String getCheckpointText() {
        String checkpointText = "";
        DBConnection c;
        Connection conn = null;
        DecimalFormat df = new DecimalFormat("#.####");

        try {
            c = new DBConnection();


            conn = c.getConnection();

            double totalPower = PowerController.getTotalPower();
            PreparedStatement stmt =
                conn.prepareStatement("SELECT * FROM energydata ORDER BY ABS(power - ? * 100) LIMIT 1");
            stmt.setInt(1, (int) totalPower);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int power = rs.getInt("power");
            double timeToUse = totalPower/power;
            String deviceName = rs.getString("devicename");
            checkpointText = deviceName + ": " + (int) (timeToUse) + "h " + df.format((timeToUse - (int) timeToUse) * 60) + "min";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (Exception ignored) {
            }
        }
        return checkpointText;
    }

    public void removeAllCheckpoints() {
        getGameWorld().removeEntities(getGameWorld().getEntitiesByComponent(CheckpointComponent.class));
    }

    public void resetToLastCheckpoint() {
        PowerController.setTotalPower(savedPower);
    }

    public double getSavedPower() {
        return savedPower;
    }
}
