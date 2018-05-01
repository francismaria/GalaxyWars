package com.mygdx.model;

import com.mygdx.model.entities.SpaceShipModel;

public class GameModel {
	
	private static GameModel instance;
	
	private SpaceShipModel spaceship;
	
	private GameModel(){
		spaceship = new SpaceShipModel(); 
	}
	
	public static GameModel getInstance(){
		if(instance == null){
			instance = new GameModel();
		}
		return instance;
	}
	
	public SpaceShipModel getSpaceShipModel(){
		return spaceship;
	}
	
	public void update(float delta){
		spaceship.updatePosition();
	}
	
}
