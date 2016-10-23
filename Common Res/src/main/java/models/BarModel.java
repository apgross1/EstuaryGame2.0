package models;

public abstract class BarModel {
	public int status;
	int maxLevel;
	
	public BarModel() {
		
	}
	
	public abstract void increase();
	
	public abstract void decrease();
	
	public boolean isEmpty() {
		if (status == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
