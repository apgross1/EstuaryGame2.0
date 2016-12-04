import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

import controller.Game1Controller;
import controller.Game2Controller;
import controller.Game3Controller;
import view.Game1View.Animation;

public class main {
	
	public static void main(String[] args) {
		
		MainRun game = new MainRun();
		
		//game.start();
		
		while(true){
			game.repaintFrame();
		}
	}
}