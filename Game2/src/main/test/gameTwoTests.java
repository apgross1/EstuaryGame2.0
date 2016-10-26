import static org.junit.Assert.*;

import org.junit.Test;


//Functions to test:
/*
 * Algae Eater Model:
 *	void healthUp()
 *	void healthDown()
 *	
 *
 *Animal Model:
 *	void speedUP()
 *	void speedDown()
 * 
 *
 *Bar Model:
 *	void increase()
 *	void decrease()
 *	bool isEmpty()
 * 
 *
 *Water Model:
 *	void incConcentration()
 *	void decOxyg()
 *	
 *Algae Model:
 *	void eaten();
 */
public class gameTwoTests {
	
	//First model
	@Test
	public void testAEHealthUp() {
		animal a = new animal(); //Default crab.
		algeaEater eater = new algeaEater(a.xloc, a.yloc);
		eater.health = 90;
		eater.healthUp(); // Increase 10.
		assertEquals(eater.health, 100);
	}
	@Test
	public void testAEHealthDown(){
		animal a = new animal();
		algaeEater eater = new algaeEater(a.xloc, a.yloc);
		eater.health = 100;
		eater.healthDown(); //decrease 10
		assertEquals(eater.health, 90);
	}
	
	@Test
	public void testincConcentration(){
		//if algae gets through, incConcentration
		algae al = new algae();
		water w = new water();
		al.setLocX(10);
		al.setLocY(10);
		water.incConcentration();
		assertEquals(w.algaeConcentration, 25);
		
	}
	
	@Test
	public void testdecOxyg(){
		//if algae gets through, decrease Oxygen
		algea al = new algae();
		water w = new water();
		al.location_x = 10; //Should be passed the barior of causing a problem.
		al.locayion_y = 10;
		if(al.location_x < 50){
			w.decOxyg();
		}
		assertEqulas(w.health, 75); //starts at 100, decrements by 25?
		
	}
	
	@Test
	public void testDecreaseBar(){
		//if algae gets through decreaseBar will be called
		bar b = new bar(50);// 50 is initial health
		b.decrease();//Assume decreases by one.(status--)
		assertEquals(b.status, 49);
	}
	
	@Test
	public void testInrecaseBar(){
		//if algae gets through increaseBar will be called
		bar b = new bar(49);// 49 is initial health
		b.increase();//Assume increase by one.(status++)
		assertEquals(b.status, 50);
	}
	
	@Test
	public void testBarFull(){
		bar b = new bar(0);
		assertEquals();
		assertEquals(b.status, 0);
	}
	
	@Test
	public void testdecreaseSpeed(){
		algae al = new algae();
		water w = new water();
		Animal a = new Animal();
		al.setLocX(10);
		al.setLocY(10);
		a.speed = 100;
		if(al.algaeLocX < 30){
			a.decreaseSpeed();
		}
		assertEquals(a.speed, 75);
	}
	
	@Test
	public void testincreaseSpeed(){
		Animal a = new Animal();
		a.speed = 100;
		if(timer > 60 && w.health <= 50){
			a.increaseSpeed();
		}
		assertEquals(a.speed, 75);
	}
	
	
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

