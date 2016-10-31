package models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import models.GabionPUModel.GabPUState;

public class ConcretePUModel extends WallModelAbstract {
	public enum ConcPUState {
		POWER_UP,WALL;
	}
	private boolean isActive;
	private boolean isPickedUp;
	private ConcPUState wallState;
	private BufferedImage powerUp;
	private BufferedImage wall;
	private Pair location;
	private Pair viewLocation;
	private Rectangle bounds;
	private int height;
	private int width;

	
	
	public ConcretePUModel() {
		setWallState(ConcPUState.POWER_UP);
		this.isActive = false;
		this.height = 10;
		this.width = 10;
		this.isPickedUp = false;
		this.location = new Pair(0,0);
		this.viewLocation = new Pair(0,0);
		
	}
	public ConcretePUModel(Pair loc) {
		location = loc;
		this.viewLocation = new Pair(0,0);
		this.setViewLocation(loc);
	}
	public boolean getIsActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		if(isActive) {
			this.setBounds(this.getViewLocation().getX(), this.getViewLocation().getY(), this.width, this.height);
		}
		this.isActive = isActive;
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
		this.setViewLocation(location);
	}
	
	@Override
	public void spawn(boolean gameStart, int numChunksRemoved) {
		
	}
	
	public void setBounds(int x, int y, int width, int height) {
		this.bounds = new Rectangle(x,y,width,height);
	}
	
	public Rectangle getBounds() {
		return (new Rectangle(this.getViewLocation().getX(),this.getViewLocation().getY(),width,height));
		
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
			this.setWallState(ConcPUState.WALL);
			this.width = 20;
			this.height = 50;
			this.setBounds(this.getViewLocation().getX(), this.getViewLocation().getY(), this.width, this.height);
		}
		else {
			this.setWallState(ConcPUState.POWER_UP);
			this.width = 10;
			this.height = 10;
			this.setBounds(this.getViewLocation().getX(), this.getViewLocation().getY(), this.width, this.height);
		}
		this.isPickedUp = isPickedUp;
	}
	public Pair getViewLocation() {
		return viewLocation;
	}
	public void setViewLocation(Pair viewLocation) {
		Random rand = new Random();
		this.viewLocation.setX(this.location.getX()*100);
		this.viewLocation.setY(this.location.getY()*(700/8));
		
	}
}
