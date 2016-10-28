package models;


public class BarModel extends BarModelAbstract {
	
	public BarModel() {
		super.setMaxLevel(100); //Health we decided for the estuary in game1
	}
	/*
	 * Implemented but never used
	@Override
	public void increase() {
		if(getStatus() < getMaxLevel()){
			setStatus(getStatus() + 1);
		}
	}
	*/

	@Override
	public void decrease(int damage) {
		if(getStatus() > 0){
			if(getStatus() >= damage){
				setStatus(getStatus() - damage);
			}else{
				setStatus(0);
			}
		}
	}

}
