package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.enums.GalacticEnergiesType;
import ch.fhnw.galacticenergies.events.GameEvent;
import javafx.util.Duration;

import java.util.stream.IntStream;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class AsteroidController {

    private int asteroidAmount = 0;
    private int maxAsteroids = 5;

    public static void onAsteroidHit(GameEvent event) {
        inc("asteroidsKilled", +1);
    }

    public static void onAsteroidReachedBorder(GameEvent event) {

    }

    public void init() {
        getGameTimer().runAtInterval(() -> {
            if(getGameWorld().getEntitiesByType(GalacticEnergiesType.ASTEROID).size() < maxAsteroids) {
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
