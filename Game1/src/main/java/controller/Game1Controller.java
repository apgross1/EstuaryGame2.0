package controller;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
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

public class Game1Controller{
	//Models
	private AnimalModel animal = new AnimalModel();
	ConcreteWallModelG1 wallModel = new ConcreteWallModelG1();
	GabionWallModelG1 gabionModel = new GabionWallModelG1();
	BarModel bar = new BarModel();
	//View
	Game1View g1view = new Game1View(this);
	
	//Vars
	private boolean gameActive;
	private ArrayList<BufferedImage> landSeqs;
	
	public Game1Controller() {
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
	//Setters
	public void setGameState(boolean b){
		gameActive = b;
	}
	public void reset() {
		//This should reset all variables (except the bar), timer and all as we're going to have 3 sub rounds in game 1
	}
	
	
	public void startGame() {
		gameActive = true;
		
		//Add intro animation here....
		
		while(gameActive){
			
			Random r = new Random();
			
			if(wallModel.getCurrentBlocks() < (wallModel.getMaxBlocks()-5) & wallModel.getActiveBlocks() < 5){//Max concrete that can be on the screen at once.
				//Spawn a concrete block at a random location within the bounds of the board.
				//int Result = r.nextInt(High-Low) + Low;
				int randx = r.nextInt(1000);
				int randy = r.nextInt(560-50) + 50;
				//Need a condition here to make sure that there is not already a chunk at that location.
				wallModel.spawnChunk(randx, randy);
			}
			
			if(gabionModel.getCurrentOysters() < (gabionModel.getMaxOysters()-5) & gabionModel.getActiveClams() < 5){//Max concrete that can be on the screen at once.
				//Spawn a concrete block at a random location within the bounds of the board.
				//int Result = r.nextInt(High-Low) + Low;
				int randx = r.nextInt(1000);
				int randy = r.nextInt(650-50) + 50;
				//Need a condition here to make sure that there is not already a chunk at that location.
				gabionModel.spawnChunk(randx, randy);
			}
			
			collisionDetectionLoop();
			g1view.repaintFrame();
		}
		//Caclulate score and then reset for round 2
		//math fn
		takeDamage();
		//one more paint
		g1view.repaintFrame();
		//set game round to 2/3
		//reset vars
		//restart game

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
		
		int protection = (gabbionsCollected * 5) + (concreteCollected * 1);
		int new_status = (bar.getStatus() - (bar.getMaxLevel() - protection));
		
		bar.setStatus(new_status);
		
		
		//Break down the walls
		wallModel.breakDown();
		gabionModel.breakDown();
	}
}
