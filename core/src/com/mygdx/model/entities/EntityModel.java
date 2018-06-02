package com.mygdx.model.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class EntityModel {
	
	/**
	 * Keeps the position of the entity.
	 */
	private Vector2 position = new Vector2();
	
	/**
	 * Variable to check if entity is or not to be removed.
	 */
	private boolean toRemove;
	
	/**
	 * Keeps the velocity according to the game difficulty.
	 */
	protected Vector2 velocity = new Vector2();
	
	/**
	 * Factor of the velocity in easy game difficulty.
	 */
	protected static final float VEL_FACTOR_EASY = 1;
	
	/**
	 * Factor of the velocity in medium game difficulty.
	 */
	protected static final float VEL_FACTOR_MEDIUM = 1.5f;
	
	/**
	 * Factor of the velocity in hard game difficulty.
	 */
	protected static final float VEL_FACTOR_HARD = 3f;
	
	/**
	 * Constructor of the class.
	 */
	public EntityModel(){
		toRemove = false;
	}
	
	/**
	 * Constructor of the class given its initial position.
	 * @param xCoord
	 * @param yCoord
	 */
	public EntityModel(float xCoord, float yCoord){
		position.x = xCoord;
		position.y = yCoord;
		toRemove = false;
	}
	
	/**
	 * Returns the x coordinate of the entity.
	 * @return x coordinate.
	 */
	public float getXCoord(){
		return position.x;
	}
	
	/**
	 * Returns the y coordinate of the entity.
	 * @return y coordinate.
	 */
	public float getYCoord(){
		return position.y;
	}
	
	/**
	 * Return the x-axis velocity.
	 * @return velocity (x-axis).
	 */
	public float getVelocityX(){
		return velocity.x;
	}
	
	/**
	 * Return the y-axis velocity.
	 * @return velocity (y-axis).
	 */
	public float getVelocityY(){
		return velocity.y;
	}
	
	public void setXCoord(float xCoord){
		position.x = xCoord;
	}
	
	public void setYCoord(float yCoord){
		position.y = yCoord;
	}
	
	public boolean isToRemove(){
		return toRemove;
	}
	
	public void setToRemove(){
		toRemove = true;
	}
	
	public void setToNotRemove(){
		toRemove = false;
	}

}
