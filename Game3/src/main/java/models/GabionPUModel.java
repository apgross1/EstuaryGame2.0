package models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import Enums.Frames;


public class GabionPUModel extends WallModelAbstract {
	private boolean isActive;
	private boolean isPickedUp;
	private Pair location;
	private Pair viewLocation;
	private HashMap<Frames, JComponent> frameMap;
	private GabPUState wallState;
	private Rectangle bounds;
	private int height;
	private int width;
	
	public enum GabPUState{
		POWER_UP, WALL;
	}
	/**
	 * Constructor for this element. It initializes several variables:
	 * i)   isActive - boolean that determines if the gabion power-up/wall has been activated
	 * ii)  height - determines the height of the gabion power-up/wall
	 * iii)	width - determines the width of the gabion power-up/wall
	 * iv)  isPickedUp - determines if the animal collided with the conrete power-up/wall
	 * v)   location - contains the location of the gabion power-up/wall
	 * vi)  viewLocation - contains the location of the gabion power-up/wall displayed in the View
	 * vii) graphics - a HashMap that contains images associates with gabion power-up/wall
	 */
	public GabionPUModel() {
		this.setWallState(GabPUState.POWER_UP);
		this.isActive = false;
		this.height = 30;
		this.width = 30;
		this.isPickedUp = false;
		this.location = new Pair(0,0);
		this.viewLocation = new Pair(0,0);
	}

	
	/**
	 * Gets the truth value of isActive, which determines if the gabion power-up is active.
	 * @return isActive a boolean which determines if the gabion power-up is active
	 */
	public boolean getIsActive() {
		return isActive;
	}
	
	/**
	 * Sets the truth value of isActive, which determines if the gabion power-up is active
	 * @param isActive a boolean which determines if the gabion power-up is active
	 */
	public void setIsActive(boolean active) {
		if(active) {
			this.setBounds(this.getViewLocation().getX(), this.getViewLocation().getY(), this.width, this.height);
		}
		isActive = active;
	}
	
	/**
	 * Gets the wallState, a ConcPUState that determines if this element is a power-up or a wall
	 * @return wallState a ConcPUState
	 */
	public GabPUState getWallState() {
		return wallState;
	}
	
	/**
	 * Sets the wall state for this element. 
	 * @param gameState a ConcPUState eNum (WALL,POWER_UP)
	 */
	public void setWallState(GabPUState gameState) {
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
	 * Sets the location of this element
	 * @param location a Pair containing the location of this element, test determines if it's test or game
	 */
	public void setLocation(Pair location, String test) {
		if(test == "test"){
			this.location = location;
		}
		else{
			this.location = location;
			this.setViewLocation(location);
		}
	}
	
	
	/**
	 * Sets the bounds of this component
	 * @param x int, the x coordinate
	 * @param y int, the y coordinate
	 * @param width int, the width
	 * @param height int, the height
	 */
	public void setBounds(int x, int y, int width, int height) {
		this.bounds = new Rectangle(x,y,width,height);
	}
	
	/**
	 * Gets the bounds of this element at a single point in time.
	 * @return a Rectangle containing position information of this element
	 */
	public Rectangle getBounds() {
		return (new Rectangle(this.getViewLocation().getX(),this.getViewLocation().getY(),width,height));
	}
	
	
	/**
	 * Sets the height of this element
	 * @return height int, the height of this element
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of this element
	 * @param height int, the height of this element
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets the width of this element
	 * @return int, the width of this element
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of this element
	 * @param width int, the width of this element
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Determines if the gabion power-up/wall has been picked up by the animal
	 * @return isPickedUp boolean, true is picked up, false otherwise
	 */
	public boolean isPickedUp() {
		return isPickedUp;
	}
	
	/**
	 * Sets a new gabion state depending on whether or not the gabion power up was picked up.
	 * If the power up was picked up, the power-up will turn into a wall and its
	 * dimensions will adjust accordingly. Otherwise, it will remain a power-up.
	 * @param isPickedUp a boolean determining if power-up was picked up by the animal
	 */
	public void setPickedUp(boolean isPickedUp) {
		if(isPickedUp) {
			this.setWallState(GabPUState.WALL);
			System.out.println("In wall form");
			System.out.println("Bounds are: " + this.getBounds().getX());
			this.width = 70;
			this.height = 150;
			this.getViewLocation().setX(this.getViewLocation().getX() + 30);
		}
		else {
			this.setWallState(GabPUState.POWER_UP);
			this.width = 30;
			this.height = 30;
			this.setBounds(this.getViewLocation().getX(), this.getViewLocation().getY(), this.width, this.height);
		}
		
		this.isPickedUp = isPickedUp;
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
	public void setViewLocation(Pair loc) {
		int tileWidth = (int)((frameMap.get(Frames.ANIMAL).getWidth()+frameMap.get(Frames.SHORE).getWidth())/7);
		int tileHeight = (int)(frameMap.get(Frames.SHORE).getHeight()/7);
		this.viewLocation.setX((int)((loc.getX()))*tileWidth);
		this.viewLocation.setY((int)(loc.getY())*tileHeight);	
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
	
	@Override
	public void breakDown() {
		
	}
	
	
	//For testing purposes
		public void setLocationTest(Pair location) {
			this.location = location;
		}
		

}
