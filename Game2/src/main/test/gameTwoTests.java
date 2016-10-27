import static org.junit.Assert.*;

import java.util.ArrayList;

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
		algaeEater eater = new algaeEater(a.xloc, a.yloc);
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
 * 	- Change touchEater and touchAlgae to one void detectCollision(obj a, obj b)
 * 	- Move create storm in controller to view.
 * 	- Remove pickedUp() as nothing is ever being picked up in game 2
 * 	- Remove duplicate createStorm()(both of them) and remove activateStorm().
 * 	- Remove all the getters for quarter, half, full etc.
 * 	- Remove takeDamadge because it will be handeled in the collision detection.
 * 	- Remove isDead because its just a getter and not needed in the controller.
 * 	- Remove barFull and barEmpty as they are just getters..
 * 	- Remove decrease speed as that is taken care of by the collision detection ie the waters health goes down as does speed.
 * 	- Update start / end game to listener to one gameStateListener
 * 
 * 
 * - Isnt it redundant to have a addObject(obj j) function because arraylist has its own library?
 * 		- We left it in the UML and tests below but I dont think its necessary
 */
	
	//Case when algae hits algae eater
	@Test
	public void testdetectCollisionSuccess(){
		//Is this function called once a collision is already detected.... or 
		//does it listen for action event. and then update something in the model.
		//Either way, this one function consolidates the two functions in the UML
		//touchEater() and touchAlgae()
		algaeeater eater = new eater(50, 30);
		algae alg = new algae(50, 30);
		
		
		detectCollision(eater, alg); //Listener should see they are touching
		//Should update alg.active in model
		assertEquals(alg.active, 0); //Now dead.
	}
	
	//Case when algae is not touching either the eater or the sanctuary
	@Test
	public void testdetectCollisionFail(){
		algaeeater eater = new eater(20, 70);
		algae alg = new algae(50, 30);
		
		
		detectCollision(eater, alg); //Listener should see they are touching
		//Should not update alg.active in model
		assertEquals(alg.active, 1); //Alg should still be alive.
	}
	
	
	//This will 1) kill the algae, 2) it will decrease the health of the water (which is also the speed attribute).
	@Test
	public void testdetectCollisionWithSanctuary(){
		algae alg = new algae(1, 1);
		water w = new water();
		
		detectCollison(w, algae); // Should kill the algae, and decrease the sanctuary (water) health.
		assertEquals(alg.active, 0);
		assertEquals(w.health, 90); // Assuming that perfect health is 100 and that a collision deducts 10
	}
	
	@Test
	public void testincLvl(){
		//Action event when algae collides?
		//not sure what this function does.
	}
	
	@Test
	public void testdecLvl(){
		//Same as above, don't understand its purpose
	}
	
	@Test
	public void testRenderGraphics(){
		//This is likely not testable as this communicates the picture to the view.
	}
	
	@Test
	public void testAddObj(){
		//Is this when an object is added to the arraylist of objects?
		ArrayList<Object> objArr = new ArrayList<Object>();
		algae a = new algae(10, 10);
		water w = new water();
		
		objArr.add(w);
		
		//addObject(a);
	}
	
	@Test
	public void testRemObj(){
		//Not even going to write this test, see above function and comments at the top.
	}
	
	@Test
	public void testGameStateListener(){
		Timer t = new Timer(0); // Pretend this is our games timer
		
		endGameListener(); //In a constant loop?
		
		
		//1) Ends timer / stops loops
		//2) Triggers end animation?
	}
}

