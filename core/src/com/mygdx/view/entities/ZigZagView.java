package com.mygdx.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.ZigZagModel;

public class ZigZagView {
	
	/**
	 * The zigzag sprite.
	 */
	private Sprite sprite;
	
	/**
	 * The zigzag model.
	 */
	private ZigZagModel model;
	
	/**
	 * Constructor of the class.
	 * @param game
	 * @param model
	 */
	public ZigZagView(GalaxyWars game, ZigZagModel model){
		this.model = model;
		Texture zigzagTxt = game.getAssetManager().get("zigzag.png");
		sprite = new Sprite(zigzagTxt);
	}
	
	/**
	 * Draws the zigzag "image".
	 * @param batch
	 */
	public void draw(SpriteBatch batch){
		batch.draw(sprite, model.getXCoord()*GalaxyWars.PIXEL_TO_METER, model.getYCoord()*GalaxyWars.PIXEL_TO_METER);
	}
}
