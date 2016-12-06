package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ConcreteWallModelG1 extends WallModelAbstract {
	private int maxBlocks;
	private int currentBlocks;
	private int activeBlocksOnBoard;
	private Collection<ConcreteChunk> chunks = new ArrayList<ConcreteChunk>();
	
	
	public ConcreteWallModelG1(){
		maxBlocks = 30;
	}
	
	public void reset(){
		Iterator<ConcreteChunk> tmp = chunks.iterator();
		while(tmp.hasNext()){
			tmp.next();
			tmp.remove();
		}
		currentBlocks = 0;
		activeBlocksOnBoard = 0;
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
		chunks.add(c);
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
	@Override
	public void spawn(boolean gameStart, int numChunksRemoved) {
		// TODO Auto-generated method stub
	}

	public void setMaxBlocks(int i) {
	maxBlocks = i;
	}

	public void setCurrentBlocks(int i) {
		currentBlocks = i;
	}
	public void setactiveBlocksOnBoard(int i){
		activeBlocksOnBoard = i;
	}
}
