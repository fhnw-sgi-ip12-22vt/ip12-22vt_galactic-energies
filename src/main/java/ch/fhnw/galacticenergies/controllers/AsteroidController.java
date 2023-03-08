package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.events.GameEvent;
import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Defines the action when an asteroid get destroyed
 * @version 1.0
 */
public class AsteroidController {
    /**
     * Shows a text when a asteroid gets hit.
     * @param event
     */
    public static void onAsteroidHit(GameEvent event)
    {
        inc("asteroidsKilled", +1);
        System.out.println("TEST");
    }

    /**
     * Asteroid reaches the border of the game.
     * @param event
     */
    public static void onAsteroidReachedBorder(GameEvent event)
    {

    }
}
