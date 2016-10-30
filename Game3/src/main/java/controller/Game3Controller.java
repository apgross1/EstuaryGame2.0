package controller;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import models.AnimalModelG3;
import models.BeachModel;
import models.BeachModel.Pair;
import models.ConcretePUModel.ConcPUState;
import models.SandPatchModel;
import models.WallModelAbstract;
import models.WaterModel;
import models.GabionPUModel.GabPUState;
import view.Game3View;

public class Game3Controller implements KeyListener {
	private boolean gameActive;
	private Game3View view;
	private AnimalModelG3 animal;
	private BeachModel beach;
	private SandPatchModel sandPatch;
	private WaterModel water;
	
	public Game3Controller() {
		setAnimal(new AnimalModelG3());
		setBeach(new BeachModel());
		setSandPatch(new SandPatchModel());
		setWater(new WaterModel());
		view = new Game3View(this);
		
	}
	

	ActionListener powerUpSpawnTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			beach.removeGabPU(beach.getGabPU().getLocation());
			beach.removeConcrPU(beach.getConcrPU().getLocation());
			
			Object time = e.getSource();
			Timer myTime = (Timer) time;
			myTime.stop();
		}
	};
	
	ActionListener powerUpWallTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (beach.getGabPU().getWallState() == GabPUState.WALL) {
				beach.removeGabPU(beach.getGabPU().getLocation());
			}
			else {
				beach.removeConcrPU(beach.getConcrPU().getLocation());
			}
			Object time = e.getSource();
			Timer myTime = (Timer) time;
			myTime.stop();
		}
	};
	
	//Duration for which power-up is available to be picked up
	public void powerUpSpawned() {
		Timer timer = new Timer(3000, powerUpSpawnTimerListener);
		timer.setRepeats(true);
		timer.start();
		while(timer.isRunning()) {
			if (beach.getGabPU().getIsActive() || beach.getConcrPU().getIsActive()) {
				timer.stop();
			}
		}
	}
	
	
	
	
	//Duration for which power-up is in wall form
	public void powerUpPickedUp() {
		Timer timer = new Timer(6000, powerUpWallTimerListener);
		timer.setRepeats(true);
		timer.start();
		while(timer.isRunning()) {
			
		}
	}
	
	public void startGame() {
		gameActive = true;
		
		while(gameActive){
			
			view.repaint();
		}
	}
	
	public void collisionPowerUps(){
		if(animal.getBounds().intersects(beach.getGabPU().getBounds())) {
			beach.getGabPU().setWallState(GabPUState.WALL);
			beach.getGabPU().setBounds(beach.getGabPU().getLocation().getX(), beach.getGabPU().getLocation().getY(), 15 , 10);
			
			this.powerUpPickedUp();
		}
		else if (animal.getBounds().intersects(beach.getConcrPU().getBounds())) {
			beach.getConcrPU().setWallState(ConcPUState.WALL);
			beach.getConcrPU().setBounds(beach.getConcrPU().getLocation().getX(), beach.getConcrPU().getLocation().getY(), 15 , 10);
			this.powerUpPickedUp();
		}
	}
	
	public void collisionDetectionLoop(){
		
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

	public AnimalModelG3 getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalModelG3 animal) {
		this.animal = animal;
	}


	public WaterModel getWater() {
		return water;
	}

	public void setWater(WaterModel water) {
		this.water = water;
	}

	public BeachModel getBeach() {
		return beach;
	}

	public void setBeach(BeachModel beach) {
		this.beach = beach;
	}
	
	public SandPatchModel getSandPatch() {
		return sandPatch;
	}

	public void setSandPatch(SandPatchModel sandPatch) {
		this.sandPatch = sandPatch;
	}
}
