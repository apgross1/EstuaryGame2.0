package models;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import models.BeachModel.Pair;

public class SandPatchModel {
	private Pair location;
	private GabionPUModel gabPU;
	private ConcretePUModel concrPU;
	private boolean vacant;
	private int height;
	private int width;
	
	public SandPatchModel() {
		height = 200;
		width = 200;
	}
	
	public SandPatchModel(Pair loc) {
		location = loc;
		height = 200;
		width = 200;
	}
	
	public SandPatchModel(WallModelAbstract powerUp) {
		if (powerUp instanceof ConcretePUModel) {
			concrPU = (ConcretePUModel)powerUp;
		}
		else {
			gabPU = (GabionPUModel) powerUp;
		}
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
		this.gabPU = gabPU;
	}

	public ConcretePUModel getConcrPU() {
		return concrPU;
	}

	public void setConcrPU(ConcretePUModel concrPU) {
		this.concrPU = concrPU;
	}

	public boolean isVacant() {
		return vacant;
	}

	public void setVacant(boolean vacant) {
		this.vacant = vacant;
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
}
