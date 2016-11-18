package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.io.File;
import controller.Game1Controller;
import enums.Direction;
import models.ConcreteWallModelG1.ConcreteChunk;
import models.GabionWallModelG1.GabionChunk;

public class Game1View extends JPanel implements KeyListener{
    private Game1Controller controller;
    private JFrame frame;
    private TexturePaint sandTexture;
    BufferedImage[][] pics;
    final int frameCount = 3;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    int picNum = 0;
    
    //Load in sprites
    private ArrayList<BufferedImage> gabSeq = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> concSeq = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> animalSeq = new ArrayList<BufferedImage>();
    

	public Game1View(Game1Controller ctl, JFrame gameF){
        controller = ctl;
        //Load all pictures in the view
        loadImgs();
        frame = gameF;
        frame.getContentPane().add(new Animation());
        frame.setBackground(Color.gray);
        
        //Full screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(controller.getDim().width, controller.getDim().height); 
        frame.setVisible(true);
        frame.setResizable(false);
        

       
        //addKeyListener
        frame.addKeyListener(this);
    }
	
	public void repaintFrame(){
		frame.repaint();
	}
	
	BufferedImage gabImg;
	BufferedImage concImg;
	BufferedImage bg;
	BufferedImage crabImg;
	BufferedImage clam;
	BufferedImage ccc;
	
    public void loadImgs(){
    	boolean check = new File("./images/testwallgrid.png").exists();
    	System.out.println("This should be true.....: " + check);
    	
    		try {
    			gabImg = ImageIO.read(new File("./Images/Game1/testwallgrid.png"));
    			concImg = ImageIO.read(new File("./Images/Game1/testwallgrid.png"));
    			
    	    	for(int i = 0; i < 30; i++){
    	    		//getSubimage(int x, int y, int w, int h)
    	    		gabSeq.add(gabImg.getSubimage(0, 100*i, 1090, 100));
    	    		concSeq.add(concImg.getSubimage(0, 100*i, 1090, 100));
    	    	}
    		} catch (IOException e) {
	    		e.printStackTrace();
		    	for(int i = 0; i < 30; i++){
		    		//Fill the arraylist with empty pictures
		    		//gabSeq.add();
		    		//concSeq.add();
		    	}
	    	}
	    	try {
					clam = ImageIO.read(new File("./Images/Game1/clam_back_0.png"));
				} catch (IOException e) {
					e.printStackTrace();
					//add a blank bg image.
				}
	    	try {
					ccc = ImageIO.read(new File("./Images/Game1/ccChunk.png"));
				} catch (IOException e) {
					e.printStackTrace();
					//
				}
	    	try{
				crabImg = ImageIO.read(new File("./Images/Game1/bluecrab_0.png"));
				animalSeq.add(crabImg);
				crabImg = ImageIO.read(new File("./Images/Game1/bluecrab_1.png"));
				animalSeq.add(crabImg);
				crabImg = ImageIO.read(new File("./Images/Game1/bluecrab_2.png"));
				animalSeq.add(crabImg);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		//add blank animal img
	    	}
	    	
	    	try {
				bg = ImageIO.read(new File("./Images/Game1/sandy.jpg"));
				sandTexture = new TexturePaint(bg, new Rectangle(0, 0, 1000, 1000));
			} catch (IOException e) {
				e.printStackTrace();
				//add a blank bg image.
			}
    	}
 
	
	 public class Animation extends JComponent {
			@Override
			public void paint(Graphics g) {
				if(controller.getAnimalModel().isMoving()){
					picNum = (picNum + 1) % frameCount;
				}
				
				//First draw background
				g.fillRect(0, 0, 1090, 50); //Draws top bar black

				((Graphics2D) g).setPaint(sandTexture);
				//g.drawImage(bg, 0, 0,this);
				g.fillRect(0, 0, controller.getDim().width, controller.getDim().height);
				
				//Draw animal at current position
				g.drawImage(animalSeq.get(picNum), controller.getAnimalModel().getLocX(),controller.getAnimalModel().getLocY(), controller.getAnimalModel().getWidth(),controller.getAnimalModel().getHeight(),this);
				
				//Draw score data and timer and health
				g.setFont(new Font("Haettenschweiler", Font.PLAIN, 30)); 
				g.setColor(Color.WHITE);
				g.drawString("Gabion: " + controller.getGabionWallModel().getCurrentOysters(), 40, 30);
				g.setColor(Color.RED);
				g.drawString("Concrete: " + controller.getWallModel().getCurrentBlocks(), 220, 30);
				g.setColor(Color.GREEN);
				g.drawString("Time Left: "+ controller.getTime(), 895, 30);
				
				g.drawString("Health: " + controller.getBarModel().getStatus(), 705, 30);
				
				g.drawRect(420, 10, 200, 20); //Behind health bar doesn't change
				
				
				g.setColor(Color.RED);
				int health = (controller.getBarModel().getStatus()*2);
				g.fillRect(420, 10, health, 20);
				
				//Draw wall sprites (First this gives an ugly error, second can we set the bg of the jpannel rather than paint on jframe?)
				g.drawImage(concSeq.get(controller.getWallModel().getCurrentBlocks()), 0, 210, 1090, 100, this);
				g.drawImage(gabSeq.get(controller.getGabionWallModel().getCurrentOysters()), 0, 100, 1090, 100, this);
				
				
				if(!controller.getInCountDown()){
					//Draw all the chunks that are active.
					Collection<ConcreteChunk> concreteChunkTemp = controller.getWallModel().getChunks();
					Collection<GabionChunk> GabionChunkTemp = controller.getGabionWallModel().getChunks();
					Iterator<GabionChunk> git = GabionChunkTemp.iterator();
					Iterator<ConcreteChunk> it = concreteChunkTemp.iterator();
					while(it.hasNext()){
						ConcreteChunk tmp = it.next();
						if(tmp.isActive()){
							//g.setColor(Color.RED);
							//g.fillRect(tmp.getLocX(), tmp.getLocY(), 10, 10);
							g.drawImage(ccc, tmp.getLocX(), tmp.getLocY(), 30, 20, this);
						}
					}
					while (git.hasNext()){
						GabionChunk tmp = git.next();
						if(tmp.isActive()){
							//g.setColor(Color.WHITE);
							//g.fillRect(tmp.getLocX(), tmp.getLocY(), 10, 10);
							g.drawImage(clam, tmp.getLocX(), tmp.getLocY(), 30, 20, this);
						}
					}
				}
				if(controller.getInCountDown()){
					//Print the timer mid screen.
					g.setFont(new Font("Rockwell", Font.PLAIN, 100)); 
					g.setColor(Color.BLACK);
					g.drawString("" + controller.getIntermTime(), 500, 310);
					
				}
			}
	 }
	 
	 @Override
		public void keyPressed(KeyEvent e) {
		    int keyCode = e.getKeyCode();
		    switch( keyCode ) {
		        case KeyEvent.VK_UP:
		            // handle up 
		        	controller.getAnimalModel().setCurrDir(Direction.NORTH);
		        	controller.getAnimalModel().setSpeedY(-7);
		            break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		        	controller.getAnimalModel().setCurrDir(Direction.SOUTH);
		        	controller.getAnimalModel().setSpeedY(7);
		            break;
		        case KeyEvent.VK_LEFT:
		            // handle left
		        	controller.getAnimalModel().setCurrDir(Direction.WEST);
		        	controller.getAnimalModel().setSpeedX(-7);
		            break;
		        case KeyEvent.VK_RIGHT :
		            // handle right
		        	controller.getAnimalModel().setCurrDir(Direction.EAST);
		        	controller.getAnimalModel().setSpeedX(7);
		            break;
		        case KeyEvent.VK_ESCAPE :
		            // handle escape (to minimize game)
		        	frame.setExtendedState(JFrame.ICONIFIED);
		            break;		            
		    }
		}
	 
		@Override
		public void keyReleased(KeyEvent e) {
		    int keyCode = e.getKeyCode();
		    switch( keyCode ) {
		        case KeyEvent.VK_UP:
		            // handle up 
		        	controller.getAnimalModel().setSpeedY(0);
		            break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		        	controller.getAnimalModel().setSpeedY(0);
		            break;
		        case KeyEvent.VK_LEFT:
		            // handle left
		        	controller.getAnimalModel().setSpeedX(0);
		            break;
		        case KeyEvent.VK_RIGHT :
		            // handle right
		        	controller.getAnimalModel().setSpeedX(0);
		            break;
		    }
			
		}

	 
	 /*
	  * Stuff we need to remove from abstract below.
	  * 
	  */


	@Override
	public void keyTyped(KeyEvent e) {
	}
}