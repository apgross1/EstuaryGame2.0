package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import controller.Game1Controller;
import enums.Direction;
import models.ConcreteWallModelG1.ConcreteChunk;
import models.GabionWallModelG1.GabionChunk;

public class Game1View extends JPanel implements KeyListener{
	private int damageLevel;
	private Game1Controller controller;
	private JFrame frame = new JFrame();
	//private JPanel action_pannel = new JPanel();
	private JPanel bar_pannel = new JPanel();
	private JPanel play_ground = new JPanel();

    //final static int frameWidth = 800;
    //final static int frameHeight = 800;
	
	public Game1View(Game1Controller ctl){
		controller = ctl;

    	frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(1000, 700);
    	frame.setVisible(true);
    	frame.setResizable(false);
   
		bar_pannel.setSize(1000, 50);
		bar_pannel.setBackground(Color.BLACK);
		bar_pannel.setVisible(true);
    	
		play_ground.setSize(1000, 500);
		play_ground.setBackground(Color.BLUE);
		play_ground.setVisible(true);
		
    	
    	//Panes
    	JSplitPane view = new  JSplitPane();
    	view.setSize(1000, 550);
    	view.setDividerSize(5);
    	view.setDividerLocation(50);
    	view.setEnabled(false);
    	
    	view.setOrientation(JSplitPane.VERTICAL_SPLIT);
    	view.setTopComponent(bar_pannel);
    	view.setBottomComponent(play_ground);
    	
    	frame.add(view);
    	
    	//addKeyListener
    	frame.addKeyListener(this);
	}
 
	
	public void repaintFrame(){
		frame.repaint();
	}
	
	 public class Animation extends JComponent {
			@Override
			public void paint(Graphics g) {
				//Draw animal at current position
				g.fillRect(controller.getAnimalModel().getLocX(),controller.getAnimalModel().getLocY(),controller.getAnimalModel().getWidth(),controller.getAnimalModel().getHeight());
				
				//Draw score data and timer and health
				g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
				g.setColor(Color.WHITE);
				g.drawString("Gabion: " + controller.getGabionWallModel().getCurrentOysters(), 20, 25);
				g.setColor(Color.RED);
				g.drawString("Concrete: " + controller.getWallModel().getCurrentBlocks(), 200, 25);
				g.setColor(Color.GREEN);
				g.drawString("TIME LEFT: null", 700, 25);
				
				g.drawRect(400, 20, 200, 20); //Behind health bar doesnt change
				
				
				g.setColor(Color.RED);
				int health = (controller.getBarModel().getStatus()*2);
				g.fillRect(400, 20, health, 20);
				
				
				//Draw all the chunks that are active.
				Collection<ConcreteChunk> concreteChunkTemp = controller.getWallModel().getChunks();
				Collection<GabionChunk> GabionChunkTemp = controller.getGabionWallModel().getChunks();
				Iterator<GabionChunk> git = GabionChunkTemp.iterator();
				Iterator<ConcreteChunk> it = concreteChunkTemp.iterator();
				while(it.hasNext()){
					//
					ConcreteChunk tmp = it.next();
					if(tmp.isActive()){
						g.setColor(Color.RED);
						g.fillRect(tmp.getLocX(), tmp.getLocY(), 10, 10);
					}
				}
				while (git.hasNext()){
					GabionChunk tmp = git.next();
					if(tmp.isActive()){
						g.setColor(Color.WHITE);
						g.fillRect(tmp.getLocX(), tmp.getLocY(), 20, 10);
					}
				}
			}
	    }
	 
	 @Override
		public void keyPressed(KeyEvent e) {
			System.out.println("y: "+ controller.getAnimalModel().getLocY());
			System.out.println("x: "+ controller.getAnimalModel().getLocX());
		    int keyCode = e.getKeyCode();
		    switch( keyCode ) {
		        case KeyEvent.VK_UP:
		            // handle up 
		        	if(controller.getAnimalModel().getCurrDir() != Direction.NORTH){
		        		controller.getAnimalModel().setCurrDir(Direction.NORTH);
		        	}
		        	if(controller.getAnimalModel().getLocY() > 50){
		        		controller.getAnimalModel().move();
		        	}
		            break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		        	if(controller.getAnimalModel().getCurrDir() != Direction.SOUTH){
		        		controller.getAnimalModel().setCurrDir(Direction.SOUTH);
		        	}
		        	if(controller.getAnimalModel().getLocY() < 560){
		        		controller.getAnimalModel().move();
		        	}
		            break;
		        case KeyEvent.VK_LEFT:
		            // handle left
		        	if(controller.getAnimalModel().getCurrDir() != Direction.WEST){
		        		controller.getAnimalModel().setCurrDir(Direction.WEST);
		        	}
		        	if(controller.getAnimalModel().getLocX() > 0){
		        		controller.getAnimalModel().move();
		        	}
		            break;
		        case KeyEvent.VK_RIGHT :
		            // handle right
		        	if(controller.getAnimalModel().getCurrDir() != Direction.EAST){
		        		controller.getAnimalModel().setCurrDir(Direction.EAST);
		        	}
		        	if(controller.getAnimalModel().getLocX() < 885){
		        		controller.getAnimalModel().move();
		        	}
		            break;
		        case KeyEvent.VK_SPACE :
		        	System.out.println("This is a temp key event to end the game (set bool gameActive in controller to false)");
		        	controller.setGameState(false);
		            break;
		    }
		}


	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	public void sway(boolean gameStart) {
		
	}
	
	public void waveCrash(boolean timeUp) {
		
	}
	
	public void remove() {
		
	}
	
	public void wallState() {
		
	}
	
	public void destroyWall(int newCurrBlocks) {
		
	}
	
	public void buildWall(int newCurrBlocks) {
		
	}
	
	public void raiseLevel(int newStatus) {
		
	}
	
	public void lowerLevel(int newStatus) {
		
	}
	
	public void warningSign() {
		
	}
	
	public void pickUp(int newXLoc, int newYLoc) {
		
	}
	
	public void perish() {
		
	}
	
	public int getDamageLevel() {
		return damageLevel;
	}
	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}
	public Game1Controller getController() {
		return controller;
	}
	public void setController(Game1Controller controller) {
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
