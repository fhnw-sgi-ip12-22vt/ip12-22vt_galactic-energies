package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.models.*;


public class PlayerController {

	private static Player player = new Player();

	/**
	 *
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
	public static void moveDown(){
		player.moveDown();
	}

	/**
	 *
	 * @param obj limit the movement of the object to the window.
	 */
	public void intersect(SpaceObject obj) {

	}

}
