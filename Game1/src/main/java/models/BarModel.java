package models;


public class BarModel extends BarModelAbstract {
	
	public BarModel() {
		setMaxLevel(100);
		setStatus(getMaxLevel());
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

	
	/*
	 * Dont think we need stuff below this line
	 */


	@Override
	public void increase(int i) {
		//Never used but has to be implemented because abstract class
	}
}
