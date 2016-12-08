package controller;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Random;

import models.AnimalModel;
import models.BarModel;
import models.ConcreteWallModelG1;
import models.ConcreteChunk;
import models.GabionWallModelG1;
import models.GabionChunk;
import view.Game1View;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.RectangularShape;

import javax.swing.JFrame;

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
	
	int overallRound = 0;
	int waveHeight = screenSize.height;
	long gameTime;
	long countDownTime;
	long startTime;
	boolean intro;
	boolean wave;
	boolean gameOneWon = false;
	boolean gameOver = false;
	boolean countdown; //if were in the three second count down mode at the end of round
	
	
	/**
	 * creates an instance of the view for the controller to use
	 * doesn't return anything but creates a view
	 * @param jframe common jframe to link all games
	 * 
	 */
	public Game1Controller(JFrame gameF) {
		g1view = new Game1View(this);
	}
	//for testing purposes
	/**
	 * makes an instance of Game1Controller strictly for
	 * testing purposes
	 */
	public Game1Controller(){
	
		
	}
	
	//Getters
	/**
	 * creates an instance of the animal model for the controller to use
	 * @return instance of the animal model 
	 */
	public AnimalModel getAnimalModel(){
		return animal;
	}
	/**
	 * creates an instance of the bar model for the controller to use
	 * @return instance of the bar model
	 */
	public BarModel getBarModel(){
		return bar;
	}
	/**
	 * creates an instance of the concrete wall model for the controller to use
	 * @return instance of the concrete wall model
	 */
	public ConcreteWallModelG1 getWallModel(){
		return wallModel;
	}
	/**
	 * creates an instance of the gabion wall model for the controller to use
	 * @return instance of the gabion wall model
	 */
	public GabionWallModelG1 getGabionWallModel(){
		return gabionModel;
	}
	/**
	 * gets the time of the game during rounds in the appropriate format (seconds)
	 * @return a long value with the time
	 */
	public long getTime(){
		return (29 -(gameTime/1000));
	}
	/**
	 * gets the time between rounds in the appropriate format (seconds)
	 * @return a long value with the middle time
	 */
	public long getIntermTime(){
		return (3 -(countDownTime/1000));
	}
	/**
	 * gets the size of someones screen so that the game is dynamic in size
	 * @return a Dimension of the size of the screen
	 */
	public Dimension getDim(){
		return screenSize;
	}
	/**
	 * tells whether it is the introduction tutorial or not
	 * @return a boolean of whether its the intro or not
	 */
	public boolean isIntro(){
		return intro;
	}
	/**
	 * tells whether it is after the round when a wave appears
	 * @return a boolean of whether the wave is happening or not
	 */
	public boolean isWave(){
		return wave;
	}
	/**
	 * shows what height the wave is at as it travles the screen
	 * @return an integer of where the wave is on y axis
	 */
	public int getWaveY(){
		return waveHeight;
	}
	/**
	 * tells whether the game is over or not
	 * @return a boolean of whether the game is done or not
	 */
	public boolean getIsGameOver(){
		return gameOver;
	}
	/**
	 * tells whether the game is over and you have won the game
	 * @return a boolean of whether you have won or not
	 */
	public boolean isWin(){
		return gameOneWon;
	}
	/**
	 * tells what round is currently happening
	 * @return an int of what round it is
	 */
	public int getRound(){
		return overallRound;
	}
	
	//Setters
	/**
	 * resets all of the variables that aren't the bar model and timer
	 */
	public void reset() {
		//This should reset all variables (except the bar), timer and all as we're going to have 3 sub rounds in game 1
		wallModel.reset();
		gabionModel.reset();
		g1view.removeAll();
		g1view.revalidate();
	
	}
	
	/**
	 * this starts the game and the timer that goes along with it, runs the entire game and updates gamestate
	 */
	public void startGame(){
		gameState = true;
		//Draw into screen.
		g1view.setUp();
		
		startTime = System.currentTimeMillis();
		intro = true;
		while((System.currentTimeMillis()-startTime)<5000){
			g1view.repaintFrame();
		}
		intro = false;
		while(overallRound < 3 && (gameState == true)){
			overallRound++;
			round();
		}
		gameState = false;
		return;
	}
	
	
	/**
	 * provides the timer for each round, also spawns oyesters at 
	 * the beginning of each round, also starts the wave at the end of
	 * each round and provides win and loss screens
	 */
	
	public void round() {
		startTime = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		final double ammountOfTicks = 60.0;	
		double ns = 1000000000 /ammountOfTicks;
		double delta = 0;
		
		long startTime = System.currentTimeMillis(); //fetch starting time
		
		while((System.currentTimeMillis()-startTime)<30){
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
			
			waveHeight = (int) ((screenSize.height) - (Math.ceil(j*(screenSize.height+500))));
			
			g1view.repaintFrame();
		}//wait 3 seconds
		wave = false;
		waveHeight = screenSize.height;

		
		
		gameTime = 30000; // Set the time to 30000 so the repaint says 0 time remaining.
		//Caclulate score and then reset for round 2
		//math fn 
		takeDamage();
		//one more paint
		g1view.repaintFrame();
		startTime = System.currentTimeMillis();
		countdown = true;
		
			if(overallRound == 3 & bar.getStatus() > 0){
				//paint you win.
				System.out.println("You won.");
				gameOneWon = true;
				gameOver = true;
			}else if(bar.getStatus() <= 0){
				//you lose.
				gameOneWon = false;
				gameOver = true;
			}
		
		while((System.currentTimeMillis()-startTime)<3000){ //Print the timer mid screen
			countDownTime = (System.currentTimeMillis() - startTime); //Used to print mid screen
			g1view.repaintFrame();
			
		}
		countdown = false;
		//reset vars
		reset();
		//set game round to 2/3
		//overallRound++;
	}
	
	/**
	 * tells wether the countodwn between rounds is happening
	 * @return a bool of wether the coutdown is happening or not
	 */
	public boolean getInCountDown(){
		return countdown;
	}
	
	/**
	 * method that takes in the animal and a chunk (gabion or concrete)
	 * and determines if the animal and the chunk collide or not 
	 * @param a the animal itself
	 * @param chunk which is either a gabion or concrete chunk
	 * @return a boolean that tells if a collision occurred or not
	 */
	public boolean collisionOccured(AnimalModel a, Object chunk){
		//Logic for seeing if a collision has occurred (swift has this built in so I've been told?)
		
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
	
	/**
	 * uses the collision occurred method to tell if a collision occurred, and if it did
	 * add whichever chunk (gabion or concrete) is hit, add it to the wall
	 */
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
	/**
	 * determines how much damage the overall health takes at the end of the round
	 */
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
			//new_status = (bar.getStatus() - protection);
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
	
	/**
	 * is a getter for what the game state is (in game one or not)
	 * @return a boolean to tell whether the game is on or not
	 */
	public boolean isGameState() {
		return gameState;
	}

	/**
	 * takes in a game state and uses it to set the overall gameState
	 * @param gameState of game 1 
	 */
	public void setGameState(boolean gameState) {
		this.gameState = gameState;
	}
	
	public boolean difficulty(){
		if(bar.getStatus() <= 20 && overallRound < 3){
			return true;
		}
		else{
			return false;
		}
	}
	public Game1View getG1view() {
		return g1view;
	}
	public void setG1view(Game1View g1view) {
		this.g1view = g1view;
	}
	
}
