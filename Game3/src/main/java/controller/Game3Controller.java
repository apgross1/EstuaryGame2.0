package controller;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import javax.swing.Timer;

import models.AnimalModelG3;
import models.BeachModel;
import models.ConcretePUModel.ConcPUState;
import models.GridBlock;
import models.WallModelAbstract;
import models.WaterModel;
import models.WaveModel;
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

		
	}
	
	public void runGame()  {
		this.setGameActive(true);
		Random die = new Random();
		int triggerSpawn = 4;
		while(getgameActive()) {
			if(triggerSpawn == die.nextInt(700000)) {
				System.out.println("Does this always");
				if(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getIsActive() == false && beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getIsActive() == false) {
					System.out.println("Print in tandem?");
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
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(false);
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
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).setVacant(true);
				System.out.println("Wall Timer stopped");
			}
			else {
				beach.removeConcrPU(beach.findPairInGrid(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getLocation()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).setVacant(true);
				System.out.println("Wall Timer stopped");
			}
			Object time = e.getSource();
			Timer myTime = (Timer) time;
			myTime.stop();
		}
	};
	
	
	//Duration for which power-up is available to be picked up
	public void powerUpSpawned() {
		timer = new Timer(500000, powerUpSpawnTimerListener);
		System.out.println("Gabion is at: (" + beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getViewLocation().getX() +", " + beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getViewLocation().getY() + ")");
		System.out.println("Concrete is at:(" + beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getViewLocation().getX() +", " + beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getViewLocation().getY() + ")");
		
		timer.setRepeats(true);
		timer.start();
		System.out.println("Spawn timer started");
	}
	
	
	
	
	//Duration for which power-up is in wall form
	public void powerUpPickedUp() {
		timer = new Timer(3000, powerUpWallTimerListener);
		timer.setRepeats(true);
		timer.start();
		System.out.println("Wall timer started");
	}
	
	public void collisionPowerUps(){
		//Gabion wall collision works (to a degree) Concrete doesn't though
		if ((beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getIsActive()) & beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().isPickedUp() == false) {
			if (animal.getBounds().intersects(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getBounds())) {
				System.out.println("Intersection between concrete and animal");
				timer.stop();
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(true);
				beach.removeGabPU(beach.findPairInGrid(beach.getGabPair()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setIsActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).setVacant(true);
				
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(true);
				this.powerUpPickedUp();
				return;
			}
		}
		if((beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getIsActive()) &  beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().isPickedUp() == false) {
			if (animal.getBounds().intersects(beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getBounds())) {
				System.out.println("Intersection between gab and animal");
				timer.stop();
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setPickedUp(true);
				beach.removeConcrPU(beach.findPairInGrid(beach.getConcPair()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).setVacant(true);
				
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setPickedUp(true);
				this.powerUpPickedUp();
				return;
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
