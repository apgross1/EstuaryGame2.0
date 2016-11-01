import models.AnimalModelG3;
import models.BeachModel;
import models.Pair;
import models.ConcretePUModel;
import models.ConcretePUModel.ConcPUState;
import models.GabionPUModel.GabPUState;
import models.GridBlock;
import models.WaterModel;
import models.GabionPUModel;
import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
		BeachModel beach = new BeachModel();
		beach.spawnConcrPU(beach.generatePPUL());
		ConcretePUModel concrWall = beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU();
		assertTrue("Should be true...", concrWall.getIsActive());
		assertTrue("Should still be in PU form...", concrWall.getWallState().equals(ConcPUState.POWER_UP));
		
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				concrWall.setActive(false);
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
	    assertFalse("Should be false...", concrWall.getIsActive());
	}
	
	//BeachModel
	//Testing spawnGab (adding gab wall to board)
	@Test
	public void testSpawnGab(){
		BeachModel beach = new BeachModel();
		beach.spawnGabPU(beach.generatePPUL());
		GabionPUModel gabWall = beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU();
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
		Collection<GridBlock> sandBlocks = beach.getBeachGrid().values();
		assertTrue("Should be true", sandBlocks.size() == 80); //80 is the size of the grid
	}
	
	//Testing PPUL generator (Possible Power-Up Location)
	@Test
	public void testGeneratePPUL() {
		BeachModel beach = new BeachModel();
		Collection<Pair> origList = beach.generatePPUL();
		beach.removeSquare((new Pair(3,5))); //Low-level
		Collection<Pair> modifiedList = beach.generatePPUL();
		assertTrue("New list should have less combos...", origList.size() > modifiedList.size());
		
		//Test if after spawning PU Pair combos decreases (it should, PUs shouldn't be stacked on each other)
		beach.spawnGabPU(beach.generatePPUL());
		Collection<Pair> listPostGabSpawn = beach.generatePPUL();
		assertTrue("New list should have less combos than modified list...", listPostGabSpawn.size() < modifiedList.size());
		
		
		beach.spawnConcrPU(beach.generatePPUL());
		Pair puBlock = null;
		for(GridBlock val : beach.getBeachGrid().values()) {
			if(val.getConcrPU() != null) {
				puBlock = beach.findPairInGrid(val.getConcrPU().getLocation());
			}
		}
		
		Collection<Pair> listPostConcrSpawn = beach.generatePPUL();
		assertTrue("New list should have less combos than modified list...", listPostConcrSpawn.size() < listPostGabSpawn.size());
		beach.removeConcrPU(puBlock);
		
		Collection<Pair> listPostRemovePU = beach.generatePPUL();

		assertTrue("New list should be one less", listPostRemovePU.size() == listPostConcrSpawn.size());
	}
	
	//Testing removal of square/position on grid
	//Spot filled by wave
	@Test
	public void testTileRemove() {
		BeachModel beach = new BeachModel();
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		Iterator<Pair> it = beach.getBeachGrid().keySet().iterator();
		while(it.hasNext()) {
			pairs.add(it.next());
		}
		beach.getBeachGrid().get(pairs.get(0)).setWater(new WaterModel(pairs.get(0)), pairs.get(0));
		beach.removeSquare(pairs.get(0));
		assertFalse("Should be false...", beach.getBeachGrid().get(pairs.get(0)).isVacant());
		assertTrue("Should be true...", beach.getBeachGrid().get(pairs.get(0)).getConcrPU().getIsActive() == false);
		assertTrue("Should be true...", beach.getBeachGrid().get(pairs.get(0)).getGabPU().getIsActive() == false);
	}
	
	
	@Test
	public void testWaveHit(){
		Game3Controller walldamage = new Game3Controller();
		ConcretePUModel conc = new ConcretePUModel();
		GabionPUModel gab = new GabionPUModel();
		conc.setActive(true);
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
	public void testSpawnTimer() {
		Game3Controller controller = new Game3Controller();
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		pairs.add(new Pair(2,1));
		controller.getBeach().spawnConcrPU(pairs);
		controller.getBeach().spawnGabPU(pairs);
		assertTrue("Should be true", controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().getIsActive());
		assertTrue("Should be true", controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().getIsActive());
		controller.powerUpSpawned();
		assertFalse("Should be false", controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().getIsActive());
		assertFalse("Should be false", controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().getIsActive());
		
		ArrayList<Pair> pairs2 = new ArrayList<Pair>();
		pairs2.add(new Pair(2,1));
		controller.getBeach().spawnConcrPU(pairs2);
		controller.getBeach().spawnGabPU(pairs2);
		assertTrue("Should be true", controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().getIsActive());
		assertTrue("Should be true", controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().getIsActive());
		controller.powerUpPickedUp();
		assertFalse("Should be false", controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().getIsActive());
		assertFalse("Should be false", controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().getIsActive());
		
	}
}
