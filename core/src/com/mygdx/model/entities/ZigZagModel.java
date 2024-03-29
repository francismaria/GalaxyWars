package com.mygdx.model.entities;

import com.mygdx.game.GalaxyWars;
import com.mygdx.game.GalaxyWars.Difficulty;
import com.mygdx.model.GameModel;

public class ZigZagModel extends EnemyModel{
	
	/**
	 * Variable to check if body should or not change direction.
	 */
	private boolean changeDirection;
	
	/**
	 * Variable to check how much time has passed since last change of dierction.
	 */
	private float timePassedChangedDirection;
	
	/**
	 * Minimun time between last change of direction.
	 */
	private static final float MIN_TIME_BETWEEN_CHANGE = 0.2f;
	
	/**
	 * Constructor of the class.
	 * Initializes the time since the last changed direction (up/down).
	 */
	public ZigZagModel(){
		super(EnemyType.ZIGZAG);
		this.changeDirection = false;
		timePassedChangedDirection = 0;
		initVelocity(GameModel.difficulty);
	}
	
	/**
	 * Initializes the velocity of the entity depending on the game difficulty.
	 */
	private void initVelocity(Difficulty difficulty){
		velocity.x = -0.8f; velocity.y = 0;
		if(difficulty.equals(Difficulty.EASY)){
			velocity.x *= VEL_FACTOR_EASY;
		} else if(difficulty.equals(Difficulty.MEDIUM)) {
			velocity.x *= VEL_FACTOR_MEDIUM;
		} else if(difficulty.equals(Difficulty.HARD)) {
			velocity.x *= VEL_FACTOR_HARD;
		}
	}
	
	/**
	 * Function on which updates the model and deals with the zigzag AI.
	 * @param delta the delta time.
	 */
	public void update(float delta){			
		timePassedChangedDirection += delta;
		int controlNumber = GameModel.getRandomNumberInt(1000);
		
		if((controlNumber % 17 == 0) && (getYCoord() < 3) && (timePassedChangedDirection >= MIN_TIME_BETWEEN_CHANGE)){
			changeDirection = true;
			timePassedChangedDirection = 0;
		}
		else if(getYCoord() < 1 && (timePassedChangedDirection >= MIN_TIME_BETWEEN_CHANGE)){
			changeDirection = true;
		}
	}
	
	/**
	 * Returns if the body has or not changed direction.
	 * @return if body has or not changed direction.
	 */
	public boolean hasChangedDirection(){
		return changeDirection;
	}
	
	/**
	 * Reset change direction var.
	 */
	public void resetChangedDirection(){
		changeDirection = false;
	}
	
}
