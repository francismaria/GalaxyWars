package com.mygdx.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.ExplosionModel;

public class ExplosionView {
	
	/**
	 * The game itself (to access the batch).
	 */
	private GalaxyWars game;
	
	/**
	 * Time passed since the beginning of the explosion. 
	 */
	private float stateTime;
	
	/**
	 * The frames of the explosion image (divided).
	 */
	private TextureRegion[] explosionFrames = new TextureRegion[5];
	
	/**
	 * The explosion model.
	 */
	private ExplosionModel model;
	
	/**
	 * Indicates whether the explosion has finished or not (controlled by the stateTime).
	 */
	private boolean finished;
	
	/**
	 * The animation itself.
	 */
	private Animation anim = null;
	
	/**
	 * Offset value to correctly draw the explosion in the position of the collision.
	 */
	private static final float OFFSET_POS = 20 / GalaxyWars.PIXEL_TO_METER;
	
	/**
	 * Constructor which initializes all the variables and calls _setTextureRegions.
	 * @param game the game itself
	 * @param model the explosion model
	 */
	public ExplosionView(GalaxyWars game, ExplosionModel model){
		this.game = game;
		this.model = model;
		this.finished = false;
		stateTime = 0;
		setTextureRegions();
	}
	
	/**
	 * Parses the texture frames of the explosion image and initializes the annimation.
	 */
	private void setTextureRegions(){
		Texture explosionTxt = game.getAssetManager().get("explosion-steps.png");
		
		TextureRegion[][] tmpFrames = TextureRegion.split(explosionTxt, 84, 118);
		
		int index = 0;
		
		for(int i = 0; i < 5; i++){
			explosionFrames[index++] = tmpFrames[0][i];
		}
		
		anim = new Animation(1f/10f, explosionFrames);
	}
	
	/**
	 * Returns if the explosion has finished or not.
	 * @return finish control variable.
	 */
	public boolean isFinished(){
		return finished;
	}
	
	/**
	 * Updates stateTime and checks if it is or not finished.
	 * @param delta
	 */
	public void update(float delta){
		stateTime += delta;
		if(anim.isAnimationFinished(stateTime)){
			finished = true;
		}
	}
	
	/**
	 * Draws the correct animation frame.
	 * @param batch
	 */
	public void draw(SpriteBatch batch){
		batch.draw((TextureRegion)anim.getKeyFrame(stateTime), (model.getXCoord()*GalaxyWars.PIXEL_TO_METER)-OFFSET_POS, (model.getYCoord()*GalaxyWars.PIXEL_TO_METER)-OFFSET_POS);
	
	 }

}
