import static org.junit.Assert.*;

import org.junit.Test;


//Functions to test:
/*
 * Algea Eater Model:
 *	void rotateFish() - This is not needed as the circle is stationary, the rotation is simply animation. Need to update UML.
 *	void healthUp()
 *	void healthDown()
 *	void incrEatSpeed() - This needed? Cant just be read from crab?
 *	void perish() - This should now be in view as this is strictly animation?
 *
 *Animal Model:
 *	void healthUP()
 *	void healthDown()
 *	void pickUp()
 *
 *Bar Model:
 *	void increase()
 *	void decrease()
 *	bool isEmpty()
 *
 *Water Model:
 *	void incConcentration()
 *	
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

}
