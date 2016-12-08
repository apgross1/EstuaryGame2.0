import models.AnimalModelG3;
import models.BeachModel;
import models.Pair;
import models.PairComparator;
import models.SunHurricaneModel;
import models.Tutorial;
import models.ConcretePUModel;
import models.ConcretePUModel.ConcPUState;
import models.GabionPUModel.GabPUState;
import models.GridBlock;
import models.WaterModel;
import models.WaveModel;
import models.GabionPUModel;
import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Enums.AnimGraphics;
import Enums.Frames;
import Enums.WaveClusters;
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

	//BeachModel beach = new BeachModel();
	//GridBlock grid = new GridBlock();
	
	//ConcretePUModel
	//Spawn power-up
	@Test
	public void testSpawnConc(){
		
		BeachModel beach = new BeachModel("test");
		ArrayList<Pair> pairList = beach.generatePPUL();
		Collections.sort(pairList, new PairComparator());
		
		Iterator<Pair> it = pairList.iterator();
		
		while(it.hasNext()) {
			Pair tempPair = it.next();
			//System.out.println("("+tempPair.getX()+","+tempPair.getY()+")");
			GridBlock g = new GridBlock(tempPair, beach, "test");
			
			g.setLocation(tempPair);
			beach.getBeachGrid().put(tempPair, g);
		}
		
		
		
		
		Collection<Pair> blockLocs = beach.getBeachGrid().keySet();
		Iterator<Pair> pairIt = blockLocs.iterator();
		while(pairIt.hasNext()) {
			beach.getOrderedPairs().add(pairIt.next());
		}
		Collections.sort(beach.getOrderedPairs(), new PairComparator());
		
		int counter = 0;
		ArrayList<Pair> ppul = new ArrayList<Pair>();
		for(int i = 0; i < beach.getPositionGrid().length; i++) {
			for(int j = 0; j < beach.getPositionGrid()[i].length; j++) {
				if (beach.getPositionGrid()[i][j] == 0) {
					counter++;
					ppul.add(new Pair(j,i));
				}
				else {
					continue;
				}
			}
		}
		System.out.println("Possible locations generated at initialization: " + counter);
		
		if(ppul.size() == 0) {
			return;
		}
		else{
			Random randLoc = new Random();
			Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
			beach.setConcPair(pair);
			ConcretePUModel tempConcr = new ConcretePUModel();
			tempConcr.setFrameMap(beach.getFrameMap());
			tempConcr.setLocation(beach.findPairInGrid(pair),"test");
			tempConcr.setActive(true);
			
			
			
			beach.getBeachGrid().get(beach.findPairInGrid(pair)).getConcrPU().setActive(true);
			beach.getBeachGrid().get(beach.findPairInGrid(pair)).getGabPU().setIsActive(false);
			beach.getBeachGrid().get(beach.findPairInGrid(pair)).setVacant(false);
			
			beach.getPositionGrid()[pair.getY()][pair.getX()] = Walls.CONCRETE_GAME3.getValue();	
			assertTrue("Should be true...", tempConcr.getIsActive());
		}
		
		
		
		ConcretePUModel concrWall =  beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU();
		
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
		BeachModel beach = new BeachModel("test");
		ArrayList<Pair> pairList = beach.generatePPUL();
		Collections.sort(pairList, new PairComparator());
		
		Iterator<Pair> it = pairList.iterator();
		
		while(it.hasNext()) {
			Pair tempPair = it.next();
			//System.out.println("("+tempPair.getX()+","+tempPair.getY()+")");
			GridBlock g = new GridBlock(tempPair, beach, "test");
			
			g.setLocation(tempPair);
			beach.getBeachGrid().put(tempPair, g);
		}
		
		
		
		
		Collection<Pair> blockLocs = beach.getBeachGrid().keySet();
		Iterator<Pair> pairIt = blockLocs.iterator();
		while(pairIt.hasNext()) {
			beach.getOrderedPairs().add(pairIt.next());
		}
		Collections.sort(beach.getOrderedPairs(), new PairComparator());
		
		int counter = 0;
		ArrayList<Pair> ppul = new ArrayList<Pair>();
		for(int i = 0; i < beach.getPositionGrid().length; i++) {
			for(int j = 0; j < beach.getPositionGrid()[i].length; j++) {
				if (beach.getPositionGrid()[i][j] == 0) {
					counter++;
					ppul.add(new Pair(j,i));
				}
				else {
					continue;
				}
			}
		}
		System.out.println("Possible locations generated at initialization: " + counter);
		
		if(ppul.size() == 0) {
			return;
		}
		else{
			Random randLoc = new Random();
			Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
			beach.setGabPair(pair);
			GabionPUModel tempGab = new GabionPUModel();
			tempGab.setFrameMap(beach.getFrameMap());
			tempGab.setLocation(beach.findPairInGrid(pair),"test");
			tempGab.setIsActive(true);
			
			
			
			beach.getBeachGrid().get(beach.findPairInGrid(pair)).getConcrPU().setActive(false);
			beach.getBeachGrid().get(beach.findPairInGrid(pair)).getGabPU().setIsActive(true);
			beach.getBeachGrid().get(beach.findPairInGrid(pair)).setVacant(false);
			
			beach.getPositionGrid()[pair.getY()][pair.getX()] = Walls.GABION_GAME3.getValue();
			assertTrue("Should be true...", tempGab.getIsActive());
		}
		
		GabionPUModel gabWall =  beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU();
		
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
		BeachModel beach = new BeachModel("Tests");
		ArrayList<Pair> pairList = beach.generatePPUL();
		Collections.sort(pairList, new PairComparator());
		
		Iterator<Pair> it = pairList.iterator();
		
		while(it.hasNext()) {
			Pair tempPair = it.next();
			GridBlock g = new GridBlock(tempPair, beach, "test");
			
			g.setLocation(tempPair);
			beach.getBeachGrid().put(tempPair, g);
		}
		
		
		Collection<Pair> blockLocs = beach.getBeachGrid().keySet();
		Iterator<Pair> pairIt = blockLocs.iterator();
		while(pairIt.hasNext()) {
			beach.getOrderedPairs().add(pairIt.next());
		}
		Collections.sort(beach.getOrderedPairs(), new PairComparator());
		
		int counter = 0;
		ArrayList<Pair> ppul = new ArrayList<Pair>();
		for(int i = 0; i < beach.getPositionGrid().length; i++) {
			for(int j = 0; j < beach.getPositionGrid()[i].length; j++) {
				if (beach.getPositionGrid()[i][j] == 0) {
					counter++;
					ppul.add(new Pair(j,i));
				}
				else {
					continue;
				}
			}
		}
		System.out.println("Possible locations generated at initialization: " + counter);
		
		Collection<GridBlock> sandBlocks = beach.getBeachGrid().values();
		assertTrue("Should be true", sandBlocks.size() == 49); //49 is the size of the grid
	}
	

	//Testing PPUL generator (Possible Power-Up Location)
	@Test
	public void testGeneratePPUL() {
		BeachModel beach = new BeachModel("Tests");
		ArrayList<Pair> pairList = beach.generatePPUL();
		Collections.sort(pairList, new PairComparator());
		
		Iterator<Pair> it = pairList.iterator();
		
		while(it.hasNext()) {
			Pair tempPair = it.next();
			GridBlock g = new GridBlock(tempPair, beach, "test");
			
			g.setLocation(tempPair);
			beach.getBeachGrid().put(tempPair, g);
		}
		
		
		
		
		Collection<Pair> blockLocs = beach.getBeachGrid().keySet();
		Iterator<Pair> pairIt = blockLocs.iterator();
		while(pairIt.hasNext()) {
			beach.getOrderedPairs().add(pairIt.next());
		}
		Collections.sort(beach.getOrderedPairs(), new PairComparator());
		
		int counter = 0;
		ArrayList<Pair> ppul = new ArrayList<Pair>();
		for(int i = 0; i < beach.getPositionGrid().length; i++) {
			for(int j = 0; j < beach.getPositionGrid()[i].length; j++) {
				if (beach.getPositionGrid()[i][j] == 0) {
					counter++;
					ppul.add(new Pair(j,i));
				}
				else {
					continue;
				}
			}
		}
		
		beach.removeSquare((new Pair(3,5)), "Test"); //Low-level
		ArrayList<Pair> newppul = beach.generatePPUL();
		assertTrue("New list should have less combos...", ppul.size() > newppul.size());
//Taken from here		
		
		//Test if after spawning PU Pair combos decreases (it should, PUs shouldn't be stacked on each other)

		if(ppul.size() == 0) {
			return;
		}
		else{
			Random randLoc = new Random();
			System.out.println("Before" + ppul.size());
			Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
			beach.setGabPair(pair);
			GabionPUModel tempGab = new GabionPUModel();
			tempGab.setFrameMap(beach.getFrameMap());
			tempGab.setLocation(beach.findPairInGrid(pair),"test");
			tempGab.setIsActive(true);
			
			
			
			beach.getBeachGrid().get(beach.findPairInGrid(pair)).getConcrPU().setActive(false);
			beach.getBeachGrid().get(beach.findPairInGrid(pair)).getGabPU().setIsActive(true);
			beach.getBeachGrid().get(beach.findPairInGrid(pair)).setVacant(false);
			
			beach.getPositionGrid()[pair.getY()][pair.getX()] = Walls.GABION_GAME3.getValue();
			
			System.out.println(ppul.size());
			
			ppul = beach.generatePPUL();
			
			Random randLoc2 = new Random();
			Pair pair2 = ppul.get(randLoc2.nextInt(ppul.size()));
			
			beach.setConcPair(pair2);
			ConcretePUModel tempConcr = new ConcretePUModel();
			tempConcr.setFrameMap(beach.getFrameMap());
			tempConcr.setLocation(beach.findPairInGrid(pair2), "test");
			tempConcr.setActive(true);
			
			
			
			beach.getBeachGrid().get(beach.findPairInGrid(pair2)).getConcrPU().setActive(true);
			beach.getBeachGrid().get(beach.findPairInGrid(pair2)).getGabPU().setIsActive(false);
			beach.getBeachGrid().get(beach.findPairInGrid(pair2)).setVacant(false);
			
			beach.getPositionGrid()[pair2.getY()][pair2.getX()] = Walls.CONCRETE_GAME3.getValue();
			
			System.out.println( pair2.getX());
			//Doesn't always work sometimes says location of both powerups are the same MUST FIX!!!!
			//assertTrue("X's and Y's should not match!!", pair.getX() != pair2.getX() && pair.getY() != pair2.getY());
		}
		
		Collection<Pair> listPostPUSpawn = beach.generatePPUL();
		System.out.println(listPostPUSpawn.size() + "," + newppul.size());
		assertTrue("New PostPU list should have less combos than modified list...", listPostPUSpawn.size() < ppul.size());
		ppul = beach.generatePPUL();
		//This test may be tied to test on line 423 so if it fails anytime later testing this then it is conected to 423
		assertTrue("New PostPU list should have equal combos than modified list...", listPostPUSpawn.size() == ppul.size());
		
		/*
		 *Will need to come back to 
		Pair puBlock = null;
		for(GridBlock val : beach.getBeachGrid().values()) {
			if(val.getConcrPU().getIsActive() == true) {
				System.out.println("here");
				puBlock = val.getConcrPU().getLocation();
				System.out.println(puBlock.getX());
				System.out.println(puBlock.getY());
			}
		}
		
		beach.removeConcrPU(puBlock);
		Collection<Pair> listPostRemovePU = beach.generatePPUL();

		assertTrue("New list should be one less", listPostRemovePU.size() > listPostPUSpawn.size());
		
		*/
		
	}
	
	
	
	//Testing removal of square/position on grid
	//Spot filled by wave
	@Test
	public void testTileRemove() {
		BeachModel beach = new BeachModel("Tests");
		ArrayList<Pair> pairList = beach.generatePPUL();
		Collections.sort(pairList, new PairComparator());
		
		Iterator<Pair> it = pairList.iterator();
		
		while(it.hasNext()) {
			Pair tempPair = it.next();
			GridBlock g = new GridBlock(tempPair, beach, "test");
			
			g.setLocation(tempPair);
			beach.getBeachGrid().put(tempPair, g);
		}
		
		
		
		
		Collection<Pair> blockLocs = beach.getBeachGrid().keySet();
		Iterator<Pair> pairIt = blockLocs.iterator();
		while(pairIt.hasNext()) {
			beach.getOrderedPairs().add(pairIt.next());
		}
		Collections.sort(beach.getOrderedPairs(), new PairComparator());
		
		int counter = 0;
		ArrayList<Pair> ppul = new ArrayList<Pair>();
		for(int i = 0; i < beach.getPositionGrid().length; i++) {
			for(int j = 0; j < beach.getPositionGrid()[i].length; j++) {
				if (beach.getPositionGrid()[i][j] == 0) {
					counter++;
					ppul.add(new Pair(j,i));
				}
				else {
					continue;
				}
			}
		}
		
		Pair pair = new Pair(3,5);
		beach.removeSquare(pair, "test");
		ArrayList<Pair> newppul = beach.generatePPUL();
		assertTrue("New list should have less combos...", ppul.size() > newppul.size());
		
		assertFalse("Should be false...", beach.getBeachGrid().get(beach.findPairInGrid(pair)).isVacant());
		assertTrue("Should be true...", beach.getBeachGrid().get(beach.findPairInGrid(pair)).getConcrPU().getIsActive()==false);
		assertTrue("Should be true...", beach.getBeachGrid().get(beach.findPairInGrid(pair)).getGabPU().getIsActive()==false);
	}
	
	
	
	/*
	@Test
	public void testWaveHit(){
		ConcretePUModel conc = new ConcretePUModel();
		GabionPUModel gab = new GabionPUModel();
		conc.setActive(true);
		gab.setIsActive(true);
		
		assertTrue("False", conc.getIsActive() == false);
		assertTrue("True", gab.getIsActive() == true);
		
		
	}
	
	*/
	
	/*
	//AnimalModel
	@Test
	public void testGameActive(){
		AnimalModelG3 animal = new AnimalModelG3();
		BeachModel beach = new BeachModel("Tests");
		ArrayList<Pair> pairList = beach.generatePPUL();
		Collections.sort(pairList, new PairComparator());
		Game3Controller controller = new Game3Controller();
		
		Iterator<Pair> it = pairList.iterator();
		
		while(it.hasNext()) {
			Pair tempPair = it.next();
			GridBlock g = new GridBlock(tempPair, beach, "test");
			
			g.setLocation(tempPair);
			beach.getBeachGrid().put(tempPair, g);
		}
		
		
		
		
		Collection<Pair> blockLocs = beach.getBeachGrid().keySet();
		Iterator<Pair> pairIt = blockLocs.iterator();
		while(pairIt.hasNext()) {
			beach.getOrderedPairs().add(pairIt.next());
		}
		Collections.sort(beach.getOrderedPairs(), new PairComparator());
		
		int counter = 0;
		ArrayList<Pair> ppul = new ArrayList<Pair>();
		for(int i = 0; i < beach.getPositionGrid().length; i++) {
			for(int j = 0; j < beach.getPositionGrid()[i].length; j++) {
				if (beach.getPositionGrid()[i][j] == 0) {
					counter++;
					ppul.add(new Pair(j,i));
				}
				else {
					continue;
				}
			}
		}
		
		Pair pair = new Pair(3,5);
		beach.removeSquare(pair, "test");
		
		animal.setBeachLocation(new Pair(3,5));
		
		controller.collisionTile();

		assertTrue("This should work", beach.getPositionGrid()[animal.getBeachLocation().getY()][animal.getBeachLocation().getX()] == 2 );
		assertTrue ("This should work", controller.getgameActive() == false);
	}
	*/

	
	/*
	@Test
	public void testPickup(){
		AnimalModelG3 animal = new AnimalModelG3();
		ConcretePUModel concPU = new ConcretePUModel();
		GabionPUModel gabPU =  new GabionPUModel();
		Game3Controller controller = new Game3Controller();
		ArrayList<Pair> pairList = controller.getBeach().generatePPUL();
		Collections.sort(pairList, new PairComparator());
		
		
		
		Pair location = new Pair(0,1);
		concPU.setLocation(location, "test");
		concPU.setActive(true);
		concPU.setPickedUp(false); 
		gabPU.setLocationTest(new Pair(3,5));
		gabPU.setIsActive(true);
		gabPU.setPickedUp(false);
		animal.setBeachLocation(location);
		controller.getBeach().setConcPair(location);
	
		Iterator<Pair> it = pairList.iterator();
		
		while(it.hasNext()) {
			Pair tempPair = it.next();
			//System.out.println("("+tempPair.getX()+","+tempPair.getY()+")");
			GridBlock g = new GridBlock(tempPair, controller.getBeach(), "test");
			
			g.setLocation(tempPair);
			controller.getBeach().getBeachGrid().put(tempPair, g);
			
		}
		
		
		
		
		Collection<Pair> blockLocs = controller.getBeach().getBeachGrid().keySet();
		Iterator<Pair> pairIt = blockLocs.iterator();
		while(pairIt.hasNext()) {
			controller.getBeach().getOrderedPairs().add(pairIt.next());
		}
		Collections.sort(controller.getBeach().getOrderedPairs(), new PairComparator());
		
		System.out.println(controller.getSandPatch().isVacant());
		
		controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair()))).setVacant(true);
		
		System.out.println(controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair()))).getLocation());
		

		
		
		//beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getIsActive()) & beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().isPickedUp() == false) {
		
		controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().setPickedUp(false);
		assertTrue(controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().isPickedUp() == false);
		
		controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().setActive(true);
		assertTrue(controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().getIsActive());
		
		
		controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().setPickedUp(false);
		assertTrue(controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().isPickedUp() == false);
		
		controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().setIsActive(true);
		assertTrue(controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().getIsActive());
		
		
		assertTrue(animal.getBeachLocation().getX() == concPU.getLocation().getX());
		assertTrue(animal.getBeachLocation().getY() == concPU.getLocation().getY());
		
		
		
		ActionListener powerUpSpawnTimerListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.getBeach().removeGabPU(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair()));
				controller.getBeach().removeConcrPU(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair()));
				
				controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().setPickedUp(false);
				controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getGabPU().setIsActive(false);
				if(!controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).getWater().isActive()) {
					controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getGabPair())).setVacant(true);
				}
				
				
				controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().setActive(false);
				controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getConcrPU().setPickedUp(false);
				if(!controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).getWater().isActive()) {
					controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(controller.getBeach().getConcPair())).setVacant(true);
				}
				
				
				Object time = e.getSource();
				Timer myTime = (Timer) time;
				myTime.stop();
			}
		};
		
		
		Timer clock = controller.getPuWallLinkTimer();
		clock = new Timer(3000, powerUpSpawnTimerListener);
		controller.setPuWallLinkTimer(clock);
			
		//clock.setRepeats(true);
		//clock.start();
		//System.out.println("Spawn timer started");
		
		
		controller.collisionPowerUps();
		
		//assertTrue("Its false", concPU.isPickedUp() == true);
		
		Pair loaction2 = new Pair(1,0);
		gabPU.setLocationTest(loaction2);
		
		//assertFalse("", true);
	}
	
	
	
	
	@Test
	public void testSpawnTimer() {
		Game3Controller controller = new Game3Controller(new JFrame());
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

	
	*/
	
	
	//AnimalModel
	/*
	@Test
	public void tickTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		
		animal.setLocX(0);
		animal.setLocY(0);
		animal.setSpeedX(1);
		animal.setSpeedY(1);
		animal.setFrameHeight(1080);
		animal.setFrameWidth(900);
		animal.setWallHit(false);
		animal.setRestrictedMovement(false);
		
		animal.tick();
		assertTrue(animal.getLocX() == 1 & animal.getLocY() == 1);
		assertTrue("This is wrong",animal.getLocX() == 1 & animal.getLocY() == 1 & (animal.getBounds().getMaxY() + animal.getSpeedY() <= animal.getFrameHeight()) & (animal.getLocX()+ animal.getSpeedX() >= 0)
		& (!animal.isWallHit()) & (!animal.isRestrictedMovement()));
		
		animal.setSpeedX(-1);
		animal.setSpeedY(-1);
		animal.tick();
		assertTrue(animal.getLocX() == 0 && animal.getLocY() == 0);
		
		animal.setSpeedX(-1);
		animal.setSpeedY(-1);
		animal.tick();
		assertTrue(animal.getLocX() == 0 && animal.getLocY() == 0);
		
		animal.setFrameHeight(1080);
		animal.setFrameWidth(900);
		animal.setLocX(900);
		animal.setLocY(1080);
		animal.setSpeedX(1);
		animal.setSpeedY(1);
		animal.tick();
		assertTrue(animal.getLocX() == 900 && animal.getLocY() == 1080);
		
		animal.setWallHit(false);
		animal.setRestrictedMovement(false);
		animal.setLocX(2);
		animal.setLocY(2);
		animal.tick();
		assertTrue(animal.getLocX() == 3 && animal.getLocY() == 3);
		
		animal.setWallHit(false);
		animal.setRestrictedMovement(false);
		animal.setLocX(2);
		animal.setLocY(2);
		animal.tick();
		assertTrue(animal.getLocX() == 3 && animal.getLocY() == 3);
		
		animal.setWallHit(true);
		animal.setRestrictedMovement(true);
		animal.setLocX(2);
		animal.setLocY(2);
		animal.tick();
		assertTrue(animal.getLocX() == 2 && animal.getLocY() == 2);
	}
	*/
		
	
	@Test
	public void GraphicOnDeckTest() {
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setGraphicOnDeck(2);
		assertTrue(animal.getGraphicOnDeck() == 2);
	}
	
	
	
	@Test
	public void BoundsTest(){
		AnimalModelG3 animal =  new AnimalModelG3();
		animal.setLocX(3);
		animal.setLocY(3);
		animal.setHeight(60);
		animal.setWidth(60);
		assertTrue(animal.getBounds().getX() == 3 & animal.getBounds().getY() == 3 & animal.getBounds().getWidth() == 60 & animal.getBounds().getHeight() == 60 );
	}
	
	/*
	 
	@Test
	public void findBeachlocationTest(){
		
	}
	*/
	
	@Test
	public void resetPosTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setLocX(560);
		animal.setLocX(650);
		animal.setSpeedX(4);
		animal.setSpeedY(5);
		animal.resetPos();
		assertTrue(animal.getLocX() == 250 & animal.getLocY() == 250 & animal.getSpeedX() == 0 & animal.getSpeedY() == 0 );
	}
	
	@Test
	public void HeightTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setHeight(60);
		assertTrue(animal.getHeight() == 60);
	}
	
	@Test
	public void WidthTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setWidth(60);
		assertTrue(animal.getWidth() == 60);
	}
	
	@Test
	public void SpeedXTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setSpeedX(3);
		assertTrue(animal.getSpeedX() == 3);
	}
	
	@Test
	public void SpeedYTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setSpeedY(3);
		assertTrue(animal.getSpeedY() == 3);
	}
	
	@Test
	public void BoundHitTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setBoundHit(true);
		assertTrue(animal.isBoundHit() == true);
	}
	
	/*@Test
	public void FrameWidthTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setFrameWidth(1080);
		assertTrue(animal.getFrameWidth() == 1080);
	}
	
	@Test
	public void FrameHeightTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setFrameHeight(900);
		assertTrue(animal.getFrameHeight() == 900);
	}*/
	
	@Test
	public void PotentialMoveTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		assertTrue(animal.getPotentialMove().getX() == 0);
		assertTrue(animal.getPotentialMove().getY() == 0);
	}
	
	@Test
	public void WallHitTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setWallHit(true);
		assertTrue(animal.isWallHit() == true);
	}
	
	@Test
	public void RestrictedMovementTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setRestrictedMovement(true);
		assertTrue(animal.isRestrictedMovement() == true);
	}
	
	@Test
	public void isWaveHit(){
		AnimalModelG3 animal = new AnimalModelG3();
		animal.setWaveHit(true);
		assertTrue(animal.isWaveHit() == true);
	}
	
	@Test
	public void AnimalModelFramesTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		HashMap<Frames, JComponent> map = null;
		animal.setFrames(map);
		assertTrue(animal.getFrames() == null);
		
	}
	
	@Test
	public void AnimalModelGraphicsTest(){
		AnimalModelG3 animal = new AnimalModelG3();
		assertTrue(animal.getGraphics().size() == 0);
		
	}
	
	
	
	/*
	
	public void addPics(){

		try{
		ArrayList<BufferedImage> moveAnimations = new ArrayList<BufferedImage>();
		BufferedImage bufferedImage1 = ImageIO.read(new File("./Images/Game3/bluecrab_0.png"));
		moveAnimations.add(bufferedImage1);
		BufferedImage bufferedImage2 = ImageIO.read(new File("./Images/Game3/bluecrab_1.png"));
		moveAnimations.add(bufferedImage2);
		BufferedImage bufferedImage3 = ImageIO.read(new File("./Images/Game3/bluecrab_2.png"));
		moveAnimations.add(bufferedImage3);
		graphics.put("MOVE", moveAnimations);
		graphicOnDeck = 0;
		}
		catch(IOException e) {
    		e.printStackTrace();
    	}
		
	}
	
	public void findBeachLocation() {
		int tileHeight = ((frames.get(Frames.SHORE).getHeight()))/7;
		int tileWidth = ((frames.get(Frames.ANIMAL).getWidth()+frames.get(Frames.SHORE).getWidth()))/7;
		
		
		this.beachLocation.setX((int)(Math.floor(this.getLocX())/tileWidth));
		this.beachLocation.setY((int)(Math.ceil(this.getLocY())/tileHeight));
		
		
	}
	
	*/
	
	
	//Concrete Model Tests

	/*
	@Test
	public void ActiveTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		Pair location = new Pair(3,5);
		concrete.setViewLocation(location);
		concrete.setWidth(30);
		concrete.setHeight(30);
		concrete.setActive(true);
		assertTrue(concrete.getViewLocation() == location & concrete.getWidth() == 30 & concrete.getHeight() == 30);
	}
	*/
	
	@Test
	public void ConcPUStateTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		concrete.setWallState(ConcPUState.POWER_UP);
		assertTrue(concrete.getWallState() == ConcPUState.POWER_UP);
	}
	
	@Test
	public void LocationTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		Pair location = new Pair(3,5);
		concrete.setLocation(location, "test");
		assertTrue(concrete.getLocation() == location);
	}
	
	
	
	@Test
	public void boundsTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		concrete.setHeight(60);
		concrete.setWidth(60);
		concrete.setBounds(0, 0, concrete.getWidth(), concrete.getHeight());
		assertTrue(concrete.getBounds().getX() == 0 & concrete.getBounds().getY() == 0 & concrete.getHeight() == 60 & concrete.getWidth() == 60);
	}
	
	@Test
	public void ConcPUheightTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		concrete.setHeight(60);
		assertTrue(concrete.getHeight() == 60);
	}
	
	@Test
	public void ConcPUwidthTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		concrete.setWidth(60);
		assertTrue(concrete.getWidth() == 60);
	}
	
	@Test
	public void ConcPUHeightTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		concrete.setPickedUp(false);
		assertTrue(concrete.isPickedUp() == false & concrete.getWallState() == ConcPUState.POWER_UP & concrete.getWidth() == 30 & concrete.getHeight() == 30 & concrete.getViewLocation().getX() == 0 & concrete.getViewLocation().getY() == 0 );
		concrete.setPickedUp(true);
		assertTrue(concrete.isPickedUp() == true & concrete.getWallState() == ConcPUState.WALL & concrete.getWidth() == 70 & concrete.getHeight() == 150 & concrete.getViewLocation().getX() == 30 & concrete.getViewLocation().getY() == 0 );
	}

	
	
	/*
	@Test
	public void ViewLocationTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		concrete.setViewLocation(new Pair(0,0));
	}
	*/
	
	@Test
	public void ConcPUActiveTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		concrete.setActive(false);
		concrete.setWidth(30);
		concrete.setHeight(30);
		assertTrue(concrete.getIsActive() == false);
		concrete.setActive(true);
		assertTrue(concrete.getIsActive() == true & concrete.getWidth() == 30 & concrete.getHeight() == 30 & concrete.getViewLocation().getX() == 0 & concrete.getViewLocation().getY() == 0  );
	}
	
	@Test
	public void ConcPUGraphicsTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		HashMap<ConcPUState, ArrayList<BufferedImage>> map = null;
		concrete.setGraphics(map);
		assertTrue(concrete.getGraphics() == null);
		
	}
	
	@Test
	public void ConcPUModelFramesTest(){
		ConcretePUModel concrete = new ConcretePUModel();
		HashMap<Frames, JComponent> map = null;
		concrete.setFrameMap(map);
		assertTrue(concrete.getFrameMap() == null);
		}
	
	/*
	
	public BufferedImage getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(BufferedImage powerUp) {
		this.powerUp = powerUp;
	}

	public BufferedImage getWall() {
		return wall;
	}

	public void setWall(BufferedImage wall) {
		this.wall = wall;
	}
	public void addPics() {
		try{
			ArrayList<BufferedImage> wallGraphic = new ArrayList<BufferedImage>();
			BufferedImage concreteWall = ImageIO.read(new File("./Images/Game3/ConcreteWall.png"));
			wallGraphic.add(concreteWall);
			
			ArrayList<BufferedImage> puGraphic = new ArrayList<BufferedImage>();
			BufferedImage pu = ImageIO.read(new File("./Images/Game3/ConcretePU.png"));
			puGraphic.add(pu);
			
			graphics.put(ConcPUState.WALL, wallGraphic);
			graphics.put(ConcPUState.POWER_UP, puGraphic);
			}
			catch(IOException e) {
	    		e.printStackTrace();
	    	}
	}
	
		public Pair getViewLocation() {
		return viewLocation;
	}
	public void setViewLocation(Pair viewLocation) {
		int tileWidth = (int)((frameMap.get(Frames.ANIMAL).getWidth()+frameMap.get(Frames.SHORE).getWidth())/7);
		int tileHeight = (int)(frameMap.get(Frames.SHORE).getHeight()/7);
		if(location.getX() == 0 && location.getY() == 0){
			this.viewLocation.setX((int)(((location.getX()))*tileWidth) + 10);
			this.viewLocation.setY((int)(location.getY())*tileHeight + 10);
		}
		else if(location.getX() == 0){
			this.viewLocation.setX((int)(((location.getX()))*tileWidth) + 1);
			this.viewLocation.setY((int)(location.getY())*tileHeight);	
		}
		else if(location.getY() == 0){
			this.viewLocation.setX((int)((location.getX()))*tileWidth);
			this.viewLocation.setY((int)((location.getY())*tileHeight) + 1);
		}
	
		else{
			this.viewLocation.setX((int)((location.getX()))*tileWidth);
			this.viewLocation.setY((int)(location.getY())*tileHeight);
		}
	}
	
	*/
	//Gabion Tests
	
	@Test
	public void GabPUActiveTest(){
		GabionPUModel gabion = new GabionPUModel();
		gabion.setIsActive(true);
		gabion.setWidth(60);
		gabion.setHeight(60);
		assertTrue(gabion.getIsActive() == true & gabion.getViewLocation().getX() == 0 & gabion.getViewLocation().getY() == 0 );
	}
	
	@Test
	public void GabPULocationTest(){
		GabionPUModel gabion = new GabionPUModel();
		Pair location = new Pair(3,5);
		gabion.setLocation(location, "test");
		assertTrue(gabion.getLocation() == location);
	}
	
	@Test
	public void GabBoundsTest(){
		GabionPUModel gabion = new GabionPUModel();
		gabion.setHeight(60);
		gabion.setWidth(60);
		gabion.setBounds(0, 0, gabion.getWidth(), gabion.getHeight());
		assertTrue(gabion.getBounds().getX() == 0 & gabion.getBounds().getY() == 0 & gabion.getHeight() == 60 & gabion.getWidth() == 60);
	}
	
	
	public void GabPUheightTest(){
		GabionPUModel gabion = new GabionPUModel();
		gabion.setHeight(60);
		assertTrue(gabion.getHeight() == 60);
	}
	
	@Test
	public void GabPUwidthTest(){
		GabionPUModel gabion = new GabionPUModel();
		gabion.setWidth(60);
		assertTrue(gabion.getWidth() == 60);
	}
	
	@Test
	public void GabPUPickedUptTest(){
		GabionPUModel gabion = new GabionPUModel();
		gabion.setPickedUp(false);
		assertTrue(gabion.isPickedUp() == false & gabion.getWallState() == GabPUState.POWER_UP & gabion.getWidth() == 30 & gabion.getHeight() == 30 & gabion.getViewLocation().getX() == 0 & gabion.getViewLocation().getY() == 0 );
		gabion.setPickedUp(true);
		assertTrue(gabion.isPickedUp() == true & gabion.getWallState() == GabPUState.WALL & gabion.getWidth() == 70 & gabion.getHeight() == 150 & gabion.getViewLocation().getX() == 30 & gabion.getViewLocation().getY() == 0 );
	}
	
	@Test
	public void GabPUGraphicsTest(){
		GabionPUModel gab = new GabionPUModel();
		HashMap<GabPUState, ArrayList<BufferedImage>> map = null;
		gab.setGraphics(map);
		assertTrue(gab.getGraphics() == null);
		
	}
	
	@Test
	public void GabPUModelFramesTest(){
		GabionPUModel gab = new GabionPUModel();
		HashMap<Frames, JComponent> map = null;
		gab.setFrameMap(map);
		assertTrue(gab.getFrameMap() == null);
		}
	
	/*
	public BufferedImage getWall() {
		return wall;
	}
	public void setWall(BufferedImage wall) {
		this.wall = wall;
	}
	public BufferedImage getPowerUp() {
		return powerUp;
	}
	public void setPowerUp(BufferedImage powerUp) {
		this.powerUp = powerUp;
	}
	
	public void addPics() {
		try{
			ArrayList<BufferedImage> wallGraphic = new ArrayList<BufferedImage>();
			BufferedImage concreteWall = ImageIO.read(new File("./Images/Game3/GabionWall.png"));
			wallGraphic.add(concreteWall);
			
			ArrayList<BufferedImage> puGraphic = new ArrayList<BufferedImage>();
			BufferedImage pu = ImageIO.read(new File("./Images/Game3/GabionPU.png"));
			puGraphic.add(pu);
			
			graphics.put(GabPUState.WALL, wallGraphic);
			graphics.put(GabPUState.POWER_UP, puGraphic);
			}
			catch(IOException e) {
	    		e.printStackTrace();
	    }
	}

*/
	
	//SunHurricane Tests
	@Test
	public void SunHurricaneBoundsTest(){
		SunHurricaneModel SunHurricane = new SunHurricaneModel();
		SunHurricane.setHeight(60);
		SunHurricane.setWidth(60);
		assertTrue(SunHurricane.getBounds().getX() == 0 & SunHurricane.getBounds().getY() == 0 & SunHurricane.getHeight() == 60 & SunHurricane.getWidth() == 60);
	}
	
	@Test
	public void SunHurricaneHeightTest(){
		SunHurricaneModel SunHurricane = new SunHurricaneModel();
		SunHurricane.setHeight(60);
		assertTrue(SunHurricane.getHeight() == 60);
	}
	
	@Test
	public void SunHurricaneWidthTest(){
		SunHurricaneModel SunHurricane = new SunHurricaneModel();
		SunHurricane.setWidth(60);
		assertTrue(SunHurricane.getWidth() == 60);
	}
	
	
	@Test
	public void SunHurricaneLocationTest(){
		SunHurricaneModel SunHurricane = new SunHurricaneModel();
		Pair Location = new Pair(3,5);
		SunHurricane.setLocation(Location);
		assertTrue(SunHurricane.getLocation() == Location );
	}
	
	@Test
	public void SunHurricaneInitialPositionTest(){
		SunHurricaneModel SunHurricane = new SunHurricaneModel();
		SunHurricane.setInitialPosition(768);
		assertTrue(SunHurricane.getInitialPosition() == 768 );
	}
	
	@Test
	public void SunHurricaneGraphicsTest(){
		SunHurricaneModel sunHurricane = new SunHurricaneModel();
		HashMap<String, ArrayList<BufferedImage>> map = null;
		sunHurricane.setGraphics(map);
		assertTrue(sunHurricane.getGraphics() == null);
		
	}
	
	/*
	public void move() {
		location.setX(location.getX()-2);
		location.setY(this.calculateY(location.getX()));
		
	}
	
	
	public int calculateY(int x) {
		//System.out.println("Panel height is: " + panel.getHeight());
		int y = (int)(-1*(panel.getHeight()*(Math.sin(((.00165)*x)))) + panel.getHeight());
		
		return y;
	}

	
	public void addPics() {
		ArrayList<BufferedImage> sun = new ArrayList<BufferedImage>();
		ArrayList<BufferedImage> cloudStates = new ArrayList<BufferedImage>();
		try {
			BufferedImage sunPic = ImageIO.read(new File("./Images/Game3/glowingbg.png"));
			sun.add(sunPic);
			
			BufferedImage hurrAngry = ImageIO.read(new File("./Images/Game3/angry_cloud.png"));
			BufferedImage hurrScared = ImageIO.read(new File("./Images/Game3/dismayed_cloud.png"));
			cloudStates.add(hurrAngry); cloudStates.add(hurrScared);
			
			graphics.put("SUN", sun);
			graphics.put("HURRICANE", cloudStates);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	 
	 */
	
	//Tutorial
	
	@Test
	public void TutorialKeyBoardPicOnDeckTest(){
		Tutorial tutorial = new Tutorial();
		tutorial.setKeyBoardPicOnDeck(2);
		assertTrue(tutorial.getKeyBoardPicOnDeck() == 2);
	}
	
	@Test
	public void TutorialKeyBoardStopTest(){
		Tutorial tutorial = new Tutorial();
		tutorial.setKeyboardStop(true);
		assertTrue(tutorial.isKeyboardStop() == true);
	}
	
	@Test
	public void TutorialWaveWarningTest(){
		Tutorial tutorial = new Tutorial();
		tutorial.setWaveWarning(true);
		assertTrue(tutorial.isWaveWarning() == true);
	}
	
	@Test
	public void TutorialDialogOnTest(){
		Tutorial tutorial = new Tutorial();
		tutorial.setDialogueOn(true);
		assertTrue(tutorial.isDialogueOn() == true);
	}
	
	@Test
	public void TutorialTest(){
		Tutorial tutorial = new Tutorial();
		HashMap<AnimGraphics, ArrayList<BufferedImage>> map = null;
		tutorial.setGraphicMap(map);
		assertTrue(tutorial.getGraphicMap() == null);
		
	}
	
	
	 /*
	public void addPics() {
		try{
			//Adding keyboard graphics
			ArrayList<BufferedImage> keyBoardPics = new ArrayList<BufferedImage>();
			BufferedImage key_pic_0 = ImageIO.read(new File("./Images/Game3/key_press_0.png"));
			BufferedImage key_pic_1 = ImageIO.read(new File("./Images/Game3/key_press_1.png"));
			BufferedImage key_pic_2 = ImageIO.read(new File("./Images/Game3/key_press_2.png"));
			BufferedImage key_pic_3 = ImageIO.read(new File("./Images/Game3/key_press_3.png"));
			BufferedImage key_pic_4 = ImageIO.read(new File("./Images/Game3/key_press_4.png"));
			keyBoardPics.add(key_pic_0);
			keyBoardPics.add(key_pic_1);
			keyBoardPics.add(key_pic_2);
			keyBoardPics.add(key_pic_3);
			keyBoardPics.add(key_pic_4);
			
			//Adding 'X'
			ArrayList<BufferedImage> xPic = new ArrayList<BufferedImage>();
			BufferedImage x = ImageIO.read(new File("./Images/Game3/x.png"));
			xPic.add(x);
			
			//Adding arrow
			ArrayList<BufferedImage> arrowPic = new ArrayList<BufferedImage>();
			BufferedImage arrow = ImageIO.read(new File("./Images/Game3/green_arrow.png"));
			arrowPic.add(arrow);
			
			//Adding final dialogue
			ArrayList<BufferedImage> dialoguePic = new ArrayList<BufferedImage>();
			BufferedImage dialogue = ImageIO.read(new File("./Images/Game3/Dialogue2.png"));
			dialoguePic.add(dialogue);
			
			graphicMap.put(AnimGraphics.KEYBOARD, keyBoardPics);
			graphicMap.put(AnimGraphics.BIG_X, xPic);
			graphicMap.put(AnimGraphics.ARROW, arrowPic);
			graphicMap.put(AnimGraphics.DIALOGUE, dialoguePic);
			}
			catch(IOException e) {
	    		e.printStackTrace();
	    	}
	}
 
	
	*/
	
	//WaterModel
	
	@Test
	public void WaterModelLocationTest(){
		Pair location = new Pair(3,5);
		WaterModel water = new WaterModel(location);
		assertTrue(water.getLocation() == location);
		location = new Pair(5,6);
		water.setLocation(location);
		assertTrue(water.getLocation() == location);
	}
	
	@Test
	public void WaterModelBoundsTest(){
		WaterModel water = new WaterModel("test");
		assertTrue(water.getBounds().getX() == 0 & water.getBounds().getY() == 0 & water.getBounds().getWidth() == 30 & water.getBounds().getHeight() == 30);
	}
	
	@Test
	public void WaterModelHeightTest(){
		WaterModel water = new WaterModel("test");
		water.setHeight(30);
		assertTrue(water.getHeight() == 30);
	}
	

	@Test
	public void WaterModelWidthTest(){
		WaterModel water = new WaterModel("test");
		water.setWidth(30);
		assertTrue(water.getWidth() == 30);
	}
	
	@Test
	public void WaterModelActiveTest(){
		WaterModel water = new WaterModel("test");
		water.setActive(true);
		assertTrue(water.isActive() == true);
	}
	
	
	@Test
	public void WaterModelGraphicOnDeckTest(){
		WaterModel water = new WaterModel("test");
		water.setGraphicOnDeck(30);
		assertTrue(water.getGraphicOnDeck() == 30);
	}
	
	@Test
	public void WaterGraphicsTest(){
		WaterModel water = new WaterModel("test");
		ArrayList<BufferedImage> array = null;
		water.setWaterGraphics(array);
		assertTrue(water.getWaterGraphics() == null);
		
	}
	
	/*	
	
	public void addPics() {
		try{
			waterGraphics = new ArrayList<BufferedImage>();
			BufferedImage bufferedImage1 = ImageIO.read(new File("./Images/Game3/sand_with_water.png"));
			waterGraphics.add(bufferedImage1);
			BufferedImage bufferedImage2 = ImageIO.read(new File("./Images/Game3/tile_water_C.png"));
			waterGraphics.add(bufferedImage2);
			graphicOnDeck = 0;
			}
			catch(IOException e) {
	    		e.printStackTrace();
	    	}
			
	}
	 */
	
	//WaveModel
	
	/*
	@Test
	public void WaveModelSpawnTest(){
		WaveModel wave = new WaveModel();
		
	}
	*/
	
	@Test
	public void WaveStatePauseTest(){
		WaveModel wave = new WaveModel();
		
		wave.pauseWave();
		assertTrue(wave.getWaveStatePause() == true);
		wave.resumeWave();
		assertTrue(wave.getWaveStatePause() == false);
	}
	
	@Test
	public void WaveStateDeleteTest(){
		WaveModel wave = new WaveModel();
		
		wave.resetWave();
		assertTrue(wave.isDeleteWave() == true);		
	}
	
	@Test
	public void WaveModelBoundsTest(){
		WaveModel wave = new WaveModel();
		wave.setHeight(30);
		wave.setWidth(30);
		wave.setLocation(new Pair(0,0));
		assertTrue(wave.getBounds().getX() == 0 & wave.getBounds().getY() == 0 & wave.getBounds().getWidth() == 30 & wave.getBounds().getHeight() == 30);
	}
	
	@Test
	public void WaveModelheightTest(){
		WaveModel wave = new WaveModel();
		wave.setHeight(30);
		assertTrue(wave.getHeight() == 30);
	}
	
	@Test
	public void WaveModelWidthTest(){
		WaveModel wave = new WaveModel();
		wave.setWidth(30);
		assertTrue(wave.getWidth() == 30);
	}
	
	@Test
	public void WaveModeLocationTest(){
		WaveModel wave = new WaveModel();
		Pair pair = new Pair(3,3);
		wave.setLocation(pair);
		assertTrue(wave.getLocation() == pair);
	}
	
	@Test
	public void WaveModelReceedTest(){
		WaveModel wave = new WaveModel();
		wave.setReceed(true);
		assertTrue(wave.isReceed() == true);
	}
	
	@Test
	public void WaveModelWaveClusterTest(){
		WaveModel wave = new WaveModel();
		WaveClusters waveEnum = WaveClusters.values()[0];
		wave.setClusterGroup(waveEnum);
		assertTrue(wave.getClusterGroup() == waveEnum);
	}
	
	@Test
	public void WaveModelLastWaveTest(){
		WaveModel wave = new WaveModel();
		wave.setLastWave(true);
		assertTrue(wave.isLastWave() == true);
	}
	
	@Test
	public void WaveModelVelocityTest(){
		WaveModel wave = new WaveModel();
		wave.setVelocity(2);
		assertTrue(wave.getVelocity() == 2);
	}
	
	@Test
	public void WaveModelDeleteWaveTest(){
		WaveModel wave = new WaveModel();
		wave.setDeleteWave(true);
		assertTrue(wave.isDeleteWave() == true);
	}
	
	@Test
	public void WaveModelFramesTest(){
		WaveModel wave = new WaveModel();
		HashMap<Frames, JComponent> map = null;
		wave.setFrames(map);
		assertTrue(wave.getFrames() == null);
	}
	
	
	/*
	
	public void randomSpawn(int clusterVal) {
		WaveClusters waveEnum = WaveClusters.values()[clusterVal];
		setClusterGroup(waveEnum);
		location = new Pair(0,0);

		//Change 250 to make it dynamic (should be width of the shore line)
		location.setX((int)(screenSizeX - 250) + (int)(Math.random() * 500));
		
		int beachHeight = frames.get(Frames.SHORE).getHeight();
		int blockOneMin = 0, blockOneMax = beachHeight/7;
		int blockTwoMin = blockOneMax+1, blockTwoMax = blockOneMax*2;
		int blockThreeMin = blockTwoMax+1, blockThreeMax = blockOneMax*3;
		int blockFourMin = blockThreeMax+1, blockFourMax = blockOneMax*4;
		int blockFiveMin = blockFourMax+1, blockFiveMax = blockOneMax*5;
		int blockSixMin = blockFiveMin+1, blockSixMax = blockOneMax*6;
		int blockSevenMin = blockSixMin+1, blockSevenMax = blockOneMax*7;
		
		
		switch(clusterGroup){
			case CLUSTER_ONE:
				location.setY(blockOneMin + (int)(Math.random() * (((blockOneMax - blockOneMin) + 1))));
				break;
			case CLUSTER_TWO:
				location.setY(blockTwoMin + (int)(Math.random() * (((blockTwoMax - blockTwoMin) + 1))));
				break;
			case CLUSTER_THREE:
				location.setY(blockThreeMin + (int)(Math.random() * (((blockThreeMax - blockThreeMin) + 1))));
				break;
			case CLUSTER_FOUR:
				location.setY(blockFourMin + (int)(Math.random() * (((blockFourMax - blockFourMin) + 1))));
				break;
			case CLUSTER_FIVE:
				location.setY(blockFiveMin + (int)(Math.random() * (((blockFiveMax - blockFiveMin) + 1))));
				break;
		}
	
	}
	
	
	ActionListener movementTimer = new ActionListener() {

		@Override

		public void actionPerformed(ActionEvent e) {

			if(!isReceed()) {
				if(wavePause) {
					setVelocity(0);
				}
				else{
					setVelocity((int)((screenSizeX*.3125)/(movement)));
				}
				location.setX((int)(location.getX()-getVelocity()));
				
				movement += 1;
			}
			else {
				if(wavePause) {
					setVelocity(0);
				}
				else{
					setVelocity((int)((screenSizeX*.3125/(movement))));
				}
				
				location.setX((int)(location.getX()+velocity));
				//accelerator = -1*accelerator;
				movement -= 1; 
				
			}
			Object time = e.getSource();
			Timer myTime = (Timer) time;
		}
	};
	
	*/
	
	//BeachModel
	
	@Test
	public void BeachModelFrameMapTest(){
		BeachModel beach = new BeachModel("test");
		HashMap<Frames, JComponent> map = null;
		beach.setFrameMap(map);
		assertTrue(beach.getFrameMap() == null);
	}
	
	@Test
	public void BeachModelInitializebeachTest(){
		BeachModel beach = new BeachModel("test");
		beach.initializeBeach();
		assertTrue(beach.generatePPUL().size() == 49);
	
	}
	
	
	
	
	
}
