package com.mygdx.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.ZigZagModel;

public class ZigZagBody extends EnemyBody{
	
	private ZigZagModel model;
	
	public ZigZagBody(World world, ZigZagModel model){
		super(world, model);
		createFixture(body, new float[]{39,32, 50,25.5f, 68,25.5f, 81,32, 67,35, 63,40, 56,40, 52,35}, 120, 68, 0);
		body.setLinearVelocity(model.getVelocityX(), model.getVelocityY());
		this.model = model;
		body.setTransform(model.getXCoord(), model.getYCoord(), 0);
	}
	
	public void update(float delta){
		model.update(delta);
		if(model.hasChangedDirection()){
			body.applyLinearImpulse(new Vector2(0, 0.005f), body.getWorldCenter(), true);
			model.resetChangedDirection();
		}
	}
}
