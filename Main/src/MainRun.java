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

public class MainRun extends JPanel implements KeyListener{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static boolean gameStarted = false;
	JButton startButton = new JButton("Start Game");
	JLabel backGround = new JLabel();
	boolean startPressed = false;
	JFrame frame;
	
	public MainRun(JFrame frame){
		//Initializing frame
		this.frame = frame;
		this.frame.setSize(screenSize.width, screenSize.height); 
		this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.frame.setUndecorated(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		this.frame.addKeyListener(this);
		System.out.println("First");

		this.frame.revalidate();
		this.frame.setVisible(true);
		
		startButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  System.out.println("Start Button Pressed!");
				  startPressed = true;
				  }});
		
    }
	
	public boolean isStartPressed(){
		return startPressed;
	}
	
	public void repaintFrame(){
		frame.repaint();
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
