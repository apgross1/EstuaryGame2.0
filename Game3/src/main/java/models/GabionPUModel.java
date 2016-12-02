package models;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Enums.Frames;
import models.ConcretePUModel.ConcPUState;

public class GabionPUModel extends WallModelAbstract {
	private boolean isActive;
	private boolean isPickedUp;
	private int PUonBeach;
	private Pair location;
	private Pair viewLocation;
	private HashMap<GabPUState, ArrayList<BufferedImage>> graphics;
	private HashMap<Frames, JComponent> frameMap;
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
		graphics = new HashMap<GabPUState, ArrayList<BufferedImage>>();
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

	public void addPics() {
		try{
			ArrayList<BufferedImage> wallGraphic = new ArrayList<BufferedImage>();
			BufferedImage concreteWall = ImageIO.read(new File("./Images/Game3/GabionWall.png"));
			wallGraphic.add(concreteWall);
			
			ArrayList<BufferedImage> puGraphic = new ArrayList<BufferedImage>();
			BufferedImage pu = ImageIO.read(new File("./Images/Game3/GabionPU.png"));
			puGraphic.add(pu);
			
			graphics.put(GabPUState.WALL, wallGraphic);
			graphics.put(GabPUState.POWER_UP, puGraphic);
			}
			catch(IOException e) {
	    		e.printStackTrace();
	    }
	}
	
	public HashMap<GabPUState, ArrayList<BufferedImage>> getGraphics() {
		return graphics;
	}

	public void setGraphics(HashMap<GabPUState, ArrayList<BufferedImage>> graphics) {
		this.graphics = graphics;
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

	public void setViewLocation(Pair loc) {
		int tileWidth = (int)((frameMap.get(Frames.ANIMAL).getWidth()+frameMap.get(Frames.SHORE).getWidth())/7);
		int tileHeight = (int)((frameMap.get(Frames.ANIMAL).getWidth()+frameMap.get(Frames.SHORE).getWidth())/7);
		this.viewLocation.setX((int)((loc.getX()))*tileWidth);
		this.viewLocation.setY((int)(loc.getY())*tileHeight);
	}

	public HashMap<Frames, JComponent> getFrameMap() {
		return frameMap;
	}

	public void setFrameMap(HashMap<Frames, JComponent> frameMap) {
		this.frameMap = frameMap;
	}
	
	

}
