package com.mygdx.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GalaxyWars;
import com.mygdx.game.model.entities.GameEntity;
import com.mygdx.game.view.GameView;

public abstract class EntityView {

	Sprite sprite;
	
	public EntityView(GalaxyWars game){
		sprite = createSprite(game);
	}
	
	public abstract Sprite createSprite(GalaxyWars game);

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
    
	public void update(GameEntity model){
        sprite.setCenter(model.getPosition().getXCoordinate() / GameView.PIXEL_TO_METER,
        		model.getPosition().getYCoordinate() / GameView.PIXEL_TO_METER);
	}
}
