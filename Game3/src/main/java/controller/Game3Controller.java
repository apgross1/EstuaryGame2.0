package controller;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;
import Enums.AnimGraphics;
import models.AnimalModelG3;
import models.BeachModel;
import models.ConcretePUModel;
import models.GabionPUModel;
import models.GridBlock;
import models.Pair;
import models.SunHurricaneModel;
import models.Tutorial;
import models.WaterModel;
import models.WaveModel;
import models.GabionPUModel.GabPUState;
import view.Game3View;
import view.Game3View.GridTile;

public class Game3Controller {
	private boolean gameActive;
	private Game3View view;
	private AnimalModelG3 animal;
	private BeachModel beach;
	private SunHurricaneModel sun;
	private SunHurricaneModel hurricane;
	private Tutorial tutorial;
	private long startTime;
	private int updates = 0;
	private int frames = 0;
	private JFrame gameFrame;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private boolean tutorialActive;
	private Timer puWallLinkTimer;
	private boolean gameWin;
	
	
	
	/**
	 * Constructor for this element. Initializes models. Sets tutorial on/off
	 * @param gameF JFrame to be used
	 * @param tutorialOn boolean that determines if the tutorial should be played
	 */
	public Game3Controller(JFrame gameF, boolean tutorialOn) {
		this.setTutorialActive(false);
		gameFrame = gameF;
		AnimalModelG3 a = new AnimalModelG3();
		a.setLocX(250);
		a.setLocY(250);
		setAnimal(a);
		setBeach(new BeachModel());
		
		tutorial = new Tutorial();
	}
	
	
	
	/**
	 * Central run loop. This method initializes the game, adds graphics, starts game timers, and
	 * enters a game loop that repaints the frame and checks for collision.
	 * The loop is broken once the player wins/loses.
	 */
	public void runGame()  {
		gameFrame.getContentPane().removeAll();
		gameFrame.revalidate();
		animal.addPics();
		tutorial.addPics();
		view = new Game3View(this, gameFrame);
		
		
		this.getBeach().setFrameMap(view.getFrameMap());
		this.getAnimal().setFrames(view.getFrameMap());
		System.out.println("Time panel width: " + this.view.getTimePanel().getWidth());
		SunHurricaneModel sun = new SunHurricaneModel(this.view.getTimePanel());
		sun.setInitialPosition(this.view.getTimePanel().getWidth()-20);
		SunHurricaneModel hurricane = new SunHurricaneModel(this.view.getTimePanel());
		hurricane.setInitialPosition((this.view.getTimePanel().getWidth())/2);
	
		setSun(sun);
		setHurricane(hurricane);
		
		this.loadImages();
		
		view.addSun();
		view.addHurricane();
		this.startTime();
		this.activateSkyTimer();
		
		this.setGameActive(true);
		if(isTutorialActive()) {
			this.activateTutorial();
		}
		
		
		startTime = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		final double ammountOfTicks = 60.0;	
		double ns = 1000000000 /ammountOfTicks;
		double delta = 0;
		long timer2 = System.currentTimeMillis();
		this.genWaveTimer();
		Random die = new Random();
		int triggerSpawn = 4;
		while(getgameActive()) {
			setGameActive(false);
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime=now;
			if(delta>=1){
				animal.tick();
				view.repaintAll();
				updates++;
				delta--;
			}
			frames++;
			if(System.currentTimeMillis()-timer2>1000){
				timer2 +=1000;
				updates = 0;
				frames = 0;
			}
			
			//Controller for now but could be implemented in Model in tick function
			animal.findBeachLocation();
			
			
			
			if(this.isTutorialActive()) {
				this.collisionPowerUps();
			}
			else{
				
				if(triggerSpawn == die.nextInt(700000)) {
					
					if(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getIsActive() == false && beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getIsActive() == false) {
						getBeach().spawnConcrPU(getBeach().generatePPUL());
						getBeach().spawnGabPU(getBeach().generatePPUL(), false);
						this.powerUpSpawned();
					}	
				}
				
			
				if((beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair()))).getConcrPU().getIsActive() && beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getIsActive()); {
					this.collisionPowerUps();
				}
			}
			this.collisionTile();
			this.view.repaintAll();
			this.view.updateLoc();	
		}
		
		
		view.startEndScreen(this.isGameWin());
	}

	
	
	/**
	 * Listener for the sky timer. Cyclically brightens the sky over the
	 * course of the game to indicate the passing of the hurricane.
	 */
	ActionListener skyTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.brightenSky();
		}
	};
	
	/**
	 * Timer for the changing color of the sky.
	 */
	public void activateSkyTimer() {
		Timer skyTimer = new Timer(1250, skyTimerListener);
		skyTimer.setRepeats(true);
		skyTimer.start();
	}
	
	/**
	 * Listener used to remove previously spawned gabion/concrete power-ups on the beach
	 * after they have been spawned for an alloted time. The tile on which they
	 * were placed will revert back to being vacant.
	 */
	ActionListener powerUpSpawnTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			beach.removeGabPU(beach.findPairInGrid(beach.getGabPair()));
			beach.removeConcrPU(beach.findPairInGrid(beach.getConcPair()));
			
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setPickedUp(false);
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setIsActive(false);
			if(!beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getWater().isActive()) {
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).setVacant(true);
			}
			
			
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setActive(false);
			beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(false);
			if(!beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getWater().isActive()) {
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).setVacant(true);
			}
			
			
			Object time = e.getSource();
			Timer myTime = (Timer) time;
			myTime.stop();
		}
	};
	
	/**
	 * Listener used to remove previously spawned gabion/concrete walls on the beach 
	 * after they have been spawned for an alloted time. The tile on which they
	 * were placed will revert back to being vacant.
	 */
	ActionListener powerUpWallTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				
			if (beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getWallState() == GabPUState.WALL) {
				animal.setWallHit(false); 
				beach.removeGabPU(beach.findPairInGrid(beach.getGabPair()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setPickedUp(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setIsActive(false);
				if(!beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getWater().isActive()) {
					beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).setVacant(true);
				}
			}
			else {
				animal.setWallHit(false);
				beach.removeConcrPU(beach.findPairInGrid(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getLocation()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(false);
				if(!beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getWater().isActive()) {
					beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).setVacant(true);
				}
				
			}
			Object time = e.getSource();
			Timer myTime = (Timer) time;
			myTime.stop();
		}
	};

	/**
	 * Timer that indicates the duration for which gabion/concrete power-ups
	 * are available to be picked up.
	 */
	public void powerUpSpawned() {
		puWallLinkTimer = new Timer(3000, powerUpSpawnTimerListener);
		puWallLinkTimer.setRepeats(true);
		puWallLinkTimer.start();
	}
	
	
	/**
	 * Timer that indicates the duration for which gabion/concrete power-up
	 * is in wall form. The gabion wall spawns longer than the concrete
	 * to indicate the relative strength of the gabion wall compared
	 * to a concrete wall. 
	 */
	public void powerUpPickedUp() {
		Timer timer = new Timer(5000,powerUpWallTimerListener);
		if (beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getWallState() == GabPUState.WALL) { 
			timer.setDelay(5000);
			timer.setInitialDelay(5000);
			timer.setRepeats(true);
			timer.start();
		}
		else {
			timer.setInitialDelay(3000);
			timer.setDelay(3000);
			timer.setRepeats(true);
			timer.start();
		}
	}
	
	/**
	 * Collision detection method to detect a collision between the animal and gabion/concrete power-up.
	 * If a collision is detected, the power-up will turn into a wall and the power-up that was not
	 * collided with the animal disappears.
	 */
	public void collisionPowerUps(){
		if ((beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getIsActive()) & beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().isPickedUp() == false) {
			if (animal.getBounds().contains(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getBounds())) {
				puWallLinkTimer.stop();
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(true);
				beach.removeGabPU(beach.findPairInGrid(beach.getGabPair()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setIsActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).setVacant(true);
				
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(true);
				this.powerUpPickedUp();
				return;
			}
		}
		if((beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getIsActive()) &  beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().isPickedUp() == false) {
			if (animal.getBounds().contains(beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getBounds())) {
				if(!this.isTutorialActive()) {
					puWallLinkTimer.stop();
				}
				
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setPickedUp(true);
				beach.removeConcrPU(beach.findPairInGrid(beach.getConcPair()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).setVacant(true);
				
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().setPickedUp(true);
				this.powerUpPickedUp();
				return;
			}
		}
	}
	
	public void collisionWaveAnimal(WaveModel w) {
		if(w.getBounds().intersects(this.getAnimal().getBounds())) {
			if(this.isTutorialActive()) {
				this.getAnimal().setWaveHit(true);
			}
			else {
				this.getAnimal().setWaveHit(true);
				this.setGameActive(false);
				this.setGameWin(false);
			}
			return;
		}
	}
	
	public void collisionWavePowerUps(WaveModel w, ArrayList<GridTile> tiles) {
		for(GridTile gr : tiles) {
			ConcretePUModel conc = gr.getGridBlock().getConcrPU();
			GabionPUModel gab = gr.getGridBlock().getGabPU();
			if(conc.getIsActive() & conc.isPickedUp()) {
				if(conc.getBounds().intersects(w.getBounds())) {
					w.setReceed(true);
				}
			}
			else if (gab.getIsActive() & gab.isPickedUp()) {
				if(gab.getBounds().intersects(w.getBounds())) {
					w.setReceed(true);
				}
			}
			else if(w.getBounds().getX() < 10) {
				w.setReceed(true);
			}
		}
	}
	
	public void fillWaterTile(WaveModel w) {
		List<Pair> pairs = this.getBeach().getGridLayers().get(w.getClusterGroup());
		
		for(int i = pairs.size()-1; i >= 0; i--) {
			GridBlock tempGrid = this.getBeach().getBeachGrid().get(this.getBeach().findPairInGrid(pairs.get(i)));
			if(tempGrid != null) {
				if(!tempGrid.getWater().isActive()) {
					if(tempGrid.getGabPU().getIsActive()) {
						tempGrid.getGabPU().setIsActive(false);
					}
					if(tempGrid.getConcrPU().getIsActive()) {
						tempGrid.getConcrPU().setActive(false);
					}
					
					if(i != pairs.size()-1) {
						this.getBeach().getBeachGrid().get(this.getBeach().findPairInGrid(pairs.get(i+1))).getWater().setGraphicOnDeck(1);
					}
					WaterModel newWatMod = new WaterModel("game");
					newWatMod.addPics();
					tempGrid.setWater(newWatMod, this.getBeach().findPairInGrid(pairs.get(i)), "");
		
					view.getLayoutContainerComps().remove(view.getWaveComponentMap().get(w.hashCode()));
					view.getWaveComponentMap().remove(w.hashCode());
					w = null;
					this.gameFrame.revalidate();
					return;
				}
				
			}
		}
	}
	
	
	/**
	 * Tracks how long the game should run (2.5 minutes).
	 * When time has elapsed the player is sent to the "winner" end screen.
	 */
	ActionListener gameTimerListener = new ActionListener() {
		public int timeElapsed;

		@Override
		public void actionPerformed(ActionEvent e) {
			Timer t = (Timer) e.getSource();
			timeElapsed += t.getDelay();
			if ((timeElapsed < 150000) & getgameActive()) {
				System.out.println("Time Elapsed: " + timeElapsed);
				sun.move();
				hurricane.move();
			}
			else {
				setGameActive(false);
				setGameWin(true);
				t.stop();
			}
		}
	};
	
	
	
	/**
	 * Starts the game timer.
	 */
	public void startTime() {
		Timer timer = new Timer(250, gameTimerListener);
		
		timer.setRepeats(true);
		timer.start();
	}


	/**
	 * Generates a wave cluster while the game is active.
	 */
	ActionListener genWaveTimer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Timer t = (Timer) e.getSource();
			if(!gameActive) {
				t.stop();
			}
			else if (!isTutorialActive()) {
				view.generateWaveCluster(false, 0);
			}
		}
	};
	
	/**
	 * Sets wave clusters to spawn every 6 seconds
	 */
	public void genWaveTimer() {
		Timer waveTimer = new Timer(6000, genWaveTimer);
		waveTimer.setRepeats(true);
		waveTimer.start();
	}
	
	/**
	 * Collision detection between an animal and a water tile. If 
	 * the animal interacts with the water tile the game ends.
	 */
	public void collisionTile() {
		int beachLocX = this.getAnimal().getPotentialMove().getX();
		int beachLocY = this.getAnimal().getPotentialMove().getY();

		if(this.getBeach().getPositionGrid()[beachLocY][beachLocX] == 2) { //2 indicates a water tile
			this.setGameActive(false);
			this.setGameWin(false);
		}
	}

	
	/**
	 * Spawns a single gabion power-up and allows
	 * the animal to move towards it. This listener is used for
	 * the tutorial.
	 */
	ActionListener singleGabSpawnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			getBeach().spawnGabPU(getBeach().generatePPUL(), isTutorialActive());
			getAnimal().setRestrictedMovement(false);
			generateLastWave();
		}
	};
	
	/**
	 * Listener used to reset tutorial to its original state
	 * where animal movement is restricted and no wave warning exists,
	 * at which point a gabion power-up is requested to be spawned.
	 */
	ActionListener tutorialResetListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			getAnimal().resetPos();
			getTutorial().setWaveWarning(false);
			generateSingleGab();
		}
	};
	
	
	/**
	 * Restricts the movement of the animal and requests a single
	 * wave cluster to be generated. This listener is used in the
	 * tutorial.
	 */
	ActionListener animalRestrictedMovement = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			animal.resetPos();
			animal.setRestrictedMovement(true);
			generateSingleWave();
		}
	};
	
	/**
	 * Listener used to animate the keyboard to mimic pressing of the keys.
	 * This listener is used in the tutorial.
	 */
	ActionListener keyboardGraphicListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if((animal.getSpeedX() != 0) || (animal.getSpeedY()!=0)) {
				tutorial.setKeyboardStop(true);
				Timer animalMove = new Timer(3000, animalRestrictedMovement);
				animalMove.setRepeats(false);
				animalMove.start();
				
				Timer tempTime = (Timer) e.getSource();
				tempTime.stop();
			}
			else {
				tutorial.setKeyBoardPicOnDeck((tutorial.getKeyBoardPicOnDeck()+1)%(tutorial.getGraphicMap().get(AnimGraphics.KEYBOARD).size()));
			}
		}
	};
	
	/**
	 * Generates a single wave in the same lane as the animal to represent 
	 * the danger of waves in the game. This listener is used in the tutorial.
	 */
	ActionListener genSingleWaveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.generateWaveCluster(true, 1);
			resetTutorial();
		}
	};
	
	/**
	 * Generates the last wave in the tutorial that will be
	 * repelled by a  gabion wall to emphasize the use of
	 * power-ups. This listener is used in the tutorial.
	 */
	ActionListener genLastWaveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(getBeach().getBeachGrid().get(getBeach().findPairInGrid(getBeach().getGabPair())).getGabPU().getWallState() == GabPUState.WALL) {
				view.generateWaveCluster(true, 2);
				Timer t = (Timer) e.getSource();
				displayDialogue();
				t.stop();
				
			}
		}
	};
	
	/**
	 * Creates dialogue box to give final instructions in the tutorial.
	 */
	ActionListener dialTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			getTutorial().setDialogueOn(true);
			initializeGamePlay();
		}
	};
	
	/**
	 * Ends the tutorial and proceeds to the actual game.
	 */
	ActionListener initializeGameListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//Turning off dialogue box
			getTutorial().setDialogueOn(false);
			setTutorialActive(false);
		}
	};
	
	/**
	 * Activates the timer for the keyboard graphic animation in the tutorial.
	 */
	public void activateKeys() {
		Timer keyTimer = new Timer(1000,keyboardGraphicListener);
		keyTimer.setRepeats(true);
		keyTimer.start();
	}

	/**
	 * Activates the timer that requests a reset of the tutorial.
	 */
	public void resetTutorial() {
		Timer resetTimer = new Timer(3000, tutorialResetListener);
		resetTimer.setRepeats(false);
		resetTimer.start();
		
	}
	
	/**
	 * Activates timer that requests generation of the final wave
	 * in the tutorial.
	 */
	public void generateLastWave() {
		Timer lastWaveTimer = new Timer(1000,genLastWaveListener);
		lastWaveTimer.setRepeats(true);
		lastWaveTimer.start();
	}
	
	
	/**
	 * Activates timer that requests generation of a single wave
	 * in the tutorial.
	 */
	public void generateSingleWave() {
		Timer waveTimer = new Timer(2000, genSingleWaveListener);
		waveTimer.setRepeats(false);
		waveTimer.start();
	}
	
	/**
	 * Activates timer that requests generation of a single
	 * gabion power-up in the tutorial.
	 */
	public void generateSingleGab() {
		Timer gabTimer = new Timer(4000, singleGabSpawnListener);
		gabTimer.setRepeats(false);
		gabTimer.start();
	}
	
	/**
	 * Activates timer that requests the display of instructions box
	 * in the tutorial.
	 */
	public void displayDialogue() {
		Timer dialTimer = new Timer(6000, dialTimerListener);
		dialTimer.setRepeats(false);
		dialTimer.start();
	}
	
	

	/**
	 * Activates timer that requests the start of gameplay after the tutorial has ended.
	 */
	public void initializeGamePlay() {
		Timer timer = new Timer(6000, initializeGameListener);
		timer.setRepeats(false);
		timer.start();
	}
	
	/**
	 * Calls a series of timers/action listeners that run through the tutorial.
	 */
	public void activateTutorial() {
		this.activateKeys();	
	}
	
	
	/**
	 *Loads the graphics for the sun and hurricane which will serve as visual representations of
	 *these game objects 
	 */
	public void loadImages() {
		sun.addPics();
		hurricane.addPics();
	}
	
	/**
	 * Getter to determine if the game is still running
	 * @return gameActive boolean, 1 if game is still active, 0 otherwise
	 */
	public boolean getgameActive() {
		return gameActive;
	}
	
	/**
	 * Sets if the game is still running
	 * @param active boolean, 1 if game is still active, 0 otherwise
	 */
	public void setGameActive(boolean active) {
		gameActive = active; 
	}

	/**
	 * Gets the animal model that is to be used in the game.
	 * @return animal AnimalModelG3, an instance of an animal
	 */
	public AnimalModelG3 getAnimal() {
		return animal;
	}

	/**
	 * Sets the animal model that is to be used in the game.
	 * @param animal AnimalModelG3, an instance of an animal
	 */
	public void setAnimal(AnimalModelG3 animal) {
		this.animal = animal;
	}

	/**
	 * Gets the beach that is used in the game.
	 * @return beach BeachModel, an instance of the beach
	 */
	public BeachModel getBeach() {
		return beach;
	}

	/**
	 * Sets the beach that is used in the game.
	 * @param beach BeachModel, an instance of the beach
	 */
	public void setBeach(BeachModel beach) {
		this.beach = beach;
	}
	
	/**
	 * Gets the sun that will be used in the game as a visualization of the timer.
	 * @return sun SunHurricaneModel, the sun which will be used in the game
	 */
	public SunHurricaneModel getSun() {
		return sun;
	}

	/**
	 * Sets the sun that will be used in the game as a visualization of the timer.
	 * @param sun SunHurricaneMode, the sun which will be used in the game
	 */
	public void setSun(SunHurricaneModel sun) {
		this.sun = sun;
	}

	/**
	 * Gets the hurricane that will be used in the game as a visualization of the timer.
	 * @return hurricane SunHurricaneModel, the hurricane which will be used in the game
	 */
	public SunHurricaneModel getHurricane() {
		return hurricane;
	}

	/**
	 * Sets the hurricane that will be used in the game as a visualization of the timer.
	 * @param hurricane SunHurricaneMode, the hurricane which will be used in the game
	 */
	public void setHurricane(SunHurricaneModel hurricane) {
		this.hurricane = hurricane;
	}

	/**
	 * Gets the dimensions of the screen.
	 * @return screenSize Dimension, dimensions of the screen
	 */
	public Dimension getScreenSize() {
		return screenSize;
	}

	/**
	 * Determines if the game is currently in the tutorial. If so, it triggers special
	 * functionality of certain functions to comply with the tutorial animations.
	 * @return
	 */
	public boolean isTutorialActive() {
		return tutorialActive;
	}

	
	/**
	 * Sets whether or not the game is still in the tutorial.
	 * @param tutorialActive boolean, 1 if game is still in tutorial, 0 otherwise
	 */
	public void setTutorialActive(boolean tutorialActive) {
		this.tutorialActive = tutorialActive;
	}

	/**
	 * Gets the tutorial object which contains getters and setters
	 * to determine if tutorial events are completed.
	 * @return tutorial Tutorial, an instance of the tutorial
	 */
	public Tutorial getTutorial() {
		return tutorial;
	}

	/**
	 * Sets the tutorial object which contains getters and setters
	 * to determine if tutorial events are completed.
	 * @param tutorial Tutorial, an instance of the tutorial
	 */
	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	/**
	 * Determines if player won the game
	 * @return gameWin boolean, 1 if player won the game, 0 if player lost the game
	 */
	public boolean isGameWin() {
		return gameWin;
	}

	/**
	 * Set if player won the game
	 * @param gameWin boolean, 1 if player won the game, 0 if player lost the game
	 */
	public void setGameWin(boolean gameWin) {
		this.gameWin = gameWin;
	}

	//For testing purposes
		public Game3Controller(){
			AnimalModelG3 a = new AnimalModelG3();
			a.setLocX(0);
			a.setLocY(0);
			setAnimal(a);
		}
}
