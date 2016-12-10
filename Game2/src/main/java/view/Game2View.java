package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import models.AlgaeModel;
import models.AnimalModelG2;
import models.BarModelG2;

import controller.Game2Controller;
import enums.Direction;


/**
 * @author Jacob
 *
 */
public class Game2View extends JPanel implements KeyListener{
	
	private Game2Controller controller;
	private JFrame frame = new JFrame();
	
	
	private AlgaeModel algae = new AlgaeModel();
	private BarModelG2 oxyBar;
	private BufferedImage arrows;
	private BufferedImage arrowUP;
	private BufferedImage arrowDOWN;
	private BufferedImage background;
	private BufferedImage character;
	private BufferedImage algaeImg;
	private BufferedImage algaeImgMed;
	private BufferedImage algaeImgBad;
	private BufferedImage catFish1;
	private BufferedImage catFish2;
	private BufferedImage catFish3;
	private BufferedImage storm1;
	private BufferedImage storm2;
	private BufferedImage storm3;
	private BufferedImage storm4;
	private BufferedImage algaeEaters;
	private int width;
	private int height;
	private int changeCount=0;
	private int frameCount= 0;
	private int tutFrameCount = 0;
	private int riverWidth;
	private boolean stopSpawn;
	
	
	Random rand = new Random();
	int randomStorm = rand.nextInt((3 - 1) + 1) + 1;
	
	public Game2View(Game2Controller ctl, JFrame gamef, Dimension size){
		
		height = (int) size.getHeight();
		width = (int) size.getWidth();
		oxyBar = new BarModelG2(200);
		controller = ctl;
		frame = gamef;
		frame.dispose();
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    
		loadImages();
    
		
    	frame.addKeyListener(this);
    	
		
		
	}
    
	
	
    
	/**
	 * Registers the controller passed in as a parameter
	 * @param cont is the desired controller to be registered
	 */
	public void addController(Game2Controller cont) {
		controller = cont;
	}
	
	
	/**
	 * Updates the Jframe by calling paint
	 */
	public void repaintFrame(){
		frame.repaint();
		
	}
	
	
	/**
	 * Loads in all the images used as bufferedImages
	 * background= The river, estuary, farm and farmer
	 * character = The blue crab
	 * algaeImg = The algae when the estuary is at good health
	 * algaeImgMed = The algae when the estuary is at medium health
	 * algaeImgBad = The algae when the estuary is at poor health
	 * storm1 = The stormcloud devoid of lightning
	 * storm2 = The storm cloud when the right most lightning bolt is firing
	 * storm3 = The storm cloud when the middle lightning bolt is firing
	 * storm4 = The storm cloud when the left most lightning bolt is firing
	 * algaeEaters = The cat fish that eat the algae
	 * Arrows = The four directional arrows on the keyboard
	 * arrowUP = The up arrow on the keyboard highlighted
	 * arrowDOWN = The down arrow on the keyboard highlighted
	 */
	public void loadImages(){
		try {
			background = ImageIO.read(new File("./Images/Game2/background.png"));
			character = ImageIO.read(new File("./Images/Game2/bluecrab_0.png"));
			algaeImg = ImageIO.read(new File("./Images/Game2/algae.png"));
			algaeImgMed = ImageIO.read(new File("./Images/Game2/algaeMedium.png"));
			algaeImgBad = ImageIO.read(new File("./Images/Game2/algaeBad.png"));
			storm1 = ImageIO.read(new File("./Images/Game2/cloud1.png"));
			storm2 = ImageIO.read(new File("./Images/Game2/cloud2.png"));
			storm3 = ImageIO.read(new File("./Images/Game2/cloud3.png"));
			storm4 = ImageIO.read(new File("./Images/Game2/cloud4.png"));
			algaeEaters= ImageIO.read(new File("./Images/Game2/AlgaeFish.png"));
			arrows = ImageIO.read(new File("./Images/Game2/arrows.png"));
			arrowUP = ImageIO.read(new File("./Images/Game2/ArrowUP.png"));
			arrowDOWN = ImageIO.read(new File("./Images/Game2/ArrowDOWN.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
	  
		}
	}
	
	public class Animation extends JComponent {
		
		
		
		/* (non-Javadoc)
		 * @see javax.swing.JComponent#paint(java.awt.Graphics)
		 */
		@Override
		public void paint(Graphics g) {
			if(controller.getNumMissed()==oxyBar.getDamagePercent()){
				
				
				if(changeCount==1){
					algaeImg = algaeImgBad;
				}
				
				else{
				oxyBar.updateDamagePercent();
				changeCount++;
				algaeImg=algaeImgMed;
				}
			}
			g.drawImage(background, 0, 0, width, height, this);
			
			if(controller.getTutorialStatus()){
				tutFrameCount++;
				Font tutorial = new Font("TimesRoman", Font.BOLD, 30);
				 g.setFont(tutorial);
				 g.setColor(Color.BLACK);
				 g.drawString("Use the arrow keys to prevent algae from depleting the estuary's oxygen!", width/6, height/2);
				
				if(tutFrameCount<60){
					g.drawImage(arrows, width/4, height/3, this);
					g.drawImage(arrowUP, width/4, height/3, this);
					
				}
				else if(tutFrameCount>=60&&tutFrameCount<120){
					g.drawImage(arrows, width/4, height/3, this);
					g.drawImage(arrowDOWN, width/4, height/3, this);
				}
				else {
					tutFrameCount=0;
					
				}
				
			}
			
			else{
			
			
			
			
			g.drawImage(character, controller.getAnimalModelG2().getLocX(),controller.getAnimalModelG2().getLocY(),controller.getAnimalModelG2().getWidth(),controller.getAnimalModelG2().getHeight(), this);		
			g.drawImage(algaeEaters, controller.getAnimalModelG2().getLocX(),controller.getAnimalModelG2().getLocY()-(algaeEaters.getHeight()/5),controller.getAnimalModelG2().getWidth()*2,controller.getAnimalModelG2().getHeight()*2, this);		

			g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
			g.setColor(Color.WHITE);
			
			if(controller.getStormStatus()==true){
				
				frameCount++;
				g.drawImage(storm1, width/3, 0, 300, 300, this);
				if(randomStorm == 1){
					if(frameCount>120 && frameCount< 130){
						g.drawImage(storm4, width/3, 0, 300, 300, this);
					}
					 else if(frameCount>120 &&frameCount <140){
						 g.drawImage(storm3, width/3, 0, 300, 300, this);
					 }
					 else if(frameCount>120 &&frameCount <150){
						 g.drawImage(storm2, width/3, 0, 300, 300, this);
					 }
					 else if( frameCount>160){
						 frameCount=0;
						 randomStorm = rand.nextInt((3 - 1) + 1) + 1;
					 }
				}
				else if(randomStorm == 2){
					if(frameCount>120 && frameCount< 130){
						g.drawImage(storm2, width/3, 0, 300, 300, this);
					}
					 else if(frameCount>120 &&frameCount <140){
						 g.drawImage(storm3, width/3, 0, 300, 300, this);
					 }
					 else if(frameCount>120 &&frameCount <150){
						 g.drawImage(storm4, width/3, 0, 300, 300, this);
					 }
					 else if( frameCount>160){
						 frameCount=0;
						 randomStorm = rand.nextInt((3 - 1) + 1) + 1;
					 }
				}
				else if(randomStorm == 3){
					if(frameCount>120 && frameCount< 130){
						g.drawImage(storm3, width/3, 0, 300, 300, this);
					}
					 else if(frameCount>120 &&frameCount <140){
						 g.drawImage(storm2, width/3, 0, 300, 300, this);
					 }
					 else if(frameCount>120 &&frameCount <150){
						 g.drawImage(storm4, width/3, 0, 300, 300, this);
					 }
					 else if( frameCount>160){
						 frameCount=0;
						 randomStorm = rand.nextInt((3 - 1) + 1) + 1;
					 }
				}
			}
			
			g.drawString("Time: " +controller.getGameTime(), 10, 25);
			
			 
			g.fillRect(150, 45, oxyBar.getMaxLevel()-(controller.getNumMissed()*oxyBar.getDamage()), oxyBar.getWidth());
			g.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
			 g.setColor(Color.WHITE);
			g.drawString("Oxygen Level: ", 0, 65);
			
			
			Collection<AlgaeModel> algaeTemp = controller.getAlgaeList();
			Iterator<AlgaeModel> it = algaeTemp.iterator();
			
			while(it.hasNext()){
				setStopSpawn(true);
				AlgaeModel tmp = it.next();
				if(tmp.isActive()){
					g.drawImage(algaeImg, tmp.getLocX(), tmp.getLocY(), tmp.getWidth(), tmp.getHeight(), this);
					if(controller.getStormStatus()==true){
						g.drawImage(algaeImg, tmp.getRiverAlgaeX(), tmp.getRiverAlgaeY(), tmp.getWidth(), tmp.getHeight(), this);
						tmp.moveRiverAlgae();
					}
					tmp.move();
				}
				
			
				if(controller.getNumMissed()*oxyBar.getDamage() >=200){
					 Font gameLose = new Font("TimesRoman", Font.BOLD, 100);
					 g.setFont(gameLose);
					 g.setColor(Color.BLACK);
					 g.drawString("You have lost ", width/4, height/2);
					 g.setFont(gameLose);
					 endGameTimer();
					 
					 
				 }
				else if(controller.getGameTime()==60){
					
						Font gameLose = new Font("TimesRoman", Font.BOLD, 100);
						 g.setFont(gameLose);
						 g.setColor(Color.BLACK);
						 g.drawString("You have won!", width/4, height/2);
						 g.setFont(gameLose);
						endGameTimer();
				}
			}
			setStopSpawn(false);
		}
		}
	}
    
	ActionListener GameEndListener = new ActionListener() {
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.setGameActive(false);
		}
	};
	
	/**
	 * After the game is over this component sets a 3 second timer to allow the user to read the game result
	 */
	public void endGameTimer() {
		Timer t = new Timer(3000, GameEndListener);
		t.setRepeats(false);
		t.start();
		
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		//not used
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 * Key listener to adjust character velocity
	 * -if Up is pressed sets velocity to -3
	 * -if down is pressed sets velocity to 3
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		switch(keyCode){
		case KeyEvent.VK_DOWN:
			if(controller.getTutorialStatus()){
				controller.setTutorialStatus(false);
			}
				controller.getAnimalModelG2().setVelocity(3);
			
			
			break;
		
		case KeyEvent.VK_UP:
			if(controller.getTutorialStatus()){
				controller.setTutorialStatus(false);
			}
			
				controller.getAnimalModelG2().setVelocity(-3);
			
			break;
			
		case KeyEvent.VK_SPACE:
			
			
			//controller.setGameActive(false);
			//frame.dispose();
		
		
		break;
		}
	}
				
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		controller.getAnimalModelG2().setVelocity(0);
		
	} 




	/**
	 * Gets the current frame of the game2
	 * @return the current game frame
	 */
	public JFrame getFrame() {
		return frame;
	}




	/**
	 * Registers the desired frame to the passed in parameter
	 * @param frame the desired frame to be registered
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	/** 
	 * Gets the width taken up by the algaeEater
	 * this is done by taking the algaeEate width and dividing by 6
	 * used to calculate collision with the algae
	 * @return riverWidth the width taken up by the algaeEater
	 */
	public int getAlgaeEaterX(){
		riverWidth = algaeEaters.getWidth()/6;
		return riverWidth;
	}




	public boolean isStopSpawn() {
		return stopSpawn;
	}




	public void setStopSpawn(boolean stopSpawn) {
		this.stopSpawn = stopSpawn;
	}
	
	
	
}