import static org.junit.Assert.*;

import org.junit.Test;


//Functions to test:
/*
 * Algae Eater Model:
 *	void rotateFish() - This is not needed as the circle is stationary, the rotation is simply animation. Need to update UML.
 *	void healthUp()
 *	void healthDown()
 *	void incrEatSpeed() - This needed? Can't just be read from crab?
 *	void perish() - This should now be in view as this is strictly animation?
 *
 *Animal Model:
 *	void healthUP()
 *	void healthDown()
 *	void pickUp()
 * shouldnt this be speed down? no heath
 *
 *Bar Model:
 *	void increase()
 *	void decrease()
 *	bool isEmpty()
 * are we even having a bar in this game?
 *
 *Water Model:
 *	void incConcentration()
 *	void decOxyg()
 *	
 */
public class gameTwoTests {
	
	//First model
	@Test
	public void testHealthUp() {
		animal a = new animal(); //Default crab.
		algeaEater eater = new algeaEater(a.xloc, a.yloc);
		//Pretending initial health is.... 90?
		eater.healthUp(); // Increase 10.
		assertEquals(eater.health, 100);
	}
	@Test
	public void testHealthDown(){
		animal a = new animal();
		algaeEater eater = new algaeEater(a.xloc, a.yloc);
		//initial health is 100
		eater.healthDown(); //decrease 10
		assertEquals(eater.health, 100);
	}
	
	@Test
	public void testincConcentration(){
		//if algae gets through, incConcentration
		algae al = new algae();
		al.setLocX(//where water is)
				)
		al.setLocY(//where water is
				)
		water.incConcentration();
		assertEquals(water.algaeConcentration, 25);
		
	}
	@Test
	public void testdecOxyg(){
		//if algae gets through, decrease Oxygen
	}
	@Test
	public void testDecreaseBar(){
		//if algae gets through, decrease bar?
	}
	@Test
	public void 
	
	
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
