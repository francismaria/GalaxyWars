package com.mygdx.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.KamikazeModel;

public class KamikazeView {

	/**
	 * The kamikaze sprite.
	 */
	private Sprite sprite;
	
	/**
	 * The kamikaze model.
	 */
	private KamikazeModel model;
	
	/**
	 * Constructor of the class.
	 * @param game
	 * @param model
	 */
	public KamikazeView(GalaxyWars game, KamikazeModel model){
		this.model = model;
		Texture zigzagTxt = game.getAssetManager().get("kamikaze.png");
		sprite = new Sprite(zigzagTxt);
	}
	
	/**
	 * Draws the kamikaze "image".
	 * @param batch
	 */
	public void draw(SpriteBatch batch){
		batch.draw(sprite, model.getXCoord()*GalaxyWars.PIXEL_TO_METER, model.getYCoord()*GalaxyWars.PIXEL_TO_METER);
	}
}
