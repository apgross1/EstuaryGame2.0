package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.OverlayLayout;
import javax.swing.border.Border;

import controller.Game3Controller;
import enums.Direction;
import enums.Waves;
import models.BeachModel;
import models.ConcretePUModel;
import models.GabionPUModel;
import models.GridBlock;
import models.Pair;
import models.SunHurricaneModel;
import models.WaterModel;
import models.WaveModel;

public class Game3View extends JPanel implements KeyListener{
	private Game3Controller controller;
	private HashMap<Integer, Wave> componentMap;
	private JFrame frame;
	private JPanel timePanel = new JPanel();
	private ArrayList<GridTile> powerUps;
	
	
	private JPanel play_ground = new JPanel(new BorderLayout());
	JLayeredPane layoutContainer = new JLayeredPane();

	public Game3View(Game3Controller ctl, JFrame gameF){
		frame = gameF;
		timePanel.setLayout(null);
		controller = ctl;
		componentMap = new HashMap<Integer,Wave>();
    	frame.setBackground(Color.gray);

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(1090, 700);
    	frame.setResizable(false);
    	
		play_ground.setSize(1090, 700);
		play_ground.setBackground(Color.WHITE);
		
		powerUps = new ArrayList<GridTile>();
    	
		
    	//Panes
		//For animal movement
		
		Animal animalPane = new Animal();
		animalPane.setPreferredSize(new Dimension(1090,700));
		JPanel beachGrid = new JPanel(new GridLayout(7,7));

		
		
		Collection<Pair> blocks = controller.getBeach().getOrderedPairs();
		Iterator<Pair> it = blocks.iterator();
		while(it.hasNext()) {
			Pair currBlock = it.next();
			JPanel beachOverlay = new JPanel();
			beachOverlay.setLayout(new OverlayLayout(beachOverlay));
			
			SandWater gridBlock = new SandWater(currBlock);
			GridTile powerUp = new GridTile(currBlock);
			powerUps.add(powerUp);
		    powerUp.setBounds((int)controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(currBlock)).getBounds().getX(), (int)controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(currBlock)).getBounds().getY(), 835, 605);
			layoutContainer.add(powerUp, new Integer(2),-1);
		    beachOverlay.add(gridBlock);
		    //beachOverlay.setBorder(BorderFactory.createLineBorder(Color.black));
		    beachGrid.add(beachOverlay);
		    
		}
		

		timePanel.setPreferredSize(new Dimension(frame.getWidth(),200));
		timePanel.setBackground(Color.CYAN);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setPreferredSize(new Dimension(200,200));
		
		
		ShoreLine water = new ShoreLine();
		
		
		water.setPreferredSize(new Dimension(100,frame.getHeight()));
		water.setVisible(true);
		beachGrid.setBounds(0, 0, 1090, 700);
		animalPane.setBounds(0, 0, 1090, 700);
		layoutContainer.add(beachGrid, new Integer(1),0);
		layoutContainer.add(animalPane, new Integer(2), 1);
		play_ground.add(timePanel, BorderLayout.NORTH);
		play_ground.add(water, BorderLayout.EAST);
		play_ground.add(layoutContainer, BorderLayout.CENTER);
		
		

		frame.add(play_ground);
		frame.setVisible(true);
    	
    	//addKeyListener
    	frame.addKeyListener(this);
    	frame.pack();
	}
 
	
	
	public void addSun() {
		System.out.println("Sun spawned");
		Sun sun = new Sun(controller.getSun());
		sun.setBounds(0, 0, 1090, 700);
		sun.setVisible(true);
		timePanel.add(sun);
		timePanel.revalidate();
		frame.revalidate();
		
	}
	
	public void addHurricane() {
		System.out.println("Hurricane spawned");
		Hurricane hurricane = new Hurricane(controller.getHurricane());
		hurricane.setBounds(0, 0, 1090, 700);
		hurricane.setVisible(true);
		timePanel.add(hurricane);
		timePanel.revalidate();
		frame.revalidate();
		
	}

	public void repaintAll(){
		frame.repaint();
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
			g.setColor(Color.GREEN);
			g.fillOval(hurricane.getLocation().getX(), hurricane.getLocation().getY(), hurricane.getWidth(), hurricane.getHeight());
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
			g.setColor(Color.YELLOW);
			g.fillOval(sun.getLocation().getX(), sun.getLocation().getY(), sun.getWidth(), sun.getHeight());
		}
	}
	
	
	public class Wave extends JComponent {
		public WaveModel wave;
		public boolean waveGone;
		public Wave(WaveModel wave) {
			this.wave = wave;
			waveGone = false;
		}
		@Override
		public void paint(Graphics g) {
			if(!this.waveGone) {
				if(wave.getBounds().intersects(controller.getAnimal().getBounds())) {
					controller.setGameActive(false);
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
				
				if((wave.getLocation().getX() > -150) && wave.getLocation().getX() < 2500) {
					g.setColor(Color.BLUE);
					g.fillOval((int)wave.getBounds().getX(), (int)wave.getBounds().getY(), (int)wave.getBounds().getWidth(), (int)wave.getHeight());
				}
				
				else if ((wave.getLocation().getX() > 950) && wave.isReceed() && wave.isLastWave()) {
					List<Pair> pairs = controller.getBeach().getGridLayers().get(wave.getClusterGroup());
					for(int i = pairs.size()-1; i >= 0; i--) {
						if(controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(pairs.get(i))) != null) {
							if(controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(pairs.get(i))).isVacant()) {
								controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(pairs.get(i))).setWater(new WaterModel(), controller.getBeach().findPairInGrid(pairs.get(i)));
								/*if(i >= 1) {
									System.out.println("Made it here!");
									controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(pairs.get(i-1))).setWater(new WaterModel(), controller.getBeach().findPairInGrid(pairs.get(i-1))); 
								}*/
								
								
								layoutContainer.remove(componentMap.get(this.hashCode()));
								componentMap.remove(this.hashCode());
								wave = null;
								this.waveGone = true;
								return;
							}
							
						}
						
					}
				}
				else {
					layoutContainer.remove(componentMap.get(this.hashCode()));
					componentMap.remove(this.hashCode());
					wave = null;
					this.waveGone = true;
				}
			}
		}
	}
	
	public class Animal extends JComponent {
		@Override
		public void paint(Graphics g) {
			g.setColor(Color.MAGENTA);
			g.fillRect((int)controller.getAnimal().getBounds().getX(),(int) controller.getAnimal().getBounds().getY(),(int) controller.getAnimal().getBounds().getWidth(), (int)controller.getAnimal().getBounds().getHeight());
		
			
		}
	}
	
	public class ShoreLine extends JComponent {
		@Override
		public void paint(Graphics g) {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 1000, 700);
		}
	}
	
	public class SandWater extends JComponent {
		private GridBlock grid;
		public SandWater(Pair pair) {
			this.grid = controller.getBeach().getBeachGrid().get(controller.getBeach().findPairInGrid(pair));
		}
		@Override
		public void paint(Graphics g) {
			String coords = "("+grid.getViewLocation().getX()+","+grid.getViewLocation().getY()+")";
			//g.drawString(coords, this.getWidth()/2, this.getHeight()/2);
			if(grid.getWater().isActive() == false) {
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0, frame.getContentPane().getComponent(0).getWidth(), frame.getContentPane().getComponent(0).getHeight());
			}
			else{
				g.setColor(Color.BLUE);
				g.fillRect(0, 0, frame.getContentPane().getComponent(0).getWidth(), frame.getContentPane().getComponent(0).getHeight());
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
		@Override
		public void paint(Graphics g) {
			if(gridBlock.getConcrPU().getIsActive()) {
				g.setColor(Color.RED);
				g.fillRect((int)gridBlock.getConcrPU().getBounds().getX(), (int)gridBlock.getConcrPU().getBounds().getY(), (int) gridBlock.getConcrPU().getBounds().getWidth(), (int) gridBlock.getConcrPU().getBounds().getHeight());
			}
			
			else if(gridBlock.getGabPU().getIsActive()) {
				g.setColor(Color.DARK_GRAY);
				g.fillRect((int)gridBlock.getGabPU().getBounds().getX(), (int)gridBlock.getGabPU().getBounds().getY(), (int) gridBlock.getGabPU().getBounds().getWidth(), (int) gridBlock.getGabPU().getBounds().getHeight());
			}
		}
	}
	
	public void generateWaveCluster() {

		int randCluster = Waves.CLUSTER_ONE.getWaveID() + (int)(Math.random() * ((Waves.CLUSTER_SEVEN.getWaveID() - Waves.CLUSTER_ONE.getWaveID()) + 1));
		for(int i = 0; i < 250; i++) {
			WaveModel wave = new WaveModel(randCluster);
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
		wave.setBounds(0, 0, 1090, 700);
		componentMap.put(wave.hashCode(), wave);
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
		
		return componentMap;
	}

	public JPanel getTimePanel() {
		return timePanel;
	}


	public void setTimePanel(JPanel timePanel) {
		this.timePanel = timePanel;
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