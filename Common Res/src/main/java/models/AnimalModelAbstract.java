package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;

public abstract class AnimalModelAbstract {
	public int locX;
	public int locY;
	public int health;
	public boolean emptyHanded;
	public HashMap<Direction, ArrayList<BufferedImage>> animations;
	public Direction currDir;
	
	
	public abstract void healthUp();
	public abstract void healthDown();
	public abstract void pickUp();
	public abstract void move();
	

}
