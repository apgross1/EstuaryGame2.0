package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
    private int damageLevel;
    private Game1Controller controller;
    private JFrame frame = new JFrame();
    private JPanel bar_pannel = new JPanel();
    private JPanel play_ground = new JPanel();
    private JPanel gab_wall = new JPanel();
    private JPanel conc_wall = new JPanel();
    private JPanel estuary = new JPanel();
    private JPanel super_panel = new JPanel();
    
    //Load in sprites
    private ArrayList<BufferedImage> gabSeq = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> concSeq = new ArrayList<BufferedImage>();
    

	public Game1View(Game1Controller ctl){
        controller = ctl;
 
        frame = new JFrame();
        frame.getContentPane().add(new Animation());
        frame.setBackground(Color.gray);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1090, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        
        //Load all pictures in the view
        loadImgs();
       
        //addKeyListener
        frame.addKeyListener(this);
    }
	
	public void repaintFrame(){
		frame.repaint();
	}
	
	BufferedImage gabImg;
	BufferedImage concImg;
	BufferedImage bg;
	
    public void loadImgs(){
    	boolean check = new File("./images/testwallgrid.png").exists();
    	System.out.println("This should be true.....: " + check);

    		try {
    			gabImg = ImageIO.read(new File("./images/testwallgrid.png"));
    			concImg = ImageIO.read(new File("./images/testwallgrid.png"));
    			
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
					bg = ImageIO.read(new File("./images/sandy.jpg"));
				} catch (IOException e) {
					e.printStackTrace();
					//add a blank bg image.
				}
    	}
 
	
	 public class Animation extends JComponent {
			@Override
			public void paint(Graphics g) {
				
				//First draw background
				g.drawImage(bg, 0, 0, this);
				
				//Draw animal at current position
				g.fillRect(controller.getAnimalModel().getLocX(),controller.getAnimalModel().getLocY(),controller.getAnimalModel().getWidth(),controller.getAnimalModel().getHeight());
				
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
				g.drawImage(gabSeq.get(controller.getGabionWallModel().getCurrentOysters()), 0, 210, 1090, 100, this);
				g.drawImage(concSeq.get(controller.getWallModel().getCurrentBlocks()), 0, 100, 1090, 100, this);
				
				
				if(!controller.getInCountDown()){
					//Draw all the chunks that are active.
					Collection<ConcreteChunk> concreteChunkTemp = controller.getWallModel().getChunks();
					Collection<GabionChunk> GabionChunkTemp = controller.getGabionWallModel().getChunks();
					Iterator<GabionChunk> git = GabionChunkTemp.iterator();
					Iterator<ConcreteChunk> it = concreteChunkTemp.iterator();
					while(it.hasNext()){
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
		        	controller.getAnimalModel().setSpeedY(-2);
		            break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		        	controller.getAnimalModel().setCurrDir(Direction.SOUTH);
		        	controller.getAnimalModel().setSpeedY(2);
		            break;
		        case KeyEvent.VK_LEFT:
		            // handle left
		        	controller.getAnimalModel().setCurrDir(Direction.WEST);
		        	controller.getAnimalModel().setSpeedX(-2);
		            break;
		        case KeyEvent.VK_RIGHT :
		            // handle right
		        	controller.getAnimalModel().setCurrDir(Direction.EAST);
		        	controller.getAnimalModel().setSpeedX(2);
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
