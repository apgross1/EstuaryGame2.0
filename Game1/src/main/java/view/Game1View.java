package view;

import java.awt.Graphics;

import controller.Game1Controller;

public class Game1View {
	private int damageLevel;
	private Game1Controller controller;
	
	
	public void paint(Graphics g) {
		
	}
	
	public void sway(boolean gameStart) {
		
	}
	
	public void waveCrash(boolean timeUp) {
		
	}
	
	public void remove() {
		
	}
	
	public void wallState() {
		
	}
	
	public void destroyWall(int newCurrBlocks) {
		
	}
	
	public void buildWall(int newCurrBlocks) {
		
	}
	
	public void raiseLevel(int newStatus) {
		
	}
	
	public void lowerLevel(int newStatus) {
		
	}
	
	public void warningSign() {
		
	}
	
	public void pickUp(int newXLoc, int newYLoc) {
		
	}
	
	public void perish() {
		
	}
	
	public int getDamageLevel() {
		return damageLevel;
	}
	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}
	public Game1Controller getController() {
		return controller;
	}
	public void setController(Game1Controller controller) {
		this.controller = controller;
	}
	
}
