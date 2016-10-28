package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class BeachModel {
	private int squareCount;
	private int landSize;
	private Collection<BufferedImage> beachStates;
	private HashMap<Pair, Object> beachGrid;
	private int[] positionGrid;
	
	public BeachModel() {
		
	}
	
	public void spawnGabPU() {
		
	}
	
	public void spawnConcrPU() {
		
	}
	
	public ArrayList<Pair> generatePPUL() {
		return null;
	}
	
	public void removeSquare() {
		
	}
	
	public int getSquareCount(){
		return squareCount;
	}
	
	public void setSquareCount(int count){
		squareCount = count;
	}

	public int getLandSize() {
		return landSize;
	}

	public void setLandSize(int landSize) {
		this.landSize = landSize;
	}

	public Collection<BufferedImage> getBeachStates() {
		return beachStates;
	}

	public void setBeachStates(Collection<BufferedImage> beachStates) {
		this.beachStates = beachStates;
	}

	public int[] getPositionGrid() {
		return positionGrid;
	}

	public void setPositionGrid(int[] beachGrid) {
		this.positionGrid = beachGrid;
	}
	
	public HashMap<Pair, Object> getBeachGrid() {
		return beachGrid;
	}

	public void setBeachGrid(HashMap<Pair, Object> beachGrid) {
		this.beachGrid = beachGrid;
	}

	public class Pair {
		private int a;
		private int b;
		
		public Pair(int num1, int num2) {
			setX(num1);
			setY(num2);
		}

		public int getX() {
			return a;
		}

		public void setX(int a) {
			this.a = a;
		}

		public int getY() {
			return b;
		}

		public void setY(int b) {
			this.b = b;
		}
	}
	
	
}
