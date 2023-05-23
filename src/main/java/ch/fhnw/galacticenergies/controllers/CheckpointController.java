package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.View;
import com.almasb.fxgl.entity.Entity;

import java.util.HashSet;

import static com.almasb.fxgl.dsl.FXGL.spawn;

/**
 * Defines the actions of the Checkpoint
 * @version 1.0
 */
public class CheckpointController {
    private Entity planet;
    private final HashSet<Double> intervals = new HashSet<>();

    public void addCheckpoint() {
        removeCheckpoint();
        planet = spawn("planet");
    }

    public void removeCheckpoint() {
        if (planet == null) {
            return;
        }
        planet.removeFromWorld();
    }

    /**
     * Create a Checkpoint
     * @param power total Power used for calculation within the Checkpoint message
     */
    public void checkCreation(double power) {
        if (ViewController.isPaused()) {
            return;
        }
        if (power < 0.3) {
            return;
        }
        double interval = Math.floor(power * (10.0/3.0)) / (10.0/3.0);

        if (!intervals.contains(interval)) {
            addCheckpoint();
            addToInterval(interval);
        }
    }

    public void addToInterval(double interval) {
        intervals.add(interval);
    }

    public void removeUnreachedIntervals() {
        intervals.removeIf(i -> i > View.levelController.getSavedPower());
    }
}
