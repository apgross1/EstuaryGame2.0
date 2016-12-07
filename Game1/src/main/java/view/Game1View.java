package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import controller.Game1Controller;
import enums.Direction;
import models.ConcreteChunk;
import models.GabionChunk;

/**
 * @author Tinytimmmy
 *
 */
public class Game1View extends JPanel implements KeyListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Game1Controller controller;
    private JFrame frame;
    private TexturePaint sandTexture;
    BufferedImage[][] pics;
    final int frameCount = 3;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    int picNum = 0;
    
	BufferedImage gabImg;
	BufferedImage concImg;
	BufferedImage bg;
	BufferedImage crabImg;
	BufferedImage clam;
	BufferedImage ccc;
	BufferedImage keypic;
	BufferedImage wave;
	BufferedImage grassone;
	BufferedImage grasstwo;
	BufferedImage grassthree;
    
    //Load in sprites
    private ArrayList<BufferedImage> animalSeq = new ArrayList<BufferedImage>();

	/**
	 * constructor for the view that takes in an instance of the controller and the
	 * overall jframe used by all games, sets up to frame that the view will use
	 * @param ctl an instance of the controller
	 * @param gameF the jframe that the view uses to paint on
	 */
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
	
    /**
     * a method that loads in all the images the view will use
     */
    public void loadImgs(){
    	//boolean check = new File("./Images/Game1/testwallgrid.png").exists();
    	boolean check = new File("./Images/Game1/Game1/testwallgrid.png").exists();
    	System.out.println("This should be true.....: " + check);
    	
    		try {
    			gabImg = ImageIO.read(new File("./Images/Game1/le_gab.png"));
    			concImg = ImageIO.read(new File("./Images/Game1/conc.jpg"));

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
					ccc = ImageIO.read(new File("./Images/Game1/concrete3.jpg"));
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
	    	try {
	    		keypic = ImageIO.read(new File("./Images/Game1/keyboard_directional.png"));
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    	try {
	    		wave = ImageIO.read(new File("./Images/Game1/whave.png"));
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    	try {
	    		grassone = ImageIO.read(new File("./Images/Game1/healthy.png"));
	    		grasstwo = ImageIO.read(new File("./Images/Game1/lesshealthy.png"));
	    		grassthree = ImageIO.read(new File("./Images/Game1/nothealthy.png"));
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
    	}
	
	/**
	 * used to repaint the jframe
	 */
	public void repaintFrame(){
		frame.repaint();
	}
    
    /**
     * this method is used to center any of the text used by the view
     * @param g the graphic that contains where the text will go
     * @param text the actual text that is being printed
     * @param font the font used by the string when printed
     */
    public void drawCenteredString(Graphics g, String text, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = (controller.getDim().width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = ((controller.getDim().height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
        // Dispose the Graphics
        g.dispose();
    }
 
	
    /**
     * this method paints all of the different components of the jframe, such as the background and 
     * the health bar that is used, also repaints whenever there is an update to any information on the screen like
     * number of gabions or concrete blocks
     *
     */
    public class Animation extends JComponent {
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			if(!controller.isIntro()){
				if(controller.getAnimalModel().isMoving()){
					picNum = (picNum + 1) % frameCount;
				}
				//Draw BG FIRST
				((Graphics2D) g).setPaint(sandTexture);
				g.fillRect(0, 0, controller.getDim().width, controller.getDim().height);
				
				//grass
				//g.drawImage(grass, 0, (int) ((controller.getDim().height)*.05), controller.getDim().width, (int) ((controller.getDim().height)*.10), this);
				int grass_to_print = controller.getDim().width / grassone.getWidth();
				if(controller.getBarModel().getStatus() > 75){
					for(int i=0; i< grass_to_print; i++){
						g.drawImage(grassone, (grassone.getWidth() * i), (int) ((controller.getDim().height)*.05), (controller.getDim().width/ grass_to_print), (int) ((controller.getDim().height)*.10), this);
					}
				}else if(controller.getBarModel().getStatus() > 50){
					for(int i=0; i< grass_to_print; i++){
						g.drawImage(grasstwo, (grassone.getWidth() * i), (int) ((controller.getDim().height)*.05), (controller.getDim().width/ grass_to_print), (int) ((controller.getDim().height)*.10), this);
					}
				}else{//health below 50
					for(int i=0; i< grass_to_print; i++){
						g.drawImage(grassthree, (grassone.getWidth() * i), (int) ((controller.getDim().height)*.05), (controller.getDim().width/ grass_to_print), (int) ((controller.getDim().height)*.10), this);
					}
				}
				
				//First draw bar
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, controller.getDim().width, (int) ((controller.getDim().height)*.05)); //top bar 5% of screen

				if(!controller.getInCountDown()){
					//Draw animal at current position
					g.drawImage(animalSeq.get(picNum), controller.getAnimalModel().getLocX(),controller.getAnimalModel().getLocY(), controller.getAnimalModel().getWidth(),controller.getAnimalModel().getHeight(),this);
					
					//Draw score data and timer and health
					g.setFont(new Font("Haettenschweiler", Font.PLAIN, 30)); 
					g.setColor(Color.WHITE);
					g.drawString("Gabion: " + controller.getGabionWallModel().getCurrentOysters(), (int)(.10*(controller.getDim().width)), (int)(.03*(controller.getDim().height)));
					g.setColor(Color.RED);
					g.drawString("Concrete: " + controller.getWallModel().getCurrentBlocks(), (int)(.20*(controller.getDim().width)), (int)(.03*(controller.getDim().height)));
					g.setColor(Color.GREEN);
					g.drawString("Time Left: "+ controller.getTime(), (int)(.65*(controller.getDim().width)), (int)(.03*(controller.getDim().height)));

					g.drawString("Round: " + controller.getRound() + " / 3", (int)(.9*(controller.getDim().width)), (int)(.03*(controller.getDim().height)));
					
				}
				g.setFont(new Font("Haettenschweiler", Font.PLAIN, 30)); 
				g.setColor(Color.GREEN);
				g.drawString("Health: " + controller.getBarModel().getStatus(), (int)(.30*(controller.getDim().width)), (int)(.03*(controller.getDim().height)));
				
				g.drawRect((int)(.41*(controller.getDim().width)), (int)(.01*(controller.getDim().height)), 200, 20); //Behind health bar doesn't change
				
				g.setColor(Color.RED);
				int health = (controller.getBarModel().getStatus()*2);
				g.fillRect((int)(.41*(controller.getDim().width)), (int)(.01*(controller.getDim().height)), health, 20);
				
				
				//Draw walls
				for(int i=0; i< controller.getGabionWallModel().getCurrentOysters(); i++){
					if(i < 15){
						g.drawImage(gabImg, (int)(controller.getDim().width / 15*i), (int)(controller.getDim().height * .15), (controller.getDim().width/15),  (int)(controller.getDim().height * .075), this);
						//g.drawImage(img, x, y, width, height, observer)
					}else{
						//do second row of gabs
						g.drawImage(gabImg, (int)(controller.getDim().width / 15*(i%15)), (int)(controller.getDim().height * .225), (controller.getDim().width/15),  (int)(controller.getDim().height * .075), this);
					}
				}
				for(int i=0; i< controller.getWallModel().getCurrentBlocks(); i++){
					if(i < 15){
						g.drawImage(concImg, (int)(controller.getDim().width / 15*i), (int)(controller.getDim().height * .3), (controller.getDim().width/15),  (int)(controller.getDim().height * .075), this);
					}else{
						//do second row of conc
						g.drawImage(concImg, (int)(controller.getDim().width / 15*(i%15)), (int)(controller.getDim().height * .375), (controller.getDim().width/15),  (int)(controller.getDim().height * .075), this);
					}
				}
				
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
				
					g.drawImage(wave, 0, (controller.getWaveY() /*- 250*/), controller.getDim().width, 2000, this);
					
				if(controller.getInCountDown()){
					if(controller.getIsGameOver() & !controller.isWin()){
						//lose
						//g.setFont(new Font("Haettenschweiler", Font.PLAIN, 50));
						g.setColor(Color.RED);
						drawCenteredString(g, "Unfortunatly you were unable to protect the estuary, next time try using more oyesters.", new Font("Haettenschweiler", Font.PLAIN, 50));
						//g.drawString("Unfortunatly you were unable to protect the estuary, next time try using more gabbions.", (int)(.15*(controller.getDim().width)), (int)(.5*(controller.getDim().height)));
						
					}else if(controller.getIsGameOver() & controller.isWin()){
						//g.setFont(new Font("Haettenschweiler", Font.PLAIN, 50));
						g.setColor(Color.GREEN);
						drawCenteredString(g, "Great Job! You were able to protect the estuary from the hurricane!", new Font("Haettenschweiler", Font.PLAIN, 50));
						//g.drawString("Great Job! You were able to protect the estuary from the hurricane!", (int)(.2*(controller.getDim().width)), (int)(.5*(controller.getDim().height)));
						
					}else{
						//Just a regular count down.
						//g.setFont(new Font("Rockwell", Font.PLAIN, 400)); 
						g.setColor(Color.BLACK);
						//g.drawString("" + controller.getIntermTime(), (int)(.5 *(controller.getDim().width)), (int)(.5 *(controller.getDim().height)));
						drawCenteredString(g, "" + controller.getIntermTime(), new Font("Rockwell", Font.PLAIN, 400));
					}
				}
			}else{
				//Is in intro mode, paint basic stuff and tutorial
				//Draw BG FIRST
				((Graphics2D) g).setPaint(sandTexture);
				g.fillRect(0, 0, controller.getDim().width, controller.getDim().height);
				
				//First draw bar
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, controller.getDim().width, (int) ((controller.getDim().height)*.05)); //top bar 5% of screen

				
				//Draw score data and timer and health
				g.setFont(new Font("Haettenschweiler", Font.PLAIN, 30)); 
				
				//Draw tutorial.
				g.setColor(Color.BLACK);
				
				//g.drawString("Quick! Collect either gabbions or concrete chuncks to protect the estuary! You decide what is more effective.", (int)(.2*(controller.getDim().width)), (int)(.5*(controller.getDim().height)));
				g.drawImage(keypic, (int)(controller.getDim().width * .5)-182, (int)(controller.getDim().height * .6), 364, 164, this);
				drawCenteredString(g, "Quick! Collect either gabbions or concrete chuncks to protect the estuary! You decide what is more effective.", new Font("Haettenschweiler", Font.PLAIN, 50));
				//g.drawImage(img, x, y, width, height, observer)
				
				
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
		        	controller.getAnimalModel().setSpeedY((controller.getDim().height / 200)*-1);
		            break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		        	controller.getAnimalModel().setCurrDir(Direction.SOUTH);
		        	controller.getAnimalModel().setSpeedY((controller.getDim().height / 200));
		            break;
		        case KeyEvent.VK_LEFT:
		            // handle left
		        	controller.getAnimalModel().setCurrDir(Direction.WEST);
		        	controller.getAnimalModel().setSpeedX((controller.getDim().height / 200)*-1);
		            break;
		        case KeyEvent.VK_RIGHT :
		            // handle right
		        	controller.getAnimalModel().setCurrDir(Direction.EAST);
		        	controller.getAnimalModel().setSpeedX((controller.getDim().height / 200));
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