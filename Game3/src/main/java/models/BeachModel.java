package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.Timer;
import enums.Walls;
import enums.Waves;
import Enums.Frames;
import Enums.WaveClusters;

public class BeachModel {
	private int squareCount;
	private int landSize;
	
	private HashMap<Pair, GridBlock> beachGrid;
	private HashMap<WaveClusters, List<Pair>> gridLayers;
	private List<Pair> orderedPairs;
	private int[][] positionGrid;
	private Pair gabPair = new Pair(0,0);
	private Pair concPair = new Pair(0,0);
	private Timer puTimer;
	private HashMap<Frames, JComponent> frameMap;

	
	public BeachModel() {
		beachGrid = new HashMap<Pair,GridBlock>();
		gridLayers = new HashMap<WaveClusters, List<Pair>>();
		positionGrid = new int[7][7];
		orderedPairs = new ArrayList<Pair>();
		this.initializeBeach();
	}
	
	//Testing purposes
	public BeachModel(String test) {
		beachGrid = new HashMap<Pair,GridBlock>();
		gridLayers = new HashMap<WaveClusters, List<Pair>>();
		positionGrid = new int[7][7];
		orderedPairs = new ArrayList<Pair>();
	}
	
	public void initializeBeach() {
		
		ArrayList<Pair> pairList = this.generatePPUL();
		Collections.sort(pairList, new PairComparator());
		Iterator<Pair> it = pairList.iterator();
	
		while(it.hasNext()) {
			Pair tempPair = it.next();
			//System.out.println("("+tempPair.getX()+","+tempPair.getY()+")");
			GridBlock g = new GridBlock(tempPair, this);
			g.addPic();
			g.setLocation(tempPair);
			beachGrid.put(tempPair, g);
		}
		
		
		
		
		Collection<Pair> blockLocs = beachGrid.keySet();
		Iterator<Pair> pairIt = blockLocs.iterator();
		while(pairIt.hasNext()) {
			orderedPairs.add(pairIt.next());
		}
		Collections.sort(orderedPairs, new PairComparator());
		
		int i = 0;
		int j = 0;
		while (i < 5) {
			List<Pair> tempLane = new ArrayList<Pair>();
			while((j%8) < 7) {
				tempLane.add(new Pair((j%8),i));
				j++;
			}
			switch(i) {
				case(0):
					System.out.println("Cluster 1");
					for(Pair p : tempLane) {
						
						System.out.println("("+p.getX()+","+p.getY()+")");
					}
					gridLayers.put(WaveClusters.CLUSTER_ONE, tempLane);
					break;
				case(1):
					System.out.println("Cluster 2");
					for(Pair p : tempLane) {
						System.out.println("("+p.getX()+","+p.getY()+")");
					}
					gridLayers.put(WaveClusters.CLUSTER_TWO, tempLane);
					break;
				case(2):
					System.out.println("Cluster 3");
					for(Pair p : tempLane) {
						System.out.println("("+p.getX()+","+p.getY()+")");
					}
					gridLayers.put(WaveClusters.CLUSTER_THREE, tempLane);
					break;
				case(3):
					System.out.println("Cluster 4");
					for(Pair p : tempLane) {
						System.out.println("("+p.getX()+","+p.getY()+")");
					}
					gridLayers.put(WaveClusters.CLUSTER_FOUR, tempLane);
					break;
				case(4):
					System.out.println("Cluster 5");
					for(Pair p : tempLane) {
						System.out.println("("+p.getX()+","+p.getY()+")");
					}
					gridLayers.put(WaveClusters.CLUSTER_FIVE, tempLane);
					break;
			}
			j++;
			i++;
		}
		
		
	}
	
	
	
	public List<Pair> getOrderedPairs() {
		return orderedPairs;
	}

	public void setOrderedPairs(List<Pair> orderedPairs) {
		this.orderedPairs = orderedPairs;
	}

	public HashMap<WaveClusters, List<Pair>> getGridLayers() {
		return gridLayers;
	}

	public void setGridLayers(HashMap<WaveClusters, List<Pair>> gridLayers) {
		this.gridLayers = gridLayers;
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
			tempGab.setFrameMap(frameMap);
			tempGab.addPics();
			tempGab.setLocation(this.findPairInGrid(pair));
			tempGab.setIsActive(true);
			beachGrid.get(this.findPairInGrid(pair)).setGabPU(tempGab);
			positionGrid[pair.getY()][pair.getX()] = Walls.GABION_GAME3.getValue();
		}
	}
	
	public void spawnConcrPU(ArrayList<Pair> ppul) {
		if(ppul.size() == 0) {
			return;
		}
		else{
			Random randLoc = new Random();
			Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
			setConcPair(pair);
			ConcretePUModel tempConcr = new ConcretePUModel();
			tempConcr.setFrameMap(frameMap);
			tempConcr.addPics();
			tempConcr.setLocation(this.findPairInGrid(pair), "game");
			tempConcr.setActive(true);
			beachGrid.get(this.findPairInGrid(pair)).setConcrPU(tempConcr);
			
			positionGrid[pair.getY()][pair.getX()] = Walls.CONCRETE_GAME3.getValue();
		}
	}
	
	public void removeConcrPU(Pair pair) {
		beachGrid.get(this.findPairInGrid(pair)).getConcrPU().setActive(false);
		beachGrid.get(this.findPairInGrid(pair)).setVacant(true);
		setConcPair(new Pair(0,0));
		
		positionGrid[pair.getY()][pair.getX()] = 0;
	}
	
	public void removeGabPU(Pair pair) {
		beachGrid.get(this.findPairInGrid(pair)).getGabPU().setIsActive(false);
		beachGrid.get(this.findPairInGrid(pair)).setVacant(true);
		setGabPair(new Pair(0,0));
		
		positionGrid[pair.getY()][pair.getX()] = 0;
	}
	
	public void removeSquare(Pair waterLoc, String test) {
		if(test == "test" || test == "Test"){
			beachGrid.get(this.findPairInGrid(waterLoc)).setWater(new WaterModel(waterLoc), waterLoc,"test");
			
			positionGrid[waterLoc.getY()][waterLoc.getX()] = Waves.WAVE_GAME3.getWaveID();
			
		}
		else{
			
		beachGrid.get(this.findPairInGrid(waterLoc)).setWater(new WaterModel(waterLoc), waterLoc, "Game");
		
		positionGrid[waterLoc.getY()][waterLoc.getX()] = Waves.WAVE_GAME3.getWaveID();
		}
	}
	
	
	public ArrayList<Pair> generatePPUL() {
		int counter = 0;
		ArrayList<Pair> ppulPair = new ArrayList<Pair>();
		for(int i = 0; i < positionGrid.length; i++) {
			for(int j = 0; j < positionGrid[i].length; j++) {
				if (positionGrid[i][j] == 0) {
					counter++;
					ppulPair.add(new Pair(j,i));
				}
				else {
					continue;
				}
			}
		}
		System.out.println("Possible locations generated at initialization: " + counter);
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

	public HashMap<Frames, JComponent> getFrameMap() {
		return frameMap;
	}

	public void setFrameMap(HashMap<Frames, JComponent> frameMap) {
		this.frameMap = frameMap;
	}

}
