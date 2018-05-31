package com.mygdx.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.ShooterModel;

public class ShooterView {

	private Sprite sprite;
	private ShooterModel model;
	
	public ShooterView(GalaxyWars game, ShooterModel model){
		this.model = model;
		Texture zigzagTxt = game.getAssetManager().get("shooter.png");
		sprite = new Sprite(zigzagTxt);
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(sprite, model.getXCoord()*GalaxyWars.PIXEL_TO_METER, model.getYCoord()*GalaxyWars.PIXEL_TO_METER);
	}
}
