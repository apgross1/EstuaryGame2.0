package models;

import java.awt.image.BufferedImage;

public class ConcretePUModel extends WallModelAbstract {
	public enum ConcPUState {
		POWER_UP,WALL;
	}
	private boolean isActive;
	private ConcPUState wallState;
	private BufferedImage powerUp;
	private BufferedImage wall;
	private int ConcretePUonbeach;

	public ConcretePUModel() {
		setWallState(ConcPUState.POWER_UP);
	}
	public boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(boolean active) {
		isActive = active;
	}
	private int getConcretePUonbeach(){
		return ConcretePUonbeach;
	}
	public void setConcretePUonbeach(int powerup){
		ConcretePUonbeach = powerup;
	}
	
	@Override
	public void breakDown() {
		
	}
	
	public BufferedImage getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(BufferedImage powerUp) {
		this.powerUp = powerUp;
	}

	public BufferedImage getWall() {
		return wall;
	}

	public void setWall(BufferedImage wall) {
		this.wall = wall;
	}
	public ConcPUState getWallState() {
		return wallState;
	}
	public void setWallState(ConcPUState gameState) {
		this.wallState = gameState;
	}
	@Override
	public void spawn(boolean gameStart, int numChunksRemoved) {
		// TODO Auto-generated method stub
		
	}
}
