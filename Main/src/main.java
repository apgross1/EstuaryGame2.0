
import javax.swing.JFrame;

import controller.Game1Controller;
import controller.Game2Controller;
import controller.Game3Controller;

public class main {
	main(){}
	
	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();
		
		
		MainRun game = new MainRun(gameFrame);
		while(!game.isStartPressed()){
			game.repaintFrame();
		}
		
		//Once the start game button is pressed well get here.
		System.out.println("Yup, were good.");
	
		

		Game1Controller g1 = new Game1Controller(gameFrame);
		Game2Controller g2 = new Game2Controller(gameFrame);
		Game3Controller g3 = new Game3Controller(gameFrame, true);
		
		g1.startGame();
		g2.startGame();
		g3.runGame();
		 
		 
	}
}