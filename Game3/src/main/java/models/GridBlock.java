package models;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import models.BeachModel.Pair;

public class GridBlock {
	private Pair location;
	private GabionPUModel gabPU;
	private ConcretePUModel concrPU;
	private WaterModel water;
	private boolean vacant;
	private int height;
	private int width;
	
	public GridBlock() {
		height = 200;
		width = 200;
		vacant = true;
	}
	
	public GridBlock(Pair loc) {
		location = loc;
		height = 200;
		width = 200;
		vacant = true;
	}
	
	public GridBlock(WallModelAbstract powerUp, Pair loc) {
		if (powerUp instanceof ConcretePUModel) {
			this.setConcrPU((ConcretePUModel)powerUp);
		}
		else {
			this.setGabPU((GabionPUModel) powerUp);
		}
		this.location = loc;
		this.setVacant(false);
	}
	
	
	public void setWater(WaterModel water) {
		gabPU = null;
		concrPU = null;
		this.water = water;
		this.setVacant(false);
		
	}
	
	public Pair getLocation() {
		return location;
	}

	public void setLocation(Pair location) {
		this.location = location;
	}

	public GabionPUModel getGabPU() {
		return gabPU;
	}

	public void setGabPU(GabionPUModel gabPU) {
		concrPU = null;
		water = null;
		this.gabPU = gabPU;
	}

	public ConcretePUModel getConcrPU() {
		return concrPU;
	}

	public void setConcrPU(ConcretePUModel concrPU) {
		this.concrPU = concrPU;
		gabPU = null;
		water = null;
	}

	public boolean isVacant() {
		return vacant;
	}

	public void setVacant(boolean vacant) {
		this.vacant = vacant;
		this.water = null;
		this.gabPU = null;
		this.concrPU = null;
	}
	
	public Rectangle getBounds() {
		return (new Rectangle(this.getLocation().getX(),this.getLocation().getY(),this.getWidth(),this.getHeight()));
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

	public WaterModel getWater() {
		return water;
	}

	
}
