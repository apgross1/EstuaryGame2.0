import models.AnimalModelG3;
import models.BeachModel;
import models.BeachModel.Pair;
import models.ConcretePUModel;
import models.ConcretePUModel.ConcPUState;
import models.GabionPUModel.GabPUState;

import models.GabionPUModel;
import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.Timer;

import org.junit.Test;

import controller.Game3Controller;

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





//Functions to test
	
	
	
	/* Beach model: -Andrew
	 * void spawnGab(GabionPUModel)
	 * void spawnConc(ConcretePUModel)
	 * ArrayList<Pair> generatePPUL()
	 * void removeSquare()
	 */

	/*GabionPUModel: -Daniel
	 * void breakdown()
	 * void spawn()
	 */
	
	/*ConcretePUModel: -Andrew
	 * void breakdown()
	 * void spawn()
	 */
	
	/*AnimalModelG3: - Daniel
	 * void healthUp()
	 * void healthDown()
	 * void pickUp()
	 */
	
	/*High-level tests: -Tim
	 *  void waveHit()
	 *  void start()
	 *  void stop()
	 *  void pickedUp() //For powerup
	 *  void endGame()
	 *  void startGame()
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
			}
		};
	    
		Timer time = new Timer(2000, actionListener);
		
		time.setRepeats(false);
	    time.start();
	    try {
			Thread.sleep(2600);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	    finally {
	    	assertFalse("Should be false...", concWall.getIsActive());
	    }
	}
	
	//Breakdown of concrete wall power-up
	@Test
	public void testBreakdown() {
		ConcretePUModel concWall = new ConcretePUModel();
		concWall.breakDown();
		assertFalse("Should pass...", concWall.getIsActive());
	}
	
	//BeachModel
	//Testing spawnGab (adding gab wall to board)
	@Test
	public void testSpawnGab() {
		BeachModel beach = new BeachModel();
		int count1 = beach.getSquareCount();
		beach.spawnGabPU();
		int count2 = beach.getSquareCount();
		assertTrue("Should be 1 less...", (count1 == count2+1));
	}
	
	//Testing spawnConcr (adding concrete to board)
	@Test
	public void testSpawnConcrete() {
		BeachModel beach = new BeachModel();
		int count1 = beach.getSquareCount();
		beach.spawnConcrPU();
		int count2 = beach.getSquareCount();
		assertTrue("Should be 1 less...", (count1 == count2+1));
	}
	//Testing PPUL generator (Possible Power-Up Location)
	@Test
	public void testGeneratePPUL() {
		BeachModel beach = new BeachModel();
		Collection<Pair> origList = beach.generatePPUL();
		beach.removeSquare(); //Low-level
		Collection<Pair> modifiedList = beach.generatePPUL();
		assertTrue("New list should have less combos...", origList.size() > modifiedList.size());
		
		//Test if after spawning PU Pair combos decreases (it should, PUs shouldn't be stacked on each other)
		beach.spawnGabPU();
		Collection<Pair> listPostGabSpawn = beach.generatePPUL();
		assertTrue("New list should have less combos than modified list...", listPostGabSpawn.size() < modifiedList.size());
		
		beach.spawnConcrPU();
		Collection<Pair> listPostConcrSpawn = beach.generatePPUL();
		assertTrue("New list should have less combos than modified list...", listPostConcrSpawn.size() < listPostGabSpawn.size());
	}
	
	//Testing removal of square/position on grid
	//Spot filled by wave or power up
	@Test
	public void testRemoveSquare() {
		BeachModel beach = new BeachModel();
		ConcretePUModel concWall = new ConcretePUModel();
		HashMap<Pair, Object> grid = new HashMap<Pair,Object>();
		grid.put(beach.new Pair(3,5), concWall);
		
		int size1 = grid.size();
		beach.removeSquare();
		int size2 = grid.size();
		assertTrue("size 1 should be greater than 2...", size1 > size2);
		
	}
	
	
	//need to add attributtes for concwallPUonbeach and gabionPUonbeach
	@Test
	public void testpickedUp() {
		models.GabionPUModel gabby = new models.GabionPUModel();
		models.ConcretePUModel conc = new models.ConcretePUModel();
		gabby.setGabionPUonBeach(1);
		//check this out
		models.AnimalModelG3 animal = new models.AnimalModelG3();
		animal.pickUp();
		assertTrue("True", gabby.getWallState().equals(GabPUState.WALL));
		conc.setConcretePUonbeach(1);
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
	
	@Test
	public void voidstartTimer(){
		Game3Controller g3clock = new Game3Controller();
		g3clock.setTime(180);
		assertTrue("True", g3clock.getgameActive() == true);
		
	}
	
	public void voidendTimer(){
		Game3Controller g3clock = new Game3Controller();
		g3clock.setTime(0);
		assertTrue("False", g3clock.getgameActive() == false);
		}
	
	
	//Gabion Power UP tests
	@Test
	public void testGabionSpawnPU(){
		GabionPUModel gabWall = new GabionPUModel();
		Game3Controller clock = new Game3Controller();
		gabWall.setGabionPUonBeach(0);
		assertEquals("Should be 0",gabWall.getGabionPUonBeach(), 0);
		
		gabWall.spawn();
		assertEquals("Should be 1",gabWall.getGabionPUonBeach(), 1);
		assertTrue("Should be greater than 0", gabWall.getXloc() > 0);
		assertTrue("Should be greater than 0", gabWall.getYloc() > 0);
		
		clock.startTime();
		assertEquals("Should be true",gabWall.getIsActive(), true);
		clock.stopTime();
		assertEquals("Should be false",gabWall.getIsActive(), false);
	}
	
	@Test
	public void testGabionBreakdownPU(){
		GabionPUModel gabWall = new GabionPUModel();
		gabWall.setGabionPUonBeach(0);
		gabWall.spawn();
		gabWall.breakDown();
		assertEquals("Should be 0",gabWall.getGabionPUonBeach(), 0);
		assertEquals(gabWall.getIsActive(), false);
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
		//nothing touched
		animal.setEmptyHanded(0);
		assertTrue("Should be 0", animal.getEmptyHanded() == 0);
		
		//Has GabionPU
		animal.setEmptyHanded(1);
		assertTrue("Should be 1", animal.getEmptyHanded() == 1);

		//Has ConcreteWallPU
		animal.setEmptyHanded(2);
		assertTrue("Should be 2", animal.getEmptyHanded() == 2);
	}
	
}
