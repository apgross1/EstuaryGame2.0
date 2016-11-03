package models;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class WaveModel {
	private int height = 15;
	private int width = 15;
	private Pair location;
	
	public WaveModel() {
		this.randomSpawn();
		this.move();
	}
	
	public void randomSpawn() {
		Random rand = new Random();
		location = new Pair(0,0);
		location.setX(900);
		location.setY(rand.nextInt(800));
	}
	
	ActionListener movementTimer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			location.setX(location.getX()-5);
			Object time = e.getSource();
			Timer myTime = (Timer) time;
		}
	};
	
	public void move() {
		Timer timer = new Timer(50, movementTimer);
		timer.setRepeats(true);
		timer.start();
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

}
