package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;

public class AnimalModelG2 extends AnimalModelAbstract {
	
	private HashMap<Direction,ArrayList<BufferedImage>> animations;
	
	private int speed;
	private boolean isDead;
	int height, width;
	
	public AnimalModelG2() {
		this.height = 75;
		this.width =75;
		this.setHealth(100);
		this.setLocY(275);
		this.setLocX(10);
		this.setSpeed(100);
		this.isDead = false;
		
	}
	
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	@Override
	public void move(){
		switch( this.getCurrDir()){
		case NORTH: 
			this.setLocY(this.getLocY()-10);
			break;
		case SOUTH: 
			this.setLocY(this.getLocY()+10);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void healthUp() {
		setHealth(getHealth()+1);
	}

	@Override
	public void healthDown() {
		setHealth(getHealth()-1);
		
	}

	@Override
	public void pickUp() {
		
	}		

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void increaseSpeed(){
		this.setSpeed(getSpeed()+1);
	}
	public void decreaseSpeed(){
		this.setSpeed(getSpeed()-1);
	}
	
	public void setAnimations(HashMap<Direction,ArrayList<BufferedImage>> animations) {
		this.animations = animations;
	}
	
	public boolean getIsDead(){
		return isDead;
	}
	
	public void setIsDead(boolean state){
		isDead = state;
	}
}
