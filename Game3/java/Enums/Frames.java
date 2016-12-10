package Enums;

import java.io.Serializable;

/**
 * @author Andrew
 *Enum class that refers to the frames used in the game
 */
public enum Frames implements Serializable {
	TIMER(0),
	ANIMAL(1),
	SHORE(2),
	BEACH_GRID(3), 
	TUTORIAL(4);
	
	private final int frameID;
	
	
	private Frames(int ID) {
		this.frameID = ID;
	}

	public int getWaveID() {
		return frameID;
	}
	
	
}
