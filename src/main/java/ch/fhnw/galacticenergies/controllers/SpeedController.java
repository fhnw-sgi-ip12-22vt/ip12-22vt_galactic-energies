package ch.fhnw.galacticenergies.controllers;

public class SpeedController {
    private static int speed =1;

    public static int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public static void speedUp(){
        if (speed < 13) {
            speed++;
        }
    }
    public static void speedDown(){
        if (speed > 1) {
            speed--;
        }
    }
}