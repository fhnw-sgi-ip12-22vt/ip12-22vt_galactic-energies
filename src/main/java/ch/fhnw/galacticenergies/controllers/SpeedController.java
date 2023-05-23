package ch.fhnw.galacticenergies.controllers;

import static ch.fhnw.galacticenergies.controllers.MovementController.getDashboardControl;

/**
 * Defines the actions of the SpeedController
 * @version 1.0
 */
public class SpeedController {
    private static int speed = 1;

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int newSpeed) {

        speed = (newSpeed < 24) ? (newSpeed / 2) : 12;
        getDashboardControl().setSpeedImage(speed);

    }
}

