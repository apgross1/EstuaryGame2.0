package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import models.BeachModel.Pair;

public class GabionPUModel extends WallModelAbstract {
	private boolean isActive;
	private int PUonBeach;
	private Pair location;
	private BufferedImage powerUp;
	private BufferedImage wall;
	private int GabionPUonbeach;
	private GabPUState wallState;

	public GabionPUModel() {
		this.setWallState(GabPUState.POWER_UP);
		this.setIsActive(false);
	}

	public boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(boolean active) {
		isActive = active;
	}
	//Spawning for PU placement

	@Override
	public void breakDown() {
		
	}
	public BufferedImage getWall() {
		return wall;
	}
	public void setWall(BufferedImage wall) {
		this.wall = wall;
	}
	public BufferedImage getPowerUp() {
		return powerUp;
	}
	public void setPowerUp(BufferedImage powerUp) {
		this.powerUp = powerUp;
	}
	
	public void setGabionPUonBeach(int PUnum){
		this.PUonBeach = PUnum;
	}
	
	public int getGabionPUonBeach(){
		return PUonBeach;
	}
	
	
	public enum GabPUState{
		POWER_UP, WALL;
	}
	public GabPUState getWallState() {
		return wallState;
	}
	public void setWallState(GabPUState gameState) {
		this.wallState = gameState;
	}

	@Override
	public void spawn(boolean gameStart, int numChunksRemoved) {
		// TODO Auto-generated method stub
		
	}

	public Pair getLocation() {
		return location;
	}

	public void setLocation(Pair location) {
		this.location = location;
	}

}
