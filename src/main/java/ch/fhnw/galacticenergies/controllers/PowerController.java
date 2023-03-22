package ch.fhnw.galacticenergies.controllers;


import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;

/**
 * Controls the current and totalPower that is being produced
 *
 * @version 1.0
 */

public class PowerController {

    private static double currentPower;
    private static double totalPower = 0;
    private static int lastTimestamp = 0;
    private static Text text;

    /**
     * Processes the ArrayLists that were created in PowerInput
     * Calculates the totalPower that was generated and stores it in totalPower
     */
    public static void calcPower() {
        totalPower = totalPower + (currentPower / 3.6);
        System.out.println(totalPower);
        text.setText("Current: " + (int) PowerController.getCurrentPower()+ "W per Hour Total: " + (int) PowerController.getTotalPower() + " Wh");

    }

    public static void initText() {
        text = new Text("Current: " + 0 + "W per Hour Total: " + 0 + " Wh");
        text.setTranslateX(10);
        text.setTranslateY(30);
        text.setFont(Font.font(30));
        getGameScene().addUINode(text);
    }

    /**
     * Getter for currentPower
     *
     * @return currentPower
     */
    public static double getCurrentPower() {
        return currentPower;
    }

    /**
     * Set the currentPower
     *
     * @param currentPower
     */
    public static void setCurrentPower(double currentPower) {
        PowerController.currentPower = currentPower;
        SpeedController.setSpeed((int)currentPower);
        calcPower();
    }

    /**
     * @return return the total value of the power.
     */
    public static double getTotalPower() {

        return totalPower;
    }


}

