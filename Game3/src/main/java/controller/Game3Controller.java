package controller;
import models.AnimalModelG3;
import models.BeachModel;
import models.BeachModel.Pair;
import models.ConcretePUModel;
import models.ConcretePUModel.ConcPUState;
import models.GabionPUModel.GabPUState;
import models.WaterModel;
import models.GabionPUModel;
import view.Game3View;

public class Game3Controller {
	private boolean gameisActive;
	
	public Game3Controller() {
		
	}
	
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
