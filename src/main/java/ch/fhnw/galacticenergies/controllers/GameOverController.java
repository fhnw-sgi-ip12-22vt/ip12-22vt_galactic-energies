package ch.fhnw.galacticenergies.controllers;

import static com.almasb.fxgl.dsl.FXGL.showConfirm;
import static javafx.application.Platform.exit;

import ch.fhnw.galacticenergies.View;
import ch.fhnw.galacticenergies.data.DBConnection;
import com.almasb.fxgl.app.GameApplication;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.scene.*;

import java.sql.*;
import java.time.LocalDate;

public class GameOverController {

    public static void showGameOver(){



        showConfirm("Game Over! You're Score was:  "+ (int)PowerController.getTotalPower()+" Restart?", yes -> {
            writeHighscore();
            if (yes) {
                FXGL.getGameController().startNewGame();
            } else {
                FXGL.getGameController().gotoMainMenu();
            }
        });

    }

    public static void writeHighscore(){
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
