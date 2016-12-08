package models;

import java.util.Random;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;

public class AlgaeModel {
	private int locX;
	private int locY;
	private int health;
	private boolean isActive;
	
	private int velocity = 2;
	private int randomYBound = 0;
	Random rand = new Random();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth = (int) screenSize.getWidth();
	int screenHeight = (int) screenSize.getHeight();
	private int height = (int) (screenHeight*.15);
	private int width = (int) (screenHeight*.15);
	int algaeXBoundMax = screenWidth;
	int algaeYBoundMax = (int) (screenHeight-(screenHeight*.1));
	int algaeYBoundMin = (int) (screenHeight*.32);
	int maxAlgaeNum = 100;
	int riverSpawnX ;
	int riverSpawnY ;
	
	

	public AlgaeModel() {
		
		randomYBound = rand.nextInt((algaeYBoundMax - algaeYBoundMin) + 1) + algaeYBoundMin;
		
		riverSpawnX = (int) (screenWidth*.87);
	    riverSpawnY = (int) (screenHeight*.11);
	    
	}
	
	public void setHeight(int h){
		height = h;
	}

	public int getHeight() {
		return height;
	}
	
	public void setWidth(int w){
		width = w;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getRandomYLocation() {
		return randomYBound;
	}

	public void move() {
		this.setLocX(getLocX() - velocity);
	}
	public void moveRiverAlgae() {
		riverSpawnX += velocity;
	}
	
	public void setRiverAlgaeY(int rY){
		riverSpawnY =rY;
	}
	
	public int getRiverAlgaeY(){
		return riverSpawnY;
	}
	
	public void setRiverAlgaeX(int rX){
		riverSpawnX =rX;
	}
	
	public int getRiverAlgaeX(){
		return riverSpawnX;
	}

	public void eaten() {
		this.setActive(false);
	}

	public void spawnAlgaeModel() {

		this.setActive(true);
		setLocX((int) (screenWidth+(screenWidth*.1)));
		setLocY(getRandomYLocation());
	}
	public void setMaxAlgae(int max) {
		maxAlgaeNum = max;
	}
	
	public int getMaxAlgae() {
		return maxAlgaeNum;
	}
	
	

	public int getLocY() {
		return locY;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}

	public int getLocX() {
		return locX;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getVelocity() {
		
		return velocity;
	}
	public void setVelocity(int v) {
		
		velocity = v;
	}
	public void setYMax(int yMax){
		 algaeYBoundMax = yMax;
	}
	public int getYMax(){
		return algaeYBoundMax;
	}
	
	
	public int getXMax(){
		return algaeXBoundMax;
	}
	public void setXMax(int xM){
		algaeXBoundMax = xM;
	}
	
	
	public int getYMin(){
		return algaeYBoundMin;
	}
	
	public void setYMin(int yMin){
		algaeYBoundMin = yMin;
	}
	
}