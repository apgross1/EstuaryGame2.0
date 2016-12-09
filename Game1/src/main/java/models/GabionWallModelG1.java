package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GabionWallModelG1 extends WallModelAbstract implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int maxOysters;
	private int currentOysters;
	private int activeClamsOnBoard;
	private Collection<GabionChunk> chunks = new ArrayList<GabionChunk>();
	
	/**
	 * creates an instance of the model that sets the max amount of oyesters
	 * that can be used in the walls
	 */
	public GabionWallModelG1() {
		maxOysters = 30;
	}
	
	/**
	 * uses an iterator to go through the wall and remove all of the chunks from the wall
	 */
	public void reset(){
		Iterator<GabionChunk> tmp = chunks.iterator();
		while(tmp.hasNext()){
			tmp.next();
			tmp.remove();
		}
		currentOysters = 0;
		activeClamsOnBoard = 0;
	}
	
	/**
	 * takes in a chunk from the board and adds it to the wall, updates the number of
	 * oysters in the wall and how many are on the board
	 * @param gc takes in a chunk from the board
	 */
	public void addPiece(GabionChunk gc) {
		//This function is called when the controller detected a collision.
		currentOysters++;
		gc.toggleActive();
		activeClamsOnBoard--;
	}

	/**
	 * Mathematical function that sets current Oysters (what is displayed on the wall)
	 * to 85% of what the player collected.
	 */
	public void breakDown() {
		currentOysters = (int) (.85*currentOysters);
	}
	
	/**
	 * takes in 2 locations and uses them to randomly spawn a chunk on the board, adds
	 * a new chunk to the array of chunks and updates the number of clams on the board 
	 * @param x_loc int of x location for chunk
	 * @param y_loc int of y location for chunk
	 */
	public void spawnChunk(int x_loc, int y_loc) {
		GabionChunk gc = new GabionChunk();
		gc.setLocX(x_loc);
		gc.setLocY(y_loc);
		gc.toggleActive();
		chunks.add(gc);
		activeClamsOnBoard++;
	}
	
	/**
	 * getter to get the active amount of clams you can pick up on the board
	 * @return an int of the number of possible pick ups
	 */
	public int getActiveClams(){
		return activeClamsOnBoard;
	}
	
	/**
	 * a getter that gets the max amount of oysters that can be used in the wall
	 * @return an int of the max amount of oysters able to be used in the wall
	 */
	public int getMaxOysters() {
		return maxOysters;
	}
	/**
	 * a getter to get the current number of oysters in the wall
	 * @return an int of the current number of oysters in the wall
	 */
	public int getCurrentOysters() {
		return currentOysters;
	}
	
	/**
	 * returns the number of chunks that are in the collection of chunk, which is 
	 * used for the chunks in the wall and the number on the board
	 * @return an int of the number of chunks in the array
	 */
	public Collection<GabionChunk> getChunks() {
		return chunks;
	}
	
	/*
	 * Below are method used in tests but not in the actual game
	 */
	/**
	 * a setter for the max amount of oysters that can be collected for the wall
	 * @param i an int that is the number of oysters that can be collected for the wall
	 */
	public void setMaxOysters(int i) {
		maxOysters = i;
		
	}
	
	/**
	 * a setter for the current number of oysters in the wall itself
	 * @param i an int that is the number of oysters in the wall
	 */
	public void setCurrentOysters(int i) {
		currentOysters = i;
		
	}

	/**
	 * a setter for the number of active clams on the board that can be picked up
	 * @param i an int that is the number of active blocks on the board
	 */
	public void setactiveClamsOnBoard(int i) {
		activeClamsOnBoard = i;
		
	}
}
