import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.Test;

import models.AnimalModel;
import models.GabionWallModelG1.GabionChunk;
import models.ConcreteWallModelG1;
import models.ConcreteWallModelG1.ConcreteChunk;
import models.GabionWallModelG1;
import models.BarModel;
import controller.Game1Controller;

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
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenHeight = 0;
	private int screenWidth = 0;
	JFrame gameFrame = new JFrame();
	
	@Test
	public void testAnimalMovement() {
		models.AnimalModel myAnimal = new models.AnimalModel(screenSize);
		screenSize.height = 10;
		screenSize.width = 10;
		
		myAnimal.setLocX(0);
		myAnimal.setLocY(50);//TOP OF SCREEN
		
		//Basic cardinal directions with no edge cases
		//South from origin
		myAnimal.setCurrDir(enums.Direction.SOUTH);
		myAnimal.move();
		assertTrue("Y should be 1... ", myAnimal.getLocY()==57);
		
		//North from 1 down from origin (to avoid edge case)
		myAnimal.setCurrDir(enums.Direction.NORTH);
		myAnimal.move();
		assertTrue("Y should be 0...", myAnimal.getLocY() == 43);
		
		//East from origin
		myAnimal.setCurrDir(enums.Direction.EAST);
		myAnimal.move();
		assertTrue("X should be 1...", myAnimal.getLocX() == 7);
		
		//West from origin+1
		myAnimal.setCurrDir(enums.Direction.WEST);
		myAnimal.move();
		assertTrue("X should be 0...", myAnimal.getLocX() == 0);
		
		/*
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
		*/
		
		//Edge cases
		//West (0,0)
		myAnimal.setLocX(0);
		myAnimal.setLocY(0);
		myAnimal.setCurrDir(enums.Direction.WEST);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == 0) && (myAnimal.getLocY() == 0));
		
		//East
		myAnimal.setLocX(885);
		myAnimal.setLocY(0);
		myAnimal.setCurrDir(enums.Direction.EAST);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == 885) && (myAnimal.getLocY() == 0));
		
		//North (0,0)
		myAnimal.setLocX(0);
		myAnimal.setLocY(0);
		myAnimal.setCurrDir(enums.Direction.NORTH);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == 0) && (myAnimal.getLocY() == 0));
		
		//South (0,200)
		myAnimal.setLocX(0);
		myAnimal.setLocY(560);
		myAnimal.setCurrDir(enums.Direction.SOUTH);
		myAnimal.move();
		assertTrue("Position should remain the same...", (myAnimal.getLocX() == 0 && (myAnimal.getLocY() == 560)));
	}
	
	
	@Test
	public void testGabWallReset(){
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.spawnChunk(50, 50);
		myGabWall.reset();
		assertEquals(myGabWall.getActiveClams(), 0);
		assertEquals(myGabWall.getMaxOysters(), 30);		
	}
	
	@Test
	public void testGabWallBreakDown(){
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setCurrentOysters(100);
		myGabWall.breakDown();
		assertEquals(85, myGabWall.getCurrentOysters());
	}
	
	@Test
	public void testGabSpawnChunk(){
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.spawnChunk(100, 100);
		myGabWall.spawnChunk(10, 100);
		assertEquals(2, myGabWall.getActiveClams());
	}
	
	@Test
	public void testConcReset(){
		models.ConcreteWallModelG1 myConWall = new models.ConcreteWallModelG1();
		myConWall.spawnChunk(50, 50);
		assertEquals(myConWall.getActiveBlocks(), 1);
		myConWall.reset();
		assertEquals(myConWall.getActiveBlocks(), 0);
	}
	
	@Test
	public void testConcAddPiece(){
		models.ConcreteWallModelG1 myConWall = new models.ConcreteWallModelG1();
		myConWall.setMaxBlocks(100);
		myConWall.spawnChunk(50,50);
		Collection<ConcreteChunk> ccc = myConWall.getChunks();
		assertTrue("Should be 1...", ccc.size() == 1);
	}
	
	@Test
	public void testAddPiece() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.spawnChunk(50,50);
		Collection<GabionChunk> gcc = myGabWall.getChunks();
		assertTrue("Should be 1...", gcc.size() == 1);
		
	}


	

	@Test
	public void testSpawn() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		for(int i=0; i<15; i++){
			myGabWall.spawnChunk(10, 15);
		}
		assertTrue("Should be 15...", myGabWall.getChunks().size() == 15);
		
		boolean allHaveCoords = true;
		java.util.Iterator<GabionChunk> it = myGabWall.getChunks().iterator();
		while(it.hasNext()) {
			GabionChunk chunk = it.next();
			if ((chunk.getLocY() < 0) && (chunk.getLocX() < 0)) {
				allHaveCoords = false;
				break;
			}
		}
		assertTrue("Should be true", allHaveCoords);
		
	}
	
	
	
	@Test
	public void testCalculateDamage() {
		//models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		//models.ConcreteWallModelG1 myConcWall = new models.ConcreteWallModelG1();
		//JFrame j = new JFrame();
		Game1Controller process = new Game1Controller();
	
		process.getBarModel().setStatus(100);
		process.getGabionWallModel().setMaxOysters(30);
		process.getGabionWallModel().setCurrentOysters(10);
		process.getWallModel().setMaxBlocks(30);
		process.getWallModel().setCurrentBlocks(10);
		process.takeDamage();	
		
		//System.out.println(process.getBarModel().getStatus());
		assertTrue("Should be 40", process.getBarModel().getStatus() == 40);
		
	}
	
	@Test
	public void testAmountRemoved() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(100);
		myGabWall.breakDown();
		assertTrue("should be 85", myGabWall.getCurrentOysters() == 85);
		myGabWall.setCurrentOysters(85);
		myGabWall.breakDown();
		assertTrue("should be 72", myGabWall.getCurrentOysters() == 72);
		
		//Edge case
		myGabWall.setCurrentOysters(1);
		myGabWall.breakDown();
		//int remove3 = myGabWall.amountRemoved(damage3);
		assertTrue("Should be no less than 0...", myGabWall.getCurrentOysters() >= 0);
	}
	
	// random spawning
	@Test
	public void testConcreteSpawn() {

		ConcreteWallModelG1 Wall = new ConcreteWallModelG1();

		Wall.setMaxBlocks(20);
		Wall.setactiveBlocksOnBoard(0);

		int BeforeSpawn = Wall.getActiveBlocks();

		Wall.spawnChunk(200, 200);
		assertTrue("Should be 1", Wall.getChunks().size() == 1);

		boolean allHaveCoords = true;

		java.util.Iterator<ConcreteChunk> it = Wall.getChunks().iterator();
		ConcreteChunk chunk = it.next();

		while (it.hasNext()) {
			if ((chunk.getLocY() < 0) && (chunk.getLocX() < 0)) {
				allHaveCoords = false;
				break;
			}

		}

		assertTrue("Should be true", allHaveCoords);

	}
	
	/*@Test
	public void testPickUpEvent() {
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		models.AnimalModel myAnimal = new models.AnimalModel(screenSize);
		//Game1Controller process = new Game1Controller(gameFrame);
		
		
		myGabWall.setMaxOysters(100);
		myGabWall.setCurrentOysters(99);
		myGabWall.setactiveClamsOnBoard(1);
		//myAnimal.GabPickUp();
		
		
		
		assertTrue("None should be on beach...", myGabWall.getActiveClams() == 0);
		assertTrue("All should be in wall...", myGabWall.getCurrentOysters() == 100);
	}*/
	@Test
	public void testCollision(){
		//models.AnimalModel myAnimal = new models.AnimalModel(screenSize);
		Game1Controller process = new Game1Controller();
		Rectangle chunk_rect = null;
		Rectangle animal_rect = new Rectangle(process.getAnimalModel().getLocX(), process.getAnimalModel().getLocY(),process.getAnimalModel().getWidth(), process.getAnimalModel().getHeight());
	}
	
	//wave hit
		/*	@Test
			public void testTakeDamage(){
				//Game1Controller process = new Game1Controller(gameFrame);
				//Game1Controller clock = new Game1Controller(gameFrame);
				ConcreteWallModelG1 wall = new ConcreteWallModelG1();
				//GabionWallModelG1 gwall = new GabionWallModelG1();
				wall.setMaxBlocks(30);
				wall.setCurrentBlocks(30);
				BarModel bar =  new BarModel();
				bar.setMaxLevel(100);
				bar.setStatus(100);
				
				process.takeDamage();
				//tests to see if collision occurred
				assertTrue("Should be 3", wall.getCurrentBlocks() == 3);
				//assertTrue("Should be true", gwall.getCurrentOysters() == 0);
				
				//time should stop after round
				//float time1 = clock.getTime();
				//float time2 = clock.getTime();
				//assertTrue("Should be equal", time1 == time2);
				
				//removing from wall
				wall.calculateAmountRemoved();
				assertTrue("Should be 12", wall.amountRemoved(50) == 12);
				wall.removeChunk(wall.amountRemoved(50));
				assertTrue("Should be 13", wall.getCurrentBlocks() == 13);
				
				//tests if bar is updated
				assertTrue("Should be less than 100", bar.getStatus() < 100);
				
				//tests if time is reset
				//assertTrue("Should be 0.0", clock.getTime() == 0.0 );
				
				//time should restart
				//assertTrue("Should be true", clock.getTime() > 0.0);
				
				//tests game over
				bar.setStatus(0);
				assertTrue("Should be true", process.isGameState() == true);
				
			}*/
			
			
			


}