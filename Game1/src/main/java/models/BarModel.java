package models;


public class BarModel extends BarModelAbstract {
	
	/**
	 * constructor for the bar model that updates the max level of the bar
	 * (how high it can go) and also sets the health at that level 
	 */
	public BarModel() {
		setMaxLevel(100);
		setStatus(getMaxLevel());
	}

	/**
	 * A function that calls setStatus(int) to decrease the health.
	 */
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
