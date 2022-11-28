package ch.fhnw.galacticenergies.models;

public abstract class SpaceObject {

	private double x_Pos;

	private double y_Pos;

	private int size;

	private Image img;

	public abstract boolean borderIntersect();

}
