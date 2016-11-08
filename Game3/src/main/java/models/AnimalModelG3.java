package models;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;

public class AnimalModelG3 extends AnimalModelAbstract{
	private HashMap<Direction,ArrayList<BufferedImage>> animations;
	private int health;

	private int emptyHanded;
	private boolean isDead;
	private int height;
	private int width;
	private int speedX;
	private int speedY;
	


	
	public AnimalModelG3() {
		this.setHeight(60);
		this.setWidth(60);
	}
	
	public void tick(){
		if ((getLocY() + speedY >= -60 & getLocX() + speedX >= -60) && (getLocY() + speedY <= 570 & getLocX()+ speedX < 776) ) {
			this.setLocX(this.getLocX() + speedX);
			this.setLocY(this.getLocY() + speedY);
		}
		else{
			
		}
	}
	
	@Override
	public void healthUp() {
		
		
	}

	@Override
	public void healthDown() {
		this.setHealth(0);
	}

	@Override
	public void pickUp() {
		
	}

	public HashMap<Direction,ArrayList<BufferedImage>> getAnimations() {
		return animations;
	}

	public void setAnimations(HashMap<Direction,ArrayList<BufferedImage>> animations) {
		this.animations = animations;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public int getHealth(){
		return health;
	}
	
	//Body may need to be deleted
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public Rectangle getBounds() {
		return (new Rectangle(this.getLocX(),this.getLocY(),this.getWidth(),this.getHeight()));
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speed) {
		this.speedX = speed;
	}
	
	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speed) {
		this.speedY = speed;
	}
}
