import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.Test;

import models.AnimalModel;
import models.GabionWallModelG1.GabionChunk;

public class Game1Tests {
	//My tests
	//Low level tests (within-class) (All methods in AnimalModel and GabionWallModel:
	//Movement
	//calculateDamageGabion
	//calculateDamageConcrete
	//
	//High level tests (Between-class):
	//Random spawning
	//Pickup
	
	//Movement of animal
	
	//Daniel's tests
		//All of BarModel and ConcreteWallModel
		////Game start (tests timer)
		////Game end (tests timer as well)
		//Wave hit (after round) (Covers BarModel)
		
	
	//Low-level testing
	//AnimalModel
	@Test
	public void testAnimalMovement() {
		models.AnimalModel myAnimal = new models.AnimalModel();
		myAnimal.setLocX(0);
		myAnimal.setLocY(0);
		
		//Basic cardinal directions with no edge cases
		//South from origin
		myAnimal.setCurrDir(enums.Direction.SOUTH);
		myAnimal.move();
		assertTrue("Y should be 1... ", myAnimal.getLocY()==1);
		
		//North from 1 down from origin (to avoid edge case)
		myAnimal.setCurrDir(enums.Direction.NORTH);
		myAnimal.move();
		assertTrue("Y should be 0...", myAnimal.getLocY() == 0);
		
		//East from origin
		myAnimal.setCurrDir(enums.Direction.EAST);
		myAnimal.move();
		assertTrue("X should be 1...", myAnimal.getLocX() == 1);
		
		//West from origin+1
		myAnimal.setCurrDir(enums.Direction.WEST);
		myAnimal.move();
		assertTrue("X should be 0...", myAnimal.getLocX() == 0);
		
		//Combinations (NE,NW,SE,SW)
		//NE from (0,1)
		myAnimal.setLocX(0);
		myAnimal.setLocY(1);
		myAnimal.setCurrDir(enums.Direction.NORTH_EAST);
		myAnimal.move();
		assertTrue("X should be 1 and y should be 0...", (myAnimal.getLocX() == 1) && (myAnimal.getLocY() == 0));
	
		//NW from (1,1)
		myAnimal.setLocX(1);
		myAnimal.setLocY(1);
		myAnimal.setCurrDir(enums.Direction.NORTH_WEST);
		myAnimal.move();
		assertTrue("X should be 0 and y should be 0...", (myAnimal.getLocX() == 0) && (myAnimal.getLocY() == 0));
		
		//SE from (0,0)
		myAnimal.setLocX(0);
		myAnimal.setLocY(0);
		myAnimal.setCurrDir(enums.Direction.SOUTH_EAST);
		myAnimal.move();
		assertTrue("X should be 1 and y should be 1...", (myAnimal.getLocX() == 1) && (myAnimal.getLocY() == 1));
		
		//SW from (1,0)
		myAnimal.setLocX(1);
		myAnimal.setLocY(0);
		myAnimal.setCurrDir(enums.Direction.SOUTH_WEST);
		myAnimal.move();
		assertTrue("X should be 0 and y should be 1...", (myAnimal.getLocX() == 0) && (myAnimal.getLocY() == 1));
		
		//Edge cases
		//West (0,0)
		myAnimal.setLocX(0);
		myAnimal.setLocY(0);
		myAnimal.setCurrDir(enums.Direction.WEST);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == 0) && (myAnimal.getLocY() == 0));
		
		JPanel frame = new JPanel();
		frame.setBounds(0, 0, 200 ,200);
		//East (199,0)
		myAnimal.setLocX(frame.getWidth());
		myAnimal.setLocY(frame.getY());
		myAnimal.setCurrDir(enums.Direction.EAST);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == frame.getWidth()) && (myAnimal.getLocY() == frame.getY()));
		
		//North (0,0)
		myAnimal.setLocX(frame.getX());
		myAnimal.setLocY(frame.getY());
		myAnimal.setCurrDir(enums.Direction.NORTH);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == frame.getX()) && (myAnimal.getLocY() == frame.getY()));
		
		//South (0,200)
		myAnimal.setLocX(frame.getWidth());
		myAnimal.setLocY(frame.getHeight());
		myAnimal.setCurrDir(enums.Direction.SOUTH);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == frame.getX() && (myAnimal.getLocY() == frame.getHeight())));
		
		//NE (200,0)
		myAnimal.setLocX(frame.getWidth());
		myAnimal.setLocY(frame.getY());
		myAnimal.setCurrDir(enums.Direction.NORTH_EAST);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == frame.getWidth() && (myAnimal.getLocY() == frame.getY())));
		
		//NW (0,0)
		myAnimal.setLocX(frame.getX());
		myAnimal.setLocY(frame.getY());
		myAnimal.setCurrDir(enums.Direction.NORTH_WEST);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == frame.getX() && (myAnimal.getLocY() == frame.getY())));
		
		//SE (200,200)
		myAnimal.setLocX(frame.getWidth());
		myAnimal.setLocY(frame.getHeight());
		myAnimal.setCurrDir(enums.Direction.SOUTH_EAST);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == frame.getWidth() && (myAnimal.getLocY() == frame.getHeight())));
		
		//SW (0,200)
		myAnimal.setLocX(frame.getX());
		myAnimal.setLocY(frame.getHeight());
		myAnimal.setCurrDir(enums.Direction.SOUTH_WEST);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == frame.getX() && (myAnimal.getLocY() == frame.getHeight())));
	}
	
	@Test
	public void testAnimalPickUp() {
		models.AnimalModel myAnimal = new models.AnimalModel();
		myAnimal.pickUp();
		assertFalse("Should be false...", myAnimal.isEmptyHanded());
	}
	
	
	//These methods will not be implemented in Game1 maybe copy them over
	//If they have effective tests that aren't included in games that
	//use healthUp/healthDown?
	@Test
	public void testHealthDown() {
		models.AnimalModel myAnimal = new models.AnimalModel();
		myAnimal.setHealth(1);
		
		//Health Down
		myAnimal.healthDown();
		assertTrue("Should be 0...", myAnimal.getHealth() == 0);
		
		//Edge case
		myAnimal.healthDown();
		assertTrue("Health should still be 0...", myAnimal.getHealth() == 0);
	}
	
	@Test
	public void testHealthUp() {
		models.AnimalModel myAnimal = new models.AnimalModel();
		myAnimal.setHealth(myAnimal.getMaxHealth()-1);
		
		//Health up
		myAnimal.healthUp();
		assertTrue("Should be max...", myAnimal.getHealth() == myAnimal.getMaxHealth());
		
		//Edge case
		myAnimal.healthUp();
		assertTrue("Should still be max...", myAnimal.getHealth() == myAnimal.getMaxHealth());
	}
	
	//GabionWallModel
	@Test
	public void testAddPiece() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(0);
		myGabWall.addPiece();
		assertTrue("Should be 1...", myGabWall.getCurrentOysters() == 1);
		
		//Edge case
		myGabWall.setCurrentOysters(myGabWall.getMaxOysters());
		myGabWall.addPiece();
		assertTrue("Should be the same number...", myGabWall.getCurrentOysters() == myGabWall.getMaxOysters());
	}
	
	@Test
	public void testRemoveChunk() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(1);
		myGabWall.removeChunk(1);
		assertTrue("Should be 0...", myGabWall.getCurrentOysters() == 0);
		
		//Edge case
		myGabWall.setCurrentOysters(0);
		myGabWall.removeChunk(1);
		assertTrue("Should be the same number...", myGabWall.getCurrentOysters() == 0);
	}
	
	@Test
	public void testIsFull() {
		//Test for full
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(myGabWall.getMaxOysters());
		assertTrue("Should be max...", myGabWall.isFull());
		
		//Test for not full
		myGabWall.setCurrentOysters(50);
		assertFalse("Should not be max...", myGabWall.isFull());
	}
	
	@Test
	public void isEmpty() {
		//Test for full
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(0);
		assertTrue("Should be min...", myGabWall.isEmpty());
		
		//Test for not full
		myGabWall.setCurrentOysters(50);
		assertFalse("Should not be max...", myGabWall.isEmpty());
	}
	
	@Test
	public void testBreakdown() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(myGabWall.getMaxOysters());
		myGabWall.breakDown();
		assertTrue("Should be empty...", myGabWall.isEmpty());
	}
	
	@Test
	public void testOystersOnBeach() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setOystersOnBeach(0);
		myGabWall.amountRemoved(5);
		assertTrue("Should be 5...", myGabWall.getOystersOnBeach() == 5);
	}
	
	@Test
	public void testSpawn() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setOystersOnBeach(0);
		int onBeachBeforeSpawn = myGabWall.getOystersOnBeach();
		myGabWall.spawn(true, 15);
		assertTrue("Should be 15...", myGabWall.getChunks().size() == 15);
		
		boolean allHaveCoords = true;
		java.util.Iterator<GabionChunk> it = myGabWall.getChunks().iterator();
		GabionChunk chunk = it.next();
		while(it.hasNext()) {
			if ((chunk.getLocY() < 0) && (chunk.getLocX() < 0)) {
				allHaveCoords = false;
				break;
			}
		}
		assertTrue("Should be true", allHaveCoords);
	}
	
	@Test
	public void testCalculateDamage() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(100);
		int damage1 = myGabWall.calculateDamage();
		
		myGabWall.setCurrentOysters(50);
		int damage2 = myGabWall.calculateDamage();
		
		assertTrue("damage1 should be less than damage2", damage1 < damage2);
	}
	
	@Test
	public void testAmountRemoved() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(100);
		int damage1 = myGabWall.calculateDamage();
		
		myGabWall.setCurrentOysters(50);
		int damage2 = myGabWall.calculateDamage();
		
		int remove1 = myGabWall.amountRemoved(damage1);
		int remove2 = myGabWall.amountRemoved(damage2);
		assertTrue("remove1 should be less than remove 2", remove1 < remove2);
		
		//Edge case
		myGabWall.setCurrentOysters(1);
		int damage3 = myGabWall.calculateDamage();
		int remove3 = myGabWall.amountRemoved(damage3);
		assertTrue("Should be no less than 0...", remove3 >= 0);
	}
	
	//High Level Tests (Involving multiple classes)
	@Test
	public void testPickUpEvent() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		models.AnimalModel myAnimal = new models.AnimalModel();
		
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(99);
		myGabWall.setOystersOnBeach(1);
		myAnimal.pickUp();
		
		assertTrue("None should be on beach...", myGabWall.getOystersOnBeach() == 0);
		assertTrue("All should be in wall...", myGabWall.getCurrentOysters() == 100);
	}
}