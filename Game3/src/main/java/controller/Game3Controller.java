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
		runGame();
		
	}
	
	public void runGame()  {
		this.setGameActive(true);
		Random die = new Random();
		int trigger = 4;
		while(getgameActive()) {
			if(trigger == die.nextInt(700000)) {
				if(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getIsActive() == false && beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getGabPU().getIsActive() == false) {
					getBeach().spawnConcrPU(getBeach().generatePPUL());
					getBeach().spawnGabPU(getBeach().generatePPUL());
					this.powerUpSpawned();
				}	
			}
			if((beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair()))).getConcrPU().getIsActive() && beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getIsActive()); {
				this.collisionPowerUps();
			}
			this.view.repaintAll();
			
		}
	}

	ActionListener powerUpSpawnTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			beach.removeGabPU(beach.findPairInGrid(beach.getGabPair()));
			beach.removeConcrPU(beach.findPairInGrid(beach.getConcPair()));
			
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setPickedUp(false);
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setIsActive(false);
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).setVacant(true);
			
			//beach.getBeachGrid().get(beach.findPairInGrid(beach.getBlockWithConc().getLocation())).getConcrPU().setPickedUp(false);
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setActive(false);
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).setVacant(true);
			
			Object time = e.getSource();
			Timer myTime = (Timer) time;
			myTime.stop();
		}
	};
	
	ActionListener powerUpWallTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getWallState() == GabPUState.WALL) {
				beach.removeGabPU(beach.findPairInGrid(beach.getGabPair()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setPickedUp(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setIsActive(false);
			}
			else {
				beach.removeConcrPU(beach.findPairInGrid(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getLocation()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(false);
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
		timer = new Timer(6000, powerUpWallTimerListener);
		timer.setRepeats(true);
		timer.start();
		System.out.println("Wall timer started");
	}
	
	public void collisionPowerUps(){
		if((beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getIsActive())) {
			if (animal.getBounds().intersects(beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getBounds())) {
				System.out.println("Intersection between gab and animal");
				timer.stop();
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setPickedUp(true);
				beach.removeConcrPU(beach.findPairInGrid(beach.getConcPair()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).setVacant(true);
				
				this.powerUpPickedUp();
			}
		}
		else if ((beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getIsActive())) {
			if (beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getIsActive() && animal.getBounds().intersects(beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getConcrPU().getBounds())) {
				System.out.println("Intersection between concrete and animal");
				timer.stop();
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(true);
				beach.removeGabPU(beach.findPairInGrid(beach.getGabPair()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setIsActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).setVacant(true);
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
