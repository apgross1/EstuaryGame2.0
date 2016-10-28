package models;


public class BarModel extends BarModelAbstract {
	
	public BarModel() {
		super.setMaxLevel(100); //Health we decided for the estuary in game1
	}

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
	
	
	
	
	
	
	
	

	@Override
	public void increase(int i) {
		//Never used but has to be implemented because abstract class
	}
}
