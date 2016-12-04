package controller;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Enums.Frames;
import Enums.AnimGraphics;
import enums.Waves;
import models.AnimalModelG3;
import models.BeachModel;
import models.ConcretePUModel.ConcPUState;
import models.GridBlock;
import models.SunHurricaneModel;
import models.Tutorial;
import models.WallModelAbstract;
import models.WaterModel;
import models.WaveModel;
import models.GabionPUModel.GabPUState;
import view.Game3View;

public class Game3Controller implements KeyListener {
	private boolean gameActive;
	private Game3View view;
	private AnimalModelG3 animal;
	private BeachModel beach;
	private GridBlock sandPatch;
	private WaterModel water;
	private SunHurricaneModel sun;
	private SunHurricaneModel hurricane;
	private Tutorial tutorial;
	private Timer timer;
	private long startTime;
	private int updates = 0;
	private int frames = 0;
	private JFrame gameFrame;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private boolean tutorialActive;
	
	
	public Game3Controller(JFrame gameF) {
		this.setTutorialActive(true);
		gameFrame = gameF;
		AnimalModelG3 a = new AnimalModelG3();
		a.setLocX(250);
		a.setLocY(250);
		setAnimal(a);
		setBeach(new BeachModel());
		setSandPatch(new GridBlock(beach));
		setWater(new WaterModel());
		tutorial = new Tutorial();
		
		
	}
	
	public void runGame()  {
		gameFrame.getContentPane().removeAll();
		gameFrame.revalidate();
		animal.addPics();
		tutorial.addPics();
		view = new Game3View(this, gameFrame);
		this.getBeach().setFrameMap(view.getFrameMap());
		this.getAnimal().setFrames(view.getFrameMap());
		this.getAnimal().setFrameWidth(view.getLayoutContainerComps().get(Frames.ANIMAL).getWidth());
		this.getAnimal().setFrameHeight(view.getLayoutContainerComps().get(Frames.ANIMAL).getHeight());
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
		this.activateTutorial();
		
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
	}

	
	
	ActionListener skyTimerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.brightenSky();
		}
	};
	
	public void activateSkyTimer() {
		Timer skyTimer = new Timer(1250, skyTimerListener);
		skyTimer.setRepeats(true);
		skyTimer.start();
	}
	
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
				
				System.out.println("Wall Timer stopped");
			}
			else {
				animal.setWallHit(false);
				beach.removeConcrPU(beach.findPairInGrid(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getLocation()));
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setActive(false);
				beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().setPickedUp(false);
				if(!beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getWater().isActive()) {
					beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).setVacant(true);
				}
				
				System.out.println("Wall Timer stopped");
			}
			Object time = e.getSource();
			Timer myTime = (Timer) time;
			myTime.stop();
		}
	};
	
	
	
	
	
	
	
	//Duration for which power-up is available to be picked up
	public void powerUpSpawned() {
		timer = new Timer(3000, powerUpSpawnTimerListener);
		System.out.println("Gabion is at: (" + beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getViewLocation().getX() +", " + beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getViewLocation().getY() + ")");
		System.out.println("Concrete is at:(" + beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getViewLocation().getX() +", " + beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getViewLocation().getY() + ")");
		
		timer.setRepeats(true);
		timer.start();
		System.out.println("Spawn timer started");
	}
	
	
	
	
	//Duration for which power-up is in wall form
	public void powerUpPickedUp() {
		if (beach.getBeachGrid().get(beach.findPairInGrid(beach.getGabPair())).getGabPU().getWallState() == GabPUState.WALL) { 
			timer = new Timer(5000, powerUpWallTimerListener);
		}
		else {
			timer = new Timer(3000, powerUpWallTimerListener);
		}
		timer.setRepeats(true);
		timer.start();
		System.out.println("Wall timer started");
	}
	
	public void collisionPowerUps(){
		if ((beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getIsActive()) & beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().isPickedUp() == false) {
			if (animal.getBounds().contains(beach.getBeachGrid().get(beach.findPairInGrid(beach.getConcPair())).getConcrPU().getBounds())) {
				System.out.println("Intersection between concrete and animal");
				timer.stop();
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
				System.out.println("Intersection between gab and animal");
				timer.stop();
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
	
	ActionListener gameTimerListener = new ActionListener() {
		public int timeElapsed;

		@Override
		public void actionPerformed(ActionEvent e) {
			Timer t = (Timer) e.getSource();
			timeElapsed += t.getDelay();
			if (timeElapsed < 120000) {
				sun.move();
				hurricane.move();
			}
			else {
				gameActive = false;
				timer.stop();
			}
		}
	};
	
	
	
	public void startTime() {
		timer = new Timer(220, gameTimerListener);
		
		timer.setRepeats(true);
		timer.start();
	}

	//Can change later for different levels of difficulty
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
	
	public void genWaveTimer() {
		Timer waveTimer = new Timer(6000, genWaveTimer);
		
		waveTimer.setRepeats(true);
		waveTimer.start();
	}
	
	public void collisionTile() {
		int beachLocX = this.getAnimal().getPotentialMove().getX();
		int beachLocY = this.getAnimal().getPotentialMove().getY();

		if(this.getBeach().getPositionGrid()[beachLocY][beachLocX] == 2) {
			System.out.println("Value where animal hit water tile: " + this.getBeach().getPositionGrid()[beachLocY][beachLocX]);
			System.out.println("Game over! Tidal pool was hit at: " + beachLocX +","+beachLocY);
			this.setGameActive(false);
		}
	}

	
	ActionListener singleGabSpawnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			getBeach().spawnGabPU(getBeach().generatePPUL(), isTutorialActive());
			getAnimal().setRestrictedMovement(false);
			generateLastWave();
		}
	};
	
	ActionListener tutorialResetListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			getAnimal().resetPos();
			getTutorial().setWaveWarning(false);
			generateSingleGab();
		}
	};
	
	
	ActionListener animalFreeMovement = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			animal.resetPos();
			animal.setRestrictedMovement(true);
			generateSingleWave();
		}
	};
	
	ActionListener keyboardGraphicListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if((animal.getSpeedX() != 0) || (animal.getSpeedY()!=0)) {
				tutorial.setKeyboardStop(true);
				Timer animalMove = new Timer(3000, animalFreeMovement);
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
	
	ActionListener genSingleWaveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.generateWaveCluster(true, 1);
			resetTutorial();
		}
	};
	
	ActionListener genLastWaveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.generateWaveCluster(true, 2);
		}
	};
	
	public void activateKeys() {
		Timer keyTimer = new Timer(1000,keyboardGraphicListener);
		keyTimer.setRepeats(true);
		keyTimer.start();
	}

	public void resetTutorial() {
		Timer resetTimer = new Timer(3000, tutorialResetListener);
		resetTimer.setRepeats(false);
		resetTimer.start();
		
	}
	
	public void generateLastWave() {
		Timer lastWaveTimer = new Timer(3000,genLastWaveListener);
		lastWaveTimer.setRepeats(false);
		lastWaveTimer.start();
	}
	
	public void generateSingleWave() {
		Timer waveTimer = new Timer(2000, genSingleWaveListener);
		waveTimer.setRepeats(false);
		waveTimer.start();
	}
	
	public void generateSingleGab() {
		Timer gabTimer = new Timer(6000, singleGabSpawnListener);
		gabTimer.setRepeats(false);
		gabTimer.start();
	}
	
	public void activateTutorial() {
		this.activateKeys();	
	}
	
	
	
	public void loadImages() {
		sun.addPics();
		hurricane.addPics();
		
	}

	public void collisionDetectionLoop(){
		
	}
	
	
	
	public void stopTime() {
		
	}

	
	public boolean getgameActive() {
		return gameActive;
	}
	public void setGameActive(boolean active) {
		gameActive = active; 

}

	public int setTime(int i) {
		return i;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public AnimalModelG3 getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalModelG3 animal) {
		this.animal = animal;
	}


	public WaterModel getWater() {
		return water;
	}

	public void setWater(WaterModel water) {
		this.water = water;
	}

	public BeachModel getBeach() {
		return beach;
	}

	public void setBeach(BeachModel beach) {
		this.beach = beach;
	}
	
	public GridBlock getSandPatch() {
		return sandPatch;
	}

	public void setSandPatch(GridBlock sandPatch) {
		this.sandPatch = sandPatch;
	}

	public SunHurricaneModel getSun() {
		return sun;
	}

	public void setSun(SunHurricaneModel sun) {
		this.sun = sun;
	}

	public SunHurricaneModel getHurricane() {
		return hurricane;
	}

	public void setHurricane(SunHurricaneModel hurricane) {
		this.hurricane = hurricane;
	}

	public Dimension getScreenSize() {
		return screenSize;
	}

	public boolean isTutorialActive() {
		return tutorialActive;
	}

	public void setTutorialActive(boolean tutorialActive) {
		this.tutorialActive = tutorialActive;
	}

	public Tutorial getTutorial() {
		return tutorial;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

}
