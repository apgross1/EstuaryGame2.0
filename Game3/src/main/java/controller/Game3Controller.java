package controller;

public class Game3Controller {
	private boolean gameisActive;
	public void startTime() {
		
	}
	
	public void stopTime() {
		
	}

	public void isstartGame() {
		gameisActive = true;
	}

	public void isendGame() {
		gameisActive = false;
		
	}
	
	public boolean getgameActive() {
		return gameisActive;
	}
	public void setgameActive(boolean active) {
		gameisActive = active; 

}

	public int setTime(int i) {
		return i;
	}
}
