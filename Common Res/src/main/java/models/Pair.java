package models;

public class Pair {
	private int a;
	private int b;
	
	public Pair(int num1, int num2) {
		setX(num1);
		setY(num2);
	}

	public Pair() {
	}
	
	public int getX() {
		return a;
	}

	public void setX(int a) {
		this.a = a;
	}

	public int getY() {
		return b;
	}

	public void setY(int b) {
		this.b = b;
	}
}