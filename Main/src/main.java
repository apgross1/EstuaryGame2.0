import javax.swing.JFrame;

import controller.Game1Controller;
import controller.Game2Controller;
import controller.Game3Controller;
import models.AnimalModelG3;

public class main {

	public static void main(String[] args) {
		
		JFrame gameFrame = new JFrame();
		
		
		Game1Controller g1 = new Game1Controller(gameFrame);
		Game2Controller g2 = new Game2Controller(gameFrame);
		Game3Controller g3 = new Game3Controller(gameFrame);
		
		g1.startGame();
		//g2.startGame();
		g3.runGame();

	}

}
