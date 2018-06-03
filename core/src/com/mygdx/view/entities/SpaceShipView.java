package com.mygdx.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.SpaceShipModel;

public class SpaceShipView {
	
	/**
	 * The spaceship view.
	 */
	private Sprite sprite;
	
	/**
	 * The spaceship model.
	 */
	private SpaceShipModel model;
	
	/**
	 * Constructor of the class.
	 * @param game
	 * @param model
	 */
	public SpaceShipView(GalaxyWars game, SpaceShipModel model){
		this.model = model;
		Texture spaceshipTxt = game.getAssetManager().get("space-ship.png");
		sprite = new Sprite(spaceshipTxt);
	}
	
	/**
	 * Draws the spaceship "image".
	 * @param batch
	 */
	public void draw(SpriteBatch batch){
		batch.draw(sprite, model.getXCoord()*GalaxyWars.PIXEL_TO_METER, model.getYCoord()*GalaxyWars.PIXEL_TO_METER);
	}
	
}
