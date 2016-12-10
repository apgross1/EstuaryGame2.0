package models;

/**
 * @author Andrew
 * Helper class that creates and stores coordinate pairs
 */
public class Pair {
	private int a;
	private int b;
	
	/**
	 * Constructor for Pair.
	 * @param num1 , an int representing the x coordinate
	 * @param num2 , an int representing the y coordinate
	 */
	public Pair(int num1, int num2) {
		setX(num1);
		setY(num2);
	}

	/**
	 * Alternative constructor.
	 */
	public Pair() {
	}
	
	/**
	 * Gets the x coordinate.
	 * @return a , an int
	 */
	public int getX() {
		return a;
	}

	/**
	 * Sets the x coordinate.
	 * @param a , an int
	 */
	public void setX(int a) {
		this.a = a;
	}

	/**
	 * Gets the y coordinate.
	 * @return b , an int
	 */
	public int getY() {
		return b;
	}

	/**
	 * Sets the y coordinate.
	 * @param b , an int
	 */
	public void setY(int b) {
		this.b = b;
	}
}