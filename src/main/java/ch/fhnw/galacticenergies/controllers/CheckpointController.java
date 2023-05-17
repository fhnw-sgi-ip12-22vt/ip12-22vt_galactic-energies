package ch.fhnw.galacticenergies.controllers;

import com.almasb.fxgl.entity.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class CheckpointController {
    private Entity planet;
    private HashSet<Double> intervals = new HashSet<Double>();

    public void addCheckpoint() {
        planet = spawn("planet");
    }

    public void removeCheckpoint() {
        if (planet == null) {
            return;
        }
        planet.removeFromWorld();
    }

    public void checkCreation(double power) {
        if (ViewController.isPaused()) {
            return;
        }
        if (power < 0.2) {
            return;
        }

        double interval = Math.ceil(power * 5.0) / 5.0;
        if (!intervals.contains(interval)) {
            addCheckpoint();
            intervals.add(interval);
        }
    }
}
