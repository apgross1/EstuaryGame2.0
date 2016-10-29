package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;

public class AnimalModelG3 extends AnimalModelAbstract{
	private HashMap<Direction,ArrayList<BufferedImage>> animations;
	private int health;
	private int xloc; //exists in abstract model not needed?
	private int yloc; //exists in abstract model not needed?
	//Exists in abstract model;
	//private int emptyHanded;

	@Override
	public void healthUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void healthDown() {
		// TODO Auto-generated method stub
		// this.health = 0;
	}

	@Override
	public void pickUp() {
		// TODO Auto-generated method stub
		// Need to address abstract class as EmptyHanded is boolean
		//this.setEmptyHanded(false);
		
	}

	public HashMap<Direction,ArrayList<BufferedImage>> getAnimations() {
		return animations;
	}

	public void setAnimations(HashMap<Direction,ArrayList<BufferedImage>> animations) {
		this.animations = animations;
	}
	
	//exists in abstract model not needed?
	/*public void setXloc(int Xloc){
		this.xloc = Xloc;
	}
	
	public int getXloc(){
		return xloc;
	}

	public void setYloc(int yloc){
		this.yloc = yloc;
	}
	
	public int getYloc(){
		return yloc;
	}
*/
	public void setHealth(int health){
		this.health = health;
	}
	
	public int getHealth(){
		return health;
	}
	
	/* Exists in abstract model
	public void setEmptyHanded(int emptyHanded){
		this.emptyHanded = emptyHanded;
	}
	
	public int getEmptyHanded(){
		return emptyHanded;
	}*/
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		switch(this.getCurrDir()){
			case NORTH:
				this.setLocY(this.getLocY() - 1);
				break;
			case SOUTH:
				this.setLocY(this.getLocY() + 1);
				break;
			case EAST:
				this.setLocX(this.getLocX() + 1);
				break;
			case WEST:
				this.setLocX(this.getLocX() - 1);
				break;
			case NORTH_EAST:
				this.setLocX(this.getLocX() + 1);
				this.setLocY(this.getLocY() - 1);
				break;
			case NORTH_WEST:
				this.setLocX(this.getLocX() - 1);
				this.setLocY(this.getLocY() - 1);
				break;
			case SOUTH_EAST:
				this.setLocX(this.getLocX() + 1);
				this.setLocY(this.getLocY() + 1);
				break;
			case SOUTH_WEST:
				this.setLocX(this.getLocX() - 1);
				this.setLocY(this.getLocY() + 1);
				break;
		}
		
	}
}
