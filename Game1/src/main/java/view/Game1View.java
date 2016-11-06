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
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BoxLayout;
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
    private JPanel bar_pannel = new JPanel();
    private JPanel play_ground = new JPanel();
    private JPanel gab_wall = new JPanel();
    private JPanel conc_wall = new JPanel();
    private JPanel estuary = new JPanel();
    private JPanel super_panel = new JPanel();

	public Game1View(Game1Controller ctl){
        controller = ctl;
 
        frame = new JFrame();
        frame.getContentPane().add(new Animation());
        frame.setBackground(Color.gray);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);
        frame.setResizable(false);
       
        super_panel.setSize(1000,700);
        super_panel.setBackground(Color.CYAN);
        super_panel.setVisible(true);
        super_panel.setLayout(new BoxLayout(super_panel, BoxLayout.Y_AXIS));
        frame.add(super_panel);
       
       
        bar_pannel.setPreferredSize(new Dimension(1000, 50));
        bar_pannel.setBackground(Color.BLACK);
        bar_pannel.setVisible(true);
        //bar_pannel.setLayout(new BoxLayout(bar_pannel, BoxLayout.LINE_AXIS));
       
        play_ground.setPreferredSize(new Dimension(1000, 400));
        play_ground.setBackground(Color.BLUE);
        play_ground.setVisible(true);
        //play_ground.setLayout(new BoxLayout(play_ground, BoxLayout.LINE_AXIS));
       
        gab_wall.setPreferredSize(new Dimension(1000, 50));
        gab_wall.setBackground(Color.RED);
        gab_wall.setVisible(true);
       
        conc_wall.setPreferredSize(new Dimension(1000, 50));
        conc_wall.setBackground(Color.GREEN);
        conc_wall.setVisible(true);
       
        estuary.setPreferredSize(new Dimension(1000, 100));
        estuary.setBackground(Color.GRAY);
        estuary.setVisible(true);
       
        super_panel.add(bar_pannel);
        super_panel.add(estuary);
        super_panel.add(gab_wall);
        super_panel.add(conc_wall);
        super_panel.add(play_ground);
 
       
       
        /*//Panes
        JSplitPane view = new  JSplitPane();
        view.setSize(1000, 550);
        view.setDividerSize(5);
        view.setDividerLocation(50);
        view.setEnabled(false);
       
        view.setOrientation(JSplitPane.VERTICAL_SPLIT);
        view.setTopComponent(bar_pannel);
        view.setBottomComponent(play_ground);
       
        frame.add(view);*/
       
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
				g.drawString("Gabion: " + controller.getGabionWallModel().getCurrentOysters(), 20, 30);
				g.setColor(Color.RED);
				g.drawString("Concrete: " + controller.getWallModel().getCurrentBlocks(), 200, 30);
				g.setColor(Color.GREEN);
				g.drawString("Time Left: "+ controller.getTime(), 775, 30);
				
				g.drawString("Health: " + controller.getBarModel().getStatus(), 625, 30);
				
				g.drawRect(400, 20, 200, 20); //Behind health bar doesn't change
				
				
				g.setColor(Color.RED);
				int health = (controller.getBarModel().getStatus()*2);
				g.fillRect(400, 20, health, 20);
				
				
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
	    }
	 
	 @Override
		public void keyPressed(KeyEvent e) {
		    int keyCode = e.getKeyCode();
		    switch( keyCode ) {
		        case KeyEvent.VK_UP:
		            // handle up 
		        	controller.getAnimalModel().setCurrDir(Direction.NORTH);
		        	controller.getAnimalModel().setSpeedY(-5);
		            break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		        	controller.getAnimalModel().setCurrDir(Direction.SOUTH);
		        	controller.getAnimalModel().setSpeedY(5);
		            break;
		        case KeyEvent.VK_LEFT:
		            // handle left
		        	controller.getAnimalModel().setCurrDir(Direction.WEST);
		        	controller.getAnimalModel().setSpeedX(-5);
		            break;
		        case KeyEvent.VK_RIGHT :
		            // handle right
		        	controller.getAnimalModel().setCurrDir(Direction.EAST);
		        	controller.getAnimalModel().setSpeedX(5);
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
