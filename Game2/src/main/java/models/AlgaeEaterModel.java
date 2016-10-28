package models;

import java.util.ArrayList;

public class AlgaeEaterModel {
	private int locX;
	private int locY;
	private boolean active;
	private int health;
	private ArrayList fishAnim;
	
	
	public AlgaeEaterModel() {
		
	}
	
	public AlgaeEaterModel(int x, int y) {
		
	}
	
	public void AEHealthUp(){
		
	}
	
	public void AEHealthDown(){
		
	}
	
	public void move(){
		
	}
	
	public void eaten(){
	}

		
	public void healthDown() {
		
	}
	
	public void healthUp() {
	
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList getFishAnim() {
		return fishAnim;
	}

	public void setFishAnim(ArrayList fishAnim) {
		this.fishAnim = fishAnim;
	}
}
