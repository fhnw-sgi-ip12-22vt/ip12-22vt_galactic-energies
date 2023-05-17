package ch.fhnw.galacticenergies.controllers;

import com.almasb.fxgl.entity.Entity;

import java.util.HashSet;

import static com.almasb.fxgl.dsl.FXGL.spawn;

/**
 * Defines the actions of the Checkpoint
 * @version 1.0
 */
public class CheckpointController {
    private Entity planet;
    private final HashSet<Double> intervals = new HashSet<Double>();

    public void addCheckpoint() {
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
        if (power < 0.5) {
            return;
        }
        double interval = Math.ceil(power * 2.0) / 2.0;
        if (!intervals.contains(interval)) {
            addCheckpoint();
            intervals.add(interval);
        }
    }
}
