import models.AnimalModelG3;
import models.BeachModel;
import models.BeachModel.Pair;
import models.ConcretePUModel;
import models.ConcretePUModel.ConcPUState;
import models.GabionPUModel.GabPUState;
import models.WaterModel;
import models.GabionPUModel;
import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.Timer;

import org.junit.Test;

import controller.Game3Controller;
import enums.Walls;
import enums.Waves;

/**
 * @author Jacob
 *
 */

//UML Changes that need to be made
	/*
	 * Gabion model attribute: boolean isActive()
	 * Concrete model attribute: boolean isActive()
	 * remove render(Graphics g) from controller, move to view
	 * added counter to beach model for squares
	 */
public class Game3Tests {
	//ConcretePUModel
	//Spawn power-up
	@Test
	public void testSpawnConc(){
		ConcretePUModel concWall = new ConcretePUModel();
		concWall.spawn();
		assertTrue("Should be true...", concWall.getIsActive());
		assertTrue("Should still be in PU form...", concWall.getWallState().equals(ConcPUState.POWER_UP));
		
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				concWall.setIsActive(false);
				Object time = e.getSource();
				Timer myTime = (Timer) time;
				myTime.stop();	
			}
		};
		
	    
		Timer time = new Timer(1000, actionListener);
		time.setRepeats(true);
		time.start();
		while(time.isRunning()) {

		}
	    assertFalse("Should be false...", concWall.getIsActive());
	}
	
	//BeachModel
	//Testing spawnGab (adding gab wall to board)
	@Test
	public void testSpawnGab(){
		BeachModel beach = new BeachModel();
		GabionPUModel gabWall = beach.getGabPU();
		beach.spawnGabPU(beach.generatePPUL());
		assertTrue("Should be true...", gabWall.getIsActive());
		assertTrue("Should still be in PU form...", gabWall.getWallState().equals(GabPUState.POWER_UP));
		
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gabWall.setIsActive(false);
				Object time = e.getSource();
				Timer myTime = (Timer) time;
				myTime.stop();
				
			}
		};
		
	    
		Timer time = new Timer(1000, actionListener);
		time.setRepeats(true);
		time.start();
		while(time.isRunning()) {

		}
	    assertFalse("Should be false...", gabWall.getIsActive());
	}
	
	//Testing intialization
	@Test
	public void testInitBeach() {
		BeachModel beach = new BeachModel();
		Collection<Object> sandBlocks = beach.getBeachGrid().values();
		assertTrue("Should be true", sandBlocks.size() == 100); //100 is the size of the grid
	}
	
	//Testing PPUL generator (Possible Power-Up Location)
	@Test
	public void testGeneratePPUL() {
		BeachModel beach = new BeachModel();
		Collection<Pair> origList = beach.generatePPUL();
		beach.removeSquare((beach.new Pair(3,5))); //Low-level
		Collection<Pair> modifiedList = beach.generatePPUL();
		assertTrue("New list should have less combos...", origList.size() > modifiedList.size());
		
		//Test if after spawning PU Pair combos decreases (it should, PUs shouldn't be stacked on each other)
		beach.spawnGabPU(beach.generatePPUL());
		Collection<Pair> listPostGabSpawn = beach.generatePPUL();
		assertTrue("New list should have less combos than modified list...", listPostGabSpawn.size() < modifiedList.size());
		
		beach.spawnConcrPU(beach.generatePPUL());
		Collection<Pair> listPostConcrSpawn = beach.generatePPUL();
		assertTrue("New list should have less combos than modified list...", listPostConcrSpawn.size() < listPostGabSpawn.size());
		beach.removeConcrPU(beach.getConcrPU().getLocation());
		
		Collection<Pair> listPostRemovePU = beach.generatePPUL();

		assertTrue("New list should be one less", listPostRemovePU.size() == listPostConcrSpawn.size()+1);
	}
	
	//Testing removal of square/position on grid
	//Spot filled by wave
	@Test
	public void testRemoveSquare() {
		BeachModel beach = new BeachModel();
		ConcretePUModel concrPU = new ConcretePUModel();
		concrPU.setLocation(beach.new Pair(4,5));
		beach.getBeachGrid().put(concrPU.getLocation(), concrPU);
		beach.getPositionGrid()[concrPU.getLocation().getX()][concrPU.getLocation().getY()] = Walls.CONCRETE_GAME3.getValue();
		//Adding concrete PU to beach
		assertTrue("Should be true", beach.getBeachGrid().containsKey(concrPU.getLocation()));
		assertTrue("Should be true...", beach.getPositionGrid()[concrPU.getLocation().getX()][concrPU.getLocation().getY()] == Walls.CONCRETE_GAME3.getValue()); 
		
		//Removing the tile...
		beach.removeSquare(concrPU.getLocation());
	
		assertFalse("Should be false...", (beach.getBeachGrid().get(concrPU.getLocation())) instanceof ConcretePUModel);
		assertTrue("Should be true...", (beach.getBeachGrid().get(concrPU.getLocation())) instanceof WaterModel);
		//Replaced with wave?
		assertTrue("Should be true...", beach.getPositionGrid()[concrPU.getLocation().getX()][concrPU.getLocation().getY()] == Waves.WAVE_GAME3.getValue());
	
	}
	
	
	@Test
	public void testWaveHit(){
		Game3Controller walldamage = new Game3Controller();
		ConcretePUModel conc = new ConcretePUModel();
		GabionPUModel gab = new GabionPUModel();
		conc.setIsActive(true);
		gab.setIsActive(true);
		
		conc.breakDown();
		gab.breakDown();
		assertFalse("False", conc.getIsActive() == false);
		assertTrue("True", gab.getIsActive() == true);
		
		
	}
	
	
	//AnimalModel
	@Test
	public void testHealthDown(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setHealth(100);
		animal.healthDown();
		assertTrue("Should be 0", animal.getHealth() == 0);
	}
	
	@Test
	public void testPickup(){
		AnimalModelG3 animal = new AnimalModelG3();
		assertFalse("", true);
	}
	
	@Test
	public void voidstartTimer(){
		Game3Controller g3clock = new Game3Controller();
		g3clock.setTime(180);
		assertTrue("True", g3clock.getgameActive());
	}
	
	@Test
	public void voidEndTimer(){
		Game3Controller g3clock = new Game3Controller();
		g3clock.setTime(0);
		assertFalse("False", g3clock.getgameActive());
	}
	
	@Test
	public void testSpawnTimer() {
		Game3Controller controller = new Game3Controller();
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		pairs.add(controller.getBeach().new Pair(2,1));
		controller.getBeach().spawnConcrPU(pairs);
		controller.getBeach().spawnGabPU(pairs);
		assertTrue("Should be true", controller.getBeach().getConcrPU().getIsActive());
		assertTrue("Should be true", controller.getBeach().getGabPU().getIsActive());
		controller.powerUpSpawned();
		assertFalse("Should be false", controller.getBeach().getConcrPU().getIsActive());
		assertFalse("Should be false", controller.getBeach().getGabPU().getIsActive());
		
		ArrayList<Pair> pairs2 = new ArrayList<Pair>();
		pairs2.add(controller.getBeach().new Pair(2,1));
		controller.getBeach().spawnConcrPU(pairs2);
		controller.getBeach().spawnGabPU(pairs2);
		assertTrue("Should be true", controller.getBeach().getConcrPU().getIsActive());
		assertTrue("Should be true", controller.getBeach().getGabPU().getIsActive());
		controller.powerUpPickedUp();
		assertFalse("Should be false", controller.getBeach().getConcrPU().getIsActive());
		assertFalse("Should be false", controller.getBeach().getGabPU().getIsActive());
		
	}
}
