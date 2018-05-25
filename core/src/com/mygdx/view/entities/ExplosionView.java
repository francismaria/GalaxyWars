package com.mygdx.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.ExplosionModel;

public class ExplosionView {
	
	private float stateTime;
	private GalaxyWars game;
	private TextureRegion[] explosionFrames = new TextureRegion[5];
	private ExplosionModel model;
	
	public ExplosionView(GalaxyWars game, ExplosionModel model){
		this.game = game;
		this.model = model;
		stateTime = 0;
		setTextureRegions();
	}
	
	private void setTextureRegions(){
		Texture explosionTxt = game.getAssetManager().get("explosion-steps.png");
		
	}
	
	public void draw(SpriteBatch batch){
		//x e y têm de ser multiplicados por GalaxyWars.PIXEL_TO_METER
		//batch.draw(x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		//batch.draw()
	}

}
