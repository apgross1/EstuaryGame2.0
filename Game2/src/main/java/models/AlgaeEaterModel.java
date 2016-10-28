package models;

public class AlgaeEaterModel {
	private int locX;
	private int locY;
	private int health;
	public AlgaeEaterModel() {
		
	}
	
	public AlgaeEaterModel(int x, int y) {
		setLocX(x);
		setLocY(y);
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
}
