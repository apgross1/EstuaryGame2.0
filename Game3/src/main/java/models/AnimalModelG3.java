package models;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Enums.Frames;
import enums.Direction;
import view.Game3View.Animal;

public class AnimalModelG3 extends AnimalModelAbstract{
	private HashMap<String,ArrayList<BufferedImage>> graphics;
	private int graphicOnDeck;
	private int health;

	private int emptyHanded;
	private boolean isDead;
	private int height;
	private int width;
	private int speedX;
	private int speedY;
	private int frameWidth;
	private int frameHeight;
	private boolean boundHit;
	private boolean restrictedMovement;
	private boolean wallHit = false;
	private boolean waveHit = false;
	private Pair beachLocation = new Pair(0,0);
	private HashMap<Frames, JComponent> frames;


	

	public AnimalModelG3() {
		this.setHeight(60);
		this.setWidth(60);
		graphics = new HashMap<String, ArrayList<BufferedImage>>();
		boundHit = false;
		restrictedMovement = false;
	
	}
	
	public void tick(){
		if (((getLocY() + speedY >= 0) & (this.getBounds().getMaxX() + speedX <= this.getFrameWidth())) && 
		   ((this.getBounds().getMaxY() + speedY <= this.getFrameHeight()) & getLocX()+ speedX >= 0)
			&& (!this.isWallHit()) && (!isRestrictedMovement())) {
			graphicOnDeck = (graphicOnDeck+1) % graphics.get("MOVE").size();
			this.setLocX(this.getLocX() + speedX);
			this.setLocY(this.getLocY() + speedY);
		}
	}
	
	public void addPics(){

		try{
		ArrayList<BufferedImage> moveAnimations = new ArrayList<BufferedImage>();
		BufferedImage bufferedImage1 = ImageIO.read(new File("./Images/Game3/bluecrab_0.png"));
		moveAnimations.add(bufferedImage1);
		BufferedImage bufferedImage2 = ImageIO.read(new File("./Images/Game3/bluecrab_1.png"));
		moveAnimations.add(bufferedImage2);
		BufferedImage bufferedImage3 = ImageIO.read(new File("./Images/Game3/bluecrab_2.png"));
		moveAnimations.add(bufferedImage3);
		graphics.put("MOVE", moveAnimations);
		graphicOnDeck = 0;
		}
		catch(IOException e) {
    		e.printStackTrace();
    	}
		
	}
	
	public int getGraphicOnDeck() {
		return graphicOnDeck;
	}

	public void setGraphicOnDeck(int graphicOnDeck) {
		this.graphicOnDeck = graphicOnDeck;
	}

	public Rectangle getBounds() {
		return (new Rectangle(this.getLocX(),this.getLocY(),this.getWidth(),this.getHeight()));
	}
	
	
	public void findBeachLocation() {
		int tileHeight = ((frames.get(Frames.SHORE).getHeight()))/7;
		int tileWidth = ((frames.get(Frames.ANIMAL).getWidth()+frames.get(Frames.SHORE).getWidth()))/7;
		
		
		this.beachLocation.setX((int)(Math.floor(this.getLocX())/tileWidth));
		this.beachLocation.setY((int)(Math.ceil(this.getLocY())/tileHeight));
		
		
	}
	
	public void resetPos() {
		this.setLocX(250);
		this.setLocY(250);
		this.setSpeedX(0);
		this.setSpeedY(0);
	}
	
	@Override
	public void healthUp() {
		
		
	}

	@Override
	public void healthDown() {
		this.setHealth(0);
	}

	@Override
	public void pickUp() {
		
	}

	public HashMap<String, ArrayList<BufferedImage>> getGraphics() {
		return graphics;
	}

	
	public void setHealth(int health){
		this.health = health;
	}
	
	
	public int getHealth(){
		return health;
	}
	
	
	//Body may need to be deleted
	@Override
	public void move() {
		
	}


	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
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

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speed) {
		this.speedX = speed;
	}
	
	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speed) {
		this.speedY = speed;
	}
	

	public boolean isBoundHit() {
		return boundHit;
	}

	public void setBoundHit(boolean boundHit) {
		this.boundHit = boundHit;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public Pair getPotentialMove() {
		return beachLocation;
	}

	public HashMap<Frames, JComponent> getFrames() {
		return frames;
	}

	public void setFrames(HashMap<Frames, JComponent> frames) {
		this.frames = frames;
	}

	public boolean isWallHit() {
		return wallHit;
	}

	public void setWallHit(boolean b) {
		wallHit = b;
		
	}
	
	public boolean isRestrictedMovement() {
		return restrictedMovement;
	}

	public void setRestrictedMovement(boolean restrictedMovement) {
		this.restrictedMovement = restrictedMovement;
	}

	public boolean isWaveHit() {
		return waveHit;
	}

	public void setWaveHit(boolean waveHit) {
		this.waveHit = waveHit;
	}
}
