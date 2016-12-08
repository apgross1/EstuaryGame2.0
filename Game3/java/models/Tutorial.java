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
	private boolean dialogueOn;
	

	/**
	 * Constructor for this element
	 */
	public Tutorial() {
		graphicMap = new HashMap<AnimGraphics,ArrayList<BufferedImage>>();
		keyBoardPicOnDeck = 0;
		waveWarning = false;
		dialogueOn = false;
	}
	
	/**
	 * Adds the graphics associates with this element. These are used to
	 * visually represent this element. 
	 */
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
			
			//Adding final dialogue
			ArrayList<BufferedImage> dialoguePic = new ArrayList<BufferedImage>();
			BufferedImage dialogue = ImageIO.read(new File("./Images/Game3/Dialogue2.png"));
			dialoguePic.add(dialogue);
			
			graphicMap.put(AnimGraphics.KEYBOARD, keyBoardPics);
			graphicMap.put(AnimGraphics.BIG_X, xPic);
			graphicMap.put(AnimGraphics.ARROW, arrowPic);
			graphicMap.put(AnimGraphics.DIALOGUE, dialoguePic);
			}
			catch(IOException e) {
	    		e.printStackTrace();
	    	}
	}
	
	/**
	 * Gets the graphic map of this element. The graphic map contains ArrayList of images
	 * corresponding to a tutorial element.
	 * @return graphicMap a HashMap that links the enumeration value of a tutorial component and the ArrayList of animation sequences
	 */
	public HashMap<AnimGraphics, ArrayList<BufferedImage>> getGraphicMap() {
		return graphicMap;
	}

	/**
	 * Sets the graphic map of this element. The graphic map contains ArrayList of images
	 * corresponding to a tutorial element.
	 * @param graphicMap a HashMap that links the enumeration value of a tutorial component and the ArrayList of animation sequences
	 */
	public void setGraphicMap(HashMap<AnimGraphics, ArrayList<BufferedImage>> graphicMap) {
		this.graphicMap = graphicMap;
	}

	/**
	 * Gets the current keyboard picture a series of keyboard pictures that are on a cyclical timer
	 * @return keyBoardPicOnDeck int, the current keyboard picture a series of keyboard pictures
	 */
	public int getKeyBoardPicOnDeck() {
		return keyBoardPicOnDeck;
	}

	/**
	 * Sets the current keyboard picture a series of keyboard pictures that are on a cyclical timer
	 * @param keyBoardPicOnDeck
	 */
	public void setKeyBoardPicOnDeck(int keyBoardPicOnDeck) {
		this.keyBoardPicOnDeck = keyBoardPicOnDeck;
	}
	
	/**
	 * Determines if keyboard animation is done in the tutorial
	 * @return boolean, 1 if keyboard animation is done, 0 otherwise
	 */
	public boolean isKeyboardStop() {
		return keyboardStop;
	}

	/**
	 * Determines if keyboard animation is done in the tutorial
	 * @param keyboardStop boolean, 1 if keyboard animation is done, 0 otherwise
	 */
	public void setKeyboardStop(boolean keyboardStop) {
		this.keyboardStop = keyboardStop;
	}

	/**
	 * Determines if wave collision warning is active in the tutorial
	 * @return waveWarning boolean, 1 if wave warning is still active, 0 otherwise
	 */
	public boolean isWaveWarning() {
		return waveWarning;
	}

	/**
	 * Sets wave collision warning is active in the tutorial
	 * @param waveWarning boolean, 1 if wave warning is still active, 0 otherwise
	 */
	public void setWaveWarning(boolean waveWarning) {
		this.waveWarning = waveWarning;
	}

	/**
	 * Determines if the dialogue box at the end of the tutorial is active
	 * @return dialogueOn, 1 if the dialogue box still exists, 0 otherwise
	 */
	public boolean isDialogueOn() {
		return dialogueOn;
	}

	/**
	 * Sets the dialogue box at the end of the tutorial is active
	 * @param dialogueOn boolean, 1 if the dialogue box still exists, 0 otherwise
	 */
	public void setDialogueOn(boolean dialogueOn) {
		this.dialogueOn = dialogueOn;
	}

}
