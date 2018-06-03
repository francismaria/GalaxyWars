package com.mygdx.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.BulletModel;

public class BulletView {

	/**
	 * The bullet sprite.
	 */
	private Sprite sprite;
	
	/**
	 * The bullet model.
	 */
	private BulletModel model;
	
	/**
	 * Constructor of the class.
	 * @param game the game itself.
	 * @param model the bullet model.
	 */
	public BulletView(GalaxyWars game, BulletModel model){
		this.model = model;
		Texture bulletTxt = game.getAssetManager().get("bullet.png");
		sprite = new Sprite(bulletTxt);
	}
	
	/**
	 * Draws the bullet "image".
	 * @param batch
	 */
	public void draw(SpriteBatch batch){
		batch.draw(sprite, model.getXCoord()*GalaxyWars.PIXEL_TO_METER, model.getYCoord()*GalaxyWars.PIXEL_TO_METER);
	}
}
