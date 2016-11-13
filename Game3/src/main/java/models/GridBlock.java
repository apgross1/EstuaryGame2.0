package models;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class GridBlock {
	private Pair location;
	private GabionPUModel gabPU = new GabionPUModel();
	private ConcretePUModel concrPU = new ConcretePUModel();
	private WaterModel water = new WaterModel();
	private boolean vacant;
	private int height;
	private int width;
	private BeachModel beach;
	private Pair viewLocation = new Pair(0,0);
	
	public GridBlock(BeachModel b) {
		gabPU.setIsActive(false);
		concrPU.setActive(false);
		height = 200;
		width = 200;
		vacant = true;
		beach = b;
	}
	
	public GridBlock(Pair loc, BeachModel b) {
		gabPU = new GabionPUModel();
		gabPU.setIsActive(false);
		beach = b;
		concrPU = new ConcretePUModel();
		concrPU.setActive(false);
		location = loc;
		this.setViewLocation(location);
		height = 200;
		width = 200;
		vacant = true;
	}
	
	public GridBlock(ConcretePUModel powerUp, Pair loc) {
		
		gabPU.setIsActive(false);
		concrPU.setActive(false);
		powerUp.setLocation(loc);
		this.setConcrPU(powerUp);
		this.location = loc;
		this.setViewLocation(location);
		this.setVacant(false);
	}
	public GridBlock(GabionPUModel powerUp, Pair loc) {
		
		gabPU.setIsActive(false);
		concrPU.setActive(false);
		powerUp.setLocation(loc);
		this.setGabPU(powerUp);
		this.location = loc;
		this.setViewLocation(location);
		this.setVacant(false);
	}
	
	public void setWater(WaterModel water, Pair loc) {
		gabPU.setIsActive(false);
		concrPU.setActive(false);
		water.setLocation(loc);
		this.water = water;
		this.water.setActive(true);
		//this.location = loc;
		this.setVacant(false);
		
		beach.getPositionGrid()[loc.getX()][loc.getY()] = 2;
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
		concrPU.setActive(false);
		water.setActive(false);
		gabPU.setIsActive(true);
		this.gabPU = gabPU;
		this.setVacant(false);
	}

	public ConcretePUModel getConcrPU() {
		return concrPU;
	}

	public void setConcrPU(ConcretePUModel concrPU) {
		concrPU.setActive(true);
		this.concrPU = concrPU;
		this.gabPU.setIsActive(false);
		this.water.setActive(false);
		
		this.setVacant(false);
	}

	public boolean isVacant() {
		return vacant;
	}

	public void setVacant(boolean vacant) {
		this.vacant = vacant;
		if(vacant) {
			this.water.setActive(false);
			this.gabPU.setIsActive(false);
			this.concrPU.setActive(false);
		}
	}
	
	public Rectangle getBounds() {
		return (new Rectangle(this.getViewLocation().getX(),this.getViewLocation().getY(),this.getWidth(),this.getHeight()));
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
	
	public Pair getViewLocation() {
		return viewLocation;
	}
	public void setViewLocation(Pair viewLocation) {
		this.viewLocation = viewLocation;
	}

	
}
