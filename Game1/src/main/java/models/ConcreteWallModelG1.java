package models;

import java.util.ArrayList;
import java.util.Collection;

public class ConcreteWallModelG1 extends WallModelAbstract {
	private int maxBlocks;
	private int currentBlocks;
	private int BlockOnBeach;
	private Collection<ConcreteChunk> chunks;
	
	public void addPiece() {
		
	}
	
	
	
	@Override
	public void breakDown() {
		
	}
	
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
	
	public int calculateAmountRemoved() {
		return 0;
	}

	public int getMaxBlocks() {
		return maxBlocks;
	}

	public void setMaxBlocks(int maxBlocks) {
		this.maxBlocks = maxBlocks;
	}

	public int getCurrentBlocks() {
		return currentBlocks;
	}

	public void setCurrentBlocks(int currentBlocks) {
		this.currentBlocks = currentBlocks;
	}
	

	
	public int getBlockOnBeach() {
		return BlockOnBeach;
	}

	public void setBlockOnBeach(int blockOnBeach) {
		BlockOnBeach = blockOnBeach;
	}

	public class ConcreteChunk {
		private int locX;
		private int locY;
		
		public ConcreteChunk() {
			locX = -1;
			locY = -1;
		}
		
		public int getLocX() {
			return locX;
		}
		public void setLocX(int locX) {
			this.locX = locX;
		}
		public int getLocY() {
			return locY;
		}
		public void setLocY(int locY) {
			this.locY = locY;
		}
		
	}
}
