package models;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class SunHurricaneModel {

	private int height = 100;
	private int width = 100;
	private Pair location;
	private JPanel panel;
	private int initialPosition;
	


	private int firstZero;
	private int secondZero;
	
	public SunHurricaneModel(JPanel p) {
		
		panel = p;
		firstZero = 0;
		secondZero = panel.getWidth();
		location = new Pair(0,0);
	}
	
	public void move() {
		location.setX(location.getX()-1);
		location.setY(this.calculateY(location.getX()));
		
	}
	
	
	public int calculateY(int x) {
		int y = ((x-firstZero)*(x-secondZero))/(1600)+initialPosition;
		return y;
	}
	
	public Rectangle getBounds() {

		return new Rectangle(location.getX(),location.getY(), this.getWidth(), this.getHeight());

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
