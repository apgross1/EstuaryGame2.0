import java.awt.GraphicsEnvironment;



import controller.Game1Controller;
import models.AnimalModel;
import models.BarModel;
import models.ConcreteWallModelG1;
import models.GabionWallModelG1;
import view.Game1View;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();
		/*
		//Controller
		Game1Controller ctlr = new Game1Controller();
		
		ctlr.startGame();
		*/
		Game1Controller g1 = new Game1Controller(gameFrame);
		//Game2Controller g2 = new Game2Controller(gameFrame);
		//Game3Controller g3 = new Game3Controller(gameFrame);
		
		g1.startGame();
		//g2.startGame();
		//g3.runGame();
		
	}

}
