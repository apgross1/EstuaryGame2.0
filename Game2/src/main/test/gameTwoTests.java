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
		water w = new water();
		w.algaeConcentration = 0;
		w.incConcentration();
		assertEquals(w.algaeConcentration, 25);
		
	}
	
	@Test
	public void testdecOxyg(){
		//if algae gets through, decrease Oxygen
		water w = new water();
		w.health = 100;
		w.decOxyg();
		assertEqulas(w.oxygen, 75); //starts at 100, decrements by 25?
		
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
	public void testisEmpty(){
		bar b = new bar(0);
		assertEquals(b.isEmpty(), 0);
	}
	
	@Test
	public void testisEmpty2(){
		bar b = new bar(100);
		assertEquals(b.isEmpty(), 1);
	}
	
	
	@Test
	public void testdecreaseSpeed(){
		Animal a = new Animal();
		a.speed = 100;
		a.decreaseSpeed();
		assertEquals(a.speed, 75);
	}
	
	@Test
	public void testincreaseSpeed(){
		Animal a = new Animal();
		a.speed = 75;
		a.increaseSpeed();
		assertEquals(a.speed, 100);
	}
	
	@Test
	public void testeaten(){
		algae a = new algae();
		a.eaten();
		assertEquals(a.active, 0);
	}
	
	@Test
	public void testeaten2(){
		algae a = new algae();
		assertEquals(a.active, 1);
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
		
	}
}

