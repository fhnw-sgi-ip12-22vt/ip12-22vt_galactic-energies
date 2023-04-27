package ch.fhnw.galacticenergies.controllers;

import static com.almasb.fxgl.dsl.FXGL.showConfirm;
import static javafx.application.Platform.exit;

public class GameOverController {

    public static void showGameOver() {
        showConfirm("Game Over! You're Score was:  " + (int) PowerController.getTotalPower() + " Restart?", yes -> {
            if (yes) {
                //TODO restart game
            } else {
                exit();
            }
        });

    }


}
