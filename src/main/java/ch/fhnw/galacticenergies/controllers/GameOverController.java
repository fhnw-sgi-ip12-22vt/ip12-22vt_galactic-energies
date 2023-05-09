package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.data.DBConnection;
import com.almasb.fxgl.dsl.FXGL;

import java.sql.*;
import java.time.LocalDate;

import static com.almasb.fxgl.dsl.FXGL.showConfirm;

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
