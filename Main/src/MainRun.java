import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Game1Controller;
import controller.Game2Controller;
import controller.Game3Controller;

public class MainRun extends JPanel implements KeyListener{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static JFrame gameFrame = new JFrame();
	static boolean gameStarted = false;
	JPanel button_pannel = new JPanel();
	JPanel bgp = new JPanel();
	JButton start = new JButton("Start");
	
	private TexturePaint backG;
	
	
	//Game1Controller g1 = new Game1Controller(gameFrame);
	//Game2Controller g2 = new Game2Controller(gameFrame);
	//Game3Controller g3 = new Game3Controller(gameFrame);

	BufferedImage bg;
	
	public MainRun(){
		//System.out.println("Here");
		button_pannel.add(start);
		
		gameFrame.getContentPane().add(new MainScreen());
		gameFrame.setBackground(Color.gray);
		
        
        //Full screen
		gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		gameFrame.setUndecorated(true);
 
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(screenSize.width, screenSize.height); 
		gameFrame.setVisible(true);
		gameFrame.setResizable(false);
		
		
		//Load images 
		try{
			bg = ImageIO.read(new File("./Images/2D_estuary.jpg"));
			backG = new TexturePaint(bg, new Rectangle(0, 0, screenSize.width, screenSize.height));
			
		}catch(Exception e){
			//
		}
        
        //addKeyListener
		gameFrame.addKeyListener(this);
		System.out.println("First");
		gameFrame.add(button_pannel);
		gameFrame.revalidate();
    }
	public void repaintFrame(){
		gameFrame.repaint();
	}
	public void addButtons(){
		System.out.print("Second");
		gameFrame.add(button_pannel);
		gameFrame.revalidate();
	}
	
	public class MainScreen extends JComponent {
		@Override
		public void paint(Graphics g) {
			
			((Graphics2D) g).setPaint(backG);
			g.fillRect(0, 0, screenSize.width, screenSize.height);
			
			//addButtons();
		}
	}
	
	
	
	
	
	//For clicking on the screen when the user wants to start / load / etc.
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
