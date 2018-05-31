package com.mygdx.model.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class EntityModel {
	
	private Vector2 position = new Vector2();
	private boolean toRemove;
	
	public EntityModel(){
		toRemove = false;
	}
	
	public EntityModel(float xCoord, float yCoord){
		position.x = xCoord;
		position.y = yCoord;
		toRemove = false;
	}
	
	public float getXCoord(){
		return position.x;
	}
	
	public float getYCoord(){
		return position.y;
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
