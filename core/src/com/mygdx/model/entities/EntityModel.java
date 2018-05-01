package com.mygdx.model.entities;

public abstract class EntityModel {
	
	private float xCoord;
	private float yCoord;
	
	public EntityModel(float xCoord, float yCoord){
		this.xCoord = xCoord; this.yCoord = yCoord;
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
	
	

}
