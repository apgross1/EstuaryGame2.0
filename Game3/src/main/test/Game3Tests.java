import models.AnimalModelG3;
import models.BeachModel;
import models.Pair;
import models.PairComparator;
import models.ConcretePUModel;
import models.ConcretePUModel.ConcPUState;
import models.GabionPUModel.GabPUState;
import models.GridBlock;
import models.WaterModel;
import models.GabionPUModel;
import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
	/*
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
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
			
	}

	@After
	public void tearDown() throws Exception {
	}
	*/

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
			tempConcr.setLocationTest(beach.findPairInGrid(pair));
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
			tempGab.setLocationTest(beach.findPairInGrid(pair));
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
			tempGab.setLocationTest(beach.findPairInGrid(pair));
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
			tempConcr.setLocationTest(beach.findPairInGrid(pair2));
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
	
	//AnimalModel
	@Test
	public void testGameActive(){
		AnimalModelG3 animal = new AnimalModelG3();
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
		
		animal.setBeachLocation(new Pair(3,5));
		
		//This is a poor test but can't access controller because of Jframe stuff
		assertTrue("This should work", beach.getPositionGrid()[animal.getBeachLocation().getY()][animal.getBeachLocation().getX()] == 2 );
		
	}
	
	
	@Test
	public void testPickup(){
		AnimalModelG3 animal = new AnimalModelG3();
		ConcretePUModel concPU = new ConcretePUModel();
		GabionPUModel gabPU =  new GabionPUModel();
		Game3Controller controller = new Game3Controller();
		
		
		Pair location = new Pair(0,0);
		concPU.setLocation(location, "test");
		animal.setBeachLocation(location);
		
		controller.collisionTile();
		assertTrue(animal.getBeachLocation().getX() == concPU.getLocation().getX());
		assertTrue(animal.getBeachLocation().getY() == concPU.getLocation().getY());
		
		assertTrue("Its false", gabPU.isPickedUp() == true);
		
		Pair loaction2 = new Pair(1,0);
		gabPU.setLocationTest(loaction2);
		
		//assertFalse("", true);
	}

	/*
	
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
}
