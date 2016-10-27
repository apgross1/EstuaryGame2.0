package controller;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enums.Direction;
import models.AnimalModel;
import models.BarModel;
import models.ConcreteWallModelG1;
import models.GabionWallModelG1;
import view.Game1View;

public class Game1Controller implements KeyListener {
	private ArrayList<Object> objects;
	private Game1View gameView;
	private boolean gameActive; // Added this instead of 2 bools blow
	//private boolean gameStart;
	//private boolean gameEnd;
	private Object tempObject;
	private boolean timeUp;
	private float currTime;
	private boolean gabHit;
	private boolean concrHit;
	private ArrayList<BufferedImage> landSeqs;
	private KeyListener e;
	
	//Models
	AnimalModel animal = new AnimalModel();
	ConcreteWallModelG1 wall = new ConcreteWallModelG1();
	GabionWallModelG1 gabion = new GabionWallModelG1();
	BarModel bar = new BarModel();
	//View
	Game1View g1view = new Game1View();
	
	public Game1Controller() {
		
	}
	
	public void startGame() {
		gameActive = true;
		//Add intro animation here....
		
		//Then start the game loop.
		while(gameActive){
			
		}
	}
	
	public void reset() {
		//This should reset all variables, timer and all as we're going to have 3 sub rounds in game 1
	}
	
	public void startTime() {
		
	}
	
	public void stopTime() {
		
	}
	
	public void collisionDetectionLoop(){
		//In this loop collision detection for (crab +gabion), and (crab + wall) will be handeled.
	}
	
	
	//Dont need this as we take care of collision detection
	public void pickedUp(ActionEvent evt) {
		
	}
	
	//This is called at the end of the round to determine mathamatically what damage is done to the health of the estuary.
	public void takeDamage() {
		//First determine what percent of the gabions + bricks are left. I say we make it that 75% of the gabions you collected remain
		//while only 10% of the bricks you collected remain. This will be standard and not have to do with the health of the sanctuary, just for visual.
		
		//Mathematically we can say the health of the estuary is initially 100.
		//Each gabion you collect can stop 5%(adjustable if need be) of the damage from that 100%
		//Each brick you collect will only stop 1%.
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//Not sure how to handle multiple keys pressed at once ie change dir to northeast southeast northwest and southwest.
	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	            // handle up 
	        	if(animal.getCurrDir() != Direction.NORTH){
	        		animal.setCurrDir(Direction.NORTH);
	        	}
	        	animal.move();
	        	
	            break;
	        case KeyEvent.VK_DOWN:
	            // handle down 
	            break;
	        case KeyEvent.VK_LEFT:
	            // handle left
	            break;
	        case KeyEvent.VK_RIGHT :
	            // handle right
	            break;
	     }
	} 

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//By level I mean bar level
	public void increaseLevel() {
		
	}
	
	public void decreaseLevel() {
		
	}
	
	public boolean quarterGone() {
		return false;
	}
	
	public boolean halfGone() {
		return false;
	}
	
	public boolean threeQuarterGone() {
		return false;
	}
	
	public boolean barFull() {
		return false;
	}
	
	public boolean barEmpty() {
		return false;
	}
	
	public void render(Graphics g) {
		
	}
	
	public void addObject(Object object) {
		objects.add(object);
	}
	
	public void removeObject(Object object) {
		
	}	
	
	public ArrayList<Object> getObjects() {
		return objects;
	}
	public void setObjects(ArrayList<Object> objects) {
		this.objects = objects;
	}
	public Game1View getGameView() {
		return gameView;
	}
	public void setGameView(Game1View gameView) {
		this.gameView = gameView;
	}
	public boolean isGameStart() {
		return gameStart;
	}
	public void setGameStart(boolean gameStart) {
		this.gameStart = gameStart;
	}
	public boolean isGameEnd() {
		return gameEnd;
	}
	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}
	public Object getTempObject() {
		return tempObject;
	}
	public void setTempObject(Object tempObject) {
		this.tempObject = tempObject;
	}
	public boolean isTimeUp() {
		return timeUp;
	}
	public void setTimeUp(boolean timeUp) {
		this.timeUp = timeUp;
	}
	public float getCurrTime() {
		return currTime;
	}
	public void setCurrTime(float currTime) {
		this.currTime = currTime;
	}
	public boolean isGabHit() {
		return gabHit;
	}
	public void setGabHit(boolean gabHit) {
		this.gabHit = gabHit;
	}
	public boolean isConcrHit() {
		return concrHit;
	}
	public void setConcrHit(boolean concrHit) {
		this.concrHit = concrHit;
	}
	public ArrayList<BufferedImage> getLandSeqs() {
		return landSeqs;
	}
	public void setLandSeqs(ArrayList<BufferedImage> landSeqs) {
		this.landSeqs = landSeqs;
	}

	public KeyListener getE() {
		return e;
	}

	public void setE(KeyListener e) {
		this.e = e;
	}

	
}
