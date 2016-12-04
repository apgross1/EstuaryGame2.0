package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AlgaeEaterModel {
	private int locX;
	private int locY;
	private boolean active;
	private int health;
	private ArrayList <BufferedImage> fishAnimation;
	private AnimalModelG2 animalModel ;
	
	public AlgaeEaterModel() {
		//animalModel = new AnimalModelG2();
		this.setLocX(animalModel.getLocX());
		this.setLocY(animalModel.getLocY());
		this.setHealth(100);
		this.setActive(true);
	}
	
	
	public void move(){
		this.setLocY(animalModel.getLocY());
	}
		
		
	public void healthDown() {
		this.setHealth(getHealth()-1);
	}
	
	public void healthUp() {
		this.setHealth(getHealth()+1);
	}
	
	public int getLocY() {
		return locY;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}

	public int getLocX() {
		return locX;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean getIsActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList getFishAnim() {
		return fishAnimation;
	}

	public void setFishAnim(ArrayList fishAnim) {
		this.fishAnimation = fishAnim;
	}
}
