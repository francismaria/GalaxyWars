package com.mygdx.gamestructs;

public class Position {
	
	float x, y;
	
	public Position(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getXCoordinate(){
		return x;
	}
	
	public float getYCoordinate(){
		return y;
	}
	
	public void setXCoordinate(float x){
		this.x = x;
	}
	
	public void setYCoordinate(float y){
		this.y = y;
	}
	
	public void setCoordinates(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString(){
		return "X pos: " + x + "\tY pos: " + y;
	}
	
}
