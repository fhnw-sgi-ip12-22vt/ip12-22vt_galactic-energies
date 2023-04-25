package ch.fhnw.galacticenergies.controllers;

import static ch.fhnw.galacticenergies.controllers.MovementControllerDEV.getDashboardControl;

public class SpeedController {
    private static int speed =1;

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int newSpeed) {

        speed = (newSpeed < 24)?(newSpeed/2) : 12;
        RocketController.getRocketControl().setSpeedMultiplier(1 + (float) (speed) / 10);
        getDashboardControl().setSpeedImage(speed);

    }
}
