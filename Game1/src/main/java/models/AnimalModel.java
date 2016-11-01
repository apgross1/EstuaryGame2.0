package models;

import enums.Direction;

public class AnimalModel extends AnimalModelAbstract {
	
	private int height = 100;
	private int width = 100;
	private int speedX = 0;
	private int speedY = 0;
	
	public AnimalModel() {
		//Set initial location and direction
		setLocX(500);
		setLocY(500);
		setCurrDir(Direction.NORTH);
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setSpeedX(int speed) {
		this.speedX = speed;
	}
	
	public void setSpeedY(int speed) {
		this.speedY = speed;
	}
	
	
	@Override
	public void move() {
		//Set conditions for bounds....
		this.setLocY(this.getLocY() + speedY);
		this.setLocX(this.getLocX() + speedX);
	}
	
	
	
	/*
	 * Don't think we need stuff below this line
	 */
	
	@Override
	public void healthUp() {
		if(getHealth() < 100){
			setHealth(getHealth()+1); //Do we want to change the increase and make it more siginificant?
		}
	}
	@Override
	public void healthDown() {
		if(getHealth() >= 1){
			setHealth(getHealth()-1);
		}
	}
	@Override
	public void pickUp() {
		//This function is not needed as we're not actually picking anything up.. Collision will be taken care of in the controller which will tell
		//the model for each type of wall / gabion to update.
	}
}
