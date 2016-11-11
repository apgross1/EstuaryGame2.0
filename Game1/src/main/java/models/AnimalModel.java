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
		System.out.println(getLocY());
		if(getCurrDir() == Direction.EAST){
			if(getLocX() < 890 + speedX){
			this.setLocX(this.getLocX() + speedX);
			}
		}
		if(getCurrDir() == Direction.WEST){
			if(getLocX() > 0 + speedX){
			this.setLocX(this.getLocX() + speedX);
			}
		}
		if(getCurrDir() == Direction.NORTH){
			if(getLocY() + speedY >= 310){
				this.setLocY(this.getLocY() + speedY);
			}
		}
		if(getCurrDir() == Direction.SOUTH){
			if(getLocY() + speedY <= 570){
				this.setLocY(this.getLocY() + speedY);
			}
		}
	}
	
	
	
	/*
	 * Don't think we need stuff below this line
	 */
	
	@Override
	public void healthUp() {
	}
	@Override
	public void healthDown() {
	}
	@Override
	public void pickUp() {
	}
}
