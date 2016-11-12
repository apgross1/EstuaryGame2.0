package models;



import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;




import javax.swing.Timer;



import enums.Waves;

public class WaveModel {

	private int height = 30;
	private int width = 30;
	private Pair location;
	private boolean receed = false;
	private double accelerator = 1.01;
	private double movement = 3;
	private Waves clusterGroup;
	private boolean lastWave;
	

	
	public WaveModel(int clusterVal) {
		this.randomSpawn(clusterVal);
		this.move();
	}

	

	public void randomSpawn(int clusterVal) {
		Waves waveEnum = Waves.values()[clusterVal-1];
		setClusterGroup(waveEnum);
		location = new Pair(0,0);

		location.setX(990 + (int)(Math.random() * ((2000 - 900) + 1)));
		location.setY(waveEnum.getMinY() + (int)(Math.random() * (((waveEnum.getMaxY()) - waveEnum.getMinY()) + 1)));
	}

	

	ActionListener movementTimer = new ActionListener() {

		@Override

		public void actionPerformed(ActionEvent e) {

			if(!isReceed()) {

				location.setX((int)(location.getX()-Math.pow(movement, accelerator)));
				movement = Math.pow(movement, accelerator);
			}
			else {
				
				if(accelerator > 0) {
					accelerator = -1*accelerator;
				}
				location.setX((int)(location.getX()+Math.pow(movement, accelerator)));
				//accelerator = -1*accelerator;
				movement = Math.pow(movement, accelerator);
				
			}
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

	public boolean isReceed() {
		return receed;
	}

	public void setReceed(boolean receed) {
		this.receed = receed;
	}

	public double getAccelerator() {
		return accelerator;
	}

	public void setAccelerator(double accelerator) {
		this.accelerator = accelerator;
	}



	public Waves getClusterGroup() {
		return clusterGroup;
	}



	public void setClusterGroup(Waves clusterGroup) {
		this.clusterGroup = clusterGroup;
	}



	public boolean isLastWave() {
		return lastWave;
	}



	public void setLastWave(boolean lastWave) {
		this.lastWave = lastWave;
	}

}
