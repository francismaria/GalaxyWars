package com.mygdx.model.entities;

public abstract class EntityModel {
	
	private float xCoord;
	private float yCoord;
	private boolean toRemove;
	
	public EntityModel(){
		toRemove = false;
	}
	
	public EntityModel(float xCoord, float yCoord){
		this.xCoord = xCoord; this.yCoord = yCoord;
		toRemove = false;
	}
	
	public float getXCoord(){
		return xCoord;
	}
	
	public float getYCoord(){
		return yCoord;
	}
	
	public void setXCoord(float xCoord){
		this.xCoord = xCoord;
	}
	
	public void setYCoord(float yCoord){
		this.yCoord = yCoord;
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
