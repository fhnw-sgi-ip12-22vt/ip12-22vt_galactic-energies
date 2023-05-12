package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.components.AsteroidComponent;
import ch.fhnw.galacticenergies.enums.GalacticEnergiesType;
import ch.fhnw.galacticenergies.events.GameEvent;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class AsteroidController {
    private int maxAsteroids = 5;

    public void init() {
        getGameTimer().runAtInterval(() -> {
            if(ViewController.isPaused()) return;
            if(getGameWorld().getEntitiesByType(GalacticEnergiesType.ASTEROID).size() < maxAsteroids) {
                addAsteroid();
            }
        }, Duration.seconds(1));
    }
    public void addAsteroid() {
        spawn("asteroid");
    }
    public void removeAllAsteroids() {
        getGameWorld().removeEntities(getGameWorld().getEntitiesByComponent(AsteroidComponent.class));
    }

    public void increaseMaxAsteroids() {
        maxAsteroids += 1;
    }
}
