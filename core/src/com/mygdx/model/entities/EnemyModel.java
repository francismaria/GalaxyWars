package com.mygdx.model.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class EnemyModel extends EntityModel{
	
	/**
	 * Defines the three different enemy types.
	 * @author Francisco / Dinis
	 */
	public enum EnemyType{
		ZIGZAG, KAMIKAZE, SHOOTER
	}
	
	/**
	 * Defines the entity type.
	 */
	private EnemyType type;
	
	/**
	 * Constructor for the class.
	 * @param type one of the three different types.
	 */
	public EnemyModel(EnemyType type){
		this.type = type;
	}
	
	/**
	 * Constructor for the class with intial position.
	 * @param type one of the three different types.
	 * @param initialXCoord x position.
	 * @param initialYCoord y position.
	 */
	public EnemyModel(EnemyType type, float initialXCoord, float initialYCoord){
		super(initialXCoord, initialYCoord);
		this.type = type;
	}
	
	/**
	 * Gets the type of the enemy.
	 * @return type.
	 */
	public EnemyType getType(){
		return type;
	}
	
	/**
	 * Sets the initial position of the entity.
	 * @param initialPos vector with the initial position.
	 */
	public void setInitialPos(Vector2 initialPos){
		setXCoord(initialPos.x);
		setYCoord(initialPos.y);
	}
}
