package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.models.Player;

public class PlayerController {

    public static Player p = new Player();

    public static void joystickDown()
    {
        p.moveDown();
    }

    public static void interactionWithAsteroid()
    {
        p.removeLive();
        p.resetPosition();
    }

}
