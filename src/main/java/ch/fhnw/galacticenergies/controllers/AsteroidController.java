package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.events.GameEvent;
import static com.almasb.fxgl.dsl.FXGL.*;
public class AsteroidController {

    public static void onAsteroidHit(GameEvent event)
    {
        inc("asteroidsKilled", +1);
        System.out.println("TEST");
    }

    public static void onAsteroidReachedBorder(GameEvent event)
    {

    }
}
