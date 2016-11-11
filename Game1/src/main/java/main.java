import java.awt.GraphicsEnvironment;

import controller.Game1Controller;
import models.AnimalModel;
import models.BarModel;
import models.ConcreteWallModelG1;
import models.GabionWallModelG1;
import view.Game1View;

public class main {

	public static void main(String[] args) {

		//Controller
		Game1Controller ctlr = new Game1Controller();
		
		ctlr.startGame();
		
	}

}
