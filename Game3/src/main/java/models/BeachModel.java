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
	private String GameState = "game";

	
	/**
	 * Constructor for this element. Initializes the
	 * i)   beach grid, a hashmap of all (x,y) coordinate pairs and their respective gridblock
	 * ii)  gridLayrers, a hashmap consisting of the locations of the wave lanes
	 * iii) positionGrid, a 7x7 grid to  plot int representations of objects on the screen
	 * iv)  orderedPairs, an ordered arrayList of grid pairs
	 */
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
	
	
	/**
	 * Creates the beach by populating beach grid and position grid with coordinate pairs.
	 * Additionally, wave cluster positions are assigned and wave clusters are placed in a HashMap
	 * to store the location of each wave cluster while generating waves.
	 */
	public void initializeBeach() {
		
		ArrayList<Pair> pairList = this.generatePPUL();
		Collections.sort(pairList, new PairComparator());
		Iterator<Pair> it = pairList.iterator();
	
		while(it.hasNext()) {
			Pair tempPair = it.next();
			//System.out.println("("+tempPair.getX()+","+tempPair.getY()+")");
			GridBlock g = new GridBlock(tempPair, this);
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
	
	/**
	 * Spawns a gabion power-up on the beach. The location of
	 * the gabion power-up is chosen randomly. Once created, the 
	 * gabion power-up is added to both the beach grid and position grid.
	 * @param ppul the arrayList of possible power-up locations
	 * @param isTutorial a boolean. If true, the method is running a version of itself designed for testing
	 */
	public void spawnGabPU(ArrayList<Pair> ppul, boolean isTutorial) {
		if(ppul.size() == 0) {
			return;
		}
		else{
			if(isTutorial) {
				Pair pair = new Pair(2,2);
				setGabPair(this.findPairInGrid(pair));
				GabionPUModel tempGab = new GabionPUModel();
				tempGab.setFrameMap(frameMap);
				tempGab.setLocation(this.findPairInGrid(pair), GameState );
				tempGab.setIsActive(true);
				beachGrid.get(this.findPairInGrid(pair)).setGabPU(tempGab);
				positionGrid[pair.getY()][pair.getX()] = Walls.GABION_GAME3.getValue();
			}
			else{
				Random randLoc = new Random();
				Pair pair = ppul.get(randLoc.nextInt(ppul.size()));
				setGabPair(this.findPairInGrid(pair));
				GabionPUModel tempGab = new GabionPUModel();
				tempGab.setFrameMap(frameMap);
				tempGab.setLocation(this.findPairInGrid(pair), GameState);
				tempGab.setIsActive(true);
				beachGrid.get(this.findPairInGrid(pair)).setGabPU(tempGab);
				positionGrid[pair.getY()][pair.getX()] = Walls.GABION_GAME3.getValue();
			}
		}
	}
	
	/**
	 * Spawns a concrete power-up on the beach. The location of
	 * the concrete power-up is chosen randomly. Once created, the 
	 * concrete power-up is added to both the beach grid and position grid.
	 * @param ppul the arrayList of possible power-up locations
	 */
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
			tempConcr.setLocation(this.findPairInGrid(pair), GameState);
			tempConcr.setActive(true);
			beachGrid.get(this.findPairInGrid(pair)).setConcrPU(tempConcr);
			
			positionGrid[pair.getY()][pair.getX()] = Walls.CONCRETE_GAME3.getValue();
		}
	}
	
	/**
	 * Removes concrete power-up from the beach. This will remove the concrete power-up from
	 * both the beach grid and the position grid.
	 * @param pair instance of Pair where the concrete power-up is located
	 */
	public void removeConcrPU(Pair pair) {
		beachGrid.get(this.findPairInGrid(pair)).getConcrPU().setActive(false);
		beachGrid.get(this.findPairInGrid(pair)).setVacant(true);
		setConcPair(new Pair(0,0));
		positionGrid[pair.getY()][pair.getX()] = 0;
	}
	
	/**
	 * Removes gabion power-up from the beach. This will remove the gabion power-up from
	 * both the beach grid and the position grid.
	 * @param pair instance of Pair where the gabion power-up is located
	 */
	public void removeGabPU(Pair pair) {
		beachGrid.get(this.findPairInGrid(pair)).getGabPU().setIsActive(false);
		beachGrid.get(this.findPairInGrid(pair)).setVacant(true);
		setGabPair(new Pair(0,0));
		positionGrid[pair.getY()][pair.getX()] = 0;
	}
	
	/**
	 * Removes a sand tile from play. This is reflected in the game by the changing of
	 * a sand block to a water block. The value of the grid cell at that position
	 * is updated in both the beach grid and the position grid.
	 * @param waterLoc the location of where the water tile should be placed
	 * @param test used to change the behavior of this function for testing purposes
	 */
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
	
	
	/**
	 * Generates an ArrayList of all possible power-up locations (PPULs) on the beach not
	 * populated by a water tile or a gabion/concrete power-up. The method
	 * is used primarily to generate a bundle of locations from which a
	 * gabion/concrete power-up can be spawned.
	 * @return ppulPair an ArrayList of vacant tiles
	 */
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
	
	
	
	/**
	 * Finds the actual Pair object in the beach grid given a
	 * separate instance of Pair.
	 * @param pair a location
	 * @return p the Pair instance located in the beach grid that corresponds to the (x,y) pair being passed in
	 */
	public Pair findPairInGrid(Pair pair) {
		for(Pair p : this.getBeachGrid().keySet()) {
			if(pair.getX() == p.getX() && pair.getY()==p.getY()) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Returns the list of ordered pairs in the beach grid
	 * @return orderedPairs , a list of ordered pairs in the beach grid
	 */
	public List<Pair> getOrderedPairs() {
		return orderedPairs;
	}

	/**
	 * Sets the list of ordered pairs located in the beach grid
	 * @param orderedPairs a list of ordered pairs
	 */
	public void setOrderedPairs(List<Pair> orderedPairs) {
		this.orderedPairs = orderedPairs;
	}

	/**
	 * Getter for the HashMap that contains the list of pairs corresponding to each wave cluster
	 * @return gridLayers , a HashMap that contains the list of pairs corresponding to each wave cluster
	 */
	public HashMap<WaveClusters, List<Pair>> getGridLayers() {
		return gridLayers;
	}

	/**
	 * Sets the HashMap that contains the list of pairs corresponding to each wave cluster
	 * @param gridLayers , a HashMap that contains the list of pairs corresponding to each wave cluster
	 */
	public void setGridLayers(HashMap<WaveClusters, List<Pair>> gridLayers) {
		this.gridLayers = gridLayers;
	}

	/**
	 * Gets the position grid. The position grid is a 7x7 grid used to map locations of
	 * beach objects.
	 * @return positionGrid, a 7x7 grid used to map locations of beach objects
	 */
	public int[][] getPositionGrid() {
		return positionGrid;
	}

	/**
	 * Sets the position grid.  The position grid is a 7x7 grid used to map locations of
	 * beach objects.
	 * @param beachGrid , a 7x7 grid used to map locations of beach objects
	 */
	public void setPositionGrid(int[][] beachGrid) {
		this.positionGrid = beachGrid;
	}
	
	/**
	 * Gets the beach grid.
	 * @return beachGrid , a HashMap containing Pair-BeachItem links
	 */
	public HashMap<Pair, GridBlock> getBeachGrid() {
		return beachGrid;
	}

	/**
	 * Sets the beach grid
	 * @param beachGrid , a HashMap containing Pair-BeachItem links
	 */
	public void setBeachGrid(HashMap<Pair, GridBlock> beachGrid) {
		this.beachGrid = beachGrid;
	}
	
	/**
	 * Gets the Pair of the gabion wall/power-up currently spawned on the beach
	 * @return gabPair , the coordinate Pair associated with the gabion wall/power-up currently spawned on the beach
	 */
	public Pair getGabPair() {
		return gabPair;
	}

	/**
	 * Sets the Pair of the gabion wall/power-up currently spawned on the beach.
	 * @param gabPair , the coordinate Pair associated with the gabion wall/power-up currently spawned on the beach
	 */
	public void setGabPair(Pair gabPair) {
		this.gabPair = gabPair;
	}

	/**
	 * Gets the Pair of the concrete wall/power-up currently spawned on the beach.
	 * @return concPair , the coordinate Pair associated with the concrete wall/power-up currently spawned on the beach
	 */
	public Pair getConcPair() {
		return concPair;
	}

	/**
	 * Sets the Pair of the concrete wall/power-up currently spawned on the beach.
	 * @param concPair , the coordinate Pair associated with the concrete wall/power-up currently spawned on the beach
	 */
	public void setConcPair(Pair concPair) {
		this.concPair = concPair;
	}

	/**
	 * Gets the HashMap that contains the frames used to show the game.
	 * This is primarily used to make the dimensions/bounds of the model
	 * relative to the size of the frames.
	 * @return frameMap , the HashMap that contains the frames used to show the game.
	 */
	public HashMap<Frames, JComponent> getFrameMap() {
		return frameMap;
	}

	/**
	 * Sets the HashMap that contains the frames used to show the game.
	 * @param frameMap , the HashMap that contains the frames used to show the game.
	 */
	public void setFrameMap(HashMap<Frames, JComponent> frameMap) {
		this.frameMap = frameMap;
	}

	
	public void setGameState(String gameState) {
		this.GameState = gameState;
	}

}
