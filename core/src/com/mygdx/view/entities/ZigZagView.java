package com.mygdx.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.ZigZagModel;

public class ZigZagView {
	
	private Sprite sprite;
	private ZigZagModel model;
	
	public ZigZagView(GalaxyWars game, ZigZagModel model){
		this.model = model;
		Texture zigzagTxt = game.getAssetManager().get("zigzag.png");
		sprite = new Sprite(zigzagTxt);
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(sprite, model.getXCoord()*GalaxyWars.PIXEL_TO_METER, model.getYCoord()*GalaxyWars.PIXEL_TO_METER);
	}
}
