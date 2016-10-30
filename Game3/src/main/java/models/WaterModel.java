package models;

import java.awt.Rectangle;

import models.BeachModel.Pair;

public class WaterModel {
	private Pair location;
	
	public WaterModel() {
		
	}
	public Pair getLocation() {
		return location;
	}
	public void setLocation(Pair location) {
		this.location = location;
	}
	
	public Rectangle getBounds() {
		return (new Rectangle(this.getLocation().getX(),this.getLocation().getY(),30,30));
	}
}
