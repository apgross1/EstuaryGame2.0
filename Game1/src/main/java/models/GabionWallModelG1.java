package models;

import java.util.ArrayList;
import java.util.Collection;

public class GabionWallModelG1 extends WallModelAbstract{
	private int maxOysters;
	private int currentOysters;
	private int oystersOnBeach;
	private Collection<GabionChunk> chunks;
	
	
	public GabionWallModelG1() {
		maxOysters = 30;
	}
	
	public void addPiece(GabionChunk gc) {
		//This function is called when the controller detected a collision.
		currentOysters++;
		gc.toggleActive();
		
	}

	public void breakDown() {
		currentOysters = (int) (.75*currentOysters);
	}
	
	public class GabionChunk {
		private int locX;
		private int locY;
		private int height = 10;
		private int width = 10;
		boolean active = false;
		
		public GabionChunk() {
			locX = -1;
			locY = -1;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean isFull() {
		return false;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public void removeChunk(int amount) {
	
	}
	
	public int amountRemoved(int damage) {
		return 0;
	}
	
	public int calculateDamage() {
		return 0;
	}

	public void setMaxOysters(int maxOysters) {
		this.maxOysters = maxOysters;
	}
	public void setCurrentOysters(int currentOysters) {
		this.currentOysters = currentOysters;
	}
	public int getOystersOnBeach() {
		return oystersOnBeach;
	}
	public void setOystersOnBeach(int oystersOnBeach) {
		this.oystersOnBeach = oystersOnBeach;
	}


	public void setChunks(Collection<GabionChunk> chunks) {
		this.chunks = chunks;
	}

	
	//Upon looking at this function (spawn), it seems like the functional flow should be:
	//Calculate damage determines damage inflicted-->amountDamage determines # pieces removed --> spawn takes in # pieces removed
	//and randomly places them on the beach
	//So maybe have gameStart as an argument (maybe...) but definitely have it take in # pieces removed. 
	@Override
	public void spawn(boolean gameStart, int numChunksRemoved) {
		
	}
	
}
