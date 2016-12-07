package models;

public class GabionChunk{
	private int locX;
	private int locY;
	private int height = 10;
	private int width = 10;
	private boolean active;
	
	/**
	 * constructor for the gabion chunks that sets an arbitrary x and y location
	 * and sets whether its active to false
	 */
	public GabionChunk() {
		locX = -1;
		locY = -1;
		active = false;
	}
	
	/**
	 * method that returns whether the chunk is active on the board or not, 
	 * which means whether it can be picked up or not
	 * @return boolean of whether it is active or not
	 */
	public boolean isActive(){
		return active;
	}
	
	/**
	 * getter for the height of the chunk
	 * @return an int which is the height
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * setter for the height of the chunk, sets the height
	 * @param height int of height of the chunk
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * getter for the width of the chunk
	 * @return an int which is the width
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * setter for the width of the chunk, sets the width
	 * @param width int of width of the chunk
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * used to toggle whether the chunk is active or not, meaning it can be picked up and used 
	 * in the wall
	 */
	public void toggleActive(){
		if(active){
			active = false;
		}else{
			active = true;
		}
	}
	
	/**
	 * getter for the y location of the chunk
	 * @return an int of the y location
	 */
	public int getLocY() {
		return locY;
	}
	
	/**
	 * setter for the y location of the chunk by taking in an int
	 * @param locY an int which is the y location
	 */
	public void setLocY(int locY) {
		this.locY = locY;
	}
	
	/**
	 * gets the x location of the chunk
	 * @return an int of the x location
	 */
	public int getLocX() {
		return locX;
	}
	
	/**
	 * setter for the x location of the chunk by taking in an int
	 * @param locX an int which is the x location
	 */
	public void setLocX(int locX) {
		this.locX = locX;
	}
}
