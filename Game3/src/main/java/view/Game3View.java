package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import controller.Game3Controller;
import enums.Direction;
import models.ConcretePUModel;
import models.GabionPUModel;

public class Game3View extends JPanel implements KeyListener{
	private Game3Controller controller;
	private JFrame frame = new JFrame();
	//private JPanel action_pannel = new JPanel();
	private JPanel play_ground = new JPanel(new BorderLayout());

    //final static int frameWidth = 800;
    //final static int frameHeight = 800;
	
	public Game3View(Game3Controller ctl){
		controller = ctl;

    	frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(1000, 700);
    	frame.setVisible(true);
    	frame.setResizable(false);
    	
		play_ground.setSize(1000, 500);
		play_ground.setBackground(Color.WHITE);
		play_ground.setVisible(true);
		
		
    	
    	//Panes
		play_ground.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		JPanel beachGrid = new JPanel(new GridLayout(10,10));
		JPanel waterGrid = new JPanel(new GridLayout(10,1));
		for (int i =0; i<(100); i++){
		    final JLabel label = new JLabel();
		    label.setText("Beach");
		    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    beachGrid.add(label);
		}
		for (int i =0; i<(10); i++){
		    final JLabel label = new JLabel();
		    label.setText("Water");
		    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    waterGrid.add(label);
		}
		play_ground.add(waterGrid, BorderLayout.EAST);
		play_ground.add(beachGrid, BorderLayout.CENTER);
		frame.add(play_ground);
		
		
		
    	//frame.add();
    	
    	//addKeyListener
    	frame.addKeyListener(this);
	}
 
	
	public void repaintFrame(){
		frame.repaint();
	}
	
	 public class Animation extends JComponent {
			@Override
			public void paint(Graphics g) {
				g.drawRect(50, 100, 30, 20);
				
			}
	 }
	 
	 
	/* @Override
		public void keyPressed(KeyEvent e) {
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
		}*/

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


	@Override
	public void keyPressed(KeyEvent e) {
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