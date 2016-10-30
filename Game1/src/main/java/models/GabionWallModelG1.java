package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import models.ConcreteWallModelG1.ConcreteChunk;

public class GabionWallModelG1 extends WallModelAbstract{
	private int maxOysters;
	private int currentOysters;
	private int activeClamsOnBoard;
	private Collection<GabionChunk> chunks = new ArrayList<GabionChunk>();
	
	
	public GabionWallModelG1() {
		maxOysters = 30;
	}
	
	public void reset(){
		Iterator<GabionChunk> tmp = chunks.iterator();
		while(tmp.hasNext()){
			tmp.next();
			tmp.remove();
		}
		currentOysters = 0;
		activeClamsOnBoard = 0;
	}
	
	public void addPiece(GabionChunk gc) {
		//This function is called when the controller detected a collision.
		currentOysters++;
		gc.toggleActive();
		activeClamsOnBoard--;
	}

	public void breakDown() {
		currentOysters = (int) (.85*currentOysters);
	}
	
	public void spawnChunk(int x_loc, int y_loc) {
		GabionChunk gc = new GabionChunk();
		gc.setLocX(x_loc);
		gc.setLocY(y_loc);
		gc.toggleActive();
		chunks.add(gc);
		activeClamsOnBoard++;
		
	}
	public int getActiveClams(){
		return activeClamsOnBoard;
	}
	
	public class GabionChunk {
		private int locX;
		private int locY;
		private int height = 10;
		private int width = 10;
		private boolean active;
		
		public GabionChunk() {
			locX = -1;
			locY = -1;
			active = false;
		}
		
		public boolean isActive(){
			return active;
		}
		public int getHeight(){
			return height;
		}
		public int getWidth(){
			return width;
		}
		
		public void toggleActive(){
			if(active){
				active = false;
			}else{
				active = true;
			}
		}
		
		public int getLocY() {
			return locY;
		}
		public void setLocY(int locY) {
			this.locY = locY;
		}
		public int getLocX() {
			return locX;
		}
		public void setLocX(int locX) {
			this.locX = locX;
		}
		
	}
	
	public int getMaxOysters() {
		return maxOysters;
	}
	public int getCurrentOysters() {
		return currentOysters;
	}
	
	public Collection<GabionChunk> getChunks() {
		return chunks;
	}
	
	
	
	/*
	 * Dont think we need stuff below this line
	 */

	@Override
	public void spawn(boolean gameStart, int numChunksRemoved) {
	}
	
}
