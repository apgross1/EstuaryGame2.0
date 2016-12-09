package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;

public abstract class AnimalModelAbstract {
	private int locX;
	private int locY;
	private int health;
	private int maxHealth;
	private boolean emptyHanded;
	private HashMap<Direction, ArrayList<BufferedImage>> moveAnimations;
	private Direction currDir;
	
	
	public int getLocX() {
		return locX;
	}
	public void setLocX(int locX) {
		this.locX = locX;
	}
	public int getLocY() {
		return locY;
	}
	public void setLocY(int locY) {
		this.locY = locY;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public boolean isEmptyHanded() {
		return emptyHanded;
	}
	public void setEmptyHanded(boolean emptyHanded) {
		this.emptyHanded = emptyHanded;
	}
	public Direction getCurrDir() {
		return currDir;
	}

	public void setCurrDir(Direction d) {
		this.currDir = d;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
}
