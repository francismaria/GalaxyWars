package com.mygdx.model.entities;

public class SpaceShipModel extends EntityModel {
	
	private static final float INITIAL_X_COORD = 30;
	private static final float INITIAL_Y_COORD = 250;
	
	public SpaceShipModel(){
		super(INITIAL_X_COORD, INITIAL_Y_COORD);
	}
	
	public void updatePosition(){
		setYCoord(getYCoord()-1);
	}

}
