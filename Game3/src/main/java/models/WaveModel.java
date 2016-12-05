package models;



import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.Timer;

import Enums.Frames;
import Enums.WaveClusters;
import enums.Waves;

public class WaveModel {

	private int height = 25;
	private int width = 25;
	private Pair location;
	private boolean receed = false;
	private double movement;
	private int velocity;
	private WaveClusters clusterGroup;
	private boolean lastWave;
	private Double screenSizeX = Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
	private HashMap<Frames, JComponent> frames;
	private boolean wavePause;
	private boolean deleteWave = false;


	public WaveModel(int clusterVal, HashMap<Frames, JComponent> f) {
		movement = (screenSizeX*.00104);
		wavePause = false;
		frames = f;
		this.randomSpawn(clusterVal);
		this.move();
	}

	

	public void randomSpawn(int clusterVal) {
		WaveClusters waveEnum = WaveClusters.values()[clusterVal];
		setClusterGroup(waveEnum);
		location = new Pair(0,0);

		//Change 250 to make it dynamic (should be width of the shore line)
		location.setX((int)(screenSizeX - 250) + (int)(Math.random() * 500));
		
		int beachHeight = frames.get(Frames.SHORE).getHeight();
		int blockOneMin = 0, blockOneMax = beachHeight/7;
		int blockTwoMin = blockOneMax+1, blockTwoMax = blockOneMax*2;
		int blockThreeMin = blockTwoMax+1, blockThreeMax = blockOneMax*3;
		int blockFourMin = blockThreeMax+1, blockFourMax = blockOneMax*4;
		int blockFiveMin = blockFourMax+1, blockFiveMax = blockOneMax*5;
		int blockSixMin = blockFiveMin+1, blockSixMax = blockOneMax*6;
		int blockSevenMin = blockSixMin+1, blockSevenMax = blockOneMax*7;
		
		
		switch(clusterGroup){
			case CLUSTER_ONE:
				location.setY(blockOneMin + (int)(Math.random() * (((blockOneMax - blockOneMin) + 1))));
				break;
			case CLUSTER_TWO:
				location.setY(blockTwoMin + (int)(Math.random() * (((blockTwoMax - blockTwoMin) + 1))));
				break;
			case CLUSTER_THREE:
				location.setY(blockThreeMin + (int)(Math.random() * (((blockThreeMax - blockThreeMin) + 1))));
				break;
			case CLUSTER_FOUR:
				location.setY(blockFourMin + (int)(Math.random() * (((blockFourMax - blockFourMin) + 1))));
				break;
			case CLUSTER_FIVE:
				location.setY(blockFiveMin + (int)(Math.random() * (((blockFiveMax - blockFiveMin) + 1))));
				break;
		}
	
	}
	
	
	ActionListener movementTimer = new ActionListener() {

		@Override

		public void actionPerformed(ActionEvent e) {

			if(!isReceed()) {
				if(wavePause) {
					setVelocity(0);
				}
				else{
					setVelocity((int)((screenSizeX*.3125)/(movement)));
				}
				location.setX((int)(location.getX()-getVelocity()));
				
				movement += 1;
			}
			else {
				if(wavePause) {
					setVelocity(0);
				}
				else{
					setVelocity((int)((screenSizeX*.3125/(movement))));
				}
				
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
	
	
	public void pauseWave() {
		this.wavePause = true;
	}

	public void resumeWave() {
		this.wavePause = false;
	}
	
	public void resetWave() {
		/*this.resumeWave();
		this.setReceed(true);
		location.setX(frames.get(Frames.ANIMAL).getWidth()+frames.get(Frames.SHORE).getWidth());*/
		this.setDeleteWave(true);
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


	public WaveClusters getClusterGroup() {
		return clusterGroup;
	}



	public void setClusterGroup(WaveClusters waveEnum) {
		this.clusterGroup = waveEnum;
	}



	public boolean isLastWave() {
		return lastWave;
	}



	public void setLastWave(boolean lastWave) {
		this.lastWave = lastWave;
	}

	public HashMap<Frames, JComponent> getFrames() {
		return frames;
	}



	public void setFrames(HashMap<Frames, JComponent> frames) {
		this.frames = frames;
	}



	public int getVelocity() {
		return velocity;
	}



	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}



	public boolean isDeleteWave() {
		return deleteWave;
	}



	public void setDeleteWave(boolean deleteWave) {
		this.deleteWave = deleteWave;
	}
	
}
