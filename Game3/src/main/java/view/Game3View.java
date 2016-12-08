package view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.OverlayLayout;
import javax.swing.Timer;
import javax.swing.border.Border;

import Enums.AnimGraphics;
import Enums.Frames;
import Enums.WaveClusters;
import controller.Game3Controller;
import enums.Direction;
import enums.Waves;
import models.AnimalModelG3;
import models.BeachModel;
import models.ConcretePUModel;
import models.ConcretePUModel.ConcPUState;
import models.GabionPUModel;
import models.GabionPUModel.GabPUState;
import models.GridBlock;
import models.Pair;
import models.SunHurricaneModel;
import models.WaterModel;
import models.WaveModel;


public class Game3View extends JPanel implements KeyListener, MouseListener {
	private Game3Controller controller;
	private HashMap<Integer, Wave> waveComponentMap;
	private HashMap<Frames, JComponent> frameMap;
	private JFrame frame;
	private JPanel timePanel = new JPanel();
	private ArrayList<GridTile> powerUps;
	private JPanel play_ground = new JPanel(new BorderLayout());
	private JLayeredPane layoutContainer = new JLayeredPane();
	private BufferedImage shoreGraphic;
	private JLabel animalPos;
	private int brightLevel;
	private Color skyColor;
	private JLabel endScreen;
	private JButton menuButton;
	private ArrayList<BufferedImage> mainMenuPics = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> exitGamePics = new ArrayList<BufferedImage>();
	private boolean exitToMain =  false;
	private boolean exitGame = false;
	

	
	/**
	 * Constructor for this element
	 * @param ctl instance of Game3Controller
	 * @param gameF instance of the JFrame shared between all 3 games
	 */
	public Game3View(Game3Controller ctl, JFrame gameF){
		//Adding end menu button images
		BufferedImage exitGame_0 = null;
		BufferedImage exitGame_1 = null;
		BufferedImage returnMain_0 = null;
		BufferedImage returnMain_1 = null;
		try {
			exitGame_0 = ImageIO.read(new File("./Images/Game3/exitGame_0.png"));
			exitGame_1 = ImageIO.read(new File("./Images/Game3/exitGame_1.png"));
			returnMain_0= ImageIO.read(new File("./Images/Game3/returnMain_0.png"));
			returnMain_1 = ImageIO.read(new File("./Images/Game3/returnMain_1.png"));
		} catch (IOException excep) {
			excep.printStackTrace();
		}
	
		this.getMainMenuPics().add(returnMain_0);
		this.getMainMenuPics().add(returnMain_1);
		this.getExitGamePics().add(exitGame_0);
		this.getExitGamePics().add(exitGame_1);
		
		
		
		
		brightLevel = 255;
		skyColor = new Color((int)0,(int)0,(int)0, (int)this.getBrightLevel());
		//Adding shore graphic (only one which is not created in a model)
		try {
			shoreGraphic = ImageIO.read(new File("./Images/Game3/Creek.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		frameMap = new HashMap<Frames, JComponent>();
		controller = ctl;
		
		
		
		frame = gameF;
		frame.dispose();
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	
		frame.setSize((int)controller.getScreenSize().getWidth(), (int)controller.getScreenSize().getHeight());
		play_ground.setSize(frame.getWidth(),frame.getHeight());
		
		timePanel.setLayout(null);

		waveComponentMap = new HashMap<Integer,Wave>();
    	frame.setBackground(Color.gray);

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    	frame.setResizable(false);
    	
    	
		play_ground.setSize(frame.getWidth(), frame.getHeight());
		play_ground.setBackground(Color.WHITE);
		
		powerUps = new ArrayList<GridTile>();
		
    	//Panes
		//For animal movement
		
		JTutorial tutorialPane = new JTutorial();
		
		Animal animalPane = new Animal();
		animalPane.setPreferredSize(new Dimension((int)(frame.getWidth()*(.875)),(int)(frame.getHeight()*(0.75))));
		
		JPanel beachGrid = new JPanel(new GridLayout(7,7));

		ShoreLine water = new ShoreLine();
	
		
		water.setPreferredSize(new Dimension((int)(frame.getWidth()*(.125)),frame.getHeight()));
		
		water.setVisible(true);
		beachGrid.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		water.setBounds(0, 0, (int)(frame.getWidth()*(.125)), frame.getHeight());
		
		animalPane.setBounds(0, 0, frame.getWidth()-water.getWidth(), (int)(frame.getHeight()*(.75)));
		tutorialPane.setBounds(0, 0, animalPane.getWidth(),animalPane.getHeight());
		Collection<Pair> blocks = controller.getBeach().getOrderedPairs();
		Iterator<Pair> it = blocks.iterator();
		while(it.hasNext()) {
			Pair currBlock = it.next();
			JPanel beachOverlay = new JPanel();
			beachOverlay.setLayout(new OverlayLayout(beachOverlay));
			
			JLabel location = new JLabel();
			//location.setText("("+currBlock.getX()+","+currBlock.getY()+")");
			SandWater gridBlock = new SandWater(currBlock);
			gridBlock.add(location);
			GridTile powerUp = new GridTile(currBlock);
			powerUps.add(powerUp);
		    powerUp.setBounds(0,0,animalPane.getWidth(),animalPane.getHeight());
			layoutContainer.add(powerUp, new Integer(2),-1);
		    beachOverlay.add(gridBlock);
		    //beachOverlay.add(location);
		    //beachOverlay.setBorder(BorderFactory.createLineBorder(Color.black));
		    beachGrid.add(beachOverlay);
		    
		}
		
		
		timePanel.setPreferredSize(new Dimension(frame.getWidth(), (int)(frame.getHeight()*.25)));
		timePanel.setBounds(0, 0, frame.getWidth(), (int)(frame.getHeight()*.25));
		timePanel.setBackground(this.getSkyColor());
		
		
		
		layoutContainer.add(beachGrid, new Integer(1),0);
		layoutContainer.add(animalPane, new Integer(2), 1);
		layoutContainer.add(tutorialPane, new Integer(3),1);
		play_ground.add(timePanel, BorderLayout.NORTH);
		play_ground.add(water, BorderLayout.EAST);
		play_ground.add(layoutContainer, BorderLayout.CENTER);
		
		

		frame.add(play_ground);
		frame.setVisible(true);
    	//addKeyListener
    	frame.addKeyListener(this);
    	frame.pack();
    	
    	//Inserting into frame map. This will allow us to reference 
    	//dimensions of each component throughout the game
    	frameMap.put(Frames.BEACH_GRID, beachGrid);
    	frameMap.put(Frames.ANIMAL, animalPane);
    	frameMap.put(Frames.TIMER, timePanel);
    	frameMap.put(Frames.SHORE, water);
    	frameMap.put(Frames.TUTORIAL, tutorialPane);
    	
   
    	animalPos = new JLabel();
		animalPos.setText("Animal is on tile: (" + this.getController().getAnimal().getPotentialMove().getX() + "," + this.controller.getAnimal().getPotentialMove().getY() + ")");
		animalPos.setBounds(frameMap.get(Frames.TIMER).getWidth()/2, frameMap.get(Frames.TIMER).getHeight()/2, 200, 100);

		
		//timePanel.add(animalPos);
		//animalPos.setVisible(true);
		timePanel.revalidate();
		frame.revalidate();
	}
 
	
	public void updateLoc() {
		animalPos.setText("Animal is on tile: (" + this.getController().getAnimal().getPotentialMove().getX() + "," + this.controller.getAnimal().getPotentialMove().getY() + ")");
	}
	
	
	/**
	 * Adds sun component to the JFrame (more specifically the panel where the timer is placed)
	 */
	public void addSun() {
		Sun sun = new Sun(controller.getSun());
		sun.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		sun.setVisible(true);
		timePanel.add(sun);
		timePanel.revalidate();
		frame.revalidate();
		
	}
	
	/**
	 * Adds hurricane component to the JFrame (more specifically the panel where the timer is placed)
	 */
	public void addHurricane() {
		Hurricane hurricane = new Hurricane(controller.getHurricane());
		hurricane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		hurricane.setVisible(true);
		timePanel.add(hurricane);
		timePanel.revalidate();
		frame.revalidate();
		
	}

	/**
	 * Repaint the JFrame and its components. Mainly used in the controller.
	 */
	public void repaintAll(){
		frame.repaint();
	}
	
	/**
	 * @author Andrew
	 *Component class for the game tutorial. Handles all of the visual
	 *representation related to the tutorial.
	 */
	public class JTutorial extends JComponent {
		@Override
		public void paint(Graphics g) {
			if(controller.isTutorialActive()) {
				drawKeyboard(g);
				drawX(g);
				drawDialogue(g);
			}
		}
		/**
		 * Paints the keyboard graphic
		 * @param g Graphics, assigned the current keyboard image
		 */
		public void drawKeyboard(Graphics g) {
			if(!controller.getTutorial().isKeyboardStop()) {
				g.drawImage((controller.getTutorial().getGraphicMap().get(AnimGraphics.KEYBOARD).get(controller.getTutorial().getKeyBoardPicOnDeck())), (int)(frame.getWidth()*.60), (int)(frame.getHeight()*.40), this);
			}
		}
		
		
		/**
		 * Paints the "X" when the animal collides with a wave during the tutorial.
		 * @param g Graphics, assigned the "X" image
		 */
		public void drawX(Graphics g) {
			if(controller.getAnimal().isWaveHit()) {
				g.drawImage((controller.getTutorial().getGraphicMap().get(AnimGraphics.BIG_X).get(0)), controller.getAnimal().getLocX(),controller.getAnimal().getLocY(), this);
			}
		}
		
		/**
		 * Paints the dialogue box at the end of the tutorial.
		 * @param g , Graphics, assigned the dialogue box image
		 */
		public void drawDialogue(Graphics g) {
			if(controller.getTutorial().isDialogueOn()) {
				g.drawImage((controller.getTutorial().getGraphicMap().get(AnimGraphics.DIALOGUE).get(0)), (int)(frameMap.get(Frames.ANIMAL).getWidth()*.6), (int)(frameMap.get(Frames.ANIMAL ).getHeight()*.30), this);
			}
		}
	}
	
	/**
	 * @author Andrew
	 *Component class for the hurricane. Used to visually represent the hurricane in the time panel.
	 */
	public class Hurricane extends JComponent {
		private SunHurricaneModel hurricane;
		/**
		 * Constructor for this element. Sets the location of the hurricane on the screen.
		 * @param s SunHurricaneModel, the instance of the hurricane to be passed in
		 */
		public Hurricane(SunHurricaneModel s) {
			hurricane = s;
			hurricane.getLocation().setX(hurricane.getPanel().getWidth()/2);
			hurricane.getLocation().setY(hurricane.calculateY(hurricane.getLocation().getX()));
			
		}
		@Override
		public void paint(Graphics g) {
			//g.setColor(Color.GREEN);
			//g.fillOval(hurricane.getLocation().getX(), hurricane.getLocation().getY(), hurricane.getWidth(), hurricane.getHeight());
			g.drawImage((hurricane.getGraphics().get("HURRICANE")).get(0), hurricane.getLocation().getX(), hurricane.getLocation().getY(), this);
		}
	}
	
	/**
	 * @author Andrew
	 *Component class for the sun. Used to visually represent the sun in the time panel.
	 */
	public class Sun extends JComponent {
		private SunHurricaneModel sun;
		/**
		 * Constructor for this element. Sets the location of the sun.
		 * @param s
		 */
		public Sun(SunHurricaneModel s) {
			sun = s;
			sun.getLocation().setX(sun.getPanel().getWidth());
			sun.getLocation().setY(-200);
		}
		@Override
		public void paint(Graphics g) {
			g.drawImage(sun.getGraphics().get("SUN").get(0), sun.getLocation().getX(), sun.getLocation().getY(), this);
		}
	}
	
	
	/**
	 * @author Andrew
	 * Component class for a wave particle. The component class handles all of the 
	 */
	public class Wave extends JComponent {
		private WaveModel wave;
		private boolean waveGone;
		
		public Wave(WaveModel wave) {
			this.wave = wave;
			waveGone = false;
			setOpaque(false);
		}
		
		public void disposeWave(Graphics g) {
			g.dispose();
			layoutContainer.remove(waveComponentMap.get(this.hashCode()));
			waveComponentMap.remove(this.hashCode());
			this.waveGone = true;
			frame.revalidate();
			controller.getAnimal().setWaveHit(false);
		}
		
		ActionListener removeWaveFromPauseListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wave.resetWave();
				
			}
		};
		
		public void removeWaveFromPauseTimer() {
			Timer t = new Timer(3000, removeWaveFromPauseListener);
			t.setRepeats(false);
			t.start();
		}
		
		@Override
		public void paint(Graphics g) {
			if(wave.isDeleteWave()) {
				disposeWave(g);
				return;
			}
			
			if(!this.waveGone) {
				controller.collisionWaveAnimal(wave);
				controller.collisionWavePowerUps(wave, powerUps);
				
				if(controller.getAnimal().isWaveHit()) {
					if(controller.isTutorialActive()) {
						wave.pauseWave();
						this.removeWaveFromPauseTimer();
					}
				}
				
				if ((wave.getLocation().getX() > frameMap.get(Frames.ANIMAL).getWidth()) && wave.isReceed() && wave.isLastWave()) {
					controller.fillWaterTile(wave);
					disposeWave(g);
				}
				
				else if((wave.getLocation().getX() > -150) && wave.getLocation().getX() < frame.getWidth()+frameMap.get(Frames.SHORE).getWidth()) {
					g.setColor(Color.BLUE);
					g.fillOval((int)wave.getBounds().getX(), (int)wave.getBounds().getY(), (int)wave.getBounds().getWidth(), (int)wave.getHeight());
				}
				
				else {
					disposeWave(g);
				}
			}
		}
	}

	public class Animal extends JComponent {
		@Override
		public void paint(Graphics g) {
			g.drawImage(controller.getAnimal().getGraphics().get("MOVE").get(controller.getAnimal().getGraphicOnDeck()), (int)controller.getAnimal().getBounds().getX(), (int) controller.getAnimal().getBounds().getY(), this);
		}
	}
	
	public class ShoreLine extends JComponent {
		@Override
		public void paint(Graphics g) {
			//g.setColor(Color.BLUE);
			
			g.drawImage(shoreGraphic, 0, 0, this);
			frame.revalidate();
			//g.fillRect(0, 0, frameMap.get(Frames.SHORE).getWidth(), frameMap.get(Frames.SHORE).getHeight());
		}
	}
	
	public class SandWater extends JComponent {
		private GridBlock grid;
		public SandWater(Pair pair) {
			this.grid = controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(pair));
		}
		@Override
		public void paint(Graphics g) {
			
			
			//g.drawString(coords, this.getWidth()/2, this.getHeight()/2);
			if(grid.getWater().isActive() == false) {
				g.drawImage(grid.getSandGraphic(),0, 0, this);
				//g.setColor(Color.YELLOW);
				//g.fillRect(0, 0, frame.getContentPane().getComponent(0).getWidth(), frame.getContentPane().getComponent(0).getHeight());
			}
			else{
				//System.out.println("View's idea of where tidal pool is: (" + grid.getLocation().getX()+","+grid.getLocation().getY()+")");
				g.drawImage(grid.getWater().getWaterGraphics().get(grid.getWater().getGraphicOnDeck()),0, 0, this);
				
				//g.setColor(Color.BLUE);
				//g.fillRect(0, 0, frame.getContentPane().getComponent(0).getWidth(), frame.getContentPane().getComponent(0).getHeight());
			}
			
		}
	}
	
	public class GridTile extends JComponent {
		private GridBlock gridBlock;
		public GridTile(Pair pair) {
			gridBlock = controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(pair));
		}
		public GridBlock getGridBlock() {
			return gridBlock;
		}
		
		public void drawArrow(Graphics g) {
			if(controller.isTutorialActive() && gridBlock.getGabPU().getIsActive()) {
				g.drawImage(controller.getTutorial().getGraphicMap().get(AnimGraphics.ARROW).get(0),(int)gridBlock.getGabPU().getBounds().getX(), (int)gridBlock.getGabPU().getBounds().getY()+40, this);
			}
		}
		
		@Override
		public void paint(Graphics g) {
			drawArrow(g);
			if(gridBlock.getConcrPU().getIsActive()) {
				//g.setColor(Color.RED);
				//g.fillRect((int)gridBlock.getConcrPU().getBounds().getX(), (int)gridBlock.getConcrPU().getBounds().getY(), (int) gridBlock.getConcrPU().getBounds().getWidth(), (int) gridBlock.getConcrPU().getBounds().getHeight());
				if(gridBlock.getConcrPU().isPickedUp()){
					g.drawImage(gridBlock.getConcrPU().getGraphics().get(ConcPUState.WALL).get(0),(int)gridBlock.getConcrPU().getBounds().getX(), (int)gridBlock.getConcrPU().getBounds().getY(), this);
					int potentialX = controller.getAnimal().getLocX() + controller.getAnimal().getSpeedX();
					int potentialY = controller.getAnimal().getLocY() + controller.getAnimal().getSpeedY();
					Rectangle potentialAnimBounds = new Rectangle(potentialX, potentialY, controller.getAnimal().getWidth(), controller.getAnimal().getHeight());
					if(potentialAnimBounds.intersects(gridBlock.getConcrPU().getBounds())) {
						controller.getAnimal().setWallHit(true);
					}
					else {
						controller.getAnimal().setWallHit(false);
					}
				}
				else{
					g.drawImage(gridBlock.getConcrPU().getGraphics().get(ConcPUState.POWER_UP).get(0),(int)gridBlock.getConcrPU().getBounds().getX(), (int)gridBlock.getConcrPU().getBounds().getY(), this);
				}
			}
			
			else if(gridBlock.getGabPU().getIsActive()) {
				//g.setColor(Color.RED);
				//g.fillRect((int)gridBlock.getGabPU().getBounds().getX(), (int)gridBlock.getGabPU().getBounds().getY(), (int) gridBlock.getGabPU().getBounds().getWidth(), (int) gridBlock.getGabPU().getBounds().getHeight());
				if(gridBlock.getGabPU().isPickedUp()){
					g.drawImage(gridBlock.getGabPU().getGraphics().get(GabPUState.WALL).get(0),(int)gridBlock.getGabPU().getBounds().getX(), (int)gridBlock.getGabPU().getBounds().getY(), this);
					int potentialX = controller.getAnimal().getLocX() + controller.getAnimal().getSpeedX();
					int potentialY = controller.getAnimal().getLocY() + controller.getAnimal().getSpeedY();
					Rectangle potentialAnimBounds = new Rectangle(potentialX, potentialY, controller.getAnimal().getWidth(), controller.getAnimal().getHeight());
					if(potentialAnimBounds.intersects(gridBlock.getGabPU().getBounds())) {
						controller.getAnimal().setWallHit(true);
					}
					else {
						controller.getAnimal().setWallHit(false);
					}
				}
				else{
					g.drawImage(gridBlock.getGabPU().getGraphics().get(GabPUState.POWER_UP).get(0),(int)gridBlock.getGabPU().getBounds().getX(), (int)gridBlock.getGabPU().getBounds().getY(), this);
				}
			}
		}
	}
	
	public void generateWaveCluster(boolean isTutorial, int clusterVal) {

		int randCluster;
		if(isTutorial) {
			randCluster = clusterVal;
		}
		else {
			System.out.println("Generating non-tutorial clusters");
			randCluster = WaveClusters.CLUSTER_ONE.getWaveID() + (int)(Math.random() * ((WaveClusters.CLUSTER_FIVE.getWaveID() - WaveClusters.CLUSTER_ONE.getWaveID()) + 1));
		}
		for(int i = 0; i < 250; i++) {
			WaveModel wave = new WaveModel(randCluster, frameMap);
			if(i == 249) {
				wave.setLastWave(true);
			}
			addWave(wave, randCluster);
		}
	}
	
	public void addWave(WaveModel w, int clusterVal) {
		WaveModel waveM = w;
		waveM.randomSpawn(clusterVal);
		Wave wave = new Wave(waveM);
		wave.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		waveComponentMap.put(wave.hashCode(), wave);
		this.layoutContainer.add(wave, new Integer(2), 1);
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) {
	        case KeyEvent.VK_UP:
	        	controller.getAnimal().setCurrDir(Direction.NORTH);
	        	controller.getAnimal().setSpeedY(-3);
	            break;
	        case KeyEvent.VK_DOWN:
	        	controller.getAnimal().setCurrDir(Direction.SOUTH);
	        	controller.getAnimal().setSpeedY(3);
	            break;
	        case KeyEvent.VK_LEFT:
	        	controller.getAnimal().setCurrDir(Direction.WEST);
	        	controller.getAnimal().setSpeedX(-3);
	            break;
	        case KeyEvent.VK_RIGHT :
	        	controller.getAnimal().setCurrDir(Direction.EAST);
	        	controller.getAnimal().setSpeedX(3);
	            break;
	        case KeyEvent.VK_SPACE :
	        	//System.out.println("This is a temp key event to end the game (set bool gameActive in controller to false)");
	        	//controller.setGameActive(false);
	        	//controller.setGameWin(false);
	        	//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	            break;
	    }
	}
	

	public Game3Controller getController() {
		return controller;
	}
	public void setController(Game3Controller controller) {
		this.controller = controller;
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
	    switch( keyCode ) {
	        case KeyEvent.VK_UP:
	            // handle up 
	        	controller.getAnimal().setSpeedY(0);
	            break;
	        case KeyEvent.VK_DOWN:
	            // handle down 
	        	controller.getAnimal().setSpeedY(0);
	            break;
	        case KeyEvent.VK_LEFT:
	            // handle left
	        	controller.getAnimal().setSpeedX(0);
	            break;
	        case KeyEvent.VK_RIGHT :
	            // handle right
	        	controller.getAnimal().setSpeedX(0);
	            break;
	    }

	
	}

	 


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public HashMap getWaveComponentMap() {
		
		return waveComponentMap;
	}

	public JPanel getTimePanel() {
		return timePanel;
	}


	public void setTimePanel(JPanel timePanel) {
		this.timePanel = timePanel;
	}

	public HashMap<Frames, JComponent> getLayoutContainerComps() {
		return frameMap;
	}



	public void setLayoutContainerComps(HashMap<Frames, JComponent> layoutContainerComps) {
		this.frameMap = layoutContainerComps;
	}



	public HashMap<Frames, JComponent> getFrameMap() {
		return frameMap;
	}



	public void setFrameMap(HashMap<Frames, JComponent> frameMap) {
		this.frameMap = frameMap;
	}
	
	public void brightenSky() {
		this.setBrightLevel(this.getBrightLevel()-1);
		if((this.getSkyColor().getBlue()+4) < 256) {
			this.setSkyColor(new Color((int)((this.getSkyColor().getBlue()*0.4)),(int)((this.getSkyColor().getBlue()*(.698))),(int)this.getSkyColor().getBlue()+4,(int)this.getBrightLevel()));
		}
		
		
		this.getTimePanel().setBackground(this.getSkyColor());
	}
	
	public void resetSky() {
		setSkyColor(new Color(0,0,0));
	}


	public Color getSkyColor() {
		return skyColor;
	}


	public void setSkyColor(Color skyColor) {
		this.skyColor = skyColor;
	}


	public int getBrightLevel() {
		return brightLevel;
	}


	public void setBrightLevel(int brightLevel) {
		this.brightLevel = brightLevel;
	}

	public void startEndScreen(boolean gameWin) {
		endScreen = new JLabel();
		endScreen.setLayout(new GridBagLayout());
		//Defining constraint for background
		ImageIcon backgroundIcon = new ImageIcon("./Images/2D_estuary.jpg"); 
		endScreen.setIcon(backgroundIcon);
		System.out.println(frame.getWidth() + " " + frame.getHeight());
		endScreen.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		
		//Creating menu button
		BufferedImage returnMain_0 = null;
		try {
			returnMain_0 = ImageIO.read(new File("./Images/Game3/returnMain_0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menuButton = new JButton(new ImageIcon(returnMain_0));
		menuButton.setName("menu");
		menuButton.setBorder(BorderFactory.createEmptyBorder());
		menuButton.setContentAreaFilled(false);
		menuButton.setPreferredSize(new Dimension(returnMain_0.getWidth(), returnMain_0.getHeight()));
		GridBagConstraints b1c = new GridBagConstraints();
		b1c.gridx = 0;
		b1c.gridy = 1;
		b1c.weightx = .1;
		b1c.weighty = 0.1;
		menuButton.addMouseListener(this);
		endScreen.add(menuButton, b1c);
		
		
		//Creating exit button
		BufferedImage exitGame_0 = null;
		
		try {
			exitGame_0 = ImageIO.read(new File("./Images/Game3/exitGame_0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JButton exitButton = new JButton(new ImageIcon(exitGame_0));
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.setName("exit");
		exitButton.addMouseListener(this);
		exitButton.setPreferredSize(new Dimension((int)(exitGame_0.getWidth()),exitGame_0.getHeight()));
		GridBagConstraints b2c = new GridBagConstraints();
		b2c.gridx = 2;
		b2c.gridy = 1;
		b2c.weightx = .1;
		b2c.weighty = .1;
		endScreen.add(exitButton, b2c);

		
		//Creating message
		JLabel resultMessage = new JLabel();
		ImageIcon resultIcon;
		if(gameWin) {
			resultIcon = new ImageIcon("./Images/Game3/gameWin.png");
		}
		else {
			resultIcon = new ImageIcon("./Images/Game3/gameLose.png");
		}
		resultMessage.setIcon(resultIcon);
		GridBagConstraints rmC = new GridBagConstraints();
		
		rmC.gridx = 1;
		rmC.gridy = 0;
		rmC.weighty = .05;
		rmC.weightx = .5;
		endScreen.add(resultMessage, rmC);

		frame.getContentPane().removeAll();
		frame.add(endScreen);
		frame.revalidate();
		frame.repaint();
	}
	
	public class ReturnToMain implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			BufferedImage returnMain_1 = null;
			try {
				returnMain_1 = ImageIO.read(new File("./Images/Game3/returnMain_1.png"));
			} catch (IOException excep) {
				excep.printStackTrace();
			}
			JButton button = (JButton) e.getSource();
			button.setIcon(new ImageIcon(returnMain_1));
		}
	}
	
	public class ExitGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			BufferedImage exitGame_1 = null;
			try {
				exitGame_1 = ImageIO.read(new File("./Images/Game3/exitGame_1.png"));
			} catch (IOException excep) {
				excep.printStackTrace();
			}
			JButton button = (JButton) e.getSource();
			button.setIcon(new ImageIcon(exitGame_1));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if(button.getName() == "exit") {
			button.setIcon(new ImageIcon(this.getExitGamePics().get(1)));
		}
		
		else {
			button.setIcon(new ImageIcon(this.getMainMenuPics().get(1)));
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if(button.getName() == "exit") {
			button.setIcon(new ImageIcon(this.getExitGamePics().get(0)));
			//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSED));
			//frame.dispose();
			setExitGame(true);
			return;
					
		}
		else {
			button.setIcon(new ImageIcon(this.getMainMenuPics().get(0)));
			setExitToMain(true);
			frame.dispose();
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public ArrayList<BufferedImage> getMainMenuPics() {
		return mainMenuPics;
	}


	public void setMainMenuPics(ArrayList<BufferedImage> mainMenuPics) {
		this.mainMenuPics = mainMenuPics;
	}


	public ArrayList<BufferedImage> getExitGamePics() {
		return exitGamePics;
	}


	public void setExitGamePics(ArrayList<BufferedImage> exitGamePics) {
		this.exitGamePics = exitGamePics;
	}


	public boolean isExitToMain() {
		return exitToMain;
	}


	public void setExitToMain(boolean exitToMain) {
		this.exitToMain = exitToMain;
	}


	public boolean isExitGame() {
		return exitGame;
	}


	public void setExitGame(boolean exitGame) {
		this.exitGame = exitGame;
	}
}