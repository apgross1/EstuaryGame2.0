package models;


public class BarModel extends BarModelAbstract {
	
	public BarModel() {
		super.setMaxLevel(100); //Health we decided for the estuary in game1
	}
	@Override
	public void increase() {
		if(getStatus() < getMaxLevel()){
			setStatus(getStatus() + 1);
		}
	}

	@Override
	public void decrease() {
		if(getStatus() < 0){
			setStatus(getStatus() - 1);
		}
	}

}
