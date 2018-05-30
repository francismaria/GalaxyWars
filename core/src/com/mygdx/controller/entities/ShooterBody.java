package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.ShooterModel;
import com.mygdx.model.entities.ZigZagModel;

public class ShooterBody extends EnemyBody {
	
	private ShooterModel model;
	
	public ShooterBody(World world, ShooterModel model){
		super(world, model);
		createFixture(body, new float[]{2,21, 22,13, 55,13, 77,21, 60,26, 50,32, 27,32, 16,26}, 75, 42, 0);
		body.setGravityScale(0);
		body.setLinearVelocity(-1f, 0f);
		this.model = model;
		body.setTransform(model.getXCoord(), model.getYCoord(), 0);
	}
	
	@Override
	public void update(){
		model.setYCoord(body.getPosition().y);
		model.setXCoord(body.getPosition().x);
	}

}
