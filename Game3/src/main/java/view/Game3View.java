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
import java.util.Collection;
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
	private JFrame frame = new JFrame();
	//private JPanel action_pannel = new JPanel();
	private JPanel play_ground = new JPanel(new BorderLayout());
	JLayeredPane layoutContainer = new JLayeredPane();
    //final static int frameWidth = 800;
    //final static int frameHeight = 800;
	
	public Game3View(Game3Controller ctl){
		controller = ctl;

    	frame = new JFrame();
    	frame.setBackground(Color.gray);

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(1000, 700);
    	frame.setResizable(false);
    	
		play_ground.setSize(1000, 700);
		play_ground.setBackground(Color.WHITE);
		
		
    	
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
		    powerUp.setBounds(0, 0, 1000, 700);
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
		public Wave(WaveModel wave) {
			this.wave = wave;
		}
		@Override
		public void paint(Graphics g) {
			if(wave.getLocation().getX() > -5) {
				g.setColor(Color.BLUE);
				g.fillRect((int)wave.getBounds().getMaxX(), (int)wave.getBounds().getMaxY(), (int)wave.getBounds().getWidth(), (int)wave.getHeight());
			}
		}
	}
	
	public class Animal extends JComponent {
		@Override
		public void paint(Graphics g) {
			g.setColor(Color.MAGENTA);
			g.fillRect((int)controller.getAnimal().getBounds().getMaxX(),(int) controller.getAnimal().getBounds().getMaxY(),(int) controller.getAnimal().getBounds().getWidth(), (int)controller.getAnimal().getBounds().getHeight());
		
			
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
		@Override
		public void paint(Graphics g) {
			if(gridBlock.getConcrPU().getIsActive()) {
				g.setColor(Color.RED);
				g.fillRect(0+gridBlock.getConcrPU().getViewLocation().getX(), gridBlock.getConcrPU().getViewLocation().getY(), (int) gridBlock.getConcrPU().getBounds().getWidth(), (int) gridBlock.getConcrPU().getBounds().getHeight());
			}
			
			else if(gridBlock.getGabPU().getIsActive()) {
				g.setColor(Color.DARK_GRAY);
				g.fillRect(0+gridBlock.getGabPU().getViewLocation().getX(), gridBlock.getGabPU().getViewLocation().getY(), (int) gridBlock.getGabPU().getBounds().getWidth(), (int) gridBlock.getGabPU().getBounds().getHeight());
			}
		}
	}
	 
	public void addWave(WaveModel w) {
		WaveModel waveM = w;
		waveM.randomSpawn();
		Wave wave = new Wave(waveM);
		wave.setBounds(0, 0, 1000, 700);
		this.layoutContainer.add(wave, new Integer(2), 1);
	}
	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) {
	        case KeyEvent.VK_UP:
	            // handle up 
	        	if(controller.getAnimal().getCurrDir() != Direction.NORTH){
	        		controller.getAnimal().setCurrDir(Direction.NORTH);
	        	}
	        	if(controller.getAnimal().getLocY() > 0){
	        		controller.getAnimal().move();
	        	}
	            break;
	        case KeyEvent.VK_DOWN:
	            // handle down 
	        	if(controller.getAnimal().getCurrDir() != Direction.SOUTH){
	        		controller.getAnimal().setCurrDir(Direction.SOUTH);
	        	}
	        	if(controller.getAnimal().getLocY() < 541){
	        		controller.getAnimal().move();
	        	}
	            break;
	        case KeyEvent.VK_LEFT:
	            // handle left
	        	if(controller.getAnimal().getCurrDir() != Direction.WEST){
	        		controller.getAnimal().setCurrDir(Direction.WEST);
	        	}
	        	if(controller.getAnimal().getLocX() > 0){
	        		controller.getAnimal().move();
	        	}
	            break;
	        case KeyEvent.VK_RIGHT :
	            // handle right
	        	if(controller.getAnimal().getCurrDir() != Direction.EAST){
	        		controller.getAnimal().setCurrDir(Direction.EAST);
	        	}
	        	if(controller.getAnimal().getLocX() < 771){
	        		controller.getAnimal().move();
	        	}
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
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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