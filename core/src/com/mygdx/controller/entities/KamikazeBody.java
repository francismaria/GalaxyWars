package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GalaxyWars;
import com.mygdx.game.GalaxyWars.Difficulty;
import com.mygdx.model.entities.KamikazeModel;
import com.mygdx.model.entities.ZigZagModel;

public class KamikazeBody extends EnemyBody {
	
	/**
	 * The model of the body.
	 */
	private KamikazeModel model;
	
	/**
	 * The body speed in easy difficulty.
	 */
	private static final float EASY_SPEED = -0.8f;
	
	/**
	 * The body speed in medium difficulty.
	 */
	private static final float MEDIUM_SPEED = -2f;
	
	/**
	 * The body speed in hard difficulty.
	 */
	private static final float HARD_SPEED = -4f;
	
	/**
	 * Constructor of the "kamikaze" body.
	 * Inits the fixture of the body and initializes the movement definitions.
	 * @param world the world.
	 * @param model the kamikaze model.
	 */
	public KamikazeBody(World world, KamikazeModel model){
		super(world, model);
		this.model = model;
		createFixture(body, new float[]{1,31, 20,22, 41,22, 59,31, 44,36, 35,42, 27,42, 18,36}, 60, 60, 0);
		initMovement();
	}
	
	/**
	 * Sets the body gravity to null and moves the body to its model position.
	 */
	private void initMovement(){
		body.setGravityScale(0);
		setVelocity(GalaxyWars.difficulty);
		body.setTransform(model.getXCoord(), model.getYCoord(), 0);
	}
	
	/**
	 * Sets the body velocity according to the game difficulty.
	 * @param difficulty the difficulty of the game.
	 */
	private void setVelocity(Difficulty difficulty){
		if(difficulty.equals(Difficulty.EASY)){
			body.setLinearVelocity(EASY_SPEED, 0f);
		} else if(difficulty.equals(Difficulty.MEDIUM)){
			body.setLinearVelocity(MEDIUM_SPEED, 0f);
		} else if(difficulty.equals(Difficulty.HARD)){
			body.setLinearVelocity(HARD_SPEED, 0f);
		}
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
