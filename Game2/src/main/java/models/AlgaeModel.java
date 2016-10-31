package models;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;

public class AlgaeModel {
	private int locX;
	private int locY;
	private int health;
	private boolean isActive;
	int height = 20;
	int width = 20;
	
	int randomYBound =0;
	Random rand = new Random();
	
	int algaeXBoundMax = 1000;
	int algaeYBoundMax = 600;
	int algaeYBoundMin = 100;
	int maxAlgaeNum = 30;
	
	public AlgaeModel() {
		randomYBound = rand.nextInt((algaeYBoundMax - algaeYBoundMin) + 1) + algaeYBoundMin;
		
	}
	
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public int getRandomYLocation(){
		return randomYBound;
	}
	
	public void move(){
		this.setLocX(getLocX()-1);
	}
	
	public void eaten() {
		this.setActive(false);
	}
	
	public void spawnAlgaeModel() {
		
		
		this.setActive(true);
	
		setLocX(950);
		setLocY(getRandomYLocation());
		
	}
	
	public int getMaxAlgae(){
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
