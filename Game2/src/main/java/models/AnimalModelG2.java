package models;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;


/**
 * @author Team 8
 *
 */

public class AnimalModelG2 extends AnimalModelCommon implements Serializable {


	private int velocity;
	private boolean isDead;
	private int height, width;
	private int y;
	Dimension size;
	int screenWidth;
	private int screenHeight;
	int charYBoundMin;
	int charYBoundMax;
	
	
	/**
	 * Constructs the animal based on screensize and sets initial variables
	 */
	public AnimalModelG2() {
		size = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) size.getWidth();
		screenHeight = (int) size.getHeight();
		
		charYBoundMin = (int) (screenHeight-(screenHeight*.1));
		charYBoundMax = (int) (screenHeight*.32);
		
		this.height = (int) (screenHeight*.11);
		this.width = (int)  (screenHeight*.11);
		
		this.isDead = false;
		this.velocity = 0;
		
		setLocY((int) (screenHeight/2)); }
	
	

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
	
	
	
	
	/**
	 * @return the height of the animal model
	 */
	public int getHeight() {
		return height;
		
	}

	/**
	 * @return the integer width of the animal model
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @param h the desired integer height
	 */
	public void setHeight(int h) {
		height = h;
		
	}
	/**
	 * Registers the width at the desired integer w
	 * @param w the desired integer height of the animal model
	 */
	public void setWidth(int w) {
		
		width = w;
		
	}
	
	/**
	 * @return the current velocity of the character
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * Registers the desired velocity of the character based on the parameter v 
	 * @param v the desired velocity of the character
	 */
	public void setVelocity(int v) {
		velocity = v;
	}

	/**
	 * Increases the velocity by a constant of 1
	 */
	public void increaseVelocity() {
		this.setVelocity(getVelocity() + 1);
	}
	
	/**
	 * Decreases the velocity by a constant of 1
	 */
	public void decreaseVelocity() {
		this.setVelocity(getVelocity() - 1);
	}


	/**
	 * Returns a boolean value indicating whether or not the character is alive
	 * @return returns whether or not the character is alive
	 */
	public boolean getIsDead() {
		return isDead;
	}

	/**
	 * Method to set whether or not the character is alive
	 * @param takes what the desired state of the player is to be set at
	 */
	public void setIsDead(boolean state) {
		isDead = state;
	}

	
	
	
	


	
}