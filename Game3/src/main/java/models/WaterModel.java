package models;

import java.awt.Rectangle;

import models.BeachModel.Pair;

public class WaterModel {
	private Pair location;
	private int height;
	private int width;
	
	public WaterModel() {
		this.height = 200;
		this.width = 200;
	}
	
	public WaterModel(Pair loc) {
		location = loc;
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
}
