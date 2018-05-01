package com.mygdx.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.SpaceShipModel;

public class SpaceShipView {
	
	private Sprite sprite;
	
	public SpaceShipView(GalaxyWars game, SpaceShipModel model){
		Texture spaceshipTxt = game.getAssetManager().get("space-ship.png");
		sprite = new Sprite(spaceshipTxt);
	}
	
	public void draw(SpriteBatch batch){
		sprite.draw(batch);
	}
}
