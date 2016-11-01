package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import models.AlgaeModel;
import models.BarModelG2;
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
    //final static int frameWidth = 800;
    //final static int frameHeight = 800;
	
	public Game2View(Game2Controller ctl){
		oxyBar = new BarModelG2(200);
		controller = ctl;

    	frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);

    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(1000, 700);
    	frame.setVisible(true);
    	frame.setResizable(false);
   
		
    	
		algaeWater.setSize(1000, 500);
		algaeWater.setBackground(Color.BLUE);
		algaeWater.setVisible(true);
		
		shallowWater.setSize(1000, 500);
		shallowWater.setBackground(Color.CYAN);
		shallowWater.setVisible(true);
		
    	
    	//Panes
    	JSplitPane view = new  JSplitPane();
    	view.setSize(1000, 550);
    	view.setDividerSize(5);
    	view.setDividerLocation(100);
    	view.setEnabled(false);
    	
    	
    	
    	view.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    	view.setDividerSize(0);
    	view.setRightComponent(algaeWater);
    	view.setLeftComponent(shallowWater);
		
	     
	      
	      
    	frame.add(view);
    	
    	//addKeyListener
    	frame.addKeyListener(this);
	}
   
    
    
	public void addController(Game2Controller cont) {
		controller = cont;
	}
	public void repaintFrame(){
		frame.repaint();
	}
	
	public class Animation extends JComponent {
		@Override
		public void paint(Graphics g) {
			//Draw animal at current position
			g.setColor(Color.ORANGE);
			g.fillRect(controller.getAnimalModelG2().getLocX(),controller.getAnimalModelG2().getY(),controller.getAnimalModelG2().getWidth(),controller.getAnimalModelG2().getHeight());
			
			
			//Draw score data and timer and health
			g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
			g.setColor(Color.WHITE);
			
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
					g.setColor(Color.GREEN);
					g.fillRect(tmp.getLocX(), tmp.getLocY(), tmp.getWidth(), tmp.getHeight());
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
		}
	}
				
	
	@Override
	public void keyReleased(KeyEvent e) {
		controller.getAnimalModelG2().setSpeed(0);
		
	}
}