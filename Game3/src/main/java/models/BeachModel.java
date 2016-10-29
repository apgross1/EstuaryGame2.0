package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import enums.Walls;
import enums.Waves;

public class BeachModel {
	private int squareCount;
	private int landSize;
	private Collection<BufferedImage> beachStates;
	private HashMap<Pair, Object> beachGrid;
	private int[][] positionGrid;
	private GabionPUModel gabPU;
	private ConcretePUModel concrPU;
	
	public BeachModel() {
		beachGrid = new HashMap<Pair,Object>();
		positionGrid = new int[10][10];
		gabPU = new GabionPUModel();
		concrPU = new ConcretePUModel();
		
	}
	
	public void spawnGabPU(ArrayList<Pair> ppul) {
		Random randLoc = new Random();
		Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
		beachGrid.put(pair, gabPU);
		gabPU.setLocation(pair);
		positionGrid[pair.getX()][pair.getY()] = Walls.GABION_GAME3.getValue();
		gabPU.setIsActive(true);
		
	}
	
	public void spawnConcrPU(ArrayList<Pair> ppul) {
		Random randLoc = new Random();
		Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
		beachGrid.put(pair, concrPU);
		concrPU.setLocation(pair);
		positionGrid[pair.getX()][pair.getY()] = Walls.CONCRETE_GAME3.getValue();
		concrPU.setIsActive(true);
	}
	
	public void removeConcrPU(Pair pair) {
		beachGrid.remove(pair);
		concrPU.setLocation(null);
		positionGrid[pair.getX()][pair.getY()] = 0;
		concrPU.setIsActive(false);
	}
	
	public void removeGabPU(Pair pair) {
		beachGrid.remove(pair);
		gabPU.setLocation(null);
		positionGrid[pair.getX()][pair.getY()] = 0;
		gabPU.setIsActive(false);
		
	}
	
	public ArrayList<Pair> generatePPUL() {
		ArrayList<Pair> ppulPair = new ArrayList<Pair>();
		for(int i = 0; i < positionGrid.length; i++) {
			for(int j = 0; j < positionGrid[i].length; j++) {
				if (positionGrid[i][j] == 0) {
					ppulPair.add(new Pair(i,j));
				}
				else {
					continue;
				}
			}
		}
		return ppulPair;
	}
	
	public void removeSquare(Pair waterLoc) {
		beachGrid.replace(waterLoc, new WaterModel());
		
		
		positionGrid[waterLoc.getX()][waterLoc.getY()] = Waves.WAVE_GAME3.getValue();
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

	public int[][] getPositionGrid() {
		return positionGrid;
	}

	public void setPositionGrid(int[][] beachGrid) {
		this.positionGrid = beachGrid;
	}
	
	public HashMap<Pair, Object> getBeachGrid() {
		return beachGrid;
	}

	public void setBeachGrid(HashMap<Pair, Object> beachGrid) {
		this.beachGrid = beachGrid;
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

	public class Pair {
		private int a;
		private int b;
		
		public Pair(int num1, int num2) {
			setY(num1);
			setX(num2);
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
