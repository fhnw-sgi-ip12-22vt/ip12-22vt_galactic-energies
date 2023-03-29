package ch.fhnw.galacticenergies.controllers;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class CheckpointController {
    private int planetAmount = 0;
    int counter = 0;
    public static void addCheckpoint(){
            spawn("planet");
    }
    public int getCheckpointAmount() {

        return planetAmount;
    }

    public void setCheckpointAmount(int planetAmount) {

        this.planetAmount = planetAmount;
    }

    public void removeCheckpoint()
    {

        planetAmount--;
    }
}
