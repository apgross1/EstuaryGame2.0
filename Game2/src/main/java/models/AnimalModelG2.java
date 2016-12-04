package models;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;

public class AnimalModelG2 extends AnimalModelAbstract {

	private HashMap<Direction, ArrayList<BufferedImage>> animations;

	private int speed;
	private boolean isDead;
	private int height, width;
	private int y;
	Dimension size;
	int screenWidth = 0;
	int screenHeight =0;
	int charYBoundMin = 0;
	int charYBoundMax = 0;
	public AnimalModelG2(Dimension s) {
		size = s;
		screenWidth = (int) size.getWidth();
		screenHeight = (int) size.getHeight();
		charYBoundMin = (int) (screenHeight-(screenHeight*.1));
		charYBoundMax = (int) (screenHeight*.32);
		this.height = (int) (screenHeight*.15);
		this.width = (int) (screenHeight*.15);
		
		
		this.isDead = false;
		this.speed = 0;
		
		y = screenHeight/2;
	}
	public void tick(){
		if(y>charYBoundMax  && speed<0)
		{
		y+=speed;
		}
		else if( y<charYBoundMin && speed>0 ){
			y+=speed;
		}
		
	}
	public int getY(){
		return y;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public void move() {
//		switch (this.getCurrDir()) {
//		case NORTH:
//			if(this.getLocY()>0){
//				
//				this.setLocY(y);
//			}
//			break;
//		case SOUTH:
//			if(this.getLocY()<585){
//				
//				this.setLocY(y);
//			}
//			
//			break;
//		default:
//			break;
//		}
	}

	@Override
	public void healthUp() {
		setHealth(getHealth() + 1);
	}

	@Override
	public void healthDown() {
		setHealth(getHealth() - 1);

	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void increaseSpeed() {
		this.setSpeed(getSpeed() + 1);
	}

	public void decreaseSpeed() {
		this.setSpeed(getSpeed() - 1);
	}

	public void setAnimations(HashMap<Direction, ArrayList<BufferedImage>> animations) {
		this.animations = animations;
	}

	public boolean getIsDead() {
		return isDead;
	}

	public void setIsDead(boolean state) {
		isDead = state;
	}

	@Override
	public void pickUp() {
		// TODO Auto-generated method stub
		
	}
}
