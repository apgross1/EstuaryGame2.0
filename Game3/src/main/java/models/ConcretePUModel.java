package models;

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

	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public ConcretePUModel() {
		setWallState(ConcPUState.POWER_UP);
		this.setIsActive(true);
		
	}
	public boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(boolean active) {
		isActive = active;
	}
	
	@Override
	public void breakDown() {
		this.setIsActive(false);
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
}
