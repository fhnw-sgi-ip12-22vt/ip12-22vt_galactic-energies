package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.enums.GalacticEnergiesType;
import ch.fhnw.galacticenergies.events.GameEvent;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class AsteroidController {

    private final int maxAsteroids = 5;
    private int asteroidAmount = 0;

    public static void onAsteroidHit(GameEvent event) {
        System.out.println("HIT");
        inc("asteroidsKilled", +1);
    }

    public static void onAsteroidReachedBorder(GameEvent event) {

    }

    public void init() {
        getGameTimer().runAtInterval(() -> {
            if (getGameWorld().getEntitiesByType(GalacticEnergiesType.ASTEROID).size() < maxAsteroids) {
                addAsteroid();
            }
        }, Duration.seconds(1));
    }

    public int getAsteroidAmount() {
        return asteroidAmount;
    }

    public void setAsteroidAmount(int asteroidAmount) {
        this.asteroidAmount = asteroidAmount;
    }

    public void addAsteroid() {
        asteroidAmount++;
        spawn("asteroid");
    }

    public void removeAsteroid() {
        asteroidAmount--;
    }
}
