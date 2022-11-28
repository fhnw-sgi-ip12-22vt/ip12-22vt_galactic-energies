package ch.fhnw.galacticenergies.models;

public class Player extends SpaceObject {

	private int lives;

	public Player() {
		super(10,50,10);
		this.lives = 3;
	}

	/**
	 * Moves Players position up (+1)
	 */
	public void moveUp() {
		setY_Pos(getY_Pos() + 1);
	}
	/**
	 * Moves Players position down (-1)
	 */
	public void moveDown() {
		setY_Pos(getY_Pos() - 1);
	}
}
