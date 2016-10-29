package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.AnimalModelG3;

import controller.Game3Controller;


public class Game3View extends JPanel {
	final static int frameWidth = 1000;
    final static int frameHeight = 1000;
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    
    private Game3Controller controller;
    
    public Game3View() {
    	frame = new JFrame();
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	panel.setPreferredSize(new Dimension(1000,1000));
    	frame.add(panel);
    	frame.addKeyListener(controller);
	}
    public class Animation extends JComponent {
		@Override
		public void paint(Graphics g) {
			
		}
    }
    
	public void addController(Game3Controller cont) {
		controller = cont;
	}
}
