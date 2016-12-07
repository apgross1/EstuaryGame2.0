import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Collection;

import javax.swing.JFrame;

import org.junit.Test;

import models.ConcreteChunk;
import models.ConcreteWallModelG1;
import models.GabionChunk;
import models.GabionWallModelG1;
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
		assertTrue("Y should be 1... ", myAnimal.getLocY()== 57);
		
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
	public void testAnimalSize(){
		models.AnimalModel animal = new models.AnimalModel(screenSize);
		animal.setHeight(24);
		assertTrue("Should be 24", animal.getHeight() == 24);
		animal.setWidth(35);
		assertTrue("Should be 35", animal.getWidth() == 35);
		
	}
	@Test
	public void testSpeed(){
		models.AnimalModel animal = new models.AnimalModel(screenSize);
		animal.setSpeedX(5);
		assertTrue("Should be 5", animal.getSpeedX() == 5);
		animal.setSpeedY(5);
		assertTrue("Should be 5", animal.getSpeedY() == 5);
	}
	@Test
	public void testIsMoving(){
		models.AnimalModel animal = new models.AnimalModel(screenSize);
		animal.setSpeedX(0);
		animal.setSpeedY(0);
		assertFalse("Should be false", animal.isMoving());
		animal.setSpeedX(5);
		assertTrue("Should be true", animal.isMoving());
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
	public void testGabChunkSize(){
		models.GabionChunk myChunk = new models.GabionChunk();
		myChunk.setHeight(5);
		assertTrue("Should be 5", myChunk.getHeight() == 5);
		myChunk.setWidth(5);
		assertTrue("Should be 5", myChunk.getWidth() == 5);
	}
	@Test
	public void testGabLocget(){
		models.GabionChunk myChunk = new models.GabionChunk();
		myChunk.setLocX(15);
		assertTrue("Should be 15", myChunk.getLocX() == 15);
		myChunk.setLocY(16);
		assertTrue("Should be 15", myChunk.getLocY() == 16);
	}
	@Test
	public void testGabActive(){
		models.GabionChunk gabchunk = new models.GabionChunk();
		gabchunk.isActive();
		assertTrue("Should be false", gabchunk.isActive() == false);
		gabchunk.toggleActive();
		assertTrue("Should be true", gabchunk.isActive() == true);
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
	public void testConcChunkSize(){
			models.ConcreteChunk myChunk = new models.ConcreteChunk();
			myChunk.setHeight(5);
			assertTrue("Should be 5", myChunk.getHeight() == 5);
			myChunk.setWidth(5);
			assertTrue("Should be 5", myChunk.getWidth() == 5);
	}
	@Test
	public void testConcLocget(){
		models.ConcreteChunk myChunk = new models.ConcreteChunk();
		myChunk.setLocX(15);
		assertTrue("Should be 15", myChunk.getLocX() == 15);
		myChunk.setLocY(16);
		assertTrue("Should be 15", myChunk.getLocY() == 16);
	}
	@Test
	public void testActive(){
		models.ConcreteChunk gabchunk = new models.ConcreteChunk();
		gabchunk.toggleActive();
		assertTrue("Should be true", gabchunk.isActive() == true);
	}
	
	@Test
	public void testConcAddPiece(){
		ConcreteWallModelG1 cwm = new ConcreteWallModelG1();
		ConcreteChunk c = new ConcreteChunk();
		
		c.setLocX(50);
		c.setLocY(50);
		cwm.addPiece(c);
		
		assertTrue("Should be 1..", cwm.getCurrentBlocks() == 1);
	}
	
	@Test
	public void testGabAddPiece() {
		GabionWallModelG1 gwm = new GabionWallModelG1();
		GabionChunk c = new GabionChunk();
		
		c.setLocX(50);
		c.setLocY(50);
		gwm.addPiece(c);
		
		assertTrue("Should be 1..", gwm.getCurrentOysters() == 1);
		
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
		java.util.Iterator<models.GabionChunk> it = myGabWall.getChunks().iterator();
		while(it.hasNext()) {
			models.GabionChunk chunk = it.next();
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
		assertTrue("Should be 40", process.getBarModel().getStatus() == 60);
		
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

		//int BeforeSpawn = Wall.getActiveBlocks();

		Wall.spawnChunk(200, 200);
		assertTrue("Should be 1", Wall.getChunks().size() == 1);

		boolean allHaveCoords = true;

		java.util.Iterator<models.ConcreteChunk> it = Wall.getChunks().iterator();
		models.ConcreteChunk chunk = it.next();

		while (it.hasNext()) {
			if ((chunk.getLocY() < 0) && (chunk.getLocX() < 0)) {
				allHaveCoords = false;
				break;
			}

		}

		assertTrue("Should be true", allHaveCoords);

	}
	
	@Test
	public void testgetActiveBlocks(){
		models.ConcreteWallModelG1 myConcWall = new models.ConcreteWallModelG1();
		assertTrue("Should be 0", myConcWall.getActiveBlocks() == 0);
		myConcWall.setactiveBlocksOnBoard(1);
		assertTrue("Should be 1", myConcWall.getActiveBlocks() == 1);
	}
	@Test
	public void testsetMaxBlocks(){
		models.ConcreteWallModelG1 myConcWall = new models.ConcreteWallModelG1();
		assertTrue("Should be 30", myConcWall.getMaxBlocks() == 30);
		myConcWall.setMaxBlocks(31);
		assertTrue("Should be 31", myConcWall.getMaxBlocks() == 31);		
	}
	@Test
	public void testsetCurrentBlocks(){
		models.ConcreteWallModelG1 myConcWall = new models.ConcreteWallModelG1();
		assertTrue("Should be 0", myConcWall.getCurrentBlocks() == 0);
		myConcWall.setCurrentBlocks(5);
		assertTrue("Should be 5", myConcWall.getCurrentBlocks() == 5);
	}
	
	@Test
	public void testgetActiveOysters(){
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		assertTrue("Should be 0", myGabWall.getActiveClams() == 0);
		myGabWall.setactiveClamsOnBoard(1);
		assertTrue("Should be 1", myGabWall.getActiveClams() == 1);
	}
	@Test
	public void testsetMaxOysters(){
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		assertTrue("Should be 30", myGabWall.getMaxOysters() == 30);
		myGabWall.setMaxOysters(31);
		assertTrue("Should be 31", myGabWall.getMaxOysters() == 31);		
	}
	@Test
	public void testsetCurrentOysters(){
		models.GabionWallModelG1 myGabWall = new models.GabionWallModelG1();
		assertTrue("Should be 0", myGabWall.getCurrentOysters() == 0);
		myGabWall.setCurrentOysters(5);
		assertTrue("Should be 5", myGabWall.getCurrentOysters() == 5);
	}
	
	@Test
	public void testBarDecrease(){
		models.BarModel bar = new models.BarModel();
		bar.decrease(50);
		assertTrue("Should be 50", bar.getStatus() == 50);
		bar.decrease(51);
		assertTrue("Should be 0", bar.getStatus() == 0);
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
		models.AnimalModel myAnimal = new models.AnimalModel(screenSize);
		GabionWallModelG1 gwm = new GabionWallModelG1();
		Game1Controller process = new Game1Controller();
		
		myAnimal.setLocX(100);
		myAnimal.setLocY(100);
		gwm.spawnChunk(100, 100);
		
		assertTrue("Should be true", process.collisionOccured(myAnimal, (gwm.getChunks().iterator()).next()));
	}
	
	//wave hit
			/*@Test
			public void testTakeDamage(){
				controller.Game1Controller process = new controller.Game1Controller();
				//Game1Controller clock = new Game1Controller(gameFrame);
				models.ConcreteWallModelG1 wall = new models.ConcreteWallModelG1();
				models.GabionWallModelG1 gwall = new models.GabionWallModelG1();
				wall.setMaxBlocks(30);
				wall.setCurrentBlocks(30);
				models.BarModel bar =  new models.BarModel();
				bar.setMaxLevel(100);
				bar.setStatus(100);
				process.round();
				
				
				//tests to see if collision occurred
				assertTrue("Should be 3", wall.getCurrentBlocks() == 3);
				//assertTrue("Should be true", gwall.getCurrentOysters() == 0);
				
				//time should stop after round
				//float time1 = clock.getTime();
				//float time2 = clock.getTime();
				//assertTrue("Should be equal", time1 == time2);
				
				//removing from wall
				wall.breakDown();
				assertTrue("Should be 12", wall.getCurrentBlocks() == 12);
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