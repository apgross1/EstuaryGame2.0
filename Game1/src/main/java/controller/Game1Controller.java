package controller;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import view.Game1View;

public class Game1Controller implements KeyListener {
	private ArrayList<Object> objects;
	private Game1View gameView;
	private boolean gameStart;
	private boolean gameEnd;
	private Object tempObject;
	private boolean timeUp;
	private float currTime;
	private boolean gabHit;
	private boolean concrHit;
	private ArrayList<BufferedImage> landSeqs;
	private KeyListener e;
	
	
	public Game1Controller() {
		
	}
	
	public Game1Controller(Game1View view) {
		this.gameView = view;
		view.getFrame().addKeyListener(this);
	}
	public void reset() {
		
	}
	
	public void startTime() {
		
	}
	
	public void stopTime() {
		
	}
	
	public void pickedUp(ActionEvent evt) {
		
	}
	
	public void takeDamage() {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
		
	}
	
	public void removeObject(Object object) {
		
	}
	
	public void endGame() {
		
	}
	
	public void startGame() {
		
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
