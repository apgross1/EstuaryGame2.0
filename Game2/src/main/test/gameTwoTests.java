import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.junit.Test;
import controller.Game2Controller;

import models.AlgaeModel;
import models.AnimalModelG2;
import models.BarModelG2;
import view.Game2View;





public class gameTwoTests {
	//Animal Tests for game2
	@Test
	public void testAnimalMove(){
		AnimalModelG2 a = new AnimalModelG2();
		int y = a.getLocY();
		a.move();
		assertEquals(y+a.getVelocity(), a.getLocY());
	}
	
	@Test
	public void testSetLocY(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setLocY(50);
		
		assertEquals(50, a.getLocY());
	}
	
	@Test
	public void testGetLocY(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setLocY(100);
		
		assertEquals(100, a.getLocY());
	}
	@Test
	public void testSetHealth(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setHealth(-10);
		assertEquals(-10, a.getHealth());
	}
	@Test
	public void testGetHealth(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setHealth(0);
		assertEquals(0, a.getHealth());
	}
	
	
	@Test
	public void testSetHeight(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setHeight(-7);
		assertEquals(-7, a.getHeight());
	}
	@Test
	public void testGetHeight(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setHeight(1000);
		assertEquals(1000,a.getHeight());
	}
	
	@Test
	public void testSetWidth(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setWidth(500);
		assertEquals(500, a.getWidth());
	}
	@Test
	public void testGetWidth(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setWidth(19876);
		assertEquals(19876, a.getWidth());
	}
	@Test
	public void testSetVelocity(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setVelocity(-10);
		assertEquals(-10, a.getVelocity());
	}
	@Test
	public void testGetVelocity(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setVelocity(75);
		assertEquals(75, a.getVelocity());
	}
	@Test
	public void testIncreaseVelocity(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setVelocity(10);
		a.increaseVelocity();
		assertEquals(11, a.getVelocity());
	}
	@Test
	public void testDecreaseVelocity(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setVelocity(0);
		a.decreaseVelocity();
		assertEquals(-1, a.getVelocity());
	}
	
	@Test
	public void testSetIsDead(){
		AnimalModelG2 a = new AnimalModelG2();
		a.setIsDead(true);
		
		assertEquals(true, a.getIsDead());
	}
	@Test
	public void testGetIsDead(){
		AnimalModelG2 a = new AnimalModelG2();
		
		assertEquals(false, a.getIsDead());
	}
	
	
	
	
	
	//Algae Tests
	@Test
	public void testSetAlgaeHeight(){
		AlgaeModel a = new AlgaeModel();
		a.setHeight(100);
		assertEquals(100, a.getHeight());
	}
	
	@Test
	public void testGetAlgaeHeight(){
		AlgaeModel a = new AlgaeModel();
		a.setHeight(0);
		assertEquals(0, a.getHeight());
	}
	
	@Test
	public void testSetAlgaeWidth(){
		AlgaeModel a = new AlgaeModel();
		a.setWidth(150);
		assertEquals(150, a.getWidth());
	}
	
	@Test
	public void testGetAlgaeWidth(){
		AlgaeModel a = new AlgaeModel();
		a.setWidth(5);
		assertEquals(5, a.getWidth());
	}
	
	@Test
	public void testSetRiverAlgaeY(){
		AlgaeModel a = new AlgaeModel();
		a.setRiverAlgaeY(100);
		assertEquals(100, a.getRiverAlgaeY());
	}
	@Test
	public void testGetRiverAlgaeY(){
		AlgaeModel a = new AlgaeModel();
		a.setRiverAlgaeY(-590);
		assertEquals(-590, a.getRiverAlgaeY());
	}
	
	@Test
	public void testSetRiverAlgaeX(){
		AlgaeModel a = new AlgaeModel();
		a.setRiverAlgaeX(0);
		assertEquals(0, a.getRiverAlgaeX());
	}
	@Test
	public void testGetRiverAlgaeX(){
		AlgaeModel a = new AlgaeModel();
		a.setRiverAlgaeX(-2340);
		assertEquals(-2340, a.getRiverAlgaeX());
	}
	@Test
	public void testSetMaxAlage(){
		AlgaeModel a = new AlgaeModel();
		a.setMaxAlgae(1000);
		assertEquals(1000, a.getMaxAlgae());
	}
	
	@Test
	public void testGetMaxAlgae(){
		AlgaeModel a = new AlgaeModel();
		a.setMaxAlgae(-23);
		assertEquals(-23, a.getMaxAlgae());
	}
	
	@Test
	public void testSetAlgLocY(){
		AlgaeModel a = new AlgaeModel();
		a.setLocY(-60);
		assertEquals(-60, a.getLocY());
	}
	
	@Test
	public void testGetAlgLocY(){
		AlgaeModel a = new AlgaeModel();
		a.setLocY(50);
		assertEquals(50, a.getLocY());
	}
	
	@Test
	public void testSetAlgLocX(){
		AlgaeModel a = new AlgaeModel();
		a.setLocX(-100);
		assertEquals(-100, a.getLocX());
	}
	
	@Test
	public void testGetAlgLocX(){
		AlgaeModel a = new AlgaeModel();
		a.setLocX(0);
		assertEquals(0, a.getLocX());
	}
	
	@Test
	public void testAlgSetVelocity(){
		AlgaeModel a = new AlgaeModel();
		a.setVelocity(-10);
		assertEquals(-10, a.getVelocity());
	}
	@Test
	public void testAlgGetVelocity(){
		AlgaeModel a = new AlgaeModel();
		a.setVelocity(75);
		assertEquals(75, a.getVelocity());
	}
	
	
	@Test
	public void testIsActive(){
		AlgaeModel a = new AlgaeModel();
		a.spawnAlgaeModel();
		assertEquals(true, a.isActive());
	}
	@Test
	public void testSetActive(){
		AlgaeModel a = new AlgaeModel();
		a.spawnAlgaeModel();
		a.setActive(false);
		assertEquals(false, a.isActive());
	}
	
	@Test
	public void testGetYMax(){
		AlgaeModel a = new AlgaeModel();
		a.setYMax(100);
		assertEquals(100, a.getYMax());
	}
	@Test
	public void testSetYMax(){
		AlgaeModel a = new AlgaeModel();
		a.setYMax(-1);
		assertEquals(-1, a.getYMax());
	}
	
	@Test
	public void testGetXMax(){
		AlgaeModel a = new AlgaeModel();
		a.setXMax(56);
		assertEquals(56, a.getXMax());
	}
	@Test
	public void testSetXMax(){
		AlgaeModel a = new AlgaeModel();
		a.setXMax(-50);
		assertEquals(-50, a.getXMax());
	}
	
	@Test
	public void testGetYMin(){
		AlgaeModel a = new AlgaeModel();
		a.setYMin(-10);
		assertEquals(-10, a.getYMin());
	}
	@Test
	public void testSetYMin(){
		AlgaeModel a = new AlgaeModel();
		a.setYMin(170);
		assertEquals(170, a.getYMin());
	}
	
	
	
	@Test
	public void testGetRandomYLocation(){
		AlgaeModel a = new AlgaeModel();
		int randY = a.getRandomYLocation();
		assertEquals(true, (randY <a.getYMax()&& randY>a.getYMin()));
		
	}
	
	@Test
	public void testAlgaeMoveRiverAlgae(){
		AlgaeModel a = new AlgaeModel();
		
		int x = a.getRiverAlgaeX();
		a.moveRiverAlgae();
		assertEquals(x+a.getVelocity(), a.getRiverAlgaeX());
	}
	
	
	
	
	@Test
	public void testAlgaeMove(){
		AlgaeModel a = new AlgaeModel();
		int x = a.getLocX();
		a.move();
		assertEquals(x-a.getVelocity(), a.getLocX());
	}
	@Test
	public void testAlgaeEaten(){
		AlgaeModel a = new AlgaeModel();
		a.spawnAlgaeModel();
		assertEquals(true, a.isActive());
		a.eaten();
		assertEquals(false, a.isActive());
		
	}
	@Test
	public void testAlgaeSpawn(){
		AlgaeModel a = new AlgaeModel();
		a.spawnAlgaeModel();
		assertEquals(true, a.getLocY()<a.getYMax()&& a.getLocY()>a.getYMin());
		
		
	}
	
	
	
	//Bar Model Tests
	
	@Test
	public void testBarConstructor(){
		BarModelG2 b = new BarModelG2(100);
		
		assertEquals(100, b.getMaxLevel());
		
		
		
	}
	@Test
	public void testUpdatePercentage(){
		BarModelG2 b = new BarModelG2();
		int damage = b.getDamagePercent()+b.getDamagePercent();
		b.updateDamagePercent();
		assertEquals(damage, b.getDamagePercent());
		
		
		
	}
	@Test
	public void testGetMaxLevel(){
		BarModelG2 b = new BarModelG2();
		b.setMaxLevel(100);
		assertEquals(100, b.getMaxLevel());
		
		
		
	}
	
	@Test
	public void testGetStatus(){
		BarModelG2 b = new BarModelG2();
		b.setStatus(-1);
		assertEquals(-1, b.getStatus());
		
		
		
	}
	
	
	@Test
	public void testisEmpty(){
		BarModelG2 b = new BarModelG2();
		b.setWidth(0);
		assertEquals(true, b.isEmpty());
		b.setWidth(10);
		assertEquals(false, b.isEmpty());
		
		
	}
	
	@Test
	public void testGetDamage(){
		BarModelG2 b = new BarModelG2();
		b.setDamage(10);
		assertEquals(10, b.getDamage());
		
		
		
	}
	//Controller Tests

	@Test
	public void testActivateStorm(){
		JFrame frame = new JFrame();
		Game2Controller c = new Game2Controller(frame);
		c.activateStorm();
		assertEquals(true, c.getStormStatus());
		
	}
	@Test
	public void testDeactivateStorm(){
		JFrame frame = new JFrame();
		Game2Controller c = new Game2Controller(frame);
		c.deactivateStorm();
		assertEquals(false, c.getStormStatus());
		
	}
	@Test
	public void testaddNumMissed(){
		JFrame frame = new JFrame();
		Game2Controller c = new Game2Controller(frame);
		int num = c.getNumMissed();
		c.addNumMissed();
		assertEquals(c.getNumMissed(), num+1);
		
	}
	@Test
	public void testspawnAlgae(){
		JFrame frame = new JFrame();
		Game2Controller c = new Game2Controller(frame);
		int num = c.getAlgaeNum();
		c.spawnAlgae();
		assertEquals(num+1, c.getAlgaeNum());
		
	}
	@Test
	public void testcollisionOccured(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		AnimalModelG2 animal = new AnimalModelG2();
		AlgaeModel algae = new AlgaeModel();
		JFrame frame = new JFrame();
		Game2Controller c = new Game2Controller(frame);
		
		animal.setLocX(10);
		animal.setLocY(10);
		algae.setLocX(10);
		algae.setLocY(10);
		
		assertEquals(true, c.collisionOccured(animal, algae));
		
	}
	
	@Test
	public void testshallowWaterCollision(){
		
		AlgaeModel algae = new AlgaeModel();
		JFrame frame = new JFrame();
		Game2Controller c = new Game2Controller(frame);
		algae.setLocX(0);
		
		
		assertEquals(true, c.shallowWaterCollision(algae));
		algae.setLocX(100);
		assertEquals(false, c.shallowWaterCollision(algae));
		
	}
	
	
	
}