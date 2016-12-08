
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Game1Controller;
import controller.Game2Controller;
import controller.Game3Controller;

public class MainRun extends JPanel implements MouseListener, KeyListener {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static boolean gameStarted = false;
	JButton startButton = new JButton("Start Game");
	JLabel backGround = new JLabel();
	boolean startPressed = false;
	JFrame frame;
	private Game1Controller g1cont;
	private Game2Controller g2cont;
	private Game3Controller g3cont;
	private boolean menuClose;
	public int num = 0;
	
	
	public MainRun(JFrame frame){
	
		
		g1cont = new Game1Controller(this.frame);
		g2cont = new Game2Controller();
		g3cont = new Game3Controller(true);
		
		//Initializing frame
		this.frame = frame;
		this.frame.setSize(screenSize.width, screenSize.height); 
		this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.frame.setUndecorated(true);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setResizable(false);
		
		
		//Assigning layouts to each panel/frame
		this.frame.setLayout(new GridBagLayout());
		this.backGround.setLayout(new GridBagLayout());
		
		
		
		//Defining constraint for start button
		startButton.setPreferredSize(new Dimension(400,100));
		GridBagConstraints b1c = new GridBagConstraints();
		b1c.gridx = (int)(this.backGround.getWidth()/2.5);
		b1c.gridy = (int)(this.backGround.getHeight()/2);
	
		
		
		
		//Defining constraint for background
		backGround.setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
		ImageIcon icon = new ImageIcon("./Images/2D_estuary.jpg"); 
		backGround.setIcon(icon);
		GridBagConstraints bRc = new GridBagConstraints();
		bRc.gridx = 0;
		bRc.gridy = 0;
		bRc.gridwidth = frame.getWidth();
		bRc.gridheight = frame.getHeight();

		//Adding panels/components
		backGround.add(startButton, b1c);
		frame.add(backGround, bRc);
		
        //addKeyListener
		startButton.addMouseListener(this);
		this.frame.addKeyListener(this);
		System.out.println("First");

		
		
		
		this.frame.revalidate();
		this.frame.setVisible(true);	
    }

	public void runMainMenu() {
		while((!menuClose)){
			//System.out.println(number);
			this.repaintFrame();
		}
		
		g1cont.startGame();
		g2cont.startGame();
		g1cont.getG1view().getFrame().dispose();
		
		g3cont.runGame();
		g2cont.getView().getFrame().dispose();
		if(g3cont.getView().isExitToMain()) {
			
			g3cont.getView().setExitToMain(false);
			this.menuClose = false;
			frame.revalidate();
			frame.repaint();
			System.out.println("Creating new frame");
			frame = new JFrame();
			this.frame.setSize(screenSize.width, screenSize.height); 
			this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			this.frame.setUndecorated(true);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.frame.setResizable(false);
			g1cont = new Game1Controller(this.frame);
			g2cont = new Game2Controller();
			g3cont = new Game3Controller(true);
			runMainMenu();
		}
		else if (g3cont.getView().isExitGame()) {
			System.exit(0);
			return;
		}
		return;
	}
	
	

	public boolean isStartPressed(){
		return startPressed;
	}
	
	public void setStartPressed(boolean startPress) {
		startPressed = startPress;
	}
	public void repaintFrame(){
		frame.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		menuClose = true;
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
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



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
