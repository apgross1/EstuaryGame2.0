package models;

/**
 * @author Team 8
 *
 */

public class BarModelG2 extends BarModelCommon{

	private int status;
	private int maxLevel;
	private int width;
	private int damage;
	private int damagePercent;
	public BarModelG2(){
		
	}
	/**
	 * Initializes the bar model with a max value based on the parameter
	 * @param maxLevel is the BarModels initial size
	 */
	public BarModelG2(int maxLevel) {
		this.maxLevel = maxLevel;
		this.width =20;
		damage = 10;
		damagePercent = (getMaxLevel()/getDamage())/3;
	}
	/**
	 * Gets the amount the bar model must decrease
	 * @return damage is the amount the bar model decreases in size
	 */
	public int getDamage(){
		return damage;
	}
	
	/**
	 * Registers the amount the barModel will decrease when taking damage
	 * @param d the desired amount the barModel will decrease when taking damage
	 */
	public void setDamage(int d){
		damage=d;
	}
	
	/**
	 * Gets the current width of the health bar
	 * @return width of the current health bar
	 */
	public int getWidth(){
		return width;
	}
	
	
	/**
	 * Registers the current width of the health bar
	 * @param w is the desired width of the barModel
	 */
	public void setWidth(int w) {
		width = w;
		
	}
	
	/* (non-Javadoc)
	 * @see models.BarModelAbstract#isEmpty()
	 */
	@Override
	public boolean isEmpty(){
		if(width == 0){
		return true;
		}
		else{
			return false;
		}
	}
	
	
	/* (non-Javadoc)
	 * @see models.BarModelAbstract#getStatus()
	 */
	@Override
	public int getStatus() {
		return status;
	}
	/* (non-Javadoc)
	 * @see models.BarModelAbstract#setStatus(int)
	 */
	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see models.BarModelAbstract#getMaxLevel()
	 */
	@Override
	public int getMaxLevel() {
		return maxLevel;
	}
	
	/* (non-Javadoc)
	 * @see models.BarModelAbstract#setMaxLevel(int)
	 */
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	/**
	 * Gets the percent of damage taken
	 * @return damagePercent the current percentage taken from the health bar
	 */
	public int getDamagePercent(){
		return damagePercent;
	}
	
	/**
	 * Gets the new percentage taken from the health bar
	 * @return damagePercent += damagePercent updates the percent of health taken from the health bar
	 */
	public int updateDamagePercent(){
		return damagePercent += damagePercent;
	}
	

}