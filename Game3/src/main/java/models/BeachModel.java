package models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.swing.Timer;

import org.omg.CORBA.INITIALIZE;

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
	private Timer puTimer;
	
	public BeachModel() {
		beachGrid = new HashMap<Pair,Object>();
		positionGrid = new int[10][10];
		gabPU = new GabionPUModel();
		concrPU = new ConcretePUModel();
		this.initializeBeach();
	}
	
	public void initializeBeach() {
		ArrayList<Pair> pairList = this.generatePPUL();
		Iterator<Pair> it = pairList.iterator();
		while(it.hasNext()) {
			Pair tempPair = it.next();
			beachGrid.put(tempPair, new SandPatchModel());
		}
	}
	
	public void spawnGabPU(ArrayList<Pair> ppul) {
		Random randLoc = new Random();
		Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
		gabPU.setLocation(pair);
		gabPU.setIsActive(true);
		beachGrid.put(pair, new SandPatchModel(gabPU));
		positionGrid[pair.getX()][pair.getY()] = Walls.GABION_GAME3.getValue();
		
		
	}
	
	public void spawnConcrPU(ArrayList<Pair> ppul) {
		Random randLoc = new Random();
		Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
		concrPU.setIsActive(true);
		concrPU.setLocation(pair);
		beachGrid.put(pair, new SandPatchModel(concrPU));
		positionGrid[pair.getX()][pair.getY()] = Walls.CONCRETE_GAME3.getValue();
		
		
	}
	
	public void removeConcrPU(Pair pair) {
		SandPatchModel sp = (SandPatchModel)beachGrid.get(pair);
		sp.setVacant(true);
		beachGrid.replace(pair, sp);
		beachGrid.remove(pair);
		concrPU.setLocation(null);
		positionGrid[pair.getX()][pair.getY()] = 0;
		concrPU.setIsActive(false);
	}
	
	public void removeGabPU(Pair pair) {
		SandPatchModel sp = (SandPatchModel)beachGrid.get(pair);
		sp.setVacant(true);
		beachGrid.replace(pair, sp);
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

	public Timer getPuTimer() {
		return puTimer;
	}

	public void setPuTimer(Timer puTimer) {
		this.puTimer = puTimer;
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
