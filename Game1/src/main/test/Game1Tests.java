import static org.junit.Assert.*;

import org.junit.Test;

import model.AnimalModel;

public class Game1Tests {
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
		
	//AnimalModel
	//Low-level testing
	@Test
	public void testAnimalMovement() {
		AnimalModel myAnimal = new AnimalModel();
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
	}
	
		

}