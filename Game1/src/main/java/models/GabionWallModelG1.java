package models;

import java.util.ArrayList;
import java.util.Collection;

public class GabionWallModelG1 extends WallModelAbstract{
	private int maxOysters;
	private int currentOysters;
	private int oystersOnBeach;
	private Collection<GabionChunk> chunks;
	
	
	public GabionWallModelG1() {
		
	}
	
	public void addPiece() {
		//This function is called when the controller detected a collision.
	}

	public void breakDown() {
		
	}
	

	//Upon looking at this function (spawn), it seems like the functional flow should be:
	//Calculate damage determines damage inflicted-->amountDamage determines # pieces removed --> spawn takes in # pieces removed
	//and randomly places them on the beach
	//So maybe have gameStart as an argument (maybe...) but definitely have it take in # pieces removed. 
	@Override
	public void spawn(boolean gameStart, int numChunksRemoved) {
		
	}
	
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
	
	public int getMaxOysters() {
		return maxOysters;
	}
	public void setMaxOysters(int maxOysters) {
		this.maxOysters = maxOysters;
	}
	public int getCurrentOysters() {
		return currentOysters;
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
	
	public Collection<GabionChunk> getChunks() {
		return chunks;
	}

	public void setChunks(Collection<GabionChunk> chunks) {
		this.chunks = chunks;
	}

	public class GabionChunk {
		private int locX;
		private int locY;
		boolean active = false;
		
		public GabionChunk() {
			locX = -1;
			locY = -1;
		}
		
		public boolean isActive(){
			return active;
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
	
}
