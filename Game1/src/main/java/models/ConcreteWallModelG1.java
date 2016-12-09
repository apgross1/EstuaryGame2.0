package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

<<<<<<< HEAD
public class ConcreteWallModelG1 extends WallModelAbstract implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
=======
public class ConcreteWallModelG1 {
>>>>>>> branch 'master' of https://github.com/apgross1/EstuaryGame2.0
	private int maxBlocks;
	private int currentBlocks;
	private int activeBlocksOnBoard;
	private Collection<ConcreteChunk> chunks = new ArrayList<ConcreteChunk>();
	
	/**
	 * creates an instance of the model that sets the max amount of blocks
	 * that can be used in the walls
	 */
	public ConcreteWallModelG1(){
		maxBlocks = 30;
	}
	
	/**
	 * uses an iterator to go through the wall and remove all of the chunks from the wall
	 */
	public void reset(){
		Iterator<ConcreteChunk> tmp = chunks.iterator();
		while(tmp.hasNext()){
			tmp.next();
			tmp.remove();
		}
		currentBlocks = 0;
		activeBlocksOnBoard = 0;
	}
	/**
	 * takes in a chunk from the board and adds it to the wall, updates the number of
	 * blocks in the wall and how many are on the board
	 * @param c takes in a chunk from the board
	 */
	public void addPiece(ConcreteChunk c) {
		currentBlocks++;
		c.toggleActive();
		activeBlocksOnBoard--;
	}
	
	/**
	 * Simple math function that breaks down the concrete walls to 10% 
	 * of what the player was able to collect.
	 */
	public void breakDown() {
		currentBlocks = (int) (.1*currentBlocks);
	}
	
	/**
	 * takes in 2 locations and uses them to randomly spawn a chunk on the board, adds
	 * a new chunk to the array of chunks and updates the number of blocks on the board 
	 * @param x_loc int of x location for chunk
	 * @param y_loc int of y location for chunk
	 */
	public void spawnChunk(int x_loc, int y_loc) {
		ConcreteChunk c = new ConcreteChunk();
		c.setLocX(x_loc);
		c.setLocY(y_loc);
		c.toggleActive();
		chunks.add(c);
		activeBlocksOnBoard++;
		
	}
	
	/**
	 * getter to get the active amount of blocks you can pick up on the board
	 * @return an int of the number of possible pick ups
	 */
	public int getActiveBlocks(){
		return activeBlocksOnBoard;
	}
	
	/**
	 * a getter that gets the max amount of blocks that can be used in the wall
	 * @return an int of the max amount of blocks able to be used in the wall
	 */
	public int getMaxBlocks() {
		return maxBlocks;
	}
	
	/**
	 * a getter to get the current number of blocks in the wall
	 * @return an int of the current number of blocks in the wall
	 */
	public int getCurrentBlocks() {
		return currentBlocks;
	}
	
	/**
	 * returns the number of chunks that are in the collection of chunk, which is 
	 * used for the chunks in the wall and the number on the board
	 * @return an int of the number of chunks in the array
	 */
	public Collection<ConcreteChunk> getChunks() {
		return chunks;
	}
	
	/*
	 * Below are method used in tests but not in the actual game
	 */
	
	/**
	 * a setter for the max amount of blocks that can be collected for the wall
	 * @param i an int that is the number of blocks that can be collected for the wall
	 */
	public void setMaxBlocks(int i) {
	maxBlocks = i;
	}
	/**
	 * a setter for the current number of blocks in the wall itself
	 * @param i an int that is the number of blocks in the wall
	 */
	public void setCurrentBlocks(int i) {
		currentBlocks = i;
	}
	/**
	 * a setter for the number of active blocks on the board that can be picked up
	 * @param i an int that is the number of active blocks on the board
	 */
	public void setactiveBlocksOnBoard(int i){
		activeBlocksOnBoard = i;
	}
}
