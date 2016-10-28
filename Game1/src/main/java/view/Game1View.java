package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Game1Controller;

public class Game1View extends JPanel{
	private int damageLevel;
	private Game1Controller controller;
	private JFrame frame = new JFrame();
    final static int frameWidth = 1000;
    final static int frameHeight = 1000;
	
	public Game1View(Game1Controller ctl){
		controller = ctl;
		//JLabel emptyLabel = new JLabel("Text-Only Label");
		//frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	
    	//System.out.println("Yep");
	}
	
	
	//No matter what I do I cannot get repaint to call paint.....
	public void repaintFrame(){
		frame.getContentPane().repaint();
		//repaint();
		System.out.println("This called?");
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		//super.paint(g);
		System.out.println("This should get called by repaint but isnt");
		g2d.drawOval(0,0,50,50);
		
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
