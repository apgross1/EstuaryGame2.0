package models;

import java.util.ArrayList;
import java.util.Collection;

import models.GabionWallModelG1.GabionChunk;

public class ConcreteWallModelG1 extends WallModelAbstract {
	private int maxBlocks;
	private int currentBlocks;
	private int BlockOnBeach;
	private int activeBlocksOnBoard;
	private Collection<ConcreteChunk> chunks;
	
	
	public ConcreteWallModelG1(){
		maxBlocks = 30;
	}
	
	public void addPiece(ConcreteChunk c) {
		currentBlocks++;
		c.toggleActive();
		activeBlocksOnBoard--;
	}
	
	@Override
	public void breakDown() {
		currentBlocks = (int) (.1*currentBlocks);
	}
	

	public void spawnChunk(int x_loc, int y_loc) {
		ConcreteChunk c = new ConcreteChunk();
		c.setLocX(x_loc);
		c.setLocY(y_loc);
		c.toggleActive();
		activeBlocksOnBoard++;
		
	}
	public int getActiveBlocks(){
		return activeBlocksOnBoard;
	}
	
	public class ConcreteChunk {
		private int locX;
		private int locY;
		private int height = 10;
		private int width = 10;
		
		private boolean active;
		
		public ConcreteChunk() {
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
	
	public int getMaxBlocks() {
		return maxBlocks;
	}
	
	public int getCurrentBlocks() {
		return currentBlocks;
	}
	
	public Collection<ConcreteChunk> getChunks() {
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
	
	public int calculateAmountRemoved() {
		return 0;
	}

	public void setMaxBlocks(int maxBlocks) {
		this.maxBlocks = maxBlocks;
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

	public void setChunks(Collection<ConcreteChunk> chunks) {
		this.chunks = chunks;
	}

	@Override
	public void spawn(boolean gameStart, int numChunksRemoved) {
		// TODO Auto-generated method stub
		
	}
}
