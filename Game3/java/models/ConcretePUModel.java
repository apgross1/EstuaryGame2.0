package models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Enums.Frames;
import Enums.TestControl;
import models.GabionPUModel.GabPUState;



public class ConcretePUModel extends WallModelAbstract {
	public enum ConcPUState {
		POWER_UP,WALL;
	}
	private boolean isActive;
	private boolean isPickedUp;
	private ConcPUState wallState;
	private HashMap<Frames, JComponent> frameMap;
	private Pair location;
	private Pair viewLocation;
	private int height;
	private int width;
	private Rectangle bounds;

	
	
	/**
	 * Constructor for this element. It initializes several variables:
	 * i)   isActive - boolean that determines if the concrete power-up/wall has been activated
	 * ii)  height - determines the height of the concrete power-up/wall
	 * iii)	width - determines the width of the concrete power-up/wall
	 * iv)  isPickedUp - determines if the animal collided with the conrete power-up/wall
	 * v)   location - contains the location of the concrete power-up/wall
	 * vi)  viewLocation - contains the location of the concrete power-up/wall displayed in the View
	 * vii) graphics - a HashMap that contains images associates with concrete power-up/wall
	 */
	public ConcretePUModel() {
		setWallState(ConcPUState.POWER_UP);
		this.isActive = false;
		this.height = 30;
		this.width = 30;
		this.isPickedUp = false;
		this.location = new Pair(0,0);
		this.viewLocation = new Pair(0,0);
	}
	
	
	/**
	 * Sets a new concrete state depending on whether or not the concrete power up was picked up.
	 * If the power up was picked up, the power-up will turn into a wall and its
	 * dimensions will adjust accordingly. Otherwise, it will remain a power-up.
	 * @param isPickedUp a boolean determining if power-up was picked up by the animal
	 */
	public void setPickedUp(boolean isPickedUp) {
		if(isPickedUp) {
			this.setWallState(ConcPUState.WALL);
			System.out.println("In wall form");
			this.width = 70;
			this.height = 150;
			this.getViewLocation().setX(this.getViewLocation().getX() + 30);
		}
		else {
			this.setWallState(ConcPUState.POWER_UP);
			this.width = 30;
			this.height = 30;
			this.setBounds(this.getViewLocation().getX(), this.getViewLocation().getY(), this.width, this.height);
		}
		this.isPickedUp = isPickedUp;
	}
	

	
	/**
	 * Gets the bounds of this element at a single point in time.
	 * @return a Rectangle containing position information of this element
	 */
	public Rectangle getBounds() {
		return (new Rectangle(this.getViewLocation().getX(),this.getViewLocation().getY(),width,height));
	}
	
	/**
	 * Gets the location of this element with respect to the View's coordinate system.
	 * @return viewLocation the Pair corresponding to the View's location of this element
	 */
	public Pair getViewLocation() {
		return viewLocation;
	}
	
	/**
	 * Sets the location of this element with respect to the View's coordinate system.
	 * @param viewLocation the Pair corresponding to the View's location of this element
	 */
	public void setViewLocation(Pair viewLocation) {
		int tileWidth = (int)((frameMap.get(Frames.ANIMAL).getWidth()+frameMap.get(Frames.SHORE).getWidth())/7);
		int tileHeight = (int)(frameMap.get(Frames.SHORE).getHeight()/7);
		this.viewLocation.setX((int)((location.getX()))*tileWidth);
		this.viewLocation.setY((int)(location.getY())*tileHeight);
	}
	
	/**
	 * Gets the truth value of isActive, which determines if the concrete power-up is active.
	 * @return isActive a boolean which determines if the concrete power-up is active
	 */
	public boolean getIsActive() {
		return isActive;
	}
	
	/**
	 * Sets the truth value of isActive, which determines if the concrete power-up is active
	 * @param isActive a boolean which determines if the concrete power-up is active
	 */
	public void setActive(boolean isActive) {
		if(isActive) {
			this.setBounds(this.getViewLocation().getX(), this.getViewLocation().getY(), this.width, this.height);
		}
		this.isActive = isActive;
	}
	
	/**
	 * Gets the wallState, a ConcPUState that determines if this element is a power-up or a wall
	 * @return wallState a ConcPUState
	 */
	public ConcPUState getWallState() {
		return wallState;
	}
	
	/**
	 * Sets the wall state for this element. 
	 * @param gameState a ConcPUState eNum (WALL,POWER_UP)
	 */
	public void setWallState(ConcPUState gameState) {
		this.wallState = gameState;
	}
	
	/**
	 * Gets the location of this element.
	 * @return a Pair containing the location of this element
	 */
	public Pair getLocation() {
		return location;
	}
	
	
	/**
	 * Gets the height of this element
	 * @return height an integer representation of height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets the height of this element
	 * @param height an integer representing the height of this element
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Gets the width of this element
	 * @return width an integer representation of width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets the width of this element
	 * @param width an integer representation of the width of this element
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Gets the boolean value for whether or not this element -up was picked up.
	 * @return a boolean determining if the animal picked up this element
	 */
	public boolean isPickedUp() {
		return isPickedUp;
	}
	
	/**
	 * Gets the frame map used mainly to set dimensions/borders relative to the screen size
	 * @return frameMap a boolean
	 */
	public HashMap<Frames, JComponent> getFrameMap() {
		return frameMap;
	}
	
	/**
	 * Sets the frame map
	 * @param frameMap a
	 */
	public void setFrameMap(HashMap<Frames, JComponent> frameMap) {
		this.frameMap = frameMap;
	}
	
	/**
	 * Sets the bounds of this element too be used in collision tomorrow.
	 * @param x the x coordinate
	 * @param y the y cooridnate
	 * @param width the width of the concrete power-up
	 * @param height the height of the concrete power-up
	 */
	public void setBounds(int x, int y, int width, int height) {
		this.bounds = new Rectangle(x,y,width,height);
	}
	
	/**
	 * Sets the location of this element.
	 * @param location a Pair corresponding to the location
	 * of this element.
	 * @param test String used to change the functionality of this
	 * method given its truth value 
	 */
	public void setLocation(Pair location, TestControl test) {
		if(test == TestControl.TEST){
			this.location = location;
		}
		else{
			this.location = location;
			this.setViewLocation(location);
		}
	}
}
