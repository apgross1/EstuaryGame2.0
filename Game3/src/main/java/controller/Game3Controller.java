package controller;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import models.AnimalModelG3;
import models.BeachModel;
import models.BeachModel.Pair;
import models.ConcretePUModel.ConcPUState;
import models.GridBlock;
import models.WallModelAbstract;
import models.WaterModel;
import models.GabionPUModel.GabPUState;
import view.Game3View;

public class Game3Controller implements KeyListener {
	private boolean gameActive;
	private Game3View view;
	private AnimalModelG3 animal;
	private BeachModel beach;
	private GridBlock sandPatch;
	private WaterModel water;
	private Timer timer;
	
	public Game3Controller() {
		setAnimal(new AnimalModelG3());
		setBeach(new BeachModel());
		setSandPatch(new GridBlock());
		setWater(new WaterModel());
		view = new Game3View(this);
		this.gameActive = true;
		gameLoop();
		
	}
	
	public void gameLoop()  {
		Random die = new Random();
		int trigger = 4;
		while(getgameActive()) {
			if(trigger == die.nextInt(6000000)) {
				if(beach.getConcrPU().getIsActive() == false && beach.getGabPU().getIsActive() == false) {
					getBeach().spawnConcrPU(getBeach().generatePPUL());
					getBeach().spawnGabPU(getBeach().generatePPUL());
					this.powerUpSpawned();
				}	
			}
			if((beach.getConcrPU().getIsActive()) && beach.getGabPU().getIsActive()) {
				System.out.println("Should exist:");
				beach.getConcrPU().getBounds();
				this.collisionPowerUps();
			}
			this.view.repaintAll();
			
		}
	}

	ActionListener powerUpSpawnTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			beach.removeGabPU(beach.findPairInGrid(beach.getGabPU().getLocation()));
			beach.removeConcrPU(beach.findPairInGrid(beach.getConcrPU().getLocation()));
			beach.getConcrPU().setActive(false);
			beach.getGabPU().setPickedUp(false);
			beach.getGabPU().setIsActive(false);
			beach.getConcrPU().setPickedUp(false);
			
			Object time = e.getSource();
			Timer myTime = (Timer) time;
			myTime.stop();
		}
	};
	
	ActionListener powerUpWallTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (beach.getGabPU().getWallState() == GabPUState.WALL) {
				beach.removeGabPU(beach.findPairInGrid(beach.getGabPU().getLocation()));
				beach.getGabPU().setPickedUp(false);
				beach.getGabPU().setIsActive(false);
			}
			else {
				beach.removeConcrPU(beach.findPairInGrid(beach.getConcrPU().getLocation()));
				beach.getConcrPU().setActive(false);
				beach.getConcrPU().setPickedUp(false);
			}
			Object time = e.getSource();
			Timer myTime = (Timer) time;
			myTime.stop();
		}
	};
	
	//Duration for which power-up is available to be picked up
	public void powerUpSpawned() {
		timer = new Timer(4000, powerUpSpawnTimerListener);
		timer.setRepeats(true);
		timer.start();
		System.out.println("Spawn timer started");
	}
	
	
	
	
	//Duration for which power-up is in wall form
	public void powerUpPickedUp() {
		Timer timer = new Timer(6000, powerUpWallTimerListener);
		timer.setRepeats(true);
		timer.start();
		System.out.println("Wall timer started");
	}
	
	public void startGame() {
		gameActive = true;
		
		while(gameActive){
			
			view.repaint();
		}
	}
	
	public void collisionPowerUps(){
		if((beach.getGabPU().getIsActive())) {
			if (animal.getBounds().intersects(beach.getGabPU().getBounds())) {
				System.out.println("Intersection between gab and animal");
				timer.stop();
				beach.getGabPU().setPickedUp(true);
				beach.removeConcrPU(beach.findPairInGrid(beach.getConcrPU().getLocation()));
				beach.getConcrPU().setActive(false);
				this.powerUpPickedUp();
			}
		}
		else if ((beach.getConcrPU().getIsActive())) {
			if (beach.getConcrPU().getIsActive() && animal.getBounds().intersects(beach.getConcrPU().getBounds())) {
				System.out.println("Intersection between concrete and animal");
				timer.stop();
				beach.getConcrPU().setPickedUp(true);
				beach.removeGabPU(beach.findPairInGrid(beach.getGabPU().getLocation()));
				beach.getGabPU().setIsActive(false);
				this.powerUpPickedUp();
			}
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
	
	public GridBlock getSandPatch() {
		return sandPatch;
	}

	public void setSandPatch(GridBlock sandPatch) {
		this.sandPatch = sandPatch;
	}
}
