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

import controller.Game3Controller;
import enums.Direction;
import models.BeachModel;
import models.ConcretePUModel;
import models.GabionPUModel;
import models.GridBlock;
import models.WaveModel;

public class Game3View extends JPanel implements KeyListener{
	private Game3Controller controller;
	private HashMap<Integer, Wave> componentMap;
	private JFrame frame = new JFrame();
	private ArrayList<GridTile> powerUps;
	
	private JPanel play_ground = new JPanel(new BorderLayout());
	JLayeredPane layoutContainer = new JLayeredPane();

	public Game3View(Game3Controller ctl){
		controller = ctl;
		componentMap = new HashMap<Integer,Wave>();
    	frame = new JFrame();
    	frame.setBackground(Color.gray);

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(1000, 700);
    	frame.setResizable(false);
    	
		play_ground.setSize(1000, 700);
		play_ground.setBackground(Color.WHITE);
		
		powerUps = new ArrayList<GridTile>();
    	
    	//Panes
		//For animal movement
		
		Animal animalPane = new Animal();
		animalPane.setPreferredSize(new Dimension(1000,700));
		JPanel beachGrid = new JPanel(new GridLayout(10,10));
		Collection<GridBlock> blocks = controller.getBeach().getBeachGrid().values();
		Iterator<GridBlock> it = blocks.iterator();
		while(it.hasNext()) {
			GridBlock currBlock = it.next();
			JPanel beachOverlay = new JPanel();
			beachOverlay.setLayout(new OverlayLayout(beachOverlay));
			
			SandWater gridBlock = new SandWater(currBlock);
			GridTile powerUp = new GridTile(currBlock);
			powerUps.add(powerUp);
		    powerUp.setBounds((int)currBlock.getBounds().getX(), (int)currBlock.getBounds().getY(), 835, 605);
			layoutContainer.add(powerUp, new Integer(2),-1);
		    beachOverlay.add(gridBlock);
		    
		    beachGrid.add(beachOverlay);
		}
		
		ShoreLine water = new ShoreLine();
		water.setPreferredSize(new Dimension(100,frame.getHeight()));
		water.setVisible(true);
		beachGrid.setBounds(0, 0, 1000, 700);
		animalPane.setBounds(0, 0, 1000, 700);
		layoutContainer.add(beachGrid, new Integer(1),0);
		layoutContainer.add(animalPane, new Integer(2), 1);
		
		play_ground.add(layoutContainer, BorderLayout.CENTER);
		play_ground.add(water, BorderLayout.EAST);
		frame.add(play_ground);
		frame.setVisible(true);
    	
    	//addKeyListener
    	frame.addKeyListener(this);
	}
 
	
	public void repaintAll(){
		frame.repaint();
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
					System.out.println("Hit!");
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
				
				if((wave.getLocation().getX() > -5) && wave.getLocation().getX() < 2000) {
					g.setColor(Color.BLUE);
					g.fillRect((int)wave.getBounds().getX(), (int)wave.getBounds().getY(), (int)wave.getBounds().getWidth(), (int)wave.getHeight());
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
		public SandWater(GridBlock g) {
			this.grid = g;
		}
		@Override
		public void paint(Graphics g) {
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
		public GridTile(GridBlock g) {
			gridBlock = g;
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

		int randCluster = 3 + (int)(Math.random() * ((9 - 3) + 1));
		System.out.println(randCluster);
		for(int i = 0; i < 500; i++) {
			WaveModel wave = new WaveModel(randCluster);
			addWave(wave, randCluster);
		}
	}
	
	public void addWave(WaveModel w, int clusterVal) {
		WaveModel waveM = w;
		waveM.randomSpawn(clusterVal);
		Wave wave = new Wave(waveM);
		wave.setBounds(0, 0, 1000, 700);
		componentMap.put(wave.hashCode(), wave);
		this.layoutContainer.add(wave, new Integer(2), 1);
	}
	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) {
	        case KeyEvent.VK_UP:
	        	controller.getAnimal().setCurrDir(Direction.NORTH);
	        	controller.getAnimal().setSpeedY(-1);
	            break;
	        case KeyEvent.VK_DOWN:
	        	controller.getAnimal().setCurrDir(Direction.SOUTH);
	        	controller.getAnimal().setSpeedY(1);
	            break;
	        case KeyEvent.VK_LEFT:
	        	controller.getAnimal().setCurrDir(Direction.WEST);
	        	controller.getAnimal().setSpeedX(-1);
	            break;
	        case KeyEvent.VK_RIGHT :
	        	controller.getAnimal().setCurrDir(Direction.EAST);
	        	controller.getAnimal().setSpeedX(1);
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

	/*
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	*/
	
}