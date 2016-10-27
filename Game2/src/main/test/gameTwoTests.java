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

/*
 * 
 * BEGIN TEST FOR CONTROLLER
 * 
 */
/*
 * Update in UML:
 * 	- Change touchEater and touchAlgae to one void collisionDetected(obj a, obj b)
 * 	- Move create storm in controller to view.
 * 	- Remove pickedUp() as nothing is ever being picked up in game 2
 * 	- Remove duplicate createStorm()
 * 	- If we decide to combine the gone() functions, remove them and make a int pctGone() function.
 */
	@Test
	public void testcollisionDetected(){
		//Is this function called once a collision is already detected.... or 
		//does it listen for action event. and then update something in the model.
		//Either way, this one function consolidates the two functions in the UML
		//touchEater() and touchAlgae()
		
	}
	
	@Test
	public void testcreateStorm(){
		//This too is in the controller but I think belongs in view because its an animation?
		//or does this actually call activate storm which triggers runoff(an animation that also affects controller for algea quantity)?
		//can the two functions be consolidated (createStorm(), activateStorm())
	}
	
	@Test
	public void testtakeDamage(){
		//Action or listener?
	}
	
	@Test
	public void testisDead(){
		//Why is this in the controller? / what purpose does this serve.
	}
	
	@Test
	public void testactivateStorm(){
		//Can this be consolidated with createStorm?
	}
	
	@Test
	public void testincLvl(){
		//Action event when algea collides?
		//not sure what this function does.
	}
	
	@Test
	public void testdecLvl(){
		//Same as above, don't understand its purpose
	}
	
	@Test
	public void pctGone(){
		//I think we should make a function that returns an integer that tells us what percent is gone 
		//rather than having 4 functions (quartergone, halfgone, threequartergone etc.)
		
		//Either way, aren't these just getters?
	}
	
	@Test
	public void testbarFull(){
		//Not sure of the purpose of this as its just a getter of sorts as it just returns a bool.
		
	}
	
	@Test
	public void testbarEmpty(){
		//Same as barFull()
		
	}
	
	@Test
	public void testdecSpeed(){
		//Is this called when a collision is detected from one of the listeners above?
		//If so... why do we even have this, we can have the listener talk to the model directly.
	}
	
	@Test
	public void testRenderGraphics(){
		//This is likely not testable as this communicates the picture to the view.
	}
	
	@Test
	public void testAddObj(){
		//Is this when an object is added to the arraylist of objects?
	}
	
	@Test
	public void testRemObj(){
		//If yes to above, I assume this is when we remove individual algeas when collisions occur.
	}
	
	@Test
	public void testEndGame(){
		//1) Ends timer / stops loops
		//2) Triggers end animation?
	}
	
	@Test
	public void testStartGame(){
		//1)Intro animation call in the view?
		//2) Start timer / loops 
	}
}

