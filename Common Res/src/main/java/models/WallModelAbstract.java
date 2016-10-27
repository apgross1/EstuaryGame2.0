package models;

public abstract class WallModelAbstract {
	public WallModelAbstract() {
		
	}
	
	public abstract void breakDown();
	
	public abstract void spawn(boolean gameStart);
}
