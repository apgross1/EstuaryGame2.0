
import javax.swing.JFrame;

import controller.Game1Controller;
import controller.Game2Controller;
import controller.Game3Controller;

public class main {
	
	
	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();
		MainRun game = new MainRun(gameFrame);

		game.runMainMenu();
		
		long lastTime = System.nanoTime();
		final double ammountOfTicks = 60.0;	
		double ns = 1000000000 /ammountOfTicks;
		double delta = 0;
		
		
		//Once the start game button is pressed well get here.
		System.out.println("Yup, were good.");
	
		/*Game1Controller g1 = new Game1Controller(gameFrame);
		Game2Controller g2 = new Game2Controller();
		Game3Controller g3 = new Game3Controller(true);
		
		g1.startGame();
		g2.startGame();
		g3.runGame();*/
	}
}