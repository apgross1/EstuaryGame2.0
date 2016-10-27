package models;

public class BarModelG2 extends BarModelAbstract{
	private int status;
	private int maxLevel;
	
	public BarModel(){
		
	}
	@Override
	public void decrease(){
		
	}
	@Override
	public boolean isEmpty(){
		if(status == 0){return true}
		else{return false}
	}
}
