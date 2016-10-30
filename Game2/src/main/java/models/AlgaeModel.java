package models;

import java.util.Collection;

public class AlgaeModel {
	private int locX;
	private int locY;
	private int health;
	private boolean isActive;
	
	
	public AlgaeModel() {
		
	}
	
	public void eaten() {
		this.setActive(false);
	}
	
	public AlgaeModel(int x, int y) {
		setLocX(x);
		setLocY(y);
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
