package models;

import java.awt.Rectangle;
import java.util.Random;

public class WaveModel {
	private int height;
	private int width;
	private Pair location;
	
	public WaveModel() {
		int width = 30;
		int height = 30;
		this.randomSpawn();
	}
	
	public void randomSpawn() {
		Random rand = new Random();
		location = new Pair(0,0);
		location.setX(250);
		location.setY(rand.nextInt(500));
	}
	
	public void move() {
		
		//location.setX(location.getX()-1);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(location.getX(),location.getY(), this.getWidth(), this.getHeight());
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

	public Pair getLocation() {
		return location;
	}

	public void setLocation(Pair location) {
		this.location = location;
	}

}
