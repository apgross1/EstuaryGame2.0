package models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import models.BeachModel.Pair;

public class ConcretePUModel extends WallModelAbstract {
	public enum ConcPUState {
		POWER_UP,WALL;
	}
	private boolean isActive;
	private ConcPUState wallState;
	private BufferedImage powerUp;
	private BufferedImage wall;
	private Pair location;
	private Rectangle bounds;
	private int height;
	private int width;

	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public ConcretePUModel() {
		setWallState(ConcPUState.POWER_UP);
		this.setIsActive(false);
		this.height = 10;
		this.width = 10;
		
	}
	public boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(boolean active) {
		isActive = active;
	}
	
	@Override
	public void breakDown() {
	}
	
	public BufferedImage getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(BufferedImage powerUp) {
		this.powerUp = powerUp;
	}

	public BufferedImage getWall() {
		return wall;
	}

	public void setWall(BufferedImage wall) {
		this.wall = wall;
	}
	public ConcPUState getWallState() {
		return wallState;
	}
	public void setWallState(ConcPUState gameState) {
		this.wallState = gameState;
	}
	
	public Pair getLocation() {
		return location;
	}
	public void setLocation(Pair location) {
		this.location = location;
	}
	
	@Override
	public void spawn(boolean gameStart, int numChunksRemoved) {
		
	}
	
	public void setBounds(int x, int y, int width, int height) {
		this.bounds = new Rectangle(x,y,width,height);
	}
	
	public Rectangle getBounds() {
		return this.bounds;
		
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

}
