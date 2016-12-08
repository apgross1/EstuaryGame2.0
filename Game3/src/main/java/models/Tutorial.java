package models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Enums.AnimGraphics;

public class Tutorial {
	private int keyBoardPicOnDeck;
	private boolean keyboardStop;
	private boolean waveWarning;
	private boolean dialogueOn;
	

	/**
	 * Constructor for this element
	 */
	public Tutorial() {
		keyBoardPicOnDeck = 0;
		waveWarning = false;
		dialogueOn = false;
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
