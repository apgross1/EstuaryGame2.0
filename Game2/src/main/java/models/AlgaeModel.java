package models;

import java.util.Random;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;

public class AlgaeModel {
	private int locX;
	private int locY;
	private int health;
	private boolean isActive;
	
	private int speed = 2;
	private int randomYBound = 0;
	Random rand = new Random();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth = (int) screenSize.getWidth();
	int screenHeight = (int) screenSize.getHeight();
	private int height = (int) (screenHeight*.15);
	private int width = (int) (screenHeight*.15);
	int algaeXBoundMax = screenWidth;
	int algaeYBoundMax = (int) (screenHeight-(screenHeight*.1));
	int algaeYBoundMin = (int) (screenHeight*.32);
	int maxAlgaeNum = 100;
	int riverSpawnX ;
	int riverSpawnY ;
	
	

	public AlgaeModel() {
		
		randomYBound = rand.nextInt((algaeYBoundMax - algaeYBoundMin) + 1) + algaeYBoundMin;

		riverSpawnX = (int) (screenWidth*.87);
	    riverSpawnY = (int) (screenHeight*.11);
	    
	}
	


	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public int getRandomYLocation() {
		return randomYBound;
	}

	public void move() {
		this.setLocX(getLocX() - speed);
	}
	public void moveRiverAlgae() {
		riverSpawnX += speed;
	}
	public int getRiverAlgaeY(){
		return riverSpawnY;
	}
	public int getRiverAlgaeX(){
		return riverSpawnX;
	}

	public void eaten() {
		this.setActive(false);
	}

	public void spawnAlgaeModel() {

		this.setActive(true);
		setLocX((int) (screenWidth+(screenWidth*.1)));
		setLocY(getRandomYLocation());
	}

	public int getMaxAlgae() {
		return maxAlgaeNum;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
