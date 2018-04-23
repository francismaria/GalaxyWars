package com.mygdx.game.model.entities;

import com.mygdx.gamestructs.Position;

public abstract class GameEntity {
	
	private Position position;
	
	public GameEntity(float x, float y){
		position = new Position(x, y);
	}
	
	public Position getPosition(){
		return position;
	}
	
	public void setPosition(int xCoord, int yCoord){
		position.setCoordinates(xCoord, yCoord);
	}
}
