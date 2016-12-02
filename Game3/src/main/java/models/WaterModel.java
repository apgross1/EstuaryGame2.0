package models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class WaterModel {
	private Pair location = new Pair(0,0);
	private int height;
	private int width;
	private boolean isActive;
	private ArrayList<BufferedImage> waterGraphics;
	private int graphicOnDeck;
	
	

	public WaterModel() {
		this.height = 200;
		this.width = 200;
		this.addPics();
	}
	
	
	public void addPics() {
		try{
			waterGraphics = new ArrayList<BufferedImage>();
			BufferedImage bufferedImage1 = ImageIO.read(new File("./Images/Game3/sand_with_water.png"));
			waterGraphics.add(bufferedImage1);
			BufferedImage bufferedImage2 = ImageIO.read(new File("./Images/Game3/tile_water_C.png"));
			waterGraphics.add(bufferedImage2);
			graphicOnDeck = 0;
			}
			catch(IOException e) {
	    		e.printStackTrace();
	    	}
			
	}
	
	public WaterModel(Pair loc) {
		location = loc;
	}
	public Pair getLocation() {
		return location;
	}
	public void setLocation(Pair location) {
		this.location = location;
	}
	
	public Rectangle getBounds() {
		return (new Rectangle(this.getLocation().getX(),this.getLocation().getY(),30,30));
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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public ArrayList<BufferedImage> getWaterGraphics() {
		return waterGraphics;
	}


	public void setWaterGraphics(ArrayList<BufferedImage> waterGraphics) {
		this.waterGraphics = waterGraphics;
	}


	public int getGraphicOnDeck() {
		return graphicOnDeck;
	}


	public void setGraphicOnDeck(int graphicOnDeck) {
		this.graphicOnDeck = graphicOnDeck;
	}
}
