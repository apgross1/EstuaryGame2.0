package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Game1Controller;
import enums.Direction;

public class Game1View extends JPanel implements KeyListener{
	private int damageLevel;
	private Game1Controller controller;
	private JFrame frame = new JFrame();
	//private JPanel action_pannel = new JPanel();

    final static int frameWidth = 1000;
    final static int frameHeight = 1000;
	
	public Game1View(Game1Controller ctl){
		controller = ctl;
    	frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	
    	//addKeyListener
    	frame.addKeyListener(this);
	}
 
	
	public void repaintFrame(){
		frame.repaint();
	}
	
	 public class Animation extends JComponent {
			@Override
			public void paint(Graphics g) {
				g.drawRect(controller.getAnimalModel().getLocX(),controller.getAnimalModel().getLocY(),controller.getAnimalModel().getWidth(),controller.getAnimalModel().getHeight());
				//g.drawRect(x,y,w,h);


			}
	    }
	 
	 @Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Key Event occured");
		    int keyCode = e.getKeyCode();
		    switch( keyCode ) {
		        case KeyEvent.VK_UP:
		            // handle up 
		        	if(controller.getAnimalModel().getCurrDir() != Direction.NORTH){
		        		controller.getAnimalModel().setCurrDir(Direction.NORTH);
		        	}
		        	controller.getAnimalModel().move();
		            break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		        	if(controller.getAnimalModel().getCurrDir() != Direction.SOUTH){
		        		controller.getAnimalModel().setCurrDir(Direction.SOUTH);
		        	}
		        	controller.getAnimalModel().move();
		            break;
		        case KeyEvent.VK_LEFT:
		            // handle left
		        	if(controller.getAnimalModel().getCurrDir() != Direction.WEST){
		        		controller.getAnimalModel().setCurrDir(Direction.WEST);
		        	}
		        	controller.getAnimalModel().move();
		            break;
		        case KeyEvent.VK_RIGHT :
		            // handle right
		        	if(controller.getAnimalModel().getCurrDir() != Direction.EAST){
		        		controller.getAnimalModel().setCurrDir(Direction.EAST);
		        	}
		        	controller.getAnimalModel().move();
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
