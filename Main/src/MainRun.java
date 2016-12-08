import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
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
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Game1Controller;
import controller.Game2Controller;
import controller.Game3Controller;
import view.Game1View.Animation;

public class MainRun extends JPanel implements KeyListener, MouseListener{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static boolean gameStarted = false;
	JButton startButton = new JButton("Start Game");
	JLabel backGround = new JLabel();
	boolean startPressed = false;
	JFrame frame;
	BufferedImage badGuy = null;
	
	private ArrayList<BufferedImage> startPics = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> exitPics = new ArrayList<BufferedImage>();
	
	
	//
	JLabel startScreen;
	JPanel backLay = new JPanel(new BorderLayout());
	JButton menuButton;
	
	public MainRun(JFrame frame){
		
		//Adding end menu button images
		BufferedImage exitGame_0 = null;
		BufferedImage exitGame_1 = null;
		BufferedImage start_0 = null;
		BufferedImage start_1 = null;
		try {
			exitGame_0 = ImageIO.read(new File("./Images/Game3/exitGame_0.png"));
			exitGame_1 = ImageIO.read(new File("./Images/Game3/exitGame_1.png"));
			start_0 = ImageIO.read(new File("./Images/start_0.png"));
			start_1 = ImageIO.read(new File("./Images/start_1.png"));
		} catch (IOException excep) {
			excep.printStackTrace();
		}
		
		try {
			badGuy = ImageIO.read(new File("./Images/Game3/exitGame_0.png"));
		} catch (IOException excep) {
			excep.printStackTrace();
		}
	
		startPics.add(start_0);
		startPics.add(start_1);
		exitPics.add(exitGame_0);
		exitPics.add(exitGame_1);
		
		this.frame = frame;
		this.frame.setSize(screenSize.width, screenSize.height); 
		this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.frame.setUndecorated(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		
		startScreen = new JLabel();
		startScreen.setLayout(new GridBagLayout());
		
		backLay.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		backLay.setSize(new Dimension(frame.getWidth(),frame.getHeight()));
		
		backLay.add(new Animation());
		backLay.setOpaque(false);
		
		//Defining constraint for background
		ImageIcon backgroundIcon = new ImageIcon("./Images/2D_estuary.jpg"); 
		startScreen.setIcon(backgroundIcon);
		System.out.println(frame.getWidth() + " " + frame.getHeight());
		startScreen.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		
		menuButton = new JButton(new ImageIcon(start_0));
		menuButton.setName("menu");
		menuButton.setBorder(BorderFactory.createEmptyBorder());
		menuButton.setContentAreaFilled(false);
		menuButton.setPreferredSize(new Dimension(start_0.getWidth(), start_0.getHeight()));
		GridBagConstraints b1c = new GridBagConstraints();
		b1c.gridx = 0;
		b1c.gridy = 1;
		b1c.weightx = .1;
		b1c.weighty = 0.1;
		menuButton.addMouseListener(this);
		startScreen.add(menuButton, b1c);
		
		JButton exitButton = new JButton(new ImageIcon(exitGame_0));
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.setName("exit");
		exitButton.addMouseListener(this);
		exitButton.setPreferredSize(new Dimension((int)(exitGame_0.getWidth()),exitGame_0.getHeight()));
		GridBagConstraints b2c = new GridBagConstraints();
		b2c.gridx = 2;
		b2c.gridy = 1;
		b2c.weightx = .1;
		b2c.weighty = .1;
		startScreen.add(exitButton, b2c);
		
		
		startButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  System.out.println("Start Button Pressed!");
				  startPressed = true;
				  }});
		
		frame.getContentPane().removeAll();
		frame.add(backLay); //(THIS NEEDS TO BE A JPANNEL OVER A JLABLE BUT ISNT WORKIN)
		frame.add(startScreen);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
		
    }
    public void drawCenteredString(Graphics g, String text, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = (screenSize.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        //int y = ((screenSize.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, (int) (.30*(screenSize.height)));
        // Dispose the Graphics
        g.dispose();
    }
	
    public class Animation extends JComponent {
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {	
			//System.out.println("StuffnThingsPls");
			g.setColor(Color.white);
			drawCenteredString(g, "Tim Sucks at naming games.", new Font("Haettenschweiler", Font.PLAIN, 50));
			/*
			int x_loc = 0;
			boolean dir = true;
			if(x_loc <= screenSize.width && x_loc >= 0){
				if(dir){ //east
					System.out.println("Goin East");
					//g.drawImage(badGuy, x_loc, (int) (.30*(screenSize.height)), this);
					g.drawRect(x_loc, (int) (.30*(screenSize.height)), 100, 100);
					x_loc++;
				}else{
					g.drawImage(badGuy, x_loc, (int) (.30*(screenSize.height)), this);
					x_loc--;
				}
			}else{
				dir = !dir;
			}
			*/
		}
    }
	public boolean isStartPressed(){
		return startPressed;
	}
	
	public void repaintFrame(){
		frame.repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if(button.getName() == "exit") {
			button.setIcon(new ImageIcon(exitPics.get(1)));
		}
		
		else {
			button.setIcon(new ImageIcon(startPics.get(1)));
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if(button.getName() == "exit") {
			button.setIcon(new ImageIcon(exitPics.get(0)));
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		else {
			button.setIcon(new ImageIcon(startPics.get(0)));
			System.out.println("Game Starting!");
			startPressed = true;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
