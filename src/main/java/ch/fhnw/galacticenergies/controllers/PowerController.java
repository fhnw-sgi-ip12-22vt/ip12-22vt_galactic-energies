package ch.fhnw.galacticenergies.controllers;


import ch.fhnw.galacticenergies.View;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

import static com.almasb.fxgl.dsl.FXGL.getGameScene;

/**
 * Controls the current and totalPower that is being produced
 *
 * @version 1.0
 */

public class PowerController {

    private static double currentPower;
    private static double totalPower = 0;
    private static Text text;

    private static final DecimalFormat df = new DecimalFormat("#.####");

    /**
     * Processes the ArrayLists that were created in PowerInput
     * Calculates the totalPower that was generated and stores it in totalPower
     */
    public static void calcPower() {
        if (ViewController.isPaused()) {
            return;
        }
        totalPower = totalPower + (currentPower / 3600);
        View.checkpointController.checkCreation(totalPower);
        text.setText("Current: " + (int) currentPower + "W per Hour Total: " + df.format(totalPower) + " Wh");
    }

    public static void initText() {
        text = new Text("Current: " + 0 + "W per Hour Total: " + 0.0000 + " Wh");
        text.setTranslateX(10);
        text.setTranslateY(30);
        text.setFont(Font.font(30));
        text.setFill(Color.WHITE);
        getGameScene().addUINode(text);
    }

    /**
     * Set the currentPower
     *
     * @param currentPower
     */
    public static void setCurrentPower(double currentPower) {
        PowerController.currentPower = currentPower;
        SpeedController.setSpeed((int) currentPower);
        calcPower();
    }

    /**
     * @return return the total value of the power.
     */
    public static double getTotalPower() {
        return totalPower;
    }

    public static void setTotalPower(double totalPowerNew) {
        totalPower = totalPowerNew;
    }
}

