package models;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;

public class AnimalModelG2 extends AnimalModelAbstract {

	private int velocity;
	private boolean isDead;
	private int height, width;
	private int y;
	Dimension size;
	int screenWidth;
	private int screenHeight;
	int charYBoundMin;
	int charYBoundMax;
	
	public AnimalModelG2() {
		size = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) size.getWidth();
		screenHeight = (int) size.getHeight();
		
		charYBoundMin = (int) (screenHeight-(screenHeight*.1));
		charYBoundMax = (int) (screenHeight*.32);
		
		this.height = (int) (screenHeight*.15);
		this.width = (int)  (screenHeight*.15);
		
		this.isDead = false;
		this.velocity = 0;
		
		setLocY((int) (screenHeight/2));
	}
	
	
	@Override
	public void move() {
		if(y>charYBoundMax  && velocity<0)
		{
		y+=velocity;
		}
		else if( y<charYBoundMin && velocity>0 ){
			y+=velocity;
		}
		
	}
	@Override
	public int getLocY(){
		return y;
	}
	
	@Override
	public void setLocY(int loc){
		y = loc;
	}
	
	@Override
	public void healthUp() {
		setHealth(getHealth() + 1);
	}

	@Override
	public void healthDown() {
		setHealth(getHealth() - 1);

	}
	@Override
	public void pickUp() {
		//Not Needed
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public int setHeight(int h) {
		return height = h;
		
	}
	public int setWidth(int w) {
		
		return width = w;
		
	}
	
	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int v) {
		velocity = v;
	}

	public void increaseVelocity() {
		this.setVelocity(getVelocity() + 1);
	}

	public void decreaseVelocity() {
		this.setVelocity(getVelocity() - 1);
	}


	public boolean getIsDead() {
		return isDead;
	}

	public void setIsDead(boolean state) {
		isDead = state;
	}

	
	
	
	


	
}
