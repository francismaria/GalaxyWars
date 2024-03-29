package com.mygdx.model.entities;

import com.badlogic.gdx.math.Vector2;
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
	 * Variable that checks if the entity can still shoot according to its position.
	 */
	private boolean canShoot;
	
	/**
	 * Variable that sets the distance between the spaceship and the entity where the latter shall stop shooting.
	 */
	private static final float STOP_SHOOTING_DISTANCE = 1f;
	
	/**
	 * Physics time variable.
	 * Represents for how much time should the bullet be flying.
	 */
	private float bulletFlyTime;
	
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
	 * Time for the bullet fly through the air in easy difficulty.
	 */
	private static final float BULLET_FLY_TIME_EASY = 3f;
	
	/**
	 * Time for the bullet fly through the air in medium difficulty.
	 */
	private static final float BULLET_FLY_TIME_MEDIUM = 2f;
	
	/**
	 * Time for the bullet fly through the air in hard difficulty.
	 */
	private static final float BULLET_FLY_TIME_HARD = 1f;
	
	/**
	 * Constructor for the class.
	 * Calls _initTimeBetweenShots.
	 */
	public ShooterModel(){
		super(EnemyType.SHOOTER);
		shoot = false;
		canShoot = true;
		initVelocity();
		initTimeBetweenShots(GameModel.difficulty);
	}
	
	/**
	 * Initializes "base" velocity.
	 */
	private void initVelocity(){
		velocity.x = -1f;
		velocity.y = 0;
	}
	
	/**
	 * Given the game difficulty it initializes the interval of minimum time between shots.
	 * @param difficulty the game difficulty.
	 */
	private void initTimeBetweenShots(Difficulty difficulty){
		if(difficulty.equals(Difficulty.EASY)){
			minTimeToShoot = EASY_MIN_TIME;
			velocity.x *= VEL_FACTOR_EASY;
			bulletFlyTime = BULLET_FLY_TIME_EASY;
		} else if(difficulty.equals(Difficulty.MEDIUM)){
			minTimeToShoot = MEDIUM_MIN_TIME;
			velocity.x *= VEL_FACTOR_MEDIUM;
			bulletFlyTime = BULLET_FLY_TIME_MEDIUM;
		} else if(difficulty.equals(Difficulty.HARD)){
			minTimeToShoot = HARD_MIN_TIME;
			velocity.x *= VEL_FACTOR_HARD;
			bulletFlyTime = BULLET_FLY_TIME_HARD;
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
	 * Deals with AI.
	 * Function to get the force the shooter enemy has to apply on the bullet to 
	 * go in the direction of the spaceship position.
	 * (Physics movement equations with g = 0N (null) and the time the bullet takes
	 * to reach the spaceship position is declared according to the game difficulty). 
	 * @param spaceship the spaceship model.
	 * @return vector representing the force to be applied in the x and y axis.
	 */
	public Vector2 getForce(SpaceShipModel spaceship){
		Vector2 force = new Vector2();
		float yTmp = (Math.abs((getYCoord()-spaceship.getYCoord())))/bulletFlyTime;
		
		force.x = 0-(getXCoord()-spaceship.getXCoord())/bulletFlyTime;
		
		if(getYCoord() > spaceship.getYCoord()){
			force.y = -yTmp;
		} else {
			force.y = yTmp;
		}
		return force;
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
		checkPosition(model);
		if(timePassedLastShot >= minTimeToShoot && canShoot){
			int controlNumber = GameModel.getRandomNumberInt(1000);
			if((controlNumber % 21 == 0)){
				shoot = true;
			}
		}
	}
	
	/**
	 * Compares the position of the entity and the spaceship.
	 * If the shooter is above or below the spaceship it must not shoot as the spaceship isn't able
	 * to move left or right. This way, this entity must only shoot if its x coordinate is bigger
	 * than the x coordinate of the spaceship.
	 */
	private void checkPosition(SpaceShipModel spaceship){
		
		float spaceshipXCoord = spaceship.getXCoord();
		
		if(Math.abs(spaceshipXCoord - getXCoord()) < STOP_SHOOTING_DISTANCE){
			canShoot = false;
		}
	}
}
