package models;

import enums.Direction;

public class AnimalModel extends AnimalModelAbstract {
	
	public AnimalModel() {
		//this.setEmptyHanded(true);//I dont think we need this?
	}
	
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

	
	//Have to add boarder controls to keep within bounds (we first have to determine the size of the screen.
	@Override
	public void move() {
		if(this.getCurrDir() == Direction.NORTH){
			this.setLocY(this.getLocY() + 1);
		}
		else if(this.getCurrDir() == Direction.EAST){
			this.setLocX(this.getLocX() + 1);
		}
		else if(this.getCurrDir() == Direction.SOUTH){
			this.setLocY(this.getLocY() - 1);
		}
		else if(this.getCurrDir() == Direction.WEST){
			this.setLocX(this.getLocX() - 1);
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
}
