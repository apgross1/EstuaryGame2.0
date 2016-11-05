package models;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import models.ConcretePUModel.ConcPUState;

public class GabionPUModel extends WallModelAbstract {
	private boolean isActive;
	private boolean isPickedUp;
	private int PUonBeach;
	private Pair location;
	private Pair viewLocation;

	private BufferedImage powerUp;
	private BufferedImage wall;
	private GabPUState wallState;
	private Rectangle bounds;
	private int height;
	private int width;
	
	public GabionPUModel() {
		this.setWallState(GabPUState.POWER_UP);
		this.isActive = false;
		this.height = 30;
		this.width = 30;
		this.isPickedUp = false;
		this.location = new Pair(0,0);
		this.viewLocation = new Pair(0,0);
	}

	public boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(boolean active) {
		if(active) {
			this.setBounds(this.getViewLocation().getX(), this.getViewLocation().getY(), this.width, this.height);
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
		this.setViewLocation(location);
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
			this.setWallState(GabPUState.WALL);
			System.out.println("In wall form");
			System.out.println("Bounds are: " + this.getBounds().getX());
			this.width = 70;
			this.height = 150;
			this.getViewLocation().setX(this.getViewLocation().getX() + 30);
		}
		else {
			this.setWallState(GabPUState.POWER_UP);
			this.width = 30;
			this.height = 30;
			this.setBounds(this.getViewLocation().getX(), this.getViewLocation().getY(), this.width, this.height);
		}
		
		this.isPickedUp = isPickedUp;
	}
	
	public Pair getViewLocation() {
		return viewLocation;
	}

	public void setViewLocation(Pair viewLocation) {
		Random rand = new Random();
		this.viewLocation.setX((int)((this.location.getX()*rand.nextInt(100))));
		this.viewLocation.setY((int)(this.location.getY()*rand.nextInt(70)));
		System.out.println("Gabion at: (" + this.getViewLocation().getX() + "," + this.getViewLocation().getY() + ")");
	}
	
	

}
