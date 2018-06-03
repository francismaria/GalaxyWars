package com.mygdx.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.backends.headless.*;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.controller.GameController;
import com.mygdx.game.GalaxyWars.Difficulty;
import com.mygdx.model.GameModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.view.GameView.Movement;

public class TestSpaceship {
	
	private GameController controller;
	
	private GameModel model;
	

    public void updateWorld(float max) {
        float delta = 0;
        while(delta <= max){
            controller.update(0.167f);
            delta+=0.2;
        }
    }
	
	@Test
	public void testMoveUp() {
		GameModel.setMaxEnemyInterval(Difficulty.EASY);
		controller = GameController.getInstance(); model = GameModel.getInstance();
		SpaceShipModel spaceship = model.getSpaceShipModel();
		updateWorld(1f);
		Vector2 initPos = new Vector2(spaceship.getXCoord(), spaceship.getYCoord());
		controller.jumpSpaceShip(Movement.UP);
		updateWorld(2f);
		Vector2 lastPos = new Vector2(spaceship.getXCoord(), spaceship.getYCoord());
		assertTrue(initPos.y < lastPos.y);
	}
	
	@Test
	public void testMoveDown(){
		GameModel.setMaxEnemyInterval(Difficulty.EASY);
		controller = GameController.getInstance(); model = GameModel.getInstance();
		SpaceShipModel spaceship = model.getSpaceShipModel();
		updateWorld(1f);
		Vector2 initPos = new Vector2(spaceship.getXCoord(), spaceship.getYCoord());
		controller.jumpSpaceShip(Movement.DOWN);
		updateWorld(2f);
		Vector2 lastPos = new Vector2(spaceship.getXCoord(), spaceship.getYCoord());
		assertTrue(initPos.y > lastPos.y);
	}
	
	@Test
	public void testGravity(){
		GameModel.setMaxEnemyInterval(Difficulty.EASY);
		controller = GameController.getInstance(); model = GameModel.getInstance();
		SpaceShipModel spaceship = model.getSpaceShipModel();
		updateWorld(1f);
		Vector2 initPos = new Vector2(spaceship.getXCoord(), spaceship.getYCoord());
		updateWorld(2f);
		Vector2 lastPos = new Vector2(spaceship.getXCoord(), spaceship.getYCoord());
		assertTrue(initPos.y > lastPos.y);
	}

}
