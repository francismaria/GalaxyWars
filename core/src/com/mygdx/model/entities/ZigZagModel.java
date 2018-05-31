package com.mygdx.model.entities;

import java.util.Random;

public class ZigZagModel extends EnemyModel{
	
	private Random controller = new Random();
	
	private boolean changeDirection;
	
	private float timePassedChangedDirection;
	
	private static final float MIN_TIME_BETWEEN_CHANGE = 0.2f;
	
	private static final float OFFSET_LIMIT = 0.2f;
	
	/**
	 * Constructor of the class.
	 */
	public ZigZagModel(){
		super(EnemyType.ZIGZAG);
		this.changeDirection = false;
		timePassedChangedDirection = 0;
	}
	
	public void update(float delta){			//Deals with AI
		timePassedChangedDirection += delta;
		System.out.println("asa"+delta);
		int controlNumber = controller.nextInt(1000) + 1;
		
		if((controlNumber % 17 == 0) && (getYCoord() < 3) && (timePassedChangedDirection >= MIN_TIME_BETWEEN_CHANGE)){
			changeDirection = true;
			timePassedChangedDirection = 0;
		}
		else if(getYCoord() < 1 && (timePassedChangedDirection >= MIN_TIME_BETWEEN_CHANGE)){
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
