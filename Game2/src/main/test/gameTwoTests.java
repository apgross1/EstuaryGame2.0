import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import org.junit.Test;

import controller.Game2Controller;
import models.AlgaeEaterModel;
import models.AlgaeModel;
import models.AnimalModelG2;
import models.BarModelG2;
import models.WaterModelG2;


//Functions to test:
/*
 * AlgaeModel Eater Model:
 *	void healthUp()
 *	void healthDown()
 *	
 *
 *AnimalModelG2 Model:
 *	void speedUP()
 *	void speedDown()
 * 
 *
 *BarModelG2 Model:
 *	void increase()
 *	void decrease()
 *	bool isEmpty()
 * 
 *
 *WaterModelG2 Model:
 *	void incConcentration()
 *	void decOxyg()
 *	
 *AlgaeModel Model:
 *	void eaten();
 */
public class gameTwoTests {
	
	//First model
	@Test
	public void testAEHealthUp() {
		AnimalModelG2 a = new AnimalModelG2(); //Default crab.
		AlgaeEaterModel eater = new AlgaeEaterModel(a.getLocX(), a.getLocY());
		eater.setHealth(90);
		eater.healthUp(); // Increase 10.
		assertEquals(eater.getHealth(), 100);
	}
	@Test
	public void testAEHealthDown(){
		AnimalModelG2 a = new AnimalModelG2();
		AlgaeEaterModel eater = new AlgaeEaterModel(a.getLocX(), a.getLocY());
		eater.setHealth(100);
		eater.healthDown(); //decrease 10
		assertEquals(eater.getHealth(), 90);
	}
	
	@Test
	public void testincConcentration(){
		//if AlgaeModel gets through, incConcentration
		WaterModelG2 w = new WaterModelG2();
		w.setAlgConcentration(0);
		w.incrAlgConcentration();
		assertEquals(w.getAlgConcentration(), 25);
		
	}
	
	@Test
	public void testdecOxyg(){
		//if AlgaeModel gets through, decrease Oxygen
		WaterModelG2 w = new WaterModelG2();
		w.setHealth(100);
		w.decOxygen();
		assertEquals(w.getOxLevel(), 75); //starts at 100, decrements by 25?
		
	}
	
	@Test
	public void testDecreaseBarModelG2(){
		//if AlgaeModel gets through decreaseBarModelG2 will be called
		BarModelG2 b = new BarModelG2(50);// 50 is initial health
		b.decrease();//Assume decreases by one.(status--)
		assertEquals(b.getStatus(), 49);
	}
	
	@Test
	public void testInrecaseBarModelG2(){
		//if AlgaeModel gets through increaseBarModelG2 will be called
		BarModelG2 b = new BarModelG2(49);// 49 is initial health
		b.increase();//Assume increase by one.(status++)
		assertEquals(b.getStatus(), 50);
	}
	
	@Test
	public void testisEmpty(){
		BarModelG2 b = new BarModelG2(0);
		assertEquals(b.isEmpty(), 0);
	}
	
	@Test
	public void testisEmpty2(){
		BarModelG2 b = new BarModelG2(100);
		assertEquals(b.isEmpty(), 1);
	}
	
	
	@Test
	public void testdecreaseSpeed(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setSpeed(100);
		a.decreaseSpeed();
		assertEquals(a.getSpeed(), 75);
	}
	
	@Test
	public void testIncreaseSpeed(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setSpeed(75);
		a.increaseSpeed();
		assertEquals(a.getSpeed(), 100);
	}
	
	@Test
	public void testeaten(){
		AlgaeModel a = new AlgaeModel();
		a.eaten();
		assertEquals(a.isActive(), 0);
	}
	
	@Test
	public void testeaten2(){
		AlgaeModel a = new AlgaeModel();
		assertEquals(a.isActive(), 1);
	}
	
	@Test
	public void testAnimalModelG2Movement() {
		AnimalModelG2 myAnimalModelG2 = new AnimalModelG2();
		myAnimalModelG2.setLocX(0);;
		myAnimalModelG2.setLocY(0);
		
		//Basic cardinal directions with no edge cases
		//South from origin
		myAnimalModelG2.setCurrDir(enums.Direction.SOUTH);
		myAnimalModelG2.move();
		assertTrue("Y should be 1... ", myAnimalModelG2.getLocY()==1);
		
		//North from 1 down from origin (to avoid edge case)
		myAnimalModelG2.setCurrDir(enums.Direction.NORTH);
		myAnimalModelG2.move();
		assertTrue("Y should be 0...", myAnimalModelG2.getLocY() == 0);
		
	}

/*
 * 
 * BEGIN TEST FOR CONTROLLER
 * 
 */
/*
 * Update in UML:
 * 	- Change touchEater and touchAlgaeModel to one void detectCollision(obj a, obj b)
 * 	- Move create storm in controller to view.
 * 	- Remove pickedUp() as nothing is ever being picked up in game 2
 * 	- Remove duplicate createStorm()(both of them) and remove activateStorm().
 * 	- Remove all the getters for quarter, half, full etc.
 * 	- Remove takeDamadge because it will be handeled in the collision detection.
 * 	- Remove isDead because its just a getter and not needed in the controller.
 * 	- Remove BarModelG2Full and BarModelG2Empty as they are just getters..
 * 	- Remove decrease speed as that is taken care of by the collision detection ie the WaterModelG2s health goes down as does speed.
 * 	- Update start / end game to listener to one gameStateListener
 * 	- Remove addobj and removobj functions
 * 
 * 
 * - Isnt it redundant to have a addObject(obj j) function because arraylist has its own library?
 * 		- We left it in the UML and tests below but I dont think its necessary
 */
	
	//Case when AlgaeModel hits AlgaeModel eater
	@Test
	public void testdetectCollisionSuccess(){
		//Is this function called once a collision is already detected.... or 
		//does it listen for action event. and then update something in the model.
		//Either way, this one function consolidates the two functions in the UML
		//touchEater() and touchAlgaeModel()
		AlgaeEaterModel eater = new AlgaeEaterModel(50, 30);
		AlgaeModel alg = new AlgaeModel(50, 30);
		
		
		Game2Controller.detectCollision(eater, alg); //Listener should see they are touching
		//Should update alg.isActive() in model
		assertEquals(alg.isActive(), 0); //Now dead.
	}
	
	//Case when AlgaeModel is not touching either the eater or the sanctuary
	@Test
	public void testdetectCollisionFail(){
		AlgaeEaterModel eater = new AlgaeEaterModel(20, 70);
		AlgaeModel alg = new AlgaeModel(50, 30);
		
		
		Game2Controller.detectCollision(eater, alg); //Listener should see they are touching
		//Should not update alg.isActive() in model
		assertEquals(alg.isActive(), 1); //Alg should still be alive.
	}
	
	
	//This will 1) kill the AlgaeModel, 2) it will decrease the health of the WaterModelG2 (which is also the speed attribute).
	@Test
	public void testdetectCollisionWithSanctuary(){
		AlgaeModel alg = new AlgaeModel(1, 1);
		WaterModelG2 w = new WaterModelG2();
		
		
		Game2Controller.detectCollision(w, alg); // Should kill the AlgaeModel, and decrease the sanctuary (WaterModelG2) health.
		assertEquals(alg.isActive(), 0);
		assertEquals(w.getHealth(), 90); // Assuming that perfect health is 100 and that a collision deducts 10
	}
	
	@Test
	public void testincLvl(){
		//Action event when AlgaeModel collides?
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

	/*@Test
	public void testGameStateListener(){
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endGameListener();
			}
		};
	    
		Timer time = new Timer(2000, actionListener);
		
		// Pretend this is our games timer
		
		 //In a constant loop?
		
		
		//1) Ends timer / stops loops
		//2) Triggers end animation?
	}*/
}
