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
import Enums.TestControl;
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
	private TestControl GameState;


	/**
	 * Constructor for this element
	 * @param clusterVal the number cluster with which this wave is associated
	 * @param f the frame map used to perform dynamic scaling
	 */
	public WaveModel(int clusterVal, HashMap<Frames, JComponent> f, TestControl test) {
		this.setGameState(test);
		movement = (screenSizeX*.00104);
		wavePause = false;
		frames = f;
		this.randomSpawn(clusterVal);
		this.move();
	}

	public WaveModel() {
		movement = (screenSizeX*.00104);
		wavePause = false;
	}


	/**
	 * Assigns this element to a wave cluster group. The location of the wave
	 * is dependent on what cluster it belongs to.
	 * @param clusterVal
	 */
	public void randomSpawn(int clusterVal) {
		WaveClusters waveEnum = WaveClusters.values()[clusterVal];
		setClusterGroup(waveEnum);
		location = new Pair(0,0);

		//Change 250 to make it dynamic (should be width of the shore line)
		location.setX((int)(screenSizeX - 250) + (int)(Math.random() * 500));
		int beachHeight = 0;
		if(this.getGameState() == TestControl.NO_TEST) {
			beachHeight = frames.get(Frames.SHORE).getHeight();
		}
		else{
			beachHeight = 1000;
		}
		int blockOneMin = 0, blockOneMax = beachHeight/7;
		int blockTwoMin = blockOneMax+1, blockTwoMax = blockOneMax*2;
		int blockThreeMin = blockTwoMax+1, blockThreeMax = blockOneMax*3;
		int blockFourMin = blockThreeMax+1, blockFourMax = blockOneMax*4;
		int blockFiveMin = blockFourMax+1, blockFiveMax = blockOneMax*5;
		
		
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
	
	
	/**
	 * ActionListener to handle movement of this element. It is
	 * meant to replicate the natural motion of a wave.
	 */
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

	

	/**
	 * Timer used to move wave in 50 millisecond intervals based
	 * on movement defined in movementTimer.
	 */
	public void move() {
		Timer timer = new Timer(50, movementTimer);
		timer.setRepeats(true);
		timer.start();
	}
	
	
	/**
	 * Turns wavePause true. If this occurs, the wave will become motionless
	 * at its current location. This method is used primarily in the tutorial.
	 */
	public void pauseWave() {
		this.wavePause = true;
	}

	/**
	 * Called after pauseWave() to resume movement of this element.
	 * This method is used primarily in the tutorial.
	 */
	public void resumeWave() {
		this.wavePause = false;
	}
	
	/**
	 * Prepares to delete the wave. This method is used
	 * primarily in the tutorial.
	 */
	public void resetWave() {
		this.setDeleteWave(true);
	}
	
	/**
	 * Gets the bounds of the wave for collision detection purposes.
	 * @return Rectangle the bounds of the wave
	 */
	public Rectangle getBounds() {
		return new Rectangle(location.getX(),location.getY(), this.getWidth(), this.getHeight());
	}

	/**
	 * Gets the height of the wave
	 * @return height int, height of the wave
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the wave
	 * @param height int, height of the wave
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets the width of the wave
	 * @return width int, width of the wave
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the wave
	 * @param width int, width of the wave
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the location of the wave.
	 * @return location Pair, location of the wave
	 */
	public Pair getLocation() {
		return location;
	}

	/**
	 * Sets the location of the wave
	 * @param location Pair, location of the wave
	 */
	public void setLocation(Pair location) {
		this.location = location;
	}

	/**
	 * Flag telling the movement listener to reverse movement of wave.
	 * @return recede boolean, 1 if the wave is receding, 0 otherwise
	 */
	public boolean isReceed() {
		return receed;
	}

	/**
	 * Sets the value of the flag telling the movement listener to reverse movement of wave to false.
	 * This will have the wave continue in its original direction.
	 * @param receed boolean, 1 if the wave is receding, 0 otherwise
	 */
	public void setReceed(boolean receed) {
		this.receed = receed;
	}


	/**
	 * Get the wave cluster group of which this element is a member
	 * @return clusterGroup WaveClusters determining which cluster this member is a part of (Cluster 1 to 5)
	 */
	public WaveClusters getClusterGroup() {
		return clusterGroup;
	}


	/**
	 * Set the wave cluster group of which this element is a member
	 * @param clusterGroup WaveClusters determining which cluster this member is a part of (Cluster 1 to 5)
	 */
	public void setClusterGroup(WaveClusters waveEnum) {
		this.clusterGroup = waveEnum;
	}



	/**
	 * Determines if this element is the last wave in a cluster. This is used
	 * to signal to the View that a grid block is ready to be turned to water
	 * @return lastWave boolean, 1 if this element is the last wave, 0 otherwise
	 */
	public boolean isLastWave() {
		return lastWave;
	}


	/**
	 * Sets this element as the last wave in a cluster. This is used
	 * to signal to the View that a grid block is ready to be turned to water
	 * @return lastWave boolean, 1 if this element is the last wave, 0 otherwise
	 */
	public void setLastWave(boolean lastWave) {
		this.lastWave = lastWave;
	}

	/**
	 * Gets the frame map used mainly to set dimensions/borders relative to the screen size
	 * @return frameMap a boolean
	 */
	public HashMap<Frames, JComponent> getFrames() {
		return frames;
	}


	/**
	 * Sets the frame map
	 * @param frameMap a
	 */
	public void setFrames(HashMap<Frames, JComponent> frames) {
		this.frames = frames;
	}



	/**
	 * Gets the velocity of this element at a single point in time
	 * @return velocity int, the velocity of this element
	 */
	public int getVelocity() {
		return velocity;
	}


	/**
	 * Sets the velocity of this element at a single point in time
	 * @return velocity int, the velocity of this element
	 */
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}



	/**
	 * Determines if this element should be deleted. This is primarily used
	 * in the tutorial.
	 * @return deleteWave boolean, 1 if this element should be deleted, 0 otherwise
	 */
	public boolean isDeleteWave() {
		return deleteWave;
	}


	/**
	 * Sets this element as one to be deleted. This is primarily used
	 * in the tutorial.
	 * @return deleteWave boolean, 1 if this element should be deleted, 0 otherwise
	 */
	public void setDeleteWave(boolean deleteWave) {
		this.deleteWave = deleteWave;
	}

	/**
	 * Determines is wave was paused. 
	 * @return wavePause boolean, 1 if wave is paused, 0 otherwise
	 */
	public boolean getWaveStatePause() {
		return this.wavePause;
	}

	public TestControl getGameState() {
		return GameState;
	}

	public void setGameState(TestControl gameState) {
		GameState = gameState;
	}
	
}
