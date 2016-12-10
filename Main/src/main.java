
import javax.swing.JFrame;

import controller.Game1Controller;
import controller.Game2Controller;
import controller.Game3Controller;

public class main {
	
	
	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();
		MainRun game = new MainRun(gameFrame);

		game.runMainMenu();
	}
}