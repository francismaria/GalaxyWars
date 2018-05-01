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
			return new GameModel();
		}
		return instance;
	}
	
	public SpaceShipModel getSpaceShipModel(){
		return spaceship;
	}
	
}
