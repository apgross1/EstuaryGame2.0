package models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import models.GabionPUModel.GabPUState;

public class ConcretePUModel extends WallModelAbstract {
	public enum ConcPUState {
		POWER_UP,WALL;
	}
	private boolean isActive;
	private boolean isPickedUp;
	private ConcPUState wallState;
	private HashMap<ConcPUState,ArrayList<BufferedImage>> graphics;
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
		this.height = 30;
		this.width = 30;
		this.isPickedUp = false;
		this.location = new Pair(0,0);
		this.viewLocation = new Pair(0,0);
		graphics = new HashMap<ConcPUState,ArrayList<BufferedImage>>();
		
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
	
	
	public void addPics() {
		try{
			ArrayList<BufferedImage> wallGraphic = new ArrayList<BufferedImage>();
			BufferedImage concreteWall = ImageIO.read(new File("./Images/Game3/ConcreteWall.png"));
			wallGraphic.add(concreteWall);
			
			ArrayList<BufferedImage> puGraphic = new ArrayList<BufferedImage>();
			BufferedImage pu = ImageIO.read(new File("./Images/Game3/ConcretePU.png"));
			puGraphic.add(pu);
			
			graphics.put(ConcPUState.WALL, wallGraphic);
			graphics.put(ConcPUState.POWER_UP, puGraphic);
			}
			catch(IOException e) {
	    		e.printStackTrace();
	    	}
	}
	
	public HashMap<ConcPUState, ArrayList<BufferedImage>> getGraphics() {
		return graphics;
	}
	public void setGraphics(HashMap<ConcPUState, ArrayList<BufferedImage>> graphics) {
		this.graphics = graphics;
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
			System.out.println("In wall form");
			this.width = 70;
			this.height = 150;
			this.getViewLocation().setX(this.getViewLocation().getX() + 30);
		}
		else {
			this.setWallState(ConcPUState.POWER_UP);
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
		this.viewLocation.setX((int)((this.location.getX()))*rand.nextInt(100));
		this.viewLocation.setY((int)(this.location.getY())*rand.nextInt(70));
		System.out.println("Concrete at: (" + this.getViewLocation().getX() + "," + this.getViewLocation().getY() + ")");
		
		
	}
}
