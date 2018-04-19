package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GalaxyWars;

public class SpaceShipView extends EntityView {

	public SpaceShipView(GalaxyWars game){
		super(game);
	}

	@Override
	public Sprite createSprite(GalaxyWars game) {
		return new Sprite();
	}
	
    @Override
    public void draw(SpriteBatch batch) {
       
        sprite.draw(batch);
    }
	
}
