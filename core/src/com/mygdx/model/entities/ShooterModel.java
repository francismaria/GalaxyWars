package com.mygdx.model.entities;

import com.mygdx.game.GalaxyWars;
import com.mygdx.game.GalaxyWars.Difficulty;
import com.mygdx.model.GameModel;

public class ShooterModel extends EnemyModel {
	
	/**
	 * Variable to set the correct time between the shots fired.
	 */
	private float timePassedLastShot;
	
	/**
	 * Variable which depends of the game difficulty to set the minimum time interval to shoot bullets.
	 */
	private float minTimeToShoot;
	
	/**
	 * Variable to check if body needs shooting.
	 */
	private boolean shoot;
	
	/**
	 * Minimum time interval between shots in easy difficulty.
	 */
	private static final float EASY_MIN_TIME = 1.5f;
	
	/**
	 * Minimum time interval between shots in medium difficulty.
	 */
	private static final float MEDIUM_MIN_TIME = 1f;
	
	/**
	 * Minimum time interval between shots in hard difficulty.
	 */
	private static final float HARD_MIN_TIME = 0.5f;
	
	/**
	 * Constructor for the class.
	 * Calls _initTimeBetweenShots.
	 */
	public ShooterModel(){
		super(EnemyType.SHOOTER);
		shoot = false;
		initTimeBetweenShots(GalaxyWars.difficulty);
	}
	
	/**
	 * Given the game difficulty it initializes the interval of minimum time between shots.
	 * @param difficulty the game difficulty.
	 */
	private void initTimeBetweenShots(Difficulty difficulty){
		if(difficulty.equals(Difficulty.EASY)){
			minTimeToShoot = EASY_MIN_TIME;
		} else if(difficulty.equals(Difficulty.MEDIUM)){
			minTimeToShoot = MEDIUM_MIN_TIME;
		} else if(difficulty.equals(Difficulty.HARD)){
			minTimeToShoot = HARD_MIN_TIME;
		}
		timePassedLastShot = 0;
	}
	
	/**
	 * Checks if it is suppose to shoot.
	 * @return if it is require to shoot.
	 */
	public boolean isToShoot(){
		return shoot;
	}
	
	/**
	 * Resets the shoot variable and puts to 0 the time passed since last shot.
	 */
	public void resetShooting(){
		shoot = false;
		timePassedLastShot = 0;
	}
	
	/**
	 * Deals with the shooter enemy AI.
	 * Shoots in the direction of the spaceship.
	 * Updates all the control variables and checks if it is possible to shoot.
	 * @param delta the delta time.
	 * @param model the spaceship model.
	 */
	public void update(float delta, SpaceShipModel model){
		timePassedLastShot += delta;
		if(timePassedLastShot >= minTimeToShoot){
			int controlNumber = GameModel.getRandomNumberInt(1000);
			if((controlNumber % 21 == 0)){
				shoot = true;
			}
		}
	}
}
