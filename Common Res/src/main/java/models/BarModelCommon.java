package models;

import java.io.Serializable;

public class BarModelCommon implements Serializable {
	public int status;
	int maxLevel;
	
	/**
	 *Constructs an instance of BarModelCommon
	 */
	public BarModelCommon() {
		
	}
	
	/**
	 * Returns true if the bar is empty. 
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (status == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Sets the max level the bar can attain.
	 * @param maxLevel
	 */
	public void setMaxLevel(int maxLevel){
		this.maxLevel = maxLevel;
	}
	
	/**
	 * Gets the max level the bar can attain.
	 * @return maxLevel , an int
	 */
	public int getMaxLevel(){
		return maxLevel;
	}
	
	
	/**
	 * Sets the current status of the bar (the percent of the bar at a given time).
	 * @param status , an int
	 */
	public void setStatus(int status){
		this.status = status;
	}
	
	/**
	 * Gets the current status of the bar.
	 * @return status , an int
	 */
	public int getStatus(){
		return status;
	}
}
