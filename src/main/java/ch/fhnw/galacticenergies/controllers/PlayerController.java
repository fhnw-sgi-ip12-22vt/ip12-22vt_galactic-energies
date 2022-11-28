package ch.fhnw.galacticenergies.controllers;

import ch.fhnw.galacticenergies.models.*;


public class PlayerController {

	private static Player player;

	public static Player getPlayer() {
		return player;
	}
	public void moveUp(){
		player.moveUp();
	}
	public void moveDown(){
		player.moveDown();
	}

	public void intersect(SpaceObject obj) {

	}

}
