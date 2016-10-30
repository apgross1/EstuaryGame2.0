package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import models.AlgaeEaterModel;
import models.AlgaeModel;
import models.AnimalModelAbstract;
import models.AnimalModelG2;
import models.WaterModelG2;
import view.Game2View;

public class Game2Controller implements KeyListener {
	private boolean gameActive;
	private Game2View view;
	private AnimalModelG2 animal;
	private AlgaeEaterModel algaeEater;
	private AlgaeModel algae;
	private WaterModelG2 water;
	
	public Game2Controller() {
		setAnimal(new AnimalModelG2());
		setWater(new WaterModelG2());
		setAlgae(new AlgaeModel());
		setAlgaeEater(new AlgaeEaterModel());
		
		view = new Game2View(this);
		view.addController(this);
	}
	
	public void startGame() {
		gameActive = true;
		
		while(gameActive){
			
			view.repaintFrame();
		}
	}
	
	public void startTime() {
		
	}
	
	public void stopTime() {
		
	}

	
	public boolean getgameActive() {
		return gameActive;
	}
	public void setGameActive(boolean active) {
		gameActive = active; 

}

	public int setTime(int i) {
		return i;
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