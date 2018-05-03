package com.mygdx.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.SpaceShipModel;

public class SpaceShipView {
	
	private Sprite sprite;
	private SpaceShipModel model;
	
	public SpaceShipView(GalaxyWars game, SpaceShipModel model){
		this.model = model;
		Texture spaceshipTxt = game.getAssetManager().get("space-ship.png");
		sprite = new Sprite(spaceshipTxt);
	}
	
	public void draw(SpriteBatch batch){
		/*sprite.setX(model.getXCoord() / GalaxyWars.PIXEL_TO_METER);
		sprite.setY(model.getYCoord() / GalaxyWars.PIXEL_TO_METER);*/
		//sprite.draw(batch, model.getXCoord(), model.getYCoord());
		batch.draw(sprite, model.getXCoord()*GalaxyWars.PIXEL_TO_METER, model.getYCoord()*GalaxyWars.PIXEL_TO_METER);
		System.out.println("MODEL: Y: "  + model.getYCoord());
	}
	
}
