package com.mygdx.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.ZigZagModel;

public class ZigZagBody extends EnemyBody{
	
	/**
	 * The zig-zag model.
	 */
	private ZigZagModel model;
	
	/**
	 * Constructor of the class.
	 * Creates the fixture for the body and initializes it with correct velocity.
	 * @param world the game world.
	 * @param model the zigzag model.
	 */
	public ZigZagBody(World world, ZigZagModel model){
		super(world, model);
		createFixture(body, new float[]{39,32, 50,25.5f, 68,25.5f, 81,32, 67,35, 63,40, 56,40, 52,35}, 120, 68, 0, ENEMY_BODY, (short) (SPACESHIP_BODY | BULLET_BODY));
		body.setLinearVelocity(model.getVelocityX(), model.getVelocityY());
		this.model = model;
		body.setTransform(model.getXCoord(), model.getYCoord(), 0);
	}
	
	/**
	 * Updates the body position depending on its model AI.
	 */
	public void update(float delta){
		model.update(delta);
		if(model.hasChangedDirection()){
			body.applyLinearImpulse(new Vector2(0, 0.005f), body.getWorldCenter(), true);
			model.resetChangedDirection();
		}
	}
}
