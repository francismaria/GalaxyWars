package com.mygdx.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mygdx.controller.GameController;
import com.mygdx.game.GalaxyWars.Difficulty;
import com.mygdx.model.GameModel;
import com.mygdx.model.entities.SpaceShipModel;

public class TestEnemiesBehaviour {

	
	private GameController controller;
	
	private GameModel model;
	
	/**
	 * Updates the controller for a limited time.
	 * @param max
	 */
    public void updateWorld(float max) {
        float delta = 0;
        while(delta <= max){
            controller.update(0.167f);
            delta+=0.2;
        }
    }
    
    @Test
    public void testEnemyCreation(){
		GameModel.setMaxEnemyInterval(Difficulty.EASY);
		controller = GameController.getInstance(); model = GameModel.getInstance();
		SpaceShipModel spaceship = model.getSpaceShipModel();
		updateWorld(10f);
		assertTrue(controller.getEnemies().size() > 0);
    }

}
