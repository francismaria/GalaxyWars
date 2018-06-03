package com.mygdx.model.entities;

import com.badlogic.gdx.math.Vector2;

public class BulletModel extends EntityModel{
	
	/**
	 * Control variable to check if the bullet was or not fired.
	 */
	private boolean fired;
	
	/**
	 * Constructor of the class.
	 */
	public BulletModel(){
		super();
		setToNotRemove();
		fired = false;
	}
	
	/**
	 * Checks if bullet was or not fired.
	 * @return if the bullet was or not fired.
	 */
	public boolean wasFired(){
		return fired;
	}
	
	/**
	 * Sets the control variable to true (fired).
	 */
	public void setFired(){
		fired = true;
	}
	
	/**
	 * Sets the model initial position given a vector representing it.
	 * @param initialPos intial position of the body.
	 */
	public void setInitialPosition(Vector2 initialPos){
		setXCoord(initialPos.x);
		setYCoord(initialPos.y);
	}
}
