package models;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class SunHurricaneModel {

	private int height = 100;
	private int width = 100;
	private Pair location;
	private JPanel panel;
	private int initialPosition;
	private HashMap<String, ArrayList<BufferedImage>> graphics; 	  


	private int firstZero;
	private int secondZero;
	
	public SunHurricaneModel(JPanel jPanel) {
		graphics = new HashMap<String, ArrayList<BufferedImage>>();
		panel = jPanel;
		firstZero = 0;
		secondZero = panel.getWidth();
		location = new Pair(0,0);
	}
	
	public void move() {
		location.setX(location.getX()-2);
		location.setY(this.calculateY(location.getX()));
		
	}
	
	
	public int calculateY(int x) {
		//System.out.println("Panel height is: " + panel.getHeight());
		int y = (int)(-1*(panel.getHeight()*(Math.sin(((.00165)*x)))) + panel.getHeight());
		
		return y;
	}
	
	public Rectangle getBounds() {

		return new Rectangle(location.getX(),location.getY(), this.getWidth(), this.getHeight());
		
	}

	public HashMap<String, ArrayList<BufferedImage>> getGraphics() {
		return graphics;
	}

	public void setGraphics(HashMap<String, ArrayList<BufferedImage>> graphics) {
		this.graphics = graphics;
	}

	public void addPics() {
		ArrayList<BufferedImage> sun = new ArrayList<BufferedImage>();
		ArrayList<BufferedImage> cloudStates = new ArrayList<BufferedImage>();
		try {
			BufferedImage sunPic = ImageIO.read(new File("./Images/Game3/glowingbg.png"));
			sun.add(sunPic);
			
			BufferedImage hurrAngry = ImageIO.read(new File("./Images/Game3/angry_cloud.png"));
			BufferedImage hurrScared = ImageIO.read(new File("./Images/Game3/dismayed_cloud.png"));
			cloudStates.add(hurrAngry); cloudStates.add(hurrScared);
			
			graphics.put("SUN", sun);
			graphics.put("HURRICANE", cloudStates);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getHeight() {

		return height;

	}



	public void setHeight(int height) {

		this.height = height;

	}



	public int getWidth() {

		return width;

	}



	public void setWidth(int width) {

		this.width = width;

	}



	public Pair getLocation() {

		return location;

	}



	public void setLocation(Pair location) {

		this.location = location;

	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public int getInitialPosition() {
		return initialPosition;
	}

	public void setInitialPosition(int initialPosition) {
		this.initialPosition = initialPosition;
	}

	
}
