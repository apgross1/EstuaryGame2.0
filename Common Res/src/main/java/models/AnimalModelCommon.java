package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;

/**
 * @author Andrew
 * Abstract class that sets the guidelines for creating an animal for each game.
 */
public abstract class AnimalModelCommon {
	private int locX;
	private int locY;
	private int health;
	private Direction currDir;
	
	
	/**
	 * Returns the horizontal location of the animal.
	 * @return locX , an int
	 */
	public int getLocX() {
		return locX;
	}
	
	/**
	 * Sets the horizontal location of the animal.
	 * @param locX , an int
	 */
	public void setLocX(int locX) {
		this.locX = locX;
	}
	
	/**
	 * Returns the vertical location of the animal.
	 * @return , an int
	 */
	public int getLocY() {
		return locY;
	}
	/**
	 * Sets the vertical location of the animal.
	 * @param locY , an int
	 */
	public void setLocY(int locY) {
		this.locY = locY;
	}
	
	/**
	 * Returns the health of the animal
	 * @return , an int
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets the health of the animal.
	 * @param health , an int
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Returns the current direction of the animal.
	 * @return currDir , a Direction enum
	 */
	public Direction getCurrDir() {
		return currDir;
	}

	/**
	 * Sets the current direction of the animal.
	 * @param d , a Direction enum
	 */
	public void setCurrDir(Direction d) {
		this.currDir = d;
	}
}
