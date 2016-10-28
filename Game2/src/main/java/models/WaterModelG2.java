package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class WaterModelG2 {
	private int health;
	private int oxLevel;
	private ArrayList<BufferedImage> waterState;
	private int algConcentration;
	
	public WaterModelG2() {
		
	}

	public void incrAlgConcentration() {
		
	}
	
	public void decrAlgConcentration() {
		
	}
 
	public void incrOxygen() {
		
	}
	
	public void decOxygen() {
		
	}
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public ArrayList<BufferedImage> getWaterState() {
		return waterState;
	}

	public void setWaterState(ArrayList<BufferedImage> waterState) {
		this.waterState = waterState;
	}

	public int getOxLevel() {
		return oxLevel;
	}

	public void setOxLevel(int oxLevel) {
		this.oxLevel = oxLevel;
	}


	public int getAlgConcentration() {
		return algConcentration;
	}


	public void setAlgConcentration(int algConcentration) {
		this.algConcentration = algConcentration;
	}
	
	
}
