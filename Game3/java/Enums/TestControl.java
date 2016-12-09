package Enums;

public enum TestControl {
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
