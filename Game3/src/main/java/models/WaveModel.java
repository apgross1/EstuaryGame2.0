package models;



import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
	private double movement;
	private Waves clusterGroup;
	private boolean lastWave;
	private Double screenSizeX = Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
	

	
	public WaveModel(int clusterVal) {
		movement = (screenSizeX*.00104);
		this.randomSpawn(clusterVal);
		this.move();
	}

	

	public void randomSpawn(int clusterVal) {
		Waves waveEnum = Waves.values()[clusterVal];
		setClusterGroup(waveEnum);
		location = new Pair(0,0);

		//Change 250 to make it dynamic (should be width of the shore line)
		location.setX((int)(screenSizeX - 250) + (int)(Math.random() * 500));
		location.setY(waveEnum.getMinY() + (int)(Math.random() * (((waveEnum.getMaxY()) - waveEnum.getMinY()) + 1)));
	}

	

	ActionListener movementTimer = new ActionListener() {

		@Override

		public void actionPerformed(ActionEvent e) {

			if(!isReceed()) {
				
				int velocity = (int)((screenSizeX*.3125)/(movement));
				
				location.setX((int)(location.getX()-velocity));
				
				movement += 1;
			}
			else {
				int velocity = (int)((screenSizeX*.3125/(movement)));
				location.setX((int)(location.getX()+velocity));
				//accelerator = -1*accelerator;
				movement -= 1; 
				
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
