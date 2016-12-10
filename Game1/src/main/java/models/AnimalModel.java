package models;

import java.awt.Dimension;
import enums.Direction;

public class AnimalModel extends AnimalModelCommon {
	
	private int height = 0;
	private int width = 0;
	private int screenHeight = 0;
	private int screenWidth = 0;
	private int speedX = 0;
	private int speedY = 0;
	
	/**
	 * constructor for the animal model that sets the spawn location, and also
	 * takes in the dimensions of the screen to help with said spawning
	 * @param Dimension s the dimensions of the screen
	 */
	public AnimalModel(Dimension s) {
		//Set screen resolution for movement
		height = 100;//animal pic
		width = 100;//animal pic
		System.out.println(s);
		screenHeight = s.height;
		screenWidth = s.width;
		//Set initial location and direction
		setLocX((s.width/2)-50);
		setLocY((int) (.85*s.height));
		setCurrDir(Direction.NORTH);
	}
	
	
	/**
	 * getter for the height of the animal
	 * @return an int with the measure of the height
	 */
	public int getHeight(){
		return height;
	}
	/**
	 * getter for the width of the animal
	 * @return an int with the measure of the width
	 */
	public int getWidth(){
		return width;
	}
	/**
	 * setter to set the height of the animal
	 * @param height an integer 
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * setter to set the width of the animal
	 * @param width an integer
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * setter to set the speed of the animal in X direction
	 * @param speed an integer of the speed
	 */
	public void setSpeedX(int speed) {
		this.speedX = speed;
	}
	/**
	 * setter to set the speed of the animal in Y direction
	 * @param speed an integer of the speed
	 */
	public void setSpeedY(int speed) {
		this.speedY = speed;
	}
	/**
	 * getter to get the speed of the animal in the x direction
	 * @return an int will the speed in the x direction
	 */
	public int getSpeedX() {
		return speedX;
	}
	/**
	 * getter to get the speed of the animal in the y direction
	 * @return an int with the speed in the y direction
	 */
	public int getSpeedY() {
		return speedY;
	}
	
	/**
	 * used to show if the animal is moving or not
	 * @return a boolean that shows if the animal is moving or not
	 */
	public boolean isMoving(){
		if(speedX != 0 || speedY != 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Updates the actual location of the animal.
	 */
	public void move() {
		if(getCurrDir() == Direction.EAST){
			if(getLocX() < (screenWidth-width) + speedX){
			this.setLocX(this.getLocX() + speedX);
			}
		}
		if(getCurrDir() == Direction.WEST){
			if(getLocX() > 0 + speedX){
			this.setLocX(this.getLocX() + speedX);
			}
		}
		if(getCurrDir() == Direction.NORTH){
			if(getLocY() + speedY >= (int)(.45 * (screenHeight))){
				this.setLocY(this.getLocY() + speedY);
			}
		}
		if(getCurrDir() == Direction.SOUTH){
			if(getLocY() + speedY <= (screenHeight-height)){
				this.setLocY(this.getLocY() + speedY);
			}
		}
	}
}
