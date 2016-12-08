package models;

public abstract class BarModelAbstract {
	public int status;
	int maxLevel;
	
	public BarModelAbstract() {
		
	}
	
	public boolean isEmpty() {
		if (status == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setMaxLevel(int maxLevel){
		this.maxLevel = maxLevel;
	}
	
	public int getMaxLevel(){
		return maxLevel;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	public int getStatus(){
		return status;
	}
}
