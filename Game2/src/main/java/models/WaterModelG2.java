package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class WaterModelG2 {
	private int algaeConcentration;
	private ArrayList waterStateAnim;
	private int health;
	private int oxLevel;
	private ArrayList<BufferedImage> waterState;
	private int algConcentration;
	
	
	public WaterModelG2() {
		
	}
	
	public void algaeCollide(){
		this.decOxygen();
		this.incrAlgConcentration();
		this.decreaseHealth();
	}
	
	public void decOxygen(){
		this.setOxLevel(getOxLevel()-1);
	}
	
	public void incrOxygen() {
		this.setOxLevel(getOxLevel()+1);
	}
	

	public void incrAlgConcentration() {
		this.setAlgConcentration(getAlgConcentration()+1);
	}
	
	public void decrAlgConcentration() {
		this.setAlgConcentration(getAlgConcentration()-1);
		
	}
 
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public void decreaseHealth(){
		this.setHealth(getHealth()-1);
	}
	public void increaseHealth(){
		this.setHealth(getHealth()+1);
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
