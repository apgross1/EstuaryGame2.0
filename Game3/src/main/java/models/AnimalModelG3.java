package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;

public class AnimalModelG3 extends AnimalModelAbstract{
	private HashMap<Direction,ArrayList<BufferedImage>> animations;
	private int health;
	private int xloc;
	private int yloc;
	private int emptyHanded;

	@Override
	public void healthUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void healthDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pickUp() {
		// TODO Auto-generated method stub
		
	}

	public HashMap<Direction,ArrayList<BufferedImage>> getAnimations() {
		return animations;
	}

	public void setAnimations(HashMap<Direction,ArrayList<BufferedImage>> animations) {
		this.animations = animations;
	}
	
	public void setXloc(int Xloc){
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

	public void setHealth(int health){
		this.health = health;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setEmptyHanded(int emptyHanded){
		this.emptyHanded = emptyHanded;
	}
	
	public int getEmptyHanded(){
		return emptyHanded;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
}
