package com.mygdx.model.entities;

public class ZigZagModel extends EnemyModel{
	
	private static final float INITIAL_X_COORD = 700;
	private static final float INITIAL_Y_COORD = 250;
	
	public ZigZagModel(){
		super(EnemyType.ZIGZAG, INITIAL_X_COORD, INITIAL_Y_COORD);
	}
}
