package com.mygdx.model.entities;

public class SpaceShipModel extends EntityModel {
	
	public SpaceShipModel(){
		super(50,50);
	}
	
	public void update(){
		setYCoord(getYCoord()-1);
	}

}
