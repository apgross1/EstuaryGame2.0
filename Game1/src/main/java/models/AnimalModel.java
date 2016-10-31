package models;

import enums.Direction;

public class AnimalModel extends AnimalModelAbstract {
	
	private int height = 100;
	private int width = 100;
	
	public AnimalModel() {
		//Set initial location and direction
		setLocX(500);
		setLocY(500);
		setCurrDir(Direction.NORTH);
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	//Have to add boarder controls to keep within bounds (we first have to determine the size of the screen.
	@Override
	public void move() {
		if(this.getCurrDir() == Direction.NORTH){
			if(getLocY() > 50){
				this.setLocY(this.getLocY() - 5);
        	}
		}
		else if(this.getCurrDir() == Direction.EAST){
			if(getLocX() < 885){
				this.setLocX(this.getLocX() + 5);
        	}
		}
		else if(this.getCurrDir() == Direction.SOUTH){
			if(getLocY() < 560){
				this.setLocY(this.getLocY() + 5);
        	}
		}
		else if(this.getCurrDir() == Direction.WEST){
			if(getLocX() > 0){
				this.setLocX(this.getLocX() - 5);
        	}
		}
		/*
		 *DECIDED FOR NOW WE'RE ONLY USING 4 DIRECTIONS BECAUSE OF KEY LISENER
		else if(this.getCurrDir() == Direction.NORTH_EAST){
			this.setLocX(this.getLocX() + 1);
			this.setLocY(this.getLocY() + 1);
		}
		else if(this.getCurrDir() == Direction.SOUTH_EAST){
			this.setLocX(this.getLocX() - 1);
			this.setLocY(this.getLocY() + 1);
		}
		else if(this.getCurrDir() == Direction.SOUTH_WEST){
			this.setLocX(this.getLocX() - 1);
			this.setLocY(this.getLocY() - 1);
		}
		else if(this.getCurrDir() == Direction.NORTH_WEST){
			this.setLocX(this.getLocX() - 1);
			this.setLocY(this.getLocY() + 1);
		}
		*/
		else{
			System.out.println("Directional data out of bounds: " + this.getCurrDir());
		}
	}
	
	
	
	/*
	 * Don't think we need stuff below this line
	 */
	
	@Override
	public void healthUp() {
		if(getHealth() < 100){
			setHealth(getHealth()+1); //Do we want to change the increase and make it more siginificant?
		}
	}
	@Override
	public void healthDown() {
		if(getHealth() >= 1){
			setHealth(getHealth()-1);
		}
	}
	@Override
	public void pickUp() {
		//This function is not needed as we're not actually picking anything up.. Collision will be taken care of in the controller which will tell
		//the model for each type of wall / gabion to update.
	}
}
