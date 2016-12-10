package models;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;

import Enums.TestControl;

public class GridBlock implements Serializable {
	private Pair location;
	private GabionPUModel gabPU = new GabionPUModel();
	private ConcretePUModel concrPU = new ConcretePUModel();
	private WaterModel water;
	private boolean vacant;
	private int height;
	private int width;
	private BeachModel beach;
	private Pair viewLocation = new Pair(0,0);
	private TestControl gameState;
	
	/**
	 * Constructor for this element. The constructor initializes:
	 * i) water, the water tile contained on this grid block
	 * ii) gabPu, the gabion power-up holder on this grid block
	 * iii) concrPU, the concrete power-up holder on this grid block
	 * iv) height, the height of the grid block
	 * v) width, the width of the grid block
	 * vi) vacant, boolean that determines if the grid block is free of any water/gabionPU/concrPU
	 * @param b BeachModel, the game beach
	 */
	public GridBlock(BeachModel b, TestControl t) {
		water = new WaterModel(t);
		gabPU.setIsActive(false);
		concrPU.setActive(false);
		height = 200;
		width = 200;
		vacant = true;
		beach = b;
		gameState = t;
	}
	
	
	/**
	 * Alternative constructor given a location and game beach
	 * @param loc Pair, view location of the grid block
	 * @param b BeachModel, the game beach being used
	 */
	public GridBlock(Pair loc, BeachModel b) {
		water = new WaterModel(TestControl.TEST);

		gabPU = new GabionPUModel();
		gabPU.setIsActive(false);
		beach = b;
		concrPU = new ConcretePUModel();
		concrPU.setActive(false);
		location = loc;
		this.setViewLocation(location);
		height = 200;
		width = 200;
		vacant = true;
	}
	
	
	/**
	 * Alternative constructor used for testing purposes given 
	 * a location, game beach, and test string
	 * @param loc Pair, view location of the grid block
	 * @param b BeachModel, the game beach being used
	 * @param Test String, to determine if a test is being conducted
	 */
	public GridBlock(Pair loc, BeachModel b, TestControl test) {
		gabPU = new GabionPUModel();
		gabPU.setIsActive(false);
		beach = b;
		concrPU = new ConcretePUModel();
		concrPU.setActive(false);
		location = loc;
		if(test == TestControl.NO_TEST) {
			this.setViewLocation(location);
		}
		
		height = 200;
		width = 200;
		vacant = true;
	}
	

	/**
	 * Alternative constructor that places concrete power-up and its pair
	 * on grid block
	 * @param powerUp ConcetePUModel, location of concrete power-up
	 * @param loc Pair, the location of the power-up
	 */
	public GridBlock(ConcretePUModel powerUp, Pair loc) {
		water = new WaterModel(TestControl.TEST);

		gabPU.setIsActive(false);
		
		concrPU.setActive(false);
		powerUp.setLocation(loc, TestControl.TEST);
		
		this.setConcrPU(powerUp);
		this.location = loc;
		this.setViewLocation(location);
		this.setVacant(false);
		
	}
	
	/**
	 * Alternative constructor that takes in a gabion power-up and its
	 * corresponding location.
	 * @param powerUp GabionPUModel, a gabion power-up/wall
	 * @param loc Pair, the location of the gabion power-up
	 */
	public GridBlock(GabionPUModel powerUp, Pair loc) {
		water = new WaterModel(TestControl.TEST);

		gabPU.setIsActive(false);
		concrPU.setActive(false);
		powerUp.setLocation(loc, TestControl.TEST);
		this.setGabPU(powerUp);
		this.location = loc;
		this.setViewLocation(location);
		this.setVacant(false);
	}
	
	
	/**
	 * Loaded setter that determines the action taken on a grid block depending
	 * on whether or not water is generated on that tile.
	 * @param water WaterModel, the potential water being added to that grid block
	 * @param loc Pair, location of the water to be placed on the grid block
	 * @param test String, used to change functionality of method to run in test-mode
	 */
	public void setWater(WaterModel water, Pair loc) {
		gabPU.setIsActive(false);
		concrPU.setActive(false);
		water.setLocation(loc);
		this.water = water;
		this.water.setActive(true);
		//this.location = loc;
		this.setVacant(false);
		
		beach.getPositionGrid()[loc.getY()][loc.getX()] = 2;
		System.out.println("Value on grid at (" + loc.getX() + "," + loc.getY() + "): " + beach.getPositionGrid()[loc.getY()][loc.getX()]);
	}
	
	
	/**
	 * Gets the location of this element
	 * @return location Pair, location of this element
	 */
	public Pair getLocation() {
		return location;
	}

	/**
	 * Sets location of this element
	 * @param location Pair, the location of this element
	 */
	public void setLocation(Pair location) {
		this.location = location;
	}

	/**
	 * Gets the gabion power-up from this grid block
	 * @return gabPU, the gabion power-up from this grid block
	 */
	public GabionPUModel getGabPU() {
		return gabPU;
	}

	/**
	 * Loaded setter that adjusts properties such as vacancy for this element
	 * @param gabPU GabionPUModel, the element which we are importing;
	 */
	public void setGabPU(GabionPUModel gabPU) {
		concrPU.setActive(false);
		water.setActive(false);
		gabPU.setIsActive(true);
		this.gabPU = gabPU;
		this.setVacant(false);
	}

	/**
	 * Gets the concrete power-up/wall help at this grid block
	 * @return concrPU , the concrete power-up/wall stored at this grid block
	 */
	public ConcretePUModel getConcrPU() {
		return concrPU;
	}

	/**
	 * Loaded setter that adjusts properties such as vacancy for this element
	 * @param concrPU ConcretePUModel, the element which we are importing;
	 */
	public void setConcrPU(ConcretePUModel concrPU) {
		concrPU.setActive(true);
		this.concrPU = concrPU;
		this.gabPU.setIsActive(false);
		this.water.setActive(false);
		
		this.setVacant(false);
	}

	/**
	 * Gets if this element is vacant
	 * @return vacant boolean, true if this element is vacant and false otherwise
	 */
	public boolean isVacant() {
		return vacant;
	}

	/**
	 * Sets vacancy of this element
	 * @param vacant boolean, true if this element is vacant and false otherwise
	 */
	public void setVacant(boolean vacant) {
		this.vacant = vacant;
		if(vacant) {
			this.water.setActive(false);
			this.gabPU.setIsActive(false);
			this.concrPU.setActive(false);
		}
	}
	
	/**
	 * Gets the bounds of the grid block
	 * @return Rectangle, the bounds of this element at a specific moment time
	 */
	public Rectangle getBounds() {
		return (new Rectangle(this.getViewLocation().getX(),this.getViewLocation().getY(),this.getWidth(),this.getHeight()));
	}

	/**
	 * Gets the height of this element
	 * @return height int, represents height of this element
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
	 * @return width int, the width of this element
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of this element
	 * @param width int, the width of thie element
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the water object from the beach grid
	 * @return water WaterModel, the water tile on this element
	 */
	public WaterModel getWater() {
		return water;
	}
	
	/**
	 * Gets the Pair corresponding to the dimensions of the screen.
	 * @return viewLocation Pair, pair of coordinates corresponding to the dimensions
	 * of the screen
	 */
	public Pair getViewLocation() {
		return viewLocation;
	}
	
	/**
	 * Sets the Pair corresponding to the dimensions of the screen.
	 * @param viewLocation Pair, pair of coordinates corresponding to the dimensions of the screen
	 */
	public void setViewLocation(Pair viewLocation) {
		this.viewLocation = viewLocation;
	}
	
}
