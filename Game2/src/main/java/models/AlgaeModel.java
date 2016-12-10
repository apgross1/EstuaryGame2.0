package models;

import java.util.Random;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Group 8
 *
 */
public class AlgaeModel implements Serializable {
	private int locX;
	private int locY;
	
	private boolean isActive;
	
	private int velocity = 2;
	private int randomYBound = 0;
	private Random rand = new Random();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();
	private int height = (int) (screenHeight*.15);
	private int width = (int) (screenHeight*.15);
	private int algaeXBoundMax = screenWidth;
	private int algaeYBoundMax = (int) (screenHeight-(screenHeight*.1));
	private int algaeYBoundMin = (int) (screenHeight*.32);
	private int maxAlgaeNum = 100;
	private int riverSpawnX;
	private int riverSpawnY;
	
	

	/**
	 * Initialize vital variables for this component
	 */
	public AlgaeModel() {
		
		randomYBound = rand.nextInt((algaeYBoundMax - algaeYBoundMin) + 1) + algaeYBoundMin;
		
		riverSpawnX = (int) (screenWidth*.87);
	    riverSpawnY = (int) (screenHeight*.11);
	    
	}
	
	/**
	 * Sets the algaemodels height based on the integer parameter h
	 * @param h the desired height of the algaemodel
	 */
	public void setHeight(int h){
		height = h;
	}

	/**
	 * Gets the currents algae model integer height
	 * @return the current height of the AlgaeModel
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets the algaemodels width based on the integer parameter w
	 * @param h the desired height of the algaemodel
	 */
	public void setWidth(int w){
		width = w;
	}
	
	/**
	 * Gets the current integer width of the algaeModel
	 * @return current width of the algaeModel
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets a random location algae are allowed to spawn at
	 * @return a random location between the minimumYBound and maxYBound
	 */
	public int getRandomYLocation() {
		return randomYBound;
	}
	
	
	/**
	 * Decrements the current X position of the algaeModel by the current velocity
	 */
	public void move() {
		this.setLocX(getLocX() - velocity);
	}
	/**
	 * Increments the current X position of the riverAlgae
	 */
	public void moveRiverAlgae() {
		riverSpawnX += velocity;
	}
	
	/**
	 * Registers the Y location for river algae based on the integer parameter rY
	 * @param rY the desired Y location for river algae to spawn
	 */
	public void setRiverAlgaeY(int rY){
		riverSpawnY =rY;
	}
	
	/**
	 * Gets the river algae's Y spawn coordinate
	 * @return the Y value of where the river algae spawns
	 */
	public int getRiverAlgaeY(){
		return riverSpawnY;
	}
	
	/**
	 * Registers the X location for river algae based on the integer parameter rX
	 * @param rX the desired Y location for river algae to spawn
	 */
	public void setRiverAlgaeX(int rX){
		riverSpawnX =rX;
	}
	
	/**
	 * Gets the river algae's X spawn coordinate
	 * @return the X value of where the river algae spawns
	 */
	public int getRiverAlgaeX(){
		return riverSpawnX;
	}

	
	/**
	 * Registers the algae's boolean active status as false
	 */
	public void eaten() {
		this.setActive(false);
	}

	/**
	 * REgisters the algae's active status as true
	 * initializes the X spawn point based on screensize
	 * initializes the Y spawn point based on @see getRandomLocation() to get a random Y location
	 */
	public void spawnAlgaeModel() {

		this.setActive(true);
		setLocX((int) (screenWidth+(screenWidth*.1)));
		setLocY(getRandomYLocation());
	}
	
	/**
	 * Registers the algae amount limit based on the max parameter
	 * @param max the desired integer algae limit
	 */
	public void setMaxAlgae(int max) {
		maxAlgaeNum = max;
	}
	
	/**
	 * Gets the limit of algae allowed to spawn during the game period
	 * @return the current maximum amount of algae allowed to be spawned
	 */
	public int getMaxAlgae() {
		return maxAlgaeNum;
	}
	
	

	/**
	 * Gets the Y location of the Algae model
	 * @return the Y location of the algaeModel
	 */
	public int getLocY() {
		return locY;
	}

	/**
	 * @param locY
	 */
	public void setLocY(int locY) {
		this.locY = locY;
	}

	/**
	 * Gets the X coordinate of the algaeModel
	 * @return the X location of the algae
	 */
	public int getLocX() {
		return locX;
	}

	/**
	 * Registers the x location of the algaeModel
	 * @param locX the desired x location of the algaeModel
	 */
	public void setLocX(int locX) {
		this.locX = locX;
	}

	/**
	 * @return true if the algaeModel has been spawned and not eaten or experienced collision
	 * @return false if eaten or collided with game edge or animalModel
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Registers whether or not the algae needs to be updated in the game
	 * @param isActive the state of the algaeModel
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the current velocity of the algaeModel
	 * @return current velocity of the algaeModel
	 */
	public int getVelocity() {
		
		return velocity;
	}
	/**
	 * Registers the velocity based  on the parameter v
	 * @param v is the desired velocity for the algaeModel
	 */
	public void setVelocity(int v) {
		
		velocity = v;
	}
	/**
	 * Registers the upper bounds for the algae y coordinate
	 * @param yMax the upper Y bounds for algae spawning
	 */
	public void setYMax(int yMax){
		 algaeYBoundMax = yMax;
	}
	/**
	 * Gets the upper bounds for the algae y coordinate
	 * @return the upper Y bounds for algae spawning 
	 */
	public int getYMax(){
		return algaeYBoundMax;
	}
	
	/**
	 * Gets the upper bounds for the algae x coordinate
	 * @return the upper X bounds for algae spawning 
	 */
	public int getXMax(){
		return algaeXBoundMax;
	}
	
	/**
	 * Registers the upper bounds for the algae x coordinate
	 * @param xM the upper x bounds for algae spawning
	 */
	public void setXMax(int xM){
		algaeXBoundMax = xM;
	}
	
	/**
	 * Gets the lower bounds for the algae y coordinate
	 * @return the lower Y bounds for algae spawning 
	 */
	public int getYMin(){
		return algaeYBoundMin;
	}
	/**
	 * Registers the lower bounds for the algae y coordinate
	 * @param yMax the lower Y bounds for algae spawning
	 */
	public void setYMin(int yMin){
		algaeYBoundMin = yMin;
	}
	
}