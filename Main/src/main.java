import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;

import controller.Game1Controller;
import controller.Game2Controller;
import controller.Game3Controller;
import models.AnimalModelG3;

public class main implements Serializable{

	public static void main(String[] args) {
		
		JFrame gameFrame = new JFrame();
		
		
		Game1Controller g1 = new Game1Controller(gameFrame);
		try {
			FileOutputStream fileOut = new FileOutputStream("./game1.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(g1);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in ./game1.txt file");
		} catch (IOException i) {
			i.printStackTrace();
		}
		Game2Controller g2 = new Game2Controller(gameFrame);
		try {
			FileOutputStream fileOut = new FileOutputStream("./game2.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(g1);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in ./game2.txt file");
		} catch (IOException i) {
			i.printStackTrace();
		}
		Game3Controller g3 = new Game3Controller(gameFrame);
		try {
			FileOutputStream fileOut = new FileOutputStream("./game3.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(g1);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in ./game3.txt file");
		} catch (IOException i) {
			i.printStackTrace();
		}
		
		g1.startGame();
		//g2.startGame();
		//g3.runGame();

	}

}
