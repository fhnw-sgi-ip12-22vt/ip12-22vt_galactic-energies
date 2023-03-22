package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.events.GameEvent;

import static com.almasb.fxgl.dsl.FXGL.inc;
public class AsteroidController {

    private int asteroidAmount = 0;
    public static void onAsteroidHit(GameEvent event)
    {
        inc("asteroidsKilled", +1);
        System.out.println("TEST");
    }

    public static void onAsteroidReachedBorder(GameEvent event)
    {

    }

    public int getAsteroidAmount() {
        return asteroidAmount;
    }

    public void setAsteroidAmount(int asteroidAmount) {
        this.asteroidAmount = asteroidAmount;
    }

    public void addAsteroid()
    {
        asteroidAmount++;
    }

    public void removeAsteroid()
    {
        asteroidAmount--;
    }
}
