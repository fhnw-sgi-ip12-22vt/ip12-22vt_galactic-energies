package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.View;
import ch.fhnw.galacticenergies.data.DBConnection;
import com.almasb.fxgl.animation.Interpolators;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

import static com.almasb.fxgl.dsl.FXGL.*;


public class GameOverController {

    private static DecimalFormat df = new DecimalFormat("#.####");

    public static void showGameOver() {
        ViewController.setPaused(true);
        View.asteroidController.removeAllAsteroids();
        View.levelController.removeAllCheckpoints();

        String score = df.format(PowerController.getTotalPower());
        Text textLevel = getUIFactoryService().newText("Game Over \n" + "You're Score was: " + score + " Wh", Color.WHITE, 22);
        textLevel.setEffect(new DropShadow(7, Color.BLACK));
        textLevel.setOpacity(0);
        centerText(textLevel);
        textLevel.setTranslateY(250);
        addUINode(textLevel);
        animationBuilder()
                .interpolator(Interpolators.SMOOTH.EASE_OUT())
                .duration(Duration.seconds(3))
                .onFinished(() -> {
                    animationBuilder()
                            .duration(Duration.seconds(3))
                            .interpolator(Interpolators.EXPONENTIAL.EASE_IN())
                            .onFinished(() -> {
                                removeUINode(textLevel);
                                writeHighscore();
                                PowerController.setTotalPower(0);
                                getGameController().gotoMainMenu();
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

    public static void writeHighscore () {
        DBConnection c = new DBConnection();
        Connection conn = c.getConnection();

        try {
            Statement statement = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO totalpower(producedpower, date) VALUES (?,?)");
            stmt.setDouble(1, PowerController.getTotalPower());
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM totalpower ORDER BY producedpower LIMIT 5");

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }


}
