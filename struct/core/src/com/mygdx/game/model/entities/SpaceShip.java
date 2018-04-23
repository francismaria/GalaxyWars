package com.mygdx.game.model.entities;

import com.badlogic.gdx.Gdx;

public class SpaceShip extends GameEntity {
	
	public SpaceShip(){
		super(50/2, 100/2);
		Gdx.app.log("SPACE SHIP: ", "created");
	}
	
	
}
