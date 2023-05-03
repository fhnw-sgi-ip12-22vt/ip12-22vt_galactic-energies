package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.components.RocketComponent;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ROCKET;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

/**
 * Generates and controls the Player object
 *
 * @version 1.0
 * implements requirements: F01A05
 */


public class RocketController {

    public static RocketComponent getRocketControl() {
        return getGameWorld().getSingleton(ROCKET).getComponent(RocketComponent.class);
    }

}
