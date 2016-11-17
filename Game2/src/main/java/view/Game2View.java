package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import models.AlgaeEaterModel;
import models.AlgaeModel;
import models.AnimalModelG2;
import models.BarModelG2;
import models.WaterModelG2;
import controller.Game2Controller;
import enums.Direction;


public class Game2View extends JPanel implements KeyListener{
	
	private Game2Controller controller;
	private JFrame frame = new JFrame();
	//private JPanel action_pannel = new JPanel();
	private JPanel algaeWater = new JPanel();
	private JPanel shallowWater = new JPanel();
	AlgaeModel algae = new AlgaeModel();
	BarModelG2 oxyBar;
	BufferedImage background;
	BufferedImage character;
	BufferedImage algaeImg;
    //final static int frameWidth = 800;
    //final static int frameHeight = 800;
	
	public Game2View(Game2Controller ctl, JFrame gamef){
		oxyBar = new BarModelG2(200);
		controller = ctl;
		frame = gamef;
		loadImages();
    	
		/*
		algaeWater.setSize(1000, 500);
		algaeWater.setBackground(Color.BLUE);
		algaeWater.setVisible(false);
		
		shallowWater.setSize(1000, 500);
		shallowWater.setBackground(Color.CYAN);
		shallowWater.setVisible(false);
    	
    	//Panes
    	JSplitPane view = new  JSplitPane();
    	view.setSize(1000, 550);
    	view.setDividerSize(5);
    	view.setDividerLocation(100);
    	view.setEnabled(false);
    	
    	
    	
    	view.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    	view.setDividerSize(0);
    	view.setRightComponent(algaeWater);
    	view.setLeftComponent(shallowWater);*/
		
	     
	      
	      
    	//frame.add(view);
    	
    	//addKeyListener
    	frame.addKeyListener(this);
	}
   
    
    
	public void addController(Game2Controller cont) {
		controller = cont;
	}
	public void repaintFrame(){
		frame.repaint();
	}
	public void loadImages(){
		try {
			background = ImageIO.read(new File("./Images/Game2/waterBackgroundG2.png"));
			character = ImageIO.read(new File("./Images/Game2/hsCrab.png"));
			algaeImg = ImageIO.read(new File("./Images/Game2/Grass.png"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
	  
		}
	}
	
	public class Animation extends JComponent {
		@Override
		public void paint(Graphics g) {
			//Draw animal at current position
			
			g.drawImage(background, 0, 0, this);
			//g.setColor(Color.ORANGE);
			//g.fillRect(controller.getAnimalModelG2().getLocX(),controller.getAnimalModelG2().getY(),controller.getAnimalModelG2().getWidth(),controller.getAnimalModelG2().getHeight());
			g.drawImage(character, controller.getAnimalModelG2().getLocX(),controller.getAnimalModelG2().getY(),controller.getAnimalModelG2().getWidth(),controller.getAnimalModelG2().getHeight(), this);		
			//Draw score data and timer and health
			g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
			g.setColor(Color.WHITE);
			
			if(controller.getStormStatus()==true){
				g.setFont(g.getFont().deriveFont(Font.BOLD));
				g.drawString("It's Storming!" , 700, 50);
				g.setFont(g.getFont().deriveFont(Font.PLAIN));
			}
			//temp condition
			g.drawString("Time: " +controller.getGameTime(), 700, 25);
			
			 if(controller.getNumMissed()*10 ==200){
				 controller.setGameActive(false);
			 }
			g.fillRect(400, 18, oxyBar.getMaxLevel()-(controller.getNumMissed()*oxyBar.getDamage()), oxyBar.getWidth());
			g.setColor(Color.BLACK);
			g.drawString("Oxygen Level: ", 250, 35);
			g.drawString("Algae Missed : "+ controller.getNumMissed(), 250, 65);
			Collection<AlgaeModel> algaeTemp = controller.getAlgaeList();
			Iterator<AlgaeModel> it = algaeTemp.iterator();
			
			while(it.hasNext()){
				//
				AlgaeModel tmp = it.next();
				if(tmp.isActive()){
					g.drawImage(algaeImg, tmp.getLocX(), tmp.getLocY(), tmp.getWidth(), tmp.getHeight(), this);
					//g.setColor(Color.GREEN);
					//g.fillRect(tmp.getLocX(), tmp.getLocY(), tmp.getWidth(), tmp.getHeight());
					tmp.move();
					
				}
				
			
			
			
			
			}
		}
	}
    
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		switch(keyCode){
		case KeyEvent.VK_DOWN:
			
				controller.getAnimalModelG2().setSpeed(5);
			
			
			break;
		
		case KeyEvent.VK_UP:
			
			
				controller.getAnimalModelG2().setSpeed(-5);
			
			break;
			
		case KeyEvent.VK_SPACE:
			
			/*
			if(controller.getStormStatus()==true){
				controller.deactivateStorm();
			}
			else{
				controller.activateStorm();
			}
			*/
			controller.setGameActive(false);
		
		
		break;
		}
	}
				
	
	@Override
	public void keyReleased(KeyEvent e) {
		controller.getAnimalModelG2().setSpeed(0);
		
	}
}