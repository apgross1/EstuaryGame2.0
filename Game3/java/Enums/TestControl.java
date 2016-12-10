package Enums;

import java.io.Serializable;

/**
 * @author Andrew
 *Enum class that refers to a tag determining whether or not the game is running in test mode
 */
public enum TestControl implements Serializable {
	TEST(0),
	NO_TEST(1);
	
	private int val;
	private TestControl(int v) {
		val = v;
	}
	
	public int getVal() {
		return val;
	}
}
