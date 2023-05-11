package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.components.AsteroidComponent;
import ch.fhnw.galacticenergies.enums.GalacticEnergiesType;
import ch.fhnw.galacticenergies.events.GameEvent;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class AsteroidController {

    private int asteroidAmount = 0;
    private int maxAsteroids = 5;

    public static void onAsteroidHit(GameEvent event) {
        inc("asteroidsKilled", +1);
    }

    public void init() {
        getGameTimer().runAtInterval(() -> {
            if(ViewController.isPaused()) return;
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

    public void removeAllAsteroids() {
        getGameWorld().removeEntities(getGameWorld().getEntitiesByComponent(AsteroidComponent.class));
    }
}
