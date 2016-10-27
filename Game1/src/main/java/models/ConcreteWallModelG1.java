package models;

public class ConcreteWallModelG1 extends WallModelAbstract {
	private int maxBlocks;
	private int currentBlocks;
	private int BlockOnBeach;
	
	public void addPiece() {
		
	}
	
	@Override
	public void breakDown() {
		
	}
	
	@Override
	public void spawn(boolean gameStart) {
		
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


}
