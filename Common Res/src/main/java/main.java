import javax.swing.JFrame;
import javax.swing.JPanel;

public class main extends JPanel{
	//Initialize the frame
	private JFrame screen = new JFrame();
	
	//This is the users score (max is 3 for winning every mini game). If the player gets at least 2, they've won the game overall.
	int minigamesWon = 0;
	
	//load all 3 mini game controllers (still struggling to update the directory dependencies, Andrew can you explain how this is done?)
	private Game1Controller game_one = new Game1Controller();
	private Game2Controller game_two = new Game2Controller();
	private Game3Controller game_three = new Game3Controller();
	
	public static void main(String[] args){
		//Hmm were' going to have to do some thinking as to how we want each sub game to communicate to this main to tell which game
		//is active. We can use an ENUM, boolean gameState...etc
	}
}
