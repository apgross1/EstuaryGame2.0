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
	private HashMap<Pair, GridBlock> beachGrid;
	private int[][] positionGrid;
	private Pair gabPair = new Pair(0,0);
	private Pair concPair = new Pair(0,0);
	private Timer puTimer;

	
	public BeachModel() {
		beachGrid = new HashMap<Pair,GridBlock>();
		positionGrid = new int[10][8];
		this.initializeBeach();
	}
	
	public void initializeBeach() {
		
		ArrayList<Pair> pairList = this.generatePPUL();
		Iterator<Pair> it = pairList.iterator();
		while(it.hasNext()) {
			Pair tempPair = it.next();
			System.out.println("X is: " + tempPair.getX() + " " + "Y is: " + tempPair.getY());
			beachGrid.put(tempPair, (new GridBlock(tempPair)));
		}
	
	}
	
	public void spawnGabPU(ArrayList<Pair> ppul) {
		if(ppul.size() == 0) {
			return;
		}
		else{
			Random randLoc = new Random();
			Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
			setGabPair(this.findPairInGrid(pair));
			GabionPUModel tempGab = new GabionPUModel();
			tempGab.setLocation(this.findPairInGrid(pair));
			tempGab.setIsActive(true);
			beachGrid.get(this.findPairInGrid(pair)).setGabPU(tempGab);
			positionGrid[pair.getX()][pair.getY()] = Walls.GABION_GAME3.getValue();
		}
	}
	
	public void spawnConcrPU(ArrayList<Pair> ppul) {
		if(ppul.size() == 0) {
			return;
		}
		else{
			System.out.println("Spawning concrete");
			Random randLoc = new Random();
			Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
			setConcPair(pair);
			ConcretePUModel tempConcr = new ConcretePUModel();
			tempConcr.setLocation(this.findPairInGrid(pair));
			tempConcr.setActive(true);
			beachGrid.get(this.findPairInGrid(pair)).setConcrPU(tempConcr);
			positionGrid[pair.getX()][pair.getY()] = Walls.CONCRETE_GAME3.getValue();
		}
	}
	
	public void removeConcrPU(Pair pair) {
		beachGrid.get(this.findPairInGrid(pair)).getConcrPU().setActive(false);
		beachGrid.get(this.findPairInGrid(pair)).setVacant(true);
		setConcPair(new Pair(0,0));
		
		positionGrid[pair.getX()][pair.getY()] = 0;
	}
	
	public void removeGabPU(Pair pair) {
		beachGrid.get(this.findPairInGrid(pair)).getGabPU().setIsActive(false);
		beachGrid.get(this.findPairInGrid(pair)).setVacant(true);
		setGabPair(new Pair(0,0));
		
		positionGrid[pair.getX()][pair.getY()] = 0;
	}
	
	public void removeSquare(Pair waterLoc) {
		beachGrid.get(this.findPairInGrid(waterLoc)).setWater(new WaterModel(waterLoc), waterLoc);
		
		positionGrid[waterLoc.getX()][waterLoc.getY()] = Waves.WAVE_GAME3.getValue();
	}
	
	
	public ArrayList<Pair> generatePPUL() {
		ArrayList<Pair> ppulPair = new ArrayList<Pair>();
		for(int i = 0; i < positionGrid.length; i++) {
			for(int j = 0; j < positionGrid[i].length; j++) {
				if (positionGrid[i][j] == 0) {
					ppulPair.add(new Pair(j,i)); //NOTE!!!!! IT MAY BE PAIR(i,j) NOT (j,i)
				}
				else {
					continue;
				}
			}
		}
		return ppulPair;
	}
	
	
	
	public Pair findPairInGrid(Pair pair) {
		for(Pair p : this.getBeachGrid().keySet()) {
			if(pair.getX() == p.getX() && pair.getY()==p.getY()) {
				return p;
			}
		}
		return null;
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
	
	public HashMap<Pair, GridBlock> getBeachGrid() {
		return beachGrid;
	}

	public void setBeachGrid(HashMap<Pair, GridBlock> beachGrid) {
		this.beachGrid = beachGrid;
	}
	
	/*public GabionPUModel getGabPU() {
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
	}*/

	public Timer getPuTimer() {
		return puTimer;
	}

	public void setPuTimer(Timer puTimer) {
		this.puTimer = puTimer;
	}

	public Pair getGabPair() {
		return gabPair;
	}

	public void setGabPair(Pair gabPair) {
		this.gabPair = gabPair;
	}

	public Pair getConcPair() {
		return concPair;
	}

	public void setConcPair(Pair concPair) {
		this.concPair = concPair;
	}

}
