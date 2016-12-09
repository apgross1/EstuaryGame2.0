package Enums;

public enum Frames {
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
