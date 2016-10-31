package models;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import models.BeachModel.Pair;
import models.ConcretePUModel.ConcPUState;

public class GabionPUModel extends WallModelAbstract {
	private boolean isActive;
	private boolean isPickedUp;
	private int PUonBeach;
	private Pair location;
	private BufferedImage powerUp;
	private BufferedImage wall;
	private GabPUState wallState;
	private Rectangle bounds;
	private int height;
	private int width;
	
	public GabionPUModel() {
		this.setWallState(GabPUState.POWER_UP);
		this.isActive = false;
		this.height = 10;
		this.width = 10;
		this.isPickedUp = false;
		this.bounds = new Rectangle(0, 0, width, height);
	}

	public boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(boolean active) {
		if(active) {
			this.setBounds(this.location.getX(), this.location.getY(), this.width, this.height);
		}
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
		
		return (new Rectangle(this.getLocation().getX(),this.getLocation().getY(),width,height));
		
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
		if(isPickedUp) {
			this.setWallState(GabPUState.WALL);
			this.width = 20;
			this.height = 50;
			this.setBounds(this.location.getX(), this.location.getY(), this.width, this.height);
		}
		else {
			this.width = 10;
			this.height = 10;
			//this.setBounds(this.location.getX(), this.location.getY(), this.width, this.height);
		}
		
		this.isPickedUp = isPickedUp;
	}


}
