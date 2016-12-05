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
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


public class Game3View extends JPanel implements KeyListener{
	private Game3Controller controller;
	private HashMap<Integer, Wave> waveComponentMap;
	private HashMap<Frames, JComponent> frameMap;
	private JFrame frame;
	private JPanel timePanel = new JPanel();
	private ArrayList<GridTile> powerUps;
	private JPanel play_ground = new JPanel(new BorderLayout());
	private JLayeredPane layoutContainer = new JLayeredPane();
	private BufferedImage shoreGraphic;
	
	public JLabel animalPos;
	public int brightLevel;
	public Color skyColor; 

	
	public Game3View(Game3Controller ctl, JFrame gameF){
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
		
		
		
		//frame.setSize(this.getFrameHeight(), frame.getWidth());
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
		    beachOverlay.setBorder(BorderFactory.createLineBorder(Color.black));
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

		
		timePanel.add(animalPos);
		animalPos.setVisible(true);
		timePanel.revalidate();
		frame.revalidate();
		
	
	}
 
	
	public void updateLoc() {
		animalPos.setText("Animal is on tile: (" + this.getController().getAnimal().getPotentialMove().getX() + "," + this.controller.getAnimal().getPotentialMove().getY() + ")");
	}
	
	
	public void addSun() {
		Sun sun = new Sun(controller.getSun());
		sun.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		sun.setVisible(true);
		timePanel.add(sun);
		timePanel.revalidate();
		frame.revalidate();
		
	}
	
	public void addHurricane() {
		System.out.println("Hurricane spawned");
		Hurricane hurricane = new Hurricane(controller.getHurricane());
		hurricane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		hurricane.setVisible(true);
		timePanel.add(hurricane);
		timePanel.revalidate();
		frame.revalidate();
		
	}

	public void repaintAll(){
		frame.repaint();
	}
	
	public class TimerGradient extends JComponent {
		@Override
		public void paint(Graphics g) {
        g.fillRect(0, 0, timePanel.getWidth(), timePanel.getHeight());
        g.setColor(Color.BLACK);
        System.out.println("Painting");
		}
	}
	
	
	public class JTutorial extends JComponent {

		@Override
		public void paint(Graphics g) {
			if(controller.isTutorialActive()) {
				drawKeyboard(g);
				drawX(g);
				drawDialogue(g);
			}
			
		}
		
		public void drawKeyboard(Graphics g) {
			if(!controller.getTutorial().isKeyboardStop()) {
				g.drawImage((controller.getTutorial().getGraphicMap().get(AnimGraphics.KEYBOARD).get(controller.getTutorial().getKeyBoardPicOnDeck())), (int)(frame.getWidth()*.60), (int)(frame.getHeight()*.40), this);
			}
		}
		
		public void drawX(Graphics g) {
			if(controller.getAnimal().isWaveHit()) {
				g.drawImage((controller.getTutorial().getGraphicMap().get(AnimGraphics.BIG_X).get(0)), controller.getAnimal().getLocX(),controller.getAnimal().getLocY(), this);
			}
		}
		
		public void drawDialogue(Graphics g) {
			if(controller.getTutorial().isDialogueOn()) {
				g.drawImage((controller.getTutorial().getGraphicMap().get(AnimGraphics.DIALOGUE).get(0)), (int)(frameMap.get(Frames.ANIMAL).getWidth()*.6), (int)(frameMap.get(Frames.ANIMAL ).getHeight()*.30), this);
			}
		}
		
	
	}
	
	public class Hurricane extends JComponent {
		SunHurricaneModel hurricane;
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
	
	public class Sun extends JComponent {
		SunHurricaneModel sun;
		public Sun(SunHurricaneModel s) {
			sun = s;
			sun.getLocation().setX(sun.getPanel().getWidth());
			sun.getLocation().setY(-200);
		}
		@Override
		public void paint(Graphics g) {
			//g.setColor(Color.YELLOW);
			//g.fillOval(sun.getLocation().getX(), sun.getLocation().getY(), sun.getWidth(), sun.getHeight());
			g.drawImage(sun.getGraphics().get("SUN").get(0), sun.getLocation().getX(), sun.getLocation().getY(), this);
		}
	}
	
	
	public class Wave extends JComponent {
		public WaveModel wave;
		public boolean waveGone;
		public Wave(WaveModel wave) {
			this.wave = wave;
			waveGone = false;
			setOpaque(false);
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
				g.dispose();
				layoutContainer.remove(waveComponentMap.get(this.hashCode()));
				waveComponentMap.remove(this.hashCode());
				this.waveGone = true;
				frame.revalidate();
				controller.getAnimal().setWaveHit(false);
				return;
			}
			
			if(!this.waveGone) {
				if(wave.getBounds().intersects(controller.getAnimal().getBounds())) {
					if(controller.isTutorialActive()) {
						controller.getAnimal().setWaveHit(true);
					}
					else {
						controller.getAnimal().setWaveHit(true);
						//controller.setGameActive(false);
					}
					
					return;
				}
				
				if(controller.getAnimal().isWaveHit()) {
					if(controller.isTutorialActive()) {
						wave.pauseWave();
						removeWaveFromPauseTimer();
					}
				}
				
				
				
				for(GridTile gr : powerUps) {
					ConcretePUModel conc = gr.getGridBlock().getConcrPU();
					GabionPUModel gab = gr.getGridBlock().getGabPU();
					if(conc.getIsActive() & conc.isPickedUp()) {
						if(conc.getBounds().intersects(wave.getBounds())) {
							wave.setReceed(true);
						}
					}
					else if (gab.getIsActive() & gab.isPickedUp()) {
						if(gab.getBounds().intersects(wave.getBounds())) {
							wave.setReceed(true);
						}
					}
					else if(wave.getBounds().getX() < 10) {
						wave.setReceed(true);
					}
				}
				
				
				
				if ((wave.getLocation().getX() > frameMap.get(Frames.ANIMAL).getWidth()) && wave.isReceed() && wave.isLastWave()) {
					List<Pair> pairs = controller.getBeach().getGridLayers().get(wave.getClusterGroup());
					System.out.println("Printing out cluster group: "); 
					Iterator it = pairs.iterator();
					while(it.hasNext()) {
						Pair tempPair = (Pair)it.next();
						System.out.println("("+tempPair.getX()+","+tempPair.getY()+")");
					}
					for(int i = pairs.size()-1; i >= 0; i--) {
						GridBlock tempGrid = controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(pairs.get(i)));
						if(tempGrid != null) {
							if(!tempGrid.getWater().isActive()) {
								if(tempGrid.getGabPU().getIsActive()) {
									tempGrid.getGabPU().setIsActive(false);
								}
								if(tempGrid.getConcrPU().getIsActive()) {
									tempGrid.getConcrPU().setActive(false);
								}
								
								if(i != pairs.size()-1) {
									controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(pairs.get(i+1))).getWater().setGraphicOnDeck(1);
								}
								WaterModel newWatMod = new WaterModel();
								newWatMod.addPics();
								tempGrid.setWater(newWatMod, controller.getBeach().findPairInGrid(pairs.get(i)));
					
								layoutContainer.remove(waveComponentMap.get(this.hashCode()));
								waveComponentMap.remove(this.hashCode());
								wave = null;
								this.waveGone = true;
								g.dispose();
								frame.revalidate();
								return;
							}
							
						}
						
					}
					
					layoutContainer.remove(waveComponentMap.get(this.hashCode()));
					waveComponentMap.remove(this.hashCode());
					wave = null;
					this.waveGone = true;
					g.dispose();
					frame.revalidate();
				}
				
				//I don't think the -150 has to be dynamic. It's off the screen for all monitors, so it shouldn't' be an issue
				else if((wave.getLocation().getX() > -150) && wave.getLocation().getX() < frame.getWidth()+frameMap.get(Frames.SHORE).getWidth()) {
					if(wave.isLastWave()){
						g.setColor(Color.green);
						g.fillOval((int)wave.getBounds().getX(), (int)wave.getBounds().getY(), (int)wave.getBounds().getWidth(), (int)wave.getHeight());
						
					}
					
						g.setColor(Color.BLUE);
						g.fillOval((int)wave.getBounds().getX(), (int)wave.getBounds().getY(), (int)wave.getBounds().getWidth(), (int)wave.getHeight());
				}
				
				else {
					layoutContainer.remove(waveComponentMap.get(this.hashCode()));
					waveComponentMap.remove(this.hashCode());
					wave = null;
					this.waveGone = true;
					g.dispose();
					frame.revalidate();
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
					g.drawImage(gridBlock.getConcrPU().getGraphics().get(ConcPUState.WALL).get(0),(int)gridBlock.getConcrPU().getBounds().getX(), (int)gridBlock.getConcrPU().getBounds().getY(), Color.yellow, this);
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
					g.drawImage(gridBlock.getConcrPU().getGraphics().get(ConcPUState.POWER_UP).get(0),(int)gridBlock.getConcrPU().getBounds().getX(), (int)gridBlock.getConcrPU().getBounds().getY(), Color.yellow, this);
				}
			}
			
			else if(gridBlock.getGabPU().getIsActive()) {
				//g.setColor(Color.RED);
				//g.fillRect((int)gridBlock.getGabPU().getBounds().getX(), (int)gridBlock.getGabPU().getBounds().getY(), (int) gridBlock.getGabPU().getBounds().getWidth(), (int) gridBlock.getGabPU().getBounds().getHeight());
				if(gridBlock.getGabPU().isPickedUp()){
					g.drawImage(gridBlock.getGabPU().getGraphics().get(GabPUState.WALL).get(0),(int)gridBlock.getGabPU().getBounds().getX(), (int)gridBlock.getGabPU().getBounds().getY(), Color.yellow, this);
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
					g.drawImage(gridBlock.getGabPU().getGraphics().get(GabPUState.POWER_UP).get(0),(int)gridBlock.getGabPU().getBounds().getX(), (int)gridBlock.getGabPU().getBounds().getY(), Color.yellow, this);
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
	        	System.out.println("This is a temp key event to end the game (set bool gameActive in controller to false)");
	        	controller.setGameActive(false);
	        	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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


	public HashMap getComponentMap() {
		
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


	public void activateTutorial() {
		
		
	}
	
	/*
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	*/
	
}