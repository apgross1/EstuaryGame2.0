package models;

import java.awt.Dimension;
import enums.Direction;

public class AnimalModel extends AnimalModelAbstract {
	
	private int height = 0;
	private int width = 0;
	private int screenHeight = 0;
	private int screenWidth = 0;
	private int speedX = 0;
	private int speedY = 0;
	
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
	
	public boolean isMoving(){
		if(speedX != 0 || speedY != 0){
			return true;
		}
		return false;
	}
	
	@Override
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
