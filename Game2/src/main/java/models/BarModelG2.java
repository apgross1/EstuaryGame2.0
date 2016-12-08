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
	public void setDamage(int d){
		damage=d;
	}
	public int getWidth(){
		return width;
	}
	public void setWidth(int w) {
		width = w;
		
	}
	
	@Override
	public boolean isEmpty(){
		if(width == 0){
		return true;
		}
		else{
			return false;
		}
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