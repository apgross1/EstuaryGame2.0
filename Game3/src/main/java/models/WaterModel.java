package models;

import java.awt.Rectangle;
import java.util.Random;

public class WaterModel {
	private Pair location = new Pair(0,0);
	private int height;
	private int width;
	private boolean isActive;
	
	

	public WaterModel() {
		this.height = 200;
		this.width = 200;
	}
	
	public WaterModel(Pair loc) {
		location = loc;
	}
	public Pair getLocation() {
		return location;
	}
	public void setLocation(Pair location) {
		this.location = location;
	}
	
	public Rectangle getBounds() {
		return (new Rectangle(this.getLocation().getX(),this.getLocation().getY(),30,30));
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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
