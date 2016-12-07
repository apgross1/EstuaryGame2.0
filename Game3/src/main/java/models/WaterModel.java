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
	
	

	/**
	 * Constructor for this element
	 */
	public WaterModel() {
		this.height = 200;
		this.width = 200;
		this.addPics();
	}
	
	
	/**
	 * Alternative constructor that passes in the location of the water tile   
	 * @param loc Pair, the location of the water tile
	 */
	public WaterModel(Pair loc) {
		location = loc;
	}
	
	/**
	 * Adds the graphics associates with this element. These are used to
	 * visually represent this element. 
	 */
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
	
	
	/**
	 * Gets location of the water tile
	 * @return location Pair, contains the x,y location of the water tile
	 */
	public Pair getLocation() {
		return location;
	}
	
	/**
	 * Sets location of the water tile
	 * @return location Pair, contains the x,y location of the water tile
	 */
	public void setLocation(Pair location) {
		this.location = location;
	}
	
	/**
	 * Gets the bounds of the water tile for collision detection and visuals
	 * @return Rectangle containing the bounds of the water tile
	 */
	public Rectangle getBounds() {
		return (new Rectangle(this.getLocation().getX(),this.getLocation().getY(),30,30));
	}

	/**
	 * Gets height of the water tile
	 * @return height int, height of the water tile
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets height of the water tile
	 * @param height int, desired height of the water tile
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets width of the water tile
	 * @return width int, width of the water tile
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets width of the water tile
	 * @param width int, desired width of the water tile
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Determines if the water tile is active. This would mean the water tile
	 * is placed on a grid block which changes the aesthetic and properties of the
	 * grid block
	 * @return isActive boolean, 1 if water tile is active, 0 otherwise
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets water tile is active or inactive. This would mean the water tile
	 * is placed on a grid block which changes the aesthetic and properties of the
	 * grid block
	 * @param isActive boolean, 1 if water tile is active, 0 otherwise
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



	/**
	 * Gets ArrayList of images visually representing this element
	 * @return waterGraphics ArrayList of BufferedImages
	 */
	public ArrayList<BufferedImage> getWaterGraphics() {
		return waterGraphics;
	}


	/**
	 * Sets ArrayList of images visually representing this element
	 * @param waterGraphics ArrayList of BufferedImages
	 */
	public void setWaterGraphics(ArrayList<BufferedImage> waterGraphics) {
		this.waterGraphics = waterGraphics;
	}


	/**
	 * Gets current graphic being panted for this element
	 * @return graphicOnDeck int, index number that corresponds to the animation array
	 */
	public int getGraphicOnDeck() {
		return graphicOnDeck;
	}


	/**
	 * Sets current graphic being panted for this element
	 * @param graphicOnDeck int, index number that corresponds to the animation array
	 */
	public void setGraphicOnDeck(int graphicOnDeck) {
		this.graphicOnDeck = graphicOnDeck;
	}
}
