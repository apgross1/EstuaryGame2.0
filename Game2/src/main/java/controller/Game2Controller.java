package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;


import models.AlgaeModel;
import models.AnimalModelG2;


import view.Game2View;
import view.Game2View.Animation;

public class Game2Controller {
	private boolean gameActive;
	private Game2View view;
	private AnimalModelG2 animal;
	
	private AlgaeModel algae;
	private Collection<AlgaeModel> algaeList = new ArrayList<AlgaeModel>();
	
	private JFrame gameFrame;
	long spawnTime=0;
	int numMissed = 0;
	long startTime;
	int updates = 0;
	int frames = 0;
	
	int spawnDelay = 2000; //in milliseconds
	boolean isStorming = false;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	public Game2Controller(JFrame gamef) {
		gameFrame = gamef;
		animal = new AnimalModelG2();
		
		algae = new AlgaeModel();
		
		
	}

	
	
	public void startGame() {
		gameFrame.getContentPane().removeAll();
		gameFrame.revalidate();
		view = new Game2View(this, gameFrame, screenSize);
		view.addController(this);
    	gameFrame.getContentPane().add(view.new Animation());
    	gameFrame.setBackground(Color.GRAY);
    	gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	gameFrame.setSize(1000, 700);
    	gameFrame.setVisible(true);
    	gameFrame.setResizable(false);
		gameActive = true;

    	
		startTime = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		final double ammountOfTicks = 60.0;	
		double ns = 1000000000 /ammountOfTicks;
		double delta = 0;
		
		long stormTimer = System.currentTimeMillis();
		
		while(gameActive){
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime=now;
			if(delta>=1){
				animal.move();
				view.repaintFrame();
				updates++;
				delta--;
			}
			
			frames++;
			collisionDetection();
			
			if(System.currentTimeMillis()-stormTimer>10000){
				stormTimer+=10000;
				if(getStormStatus()==true){
					deactivateStorm();
				}
				else{
					activateStorm();
				}
				
			}
			
			
			
			
			if(algaeList.size()<algae.getMaxAlgae()){
				if(System.currentTimeMillis()>=spawnTime+getSpawnDelay()){
				spawnAlgae();
				spawnTime = System.currentTimeMillis();
				
				}
				
			}
			
		}
	
	}
	
	public boolean getStormStatus(){
		return isStorming;
	}
	public void activateStorm(){
	
		isStorming = true;
		setSpawnDelay(getSpawnDelay()-1700);
	}
	public void deactivateStorm(){
		
		isStorming = false;
		setSpawnDelay(getSpawnDelay()+1700);
	}
	public int getSpawnDelay(){
		return spawnDelay;
	}
	public void setSpawnDelay(int delay){
		spawnDelay = delay;
	}
	
	public long getGameTime(){
		return (System.currentTimeMillis()-startTime)/1000;
	}
	
	public void addNumMissed(){
		numMissed++;
	}
	public int getNumMissed(){
		return numMissed;
	}
	
	public void spawnAlgae(){
		AlgaeModel newAlgae = new AlgaeModel();
		newAlgae.spawnAlgaeModel();
		algaeList.add(newAlgae);
	}
	
	public Collection<AlgaeModel> getAlgaeList() {
		return algaeList;
	}

	public boolean getgameActive() {
		return gameActive;
	}
	
	public void setGameActive(boolean active) {
		gameActive = active; 
	}
	
	public boolean collisionOccured(AnimalModelG2 animal, AlgaeModel algae){
		
		Rectangle algae_rect = new Rectangle(algae.getLocX(), algae.getLocY(), algae.getWidth(), algae.getHeight());
		Rectangle animal_rect = new Rectangle(animal.getLocX(), animal.getLocY(), animal.getWidth(), animal.getHeight());
		
		if(animal_rect.getBounds().intersects(algae_rect)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean shallowWaterCollision(AlgaeModel algae){
		if(algae.getLocX()<=0){
			return true;
		}
		return false;
	}
	
	public void collisionDetection(){
		
		Collection<AlgaeModel> algaeList = getAlgaeList();
		
		Iterator<AlgaeModel> it = algaeList.iterator();
		
		while(it.hasNext()){
			
			AlgaeModel tmp = it.next();
			
			if(tmp.isActive()){
				if(collisionOccured(animal, tmp)){
					tmp.eaten();
					
				}
				if(shallowWaterCollision(tmp)){
					tmp.eaten();
					addNumMissed();
				}	
			}
		}		
	}

	public AnimalModelG2 getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalModelG2 animal) {
		this.animal = animal;
	}

	

	
	public AlgaeModel getAlgae() {
		return algae;
	}

	public void setAlgae(AlgaeModel algae) {
		this.algae = algae;
	}
	public int getAlgaeNum(){
		return algaeList.size();
	}
	

	public AnimalModelG2 getAnimalModelG2() {
		return this.animal;
	}



	

	
}