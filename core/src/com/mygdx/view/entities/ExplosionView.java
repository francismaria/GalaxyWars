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
	
	private boolean finished;
	
	private Animation anim = null;
	
	public ExplosionView(GalaxyWars game, ExplosionModel model){
		this.game = game;
		this.model = model;
		this.finished = false;
		stateTime = 0;
		setTextureRegions();
	}
	
	private void setTextureRegions(){
		Texture explosionTxt = game.getAssetManager().get("explosion-steps.png");
		
		TextureRegion[][] tmpFrames = TextureRegion.split(explosionTxt, 120, 169);
		
		int index = 0;
		
		for(int i = 0; i < 5; i++){
			explosionFrames[index++] = tmpFrames[0][i];
		}
		
		anim = new Animation(1f/5f, explosionFrames);
	}
	
	public boolean isFinished(){
		return finished;
	}
	
	public void update(float delta){
		stateTime += delta;
		if(anim.isAnimationFinished(stateTime)){
			finished = true;
		}
	}
	
	public void draw(SpriteBatch batch){
		//x e y têm de ser multiplicados por GalaxyWars.PIXEL_TO_METER
		//batch.draw(x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		//batch.draw()
		
		batch.draw((TextureRegion)anim.getKeyFrame(stateTime), model.getXCoord()*GalaxyWars.PIXEL_TO_METER, model.getYCoord()*GalaxyWars.PIXEL_TO_METER);
	
	 }

}
