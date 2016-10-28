package models;

public class BarModelG2 extends BarModelAbstract{
	private int status;
	private int maxLevel;
	
	public BarModelG2(){
		
	}
	public BarModelG2(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	@Override
	public void decrease(){
		
	}
	@Override
	public boolean isEmpty(){
		return false;
	}
	@Override
	public void increase() {
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

}
