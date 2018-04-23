package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GalaxyWars;

public class SpaceShipView extends EntityView {
	
	private TextureRegion region;
	
	public SpaceShipView(GalaxyWars game){
		super(game);
	}

	@Override
	public Sprite createSprite(GalaxyWars game) {
		region = createTextureRegion(game);
		return new Sprite(region);
	}
	
	public TextureRegion createTextureRegion(GalaxyWars game){
        Texture noThrustTexture = game.getAssetManager().get("spaceship.png");
        return new TextureRegion(noThrustTexture, noThrustTexture.getWidth(), noThrustTexture.getHeight());
	}
	
	
    @Override
    public void draw(SpriteBatch batch) {
    	sprite.setSize(100, 68);
        sprite.draw(batch);
    }
	
}
