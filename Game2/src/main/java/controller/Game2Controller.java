package controller;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.Timer;

import models.AlgaeEaterModel;
import models.AlgaeModel;
import models.AnimalModelAbstract;
import models.AnimalModelG2;
import models.WaterModelG2;
import view.Game2View;
import java.awt.event.*;
public class Game2Controller implements KeyListener {
	private boolean gameActive;
	private Game2View view;
	private AnimalModelG2 animal;
	private AlgaeEaterModel algaeEater;
	private AlgaeModel algae;
	private Collection<AlgaeModel> algaeList = new ArrayList<AlgaeModel>();
	private WaterModelG2 water;
	int count = 0;
	
	public Game2Controller() {
		animal = new AnimalModelG2();
		water = new WaterModelG2();
		algae = new AlgaeModel();
		algaeEater = new AlgaeEaterModel();
		
		view = new Game2View(this);
		view.addController(this);
		
	}
//	ActionListener spawnAlgae = new ActionListener(){
//		@Override
//		public void actionPerformed(ActionEvent event){
//			
//			AlgaeModel newAlgae = new AlgaeModel();
//			newAlgae.spawnAlgaeModel();
//			algaeList.add(newAlgae);
//			
//			
//		}
//	};

	
	public void startGame() {
		
		gameActive = true;
		
		while(gameActive){
			
			view.repaintFrame();
			collisionDetection();
			
			if(algaeList.size()< algae.getMaxAlgae() & count ==100000)
			{
				AlgaeModel newAlgae = new AlgaeModel();
				newAlgae.spawnAlgaeModel();
				algaeList.add(newAlgae);
				count=0;
				
				
			}
			System.out.println(count);
			count++;
			
			
			
			}
			
		
		
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

	
	boolean collisionOccured(AnimalModelG2 animal, AlgaeModel algae){
		
		
		Rectangle algae_rect = new Rectangle(algae.getLocX(), algae.getLocY(), algae.getWidth(), algae.getHeight());
		Rectangle animal_rect = new Rectangle(animal.getLocX(), animal.getLocY(), animal.getWidth(), animal.getHeight());
		
		
		
		if(animal_rect.getBounds().intersects(algae_rect)){
			return true;
		}
		else{
		return false;
		}
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
				
			}
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public AnimalModelG2 getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalModelG2 animal) {
		this.animal = animal;
	}

	

	public WaterModelG2 getWater() {
		return water;
	}

	public void setWater(WaterModelG2 water) {
		this.water = water;
	}
	
	public AlgaeModel getAlgae() {
		return algae;
	}

	public void setAlgae(AlgaeModel algae) {
		this.algae = algae;
	}
	public AlgaeEaterModel getAlgaeEater() {
		return algaeEater;
	}

	public void setAlgaeEater(AlgaeEaterModel ae) {
		this.algaeEater = ae;
	}

	public AnimalModelG2 getAnimalModelG2() {
		return this.animal;
	}

	
}