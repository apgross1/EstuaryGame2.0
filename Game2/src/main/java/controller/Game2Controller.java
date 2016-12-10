package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;

import models.AlgaeModel;
import models.AnimalModelG2;

import view.Game2View;
import view.Game2View.Animation;


/**
 * @author Team 8
 *
 */
public class Game2Controller implements Serializable {
	private boolean gameActive;
	private transient Game2View view;
	private AnimalModelG2 animal;

	private AlgaeModel algae;
	private Collection<AlgaeModel> algaeList = new ArrayList<AlgaeModel>();

	private JFrame gameFrame;
	private long spawnTime = 0;
	private int numMissed = 0;
	private long startTime;
	private int updates = 0;
	private int frames = 0;

	private int spawnDelay = 2000; // in milliseconds
	private boolean isStorming = false;
	private boolean tutorialActive;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) screenSize.getWidth();
	private int height = (int) screenSize.getHeight();

	public Game2Controller(JFrame gamef) {
		gameFrame = gamef;
		animal = new AnimalModelG2();

		algae = new AlgaeModel();
		view = null;

	}

	
	/**
	 * Initializes game and contains the main game loop
	 * Limits to 60fps by updating the game every 60 times per second
	 * If tutorialActive is true, the tutorial initializes
	 * else the main game loop starts
	 */
	public void startGame() {
		gameFrame.getContentPane().removeAll();
		gameFrame.revalidate();
		view = new Game2View(this, gameFrame, screenSize);
		view.addController(this);
		gameActive = true;
		tutorialActive = true;

		long lastTime = System.nanoTime();
		final double ammountOfTicks = 60.0;
		double ns = 1000000000 / ammountOfTicks;
		double delta = 0;
		startTime = System.currentTimeMillis();
		long endTimer = 0;
		long stormTimer = System.currentTimeMillis();

		while (gameActive) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {

				animal.move();
				view.repaintFrame();
				updates++;
				delta--;

			}
			if (tutorialActive) {
				startTime = System.currentTimeMillis();
				stormTimer = System.currentTimeMillis();
			} else {

				frames++;
				collisionDetection();

				if (System.currentTimeMillis() - stormTimer > 8000) {
					stormTimer += 10000;
					if (getStormStatus() == true) {
						deactivateStorm();
					} else {
						activateStorm();
					}

				}

				if (algaeList.size() < algae.getMaxAlgae()) {
					if (System.currentTimeMillis() >= spawnTime + getSpawnDelay()) {
						spawnAlgae();
						spawnTime = System.currentTimeMillis();

					}

				}

			}

		}

	}

	/**
	 * Gets the current storm status
	 * @return isStorming is true if the storm is active and causing runnoff
	 * 		   isStorming is false if the storm is gone and algae is spawning at the regular rate
	 */
	public boolean getStormStatus() {
		return isStorming;
	}

	/**
	 * Registers the storm status as true and registers the spawn delay to decrease
	 * This causes the algae to spawn at a fast rate
	 */
	public void activateStorm() {

		isStorming = true;
		setSpawnDelay(getSpawnDelay() - 1700);
	}

	/**
	 * Registers the storm status as false and registers the spawn delay to increase back to its original value
	 * This causes the algae to spawn at a normal rate
	 */
	public void deactivateStorm() {

		isStorming = false;
		setSpawnDelay(getSpawnDelay() + 1700);
	}

	/**
	 * Gets the time in between the algae spawns
	 * @return spawnDelay is the time in between algae spawns
	 */
	public int getSpawnDelay() {
		return spawnDelay;
	}

	/**
	 * Registers the amount of time in between algae spawns based on the passed in parameter
	 * @param delay is the desired time in between algae spawns
	 */
	public void setSpawnDelay(int delay) {
		spawnDelay = delay;
	}

	/**
	 * Gets the current system time in milliseconds and converts it to seconds
	 * @return the game time in seconds
	 */
	public long getGameTime() {
		return (System.currentTimeMillis() - startTime) / 1000;
	}

	/**
	 * Increments the number of algae missed by the animalModel
	 * missed algae are algae that enter the estuary by leaving the screen (algae x coordinate<0)
	 */
	public void addNumMissed() {
		numMissed++;
	}

	/**
	 * @return numMissed the algae that enter the estuary by leaving the screen (algae x coordinate<0)
	 */
	public int getNumMissed() {
		return numMissed;
	}

	
	/**
	 * Creates a new AlgaeModel and ads it to an arrayList of all spawned algae
	 * arrayList only contains active algae
	 */
	public void spawnAlgae() {
		AlgaeModel newAlgae = new AlgaeModel();
		newAlgae.spawnAlgaeModel();
		if (!view.isStopSpawn()) {
			algaeList.add(newAlgae);
		}

	}

	
	/**
	 * @return algaeList is the list of current active algae
	 */
	public Collection<AlgaeModel> getAlgaeList() {
		return algaeList;
	}
	
	
	/**
	 * @return gameActive is the state of the game
	 * returns false if the game is completed
	 * returns true if the game is still underway
	 */
	public boolean getgameActive() {
		return gameActive;
	}

	
	/**
	 * Registers the gameActive state to equal the parameter active
	 * @param active is the desired state of the game
	 * if true the game will begin
	 * if false the game will end
	 * 
	 */
	public void setGameActive(boolean active) {
		gameActive = active;
	}

	/**
	 * @param animal takes in the animalModel
	 * @param algae takes in the algae
	 * @return true if the animal model intersects with the algae model
	 * 		   false if the animal model does not intersect with the algae model
	 */
	public boolean collisionOccured(AnimalModelG2 animal, AlgaeModel algae) {

		int algMod = 0;
		if (view != null) {
			algMod = view.getAlgaeEaterX();
		}
		Rectangle algae_rect = new Rectangle(algae.getLocX(), algae.getLocY(), algae.getWidth(), algae.getHeight());
		Rectangle animal_rect = new Rectangle(animal.getLocX() + algMod, animal.getLocY(), animal.getWidth(),
				animal.getHeight());

		if (animal_rect.getBounds().intersects(algae_rect)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param algae is the algae model to be tested
	 * @return true if the algae goes off the screen (algae x coord <0)
	 * 		   false if the algae is still on the screen (algae x coord >0)
	 */
	public boolean shallowWaterCollision(AlgaeModel algae) {
		if (algae.getLocX() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Gets all the algae currently active in the game by getting the algae list
	 * Iterates through the list and checks each algae for collision with either the animal model or
	 * if the algae goes off the screen 
	 * if either returns true it sets the algae's eaten state as true and removes it from the game
	 * if it leaves the screen the number of algae missed is incremented
	 * 
	 */
	public void collisionDetection() {

		Collection<AlgaeModel> algaeList = getAlgaeList();

		Iterator<AlgaeModel> it = algaeList.iterator();

		while (it.hasNext()) {

			AlgaeModel tmp = it.next();

			if (tmp.isActive()) {
				if (collisionOccured(animal, tmp)) {
					tmp.eaten();

				} else if (shallowWaterCollision(tmp)) {
					tmp.eaten();
					addNumMissed();
				}
			}
		}
	}

	/**
	 * Gets the current animalModel
	 * @return the animalModel
	 */
	public AnimalModelG2 getAnimal() {
		return animal;
	}
	
	/**Registers the desired animalModel
	 * @param animal is the desired animalModel to be used in game
	 */
	public void setAnimal(AnimalModelG2 animal) {
		this.animal = animal;
	}


	/**
	 * Gets the current algae model
	 * @return algae is the current algaeModel
	 */
	public AlgaeModel getAlgae() {
		return algae;
	}

	/**
	 * Registers the current algaeModel = to the passed in parameter algaeModel
	 * @param algae is the desired algaeModel to be registered
	 */
	public void setAlgae(AlgaeModel algae) {
		this.algae = algae;
	}


	/**
	 * @return the current number of algae active
	 */
	public int getAlgaeNum() {
		return algaeList.size();
	}

	
	/**
	 * Gets the current animalModel
	 * @return the current animalModel
	 */
	public AnimalModelG2 getAnimalModelG2() {
		return this.animal;
	}

	/**
	 * Gets the current tutorial state
	 * @return true if the tutorial is active
	 * 		   false if the tutorial is over
	 */
	public boolean getTutorialStatus() {

		return tutorialActive;
	}

	/**
	 * Registers the state of the tutorial = to the desired value
	 * @param active is the desired state of the tutorial
	 */
	public void setTutorialStatus(boolean active) {

		tutorialActive = active;
	}

	/**
	 * Gets the current component view
	 * @return the current view used in the game
	 */
	public Game2View getView() {
		return view;
	}

	/**
	 * Registers the view = to the desired game view based on param
	 * @param view is the desired game view
	 */
	public void setView(Game2View view) {
		this.view = view;

	}

}