package models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Enums.AnimGraphics;

public class Tutorial {
	private HashMap<AnimGraphics, ArrayList<BufferedImage>> graphicMap;
	private int keyBoardPicOnDeck;
	private boolean keyboardStop;
	private boolean waveWarning;
	

	public Tutorial() {
		graphicMap = new HashMap<AnimGraphics,ArrayList<BufferedImage>>();
		keyBoardPicOnDeck = 0;
		waveWarning = false;
	}
	
	public void addPics() {
		try{
			//Adding keyboard graphics
			ArrayList<BufferedImage> keyBoardPics = new ArrayList<BufferedImage>();
			BufferedImage key_pic_0 = ImageIO.read(new File("./Images/Game3/key_press_0.png"));
			BufferedImage key_pic_1 = ImageIO.read(new File("./Images/Game3/key_press_1.png"));
			BufferedImage key_pic_2 = ImageIO.read(new File("./Images/Game3/key_press_2.png"));
			BufferedImage key_pic_3 = ImageIO.read(new File("./Images/Game3/key_press_3.png"));
			BufferedImage key_pic_4 = ImageIO.read(new File("./Images/Game3/key_press_4.png"));
			keyBoardPics.add(key_pic_0);
			keyBoardPics.add(key_pic_1);
			keyBoardPics.add(key_pic_2);
			keyBoardPics.add(key_pic_3);
			keyBoardPics.add(key_pic_4);
			
			//Adding 'X'
			ArrayList<BufferedImage> xPic = new ArrayList<BufferedImage>();
			BufferedImage x = ImageIO.read(new File("./Images/Game3/x.png"));
			xPic.add(x);
			
			//Adding arrow
			ArrayList<BufferedImage> arrowPic = new ArrayList<BufferedImage>();
			BufferedImage arrow = ImageIO.read(new File("./Images/Game3/green_arrow.png"));
			arrowPic.add(arrow);
			
			graphicMap.put(AnimGraphics.KEYBOARD, keyBoardPics);
			graphicMap.put(AnimGraphics.BIG_X, xPic);
			graphicMap.put(AnimGraphics.ARROW, arrowPic);
			}
			catch(IOException e) {
	    		e.printStackTrace();
	    	}
	}
	
	public HashMap<AnimGraphics, ArrayList<BufferedImage>> getGraphicMap() {
		return graphicMap;
	}

	public void setGraphicMap(HashMap<AnimGraphics, ArrayList<BufferedImage>> graphicMap) {
		this.graphicMap = graphicMap;
	}

	public int getKeyBoardPicOnDeck() {
		return keyBoardPicOnDeck;
	}

	public void setKeyBoardPicOnDeck(int keyBoardPicOnDeck) {
		this.keyBoardPicOnDeck = keyBoardPicOnDeck;
	}
	
	public boolean isKeyboardStop() {
		return keyboardStop;
	}

	public void setKeyboardStop(boolean keyboardStop) {
		this.keyboardStop = keyboardStop;
	}

	public boolean isWaveWarning() {
		return waveWarning;
	}

	public void setWaveWarning(boolean waveWarning) {
		this.waveWarning = waveWarning;
	}

}
