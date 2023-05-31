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
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.removeUINode;

/**
 * Defines the level of the current game
 *
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
public class LevelController {
    private Text uiTextLevel;

    private double savedPower = 0;

    /**
     * Show the next level in the game
     */
    public void nextLevel() {
        inc("level", +1);
        savedPower = PowerController.getTotalPower();
        View.asteroidController.increaseMaxAsteroids();

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
            .onFinished(() -> animationBuilder()
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
                .buildAndPlay())
            .fadeIn(textLevel)
            .buildAndPlay();
    }

    public void setUiTextLevel(Text uiTextLevel) {
        this.uiTextLevel = uiTextLevel;
    }

    public String getCheckpointText() {
        StringBuilder checkpointText = new StringBuilder();
        DBConnection c;
        Connection conn = null;
        DecimalFormat df = new DecimalFormat("#.####");

        try {
            c = new DBConnection();
            conn = c.getConnection();

            double totalPower = PowerController.getTotalPower();
            PreparedStatement stmt =
                conn.prepareStatement("SELECT * FROM energydata ORDER BY ABS(power - ? * 100) LIMIT 2");
            stmt.setDouble(1, totalPower);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (checkpointText.toString() != "") {
                    checkpointText.append("\n");
                }
                int power = rs.getInt("power");
                double timeToUse = totalPower/power;
                String deviceName = rs.getString("devicename");
                checkpointText.append(deviceName).append(": ");

                int hours = (int) timeToUse;
                timeToUse -= hours;
                int minutes = (int) (timeToUse * 60);
                timeToUse -= (double) minutes / 60;
                int seconds = (int) (timeToUse * 3600);

                if (hours > 0) {
                    checkpointText.append(hours).append("h ");
                }
                if (minutes > 0) {
                    checkpointText.append(minutes).append("min ");
                }
                if (hours == 0) {
                    checkpointText.append(seconds).append("s");
                }
            }




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (Exception ignored) {
            }
        }
        return checkpointText.toString();
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

    public void setSavedPower(double savedPower) {
        this.savedPower = savedPower;
    }
}
