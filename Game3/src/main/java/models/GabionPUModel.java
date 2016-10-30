package models;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import models.BeachModel.Pair;

public class GabionPUModel extends WallModelAbstract {
	private boolean isActive;
	private boolean isPickedUp;
	private int PUonBeach;
	private Pair location;
	private BufferedImage powerUp;
	private BufferedImage wall;
	private int GabionPUonbeach;
	private GabPUState wallState;
	private Rectangle bounds;
	private int height;
	private int width;
	
	public GabionPUModel() {
		this.setWallState(GabPUState.POWER_UP);
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
	
	public boolean isPickedUp() {
		return isPickedUp;
	}

	public void setPickedUp(boolean isPickedUp) {
		this.isPickedUp = isPickedUp;
	}


}
