package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.components.RocketComponent;
import ch.fhnw.galacticenergies.models.*;

import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.ROCKET;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

/**
 * Generates and controls the Player object
 * @version 1.0
 */
public class RocketController {
	/**
	 * Initialization of a new player.
	 */
	private static Player player = new Player();

	public static void initPlayer(double x, double y){
		player = new Player(x,y);
	}

	/**
	 * @return name of the player
	 */
	public static Player getPlayer() {
		return player;
	}

	/**
	 * Moves player up.
	 */
	public static void moveUp(){
		player.moveUp();
	}

	/**
	 * Moves player down.
	 */
	public static void moveDown(double maxY){
		player.moveDown(maxY);
	}

	/**
	 * Handels the intersection of two SpaceObjects
	 * @param obj limit the movement of the object to the window.
	 */
	public void intersect(SpaceObject obj) {

	}

	public static RocketComponent getRocketControl() {
		return getGameWorld().getSingleton(ROCKET).getComponent(RocketComponent.class);
	}

}
