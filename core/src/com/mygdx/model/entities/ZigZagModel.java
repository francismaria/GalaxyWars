package com.mygdx.model.entities;

import java.util.Random;

public class ZigZagModel extends EnemyModel{
	
	private Random controller = new Random();
	
	private boolean changeDirection;
	
	private static final float OFFSET_LIMIT = 0.2f;
	
	/**
	 * Constructor of the class.
	 */
	public ZigZagModel(){
		super(EnemyType.ZIGZAG);
		this.changeDirection = false;
	}
	
	public void update(){			//Deals with AI
		int controlNumber = controller.nextInt(10) + 1;
		
		if(controlNumber % 5 == 0){
			changeDirection = true;
		}
	}
	
	public boolean hasChangedDirection(){
		return changeDirection;
	}
	
	public void resetChangedDirection(){
		changeDirection = false;
	}
	
}
