package ch.fhnw.galacticenergies.models;

public abstract class SpaceObject {

	private double x_Pos;

	private double y_Pos;

	private int size;

	//private Image img;


	public SpaceObject() {
	}

	public SpaceObject(double x_Pos, double y_Pos, int size) {
		this.x_Pos = x_Pos;
		this.y_Pos = y_Pos;
		this.size = size;
	}
	/**
	 *
	 * @return Value of x_Pos.
	 */
	public double getX_Pos() {
		return x_Pos;
	}

	/**
	 *
	 * @param x_Pos Position of SpaceObject on the X-Axis.
	 */
	public void setX_Pos(double x_Pos) {
		this.x_Pos = x_Pos;
	}

	/**
	 *
	 * @return Value of y_Pos.
	 */
	public double getY_Pos() {
		return y_Pos;
	}
	/**
	 *
	 * @param y_Pos Position of SpaceObject on the X-Axis.
	 */
	public void setY_Pos(double y_Pos) {
		this.y_Pos = y_Pos;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public abstract boolean borderIntersect();

}
