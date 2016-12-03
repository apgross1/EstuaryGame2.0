package controller;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import enums.Direction;
import models.AnimalModel;
import models.BarModel;
import models.ConcreteWallModelG1;
import models.ConcreteWallModelG1.ConcreteChunk;
import models.GabionWallModelG1;
import models.GabionWallModelG1.GabionChunk;
import view.Game1View;

import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game1Controller{
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//Models
	private AnimalModel animal = new AnimalModel(screenSize);
	ConcreteWallModelG1 wallModel = new ConcreteWallModelG1();
	GabionWallModelG1 gabionModel = new GabionWallModelG1();
	BarModel bar = new BarModel();
	//View
	Game1View g1view;
	
	//Vars
	private boolean gameState;
	
	private ArrayList<BufferedImage> gabSeq;// = new ArrayList<BufferedImage>(30);;
	//private BufferedImage gabSeq;
	int overallRound = 0;
	int waveHeight = screenSize.height;
	long gameTime;
	long countDownTime;
	long startTime;
	boolean intro;
	boolean wave;
	boolean countdown; //if were in the three second count down mode at the end of round
	
	
	public Game1Controller(JFrame gameF) {
		g1view = new Game1View(this, gameF);
	}
	
	//Getters
	public AnimalModel getAnimalModel(){
		return animal;
	}
	public BarModel getBarModel(){
		return bar;
	}
	public ConcreteWallModelG1 getWallModel(){
		return wallModel;
	}
	public GabionWallModelG1 getGabionWallModel(){
		return gabionModel;
	}
	public long getTime(){
		return (30 -(gameTime/1000));
	}
	public long getIntermTime(){
		return (3 -(countDownTime/1000));
	}
	public Dimension getDim(){
		return screenSize;
	}
	public boolean isIntro(){
		return intro;
	}
	public boolean isWave(){
		return wave;
	}
	public int getWaveY(){
		return waveHeight;
	}
	
	//Setters
	public void reset() {
		//This should reset all variables (except the bar), timer and all as we're going to have 3 sub rounds in game 1
		wallModel.reset();
		gabionModel.reset();
		g1view.removeAll();
		g1view.revalidate();
	
	}
	
	public void startGame(){
		gameState = true;
		//Draw into screen.
		startTime = System.currentTimeMillis();
		intro = true;
		while((System.currentTimeMillis()-startTime)<5000){
			g1view.repaintFrame();
		}
		intro = false;
		while(overallRound < 3 && (gameState == true)){
			System.out.println(this.gameState);
			round();
		}
		gameState = false;
		return;
	}
	
	
	public void round() {
		
		//Add intro animation here....
		
		
		startTime = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		final double ammountOfTicks = 60.0;	
		double ns = 1000000000 /ammountOfTicks;
		double delta = 0;
		//long timer = System.currentTimeMillis();
		
		
		
		long startTime = System.currentTimeMillis(); //fetch starting time
		
		while((System.currentTimeMillis()-startTime)<5000){
			if(!this.gameState) { //For testing purposes...just to close the game at will
				return;
			}
			gameTime = (System.currentTimeMillis() - startTime); //Used to print on screen
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime=now;
			if(delta>=1){
				animal.move();
				g1view.repaintFrame();
				delta--;
			}
			
			Random r = new Random();
			if(wallModel.getCurrentBlocks() <= (wallModel.getMaxBlocks()-5) & wallModel.getActiveBlocks() < 5){//Max concrete that can be on the screen at once.
				//Spawn a concrete block at a random location within the bounds of the board.
				//int Result = r.nextInt(High-Low) + Low;
				int randx = r.nextInt(screenSize.width);
				int randy = r.nextInt(screenSize.height - (int)(.45*(screenSize.height))) + (int)(.45*(screenSize.height)); //This is going to have to change depending on percet of screen.
				//Need a condition here to make sure that there is not already a chunk at that location.
				wallModel.spawnChunk(randx, randy);
			}
			
			if(gabionModel.getCurrentOysters() <= (gabionModel.getMaxOysters()-3) & gabionModel.getActiveClams() < 3){//Max concrete that can be on the screen at once.
				//Spawn a concrete block at a random location within the bounds of the board.
				//int Result = r.nextInt(High-Low) + Low;
				int randx = r.nextInt(screenSize.width);
				int randy = r.nextInt(screenSize.height - (int)(.45*(screenSize.height))) + (int)(.45*(screenSize.height)); //This is going to have to change depending on percet of screen.
				//Need a condition here to make sure that there is not already a chunk at that location.
				gabionModel.spawnChunk(randx, randy);
			}
			
			collisionDetectionLoop();
			g1view.repaintFrame();
		}
		//Do wave
		wave = true;
		startTime = System.currentTimeMillis();
		while((System.currentTimeMillis()-startTime)<3000){
			int pct = ((int)(System.currentTimeMillis()-startTime)*100)/3000;
			
			float j = ((float) pct)/100;
			
			//System.out.println(j);
			//System.out.println(pct);
			//System.out.println(Math.ceil((pct/100)));//*screenSize.height));
			
			waveHeight = (int) (screenSize.height - (Math.ceil(j*screenSize.height)));
			System.out.println(waveHeight);
			g1view.repaint();
		} //wait 3 seconds
		wave = false;

		
		
		gameTime = 30000; // Set the time to 30000 so the repaint says 0 time remaining.
		//Caclulate score and then reset for round 2
		//math fn 
		takeDamage();
		//one more paint
		g1view.repaintFrame();
		startTime = System.currentTimeMillis();
		countdown = true;
		while((System.currentTimeMillis()-startTime)<3000){ //Print the timer mid screen
			countDownTime = (System.currentTimeMillis() - startTime); //Used to print mid screen
			g1view.repaintFrame();
			
		}
		countdown = false;
		//reset vars
		reset();
		//set game round to 2/3
		overallRound++;
	}
	
	public boolean getInCountDown(){
		return countdown;
	}
	
	boolean collisionOccured(AnimalModel a, Object chunk){
		//Logic for seing if a collision has occurred (swift has this built in so I've been told?)
		
		Rectangle chunk_rect = null;
		Rectangle animal_rect = new Rectangle(a.getLocX(), a.getLocY(), a.getWidth(), a.getHeight());
		
		int chunk_x = a.getLocX();
		int chunk_y = a.getLocY();
		
		if(chunk instanceof GabionChunk){
			chunk_x = ((GabionChunk) chunk).getLocX();
			chunk_y = ((GabionChunk) chunk).getLocY();
			chunk_rect = new Rectangle(chunk_x, chunk_y, ((GabionChunk) chunk).getWidth(), ((GabionChunk) chunk).getHeight());
			
		}else if(chunk instanceof ConcreteChunk){
			chunk_x = ((ConcreteChunk) chunk).getLocX();
			chunk_y = ((ConcreteChunk) chunk).getLocY();
			chunk_rect = new Rectangle(chunk_x, chunk_y, ((ConcreteChunk) chunk).getWidth(), ((ConcreteChunk) chunk).getHeight());
		}
		
		if(animal_rect.getBounds().intersects(chunk_rect)){
			return true;
		}
		return false;
	}
	
	public void collisionDetectionLoop(){
		//In this loop collision detection for (crab +gabion), and (crab + wall) will be handled.
		Collection<GabionChunk> gabionChunkTemp = gabionModel.getChunks();
		Collection<ConcreteChunk> concreteChunkTemp = wallModel.getChunks();
		
		for(GabionChunk gc: gabionChunkTemp){
			if(gc.isActive()){
				if(collisionOccured(animal, gc)){
					gabionModel.addPiece(gc);
					}
				}
			}
		
		for(ConcreteChunk cc: concreteChunkTemp){
			if(cc.isActive()){
				if(collisionOccured(animal, cc)){
					wallModel.addPiece(cc);
					}
				}
			}
	}
	
	//This is called at the end of the round to determine mathamatically what damage is done to the health of the estuary.
	public void takeDamage() {
		//Get Vars
		int gabbionsCollected = gabionModel.getCurrentOysters();
		int concreteCollected = wallModel.getCurrentBlocks();
		
		//changed the gabion level to 4 so that it is harder
		int protection = (gabbionsCollected * 4) + (concreteCollected * 2);
		int new_status;
		
		//Prevent health going up
		if(protection <= 100){
			new_status = (bar.getStatus() - (bar.getMaxLevel() - protection));
		}else{
			new_status = (bar.getStatus() - (bar.getMaxLevel() - 100));
		}
		
		if(new_status > 0){
			bar.setStatus(new_status);
		}else{
			//Set to 0 and don't allow another round because the user lost game 1 overall.
			bar.setStatus(0);
			gameState = false;
			
		}
		
		//Break down the walls
		wallModel.breakDown();
		gabionModel.breakDown();
	}
	
	public boolean isGameState() {
		return gameState;
	}

	public void setGameState(boolean gameState) {
		this.gameState = gameState;
	}

}
