package com.mygdx.game.model;

import com.mygdx.game.model.entities.SpaceShip;

public class GameModel {
	
	private static GameModel instance;
	
	private SpaceShip spaceShip;
	
	private GameModel(){
		
	}
	
    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }
    
    

}
