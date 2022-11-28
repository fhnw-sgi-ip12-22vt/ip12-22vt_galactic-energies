package ch.fhnw.galacticenergies.models;

public class Player extends SpaceObject {

	private int lives;

	public Player() {
		super(10,50,10);
		this.lives = 3;
	}

	/**
	 * Moves Players position up
	 */
	public void moveUp() {
		setX_Pos(getX_Pos() + 1);
	}

	public void moveDown() {
		setX_Pos(getX_Pos() + 1);
	}

	@Override
	public boolean borderIntersect() {
		return false;
	}
}
