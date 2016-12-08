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
	
	/**
	 * Constructor for this element.
	 * @param jPanel
	 */
	public SunHurricaneModel(JPanel jPanel) {
		panel = jPanel;
		location = new Pair(0,0);
	}
	
	//for testing purposes
	public SunHurricaneModel() {
		location = new Pair(0,0);
	}
	
	/**
	 * Changes position of the sun/hurricane
	 */
	public void move() {
		location.setX(location.getX()-2);
		location.setY(this.calculateY(location.getX()));	
	}
	
	
	/**
	 * Calculates they location of the sun/hurricane given its x position
	 * @param x int, the x positon of the hurricane/sun
	 * @return y int, the y position of the hurricane/sun
	 */
	public int calculateY(int x) {
		int y = (int)(-1*(panel.getHeight()*(Math.sin(((.00165)*x)))) + panel.getHeight());
		
		return y;
	}
	
	/**
	 * Gets the bounds of the sun/hurricane for collisin purposes
	 * @return Rectangle enveloping the sun/hurricane
	 */
	public Rectangle getBounds() {
		return new Rectangle(location.getX(),location.getY(), this.getWidth(), this.getHeight());
	}


	/**
	 * Gets the height of the sun/hurricane
	 * @return height int, the height (y value) of the sun/hurricane
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * Sets the height of the sun/hurricane
	 * @param height int, the height (y value) of the sun/hurricane
	 */
	public void setHeight(int height) {
		this.height = height;
	}



	/**
	 * Gets the width of the sun/hurricane
	 * @param width int, the width (x value) of the sun/hurricane
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the sun/hurricane
	 * @param width int, the width (x value) of the sun/hurricane
	 */
	public void setWidth(int width) {
		this.width = width;
	}



	/**
	 * Get the location of the sun/hurricane
	 * @return location Pair, the location of the sun/hurricane
	 */
	public Pair getLocation() {

		return location;

	}


	/**
	 * Set the location of the sun/hurricane
	 * @return location Pair, the location of the sun/hurricane
	 */
	public void setLocation(Pair location) {

		this.location = location;

	}
	
	/**
	 * Gets the panel in which the sun/hurricane are located. This
	 * is used primarily to dynamically set the location of
	 * the sun/hurricane given the size of the screen.
	 * @return panel JPanel, the panel in which the sun/hurricane are located
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Sets the panel in which the sun/hurricane are located. This
	 * is used primarily to dynamically set the location of
	 * the sun/hurricane given the size of the screen.
	 * @return panel JPanel, the panel in which the sun/hurricane are located
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	
	/**
	 * Gets the intial position of the sun/hurricane prior to starting the game
	 * @return intialPosition int, represents the initial "x" (horizontal) location 
	 */
	public int getInitialPosition() {
		return initialPosition;
	}

	/**
	 * Sets the intial position of the sun/hurricane prior to starting the game
	 * @return intialPosition int, represents the initial "x" (horizontal) location 
	 */
	public void setInitialPosition(int initialPosition) {
		this.initialPosition = initialPosition;
	}

	
}
