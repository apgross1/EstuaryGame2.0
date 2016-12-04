package models;

public class BarModelG2 extends BarModelAbstract{
	private int status;
	private int maxLevel;
	private int width;
	private int damage;
	private int damagePercent;
	public BarModelG2(){
		
	}
	public BarModelG2(int maxLevel) {
		this.maxLevel = maxLevel;
		this.width =20;
		damage = 10;
		damagePercent = (getMaxLevel()/getDamage())/3;
	}
	public int getDamage(){
		return damage;
	}
	public int getWidth(){
		return width;
	}
	@Override
	public void decrease(int i){
		
	}
	@Override
	public boolean isEmpty(){
		return false;
	}
	@Override
	public void increase(int i) {
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
	public int getDamagePercent(){
		return damagePercent;
	}
	public int updateDamagePercent(){
		return damagePercent += damagePercent;
	}

}
