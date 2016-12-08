package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
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


import models.AlgaeModel;
import models.AnimalModelG2;
import models.BarModelG2;

import controller.Game2Controller;
import enums.Direction;


public class Game2View extends JPanel implements KeyListener{
	
	private Game2Controller controller;
	private JFrame frame = new JFrame();
	
	
	AlgaeModel algae = new AlgaeModel();
	BarModelG2 oxyBar;
	BufferedImage arrows;
	BufferedImage arrowUP;
	BufferedImage arrowDOWN;
	BufferedImage background;
	BufferedImage character;
	BufferedImage algaeImg;
	BufferedImage algaeImgMed;
	BufferedImage algaeImgBad;
	BufferedImage catFish1;
	BufferedImage catFish2;
	BufferedImage catFish3;
	BufferedImage storm1;
	BufferedImage storm2;
	BufferedImage storm3;
	BufferedImage storm4;
	BufferedImage algaeEaters;
	int width;
	int height;
	int changeCount=0;
	int frameCount= 0;
	int tutFrameCount = 0;
	
	
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
    
	
	
    
	public void addController(Game2Controller cont) {
		controller = cont;
	}
	public void repaintFrame(){
		frame.repaint();
		
	}
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
			
			if(controller.getTutoralStatus()){
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
				//
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
					 controller.setGameActive(false);
					 
				 }
				else if(controller.getGameTime()==60){
					
						Font gameLose = new Font("TimesRoman", Font.BOLD, 100);
						 g.setFont(gameLose);
						 g.setColor(Color.BLACK);
						 g.drawString("You have won!", width/4, height/2);
						 g.setFont(gameLose);
						 controller.setGameActive(false);
					
				}
			
			
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
			if(controller.getTutoralStatus()){
				controller.setTutorialStatus(false);
			}
				controller.getAnimalModelG2().setVelocity(5);
			
			
			break;
		
		case KeyEvent.VK_UP:
			if(controller.getTutoralStatus()){
				controller.setTutorialStatus(false);
			}
			
				controller.getAnimalModelG2().setVelocity(-5);
			
			break;
			
		case KeyEvent.VK_SPACE:
			
			
			controller.setGameActive(false);
			frame.dispose();
		
		
		break;
		}
	}
				
	
	@Override
	public void keyReleased(KeyEvent e) {
		controller.getAnimalModelG2().setVelocity(0);
		
	}
	public int getAlgaeEaterX(){
		return algaeEaters.getWidth()/6;
	}
	
	
}