package com.mygdx.model.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class EnemyModel extends EntityModel{
	
	public enum EnemyType{
		ZIGZAG, KAMIKAZE, SHOOTER
	}
	
	private EnemyType type;
	
	public EnemyModel(EnemyType type){
		this.type = type;
	}
	
	public EnemyModel(EnemyType type, float initialXCoord, float initialYCoord){
		super(initialXCoord, initialYCoord);
		this.type = type;
	}
	
	public EnemyType getType(){
		return type;
	}
	
	public void setInitialPos(Vector2 initialPos){
		setXCoord(initialPos.x);
		setYCoord(initialPos.y);
	}
}
