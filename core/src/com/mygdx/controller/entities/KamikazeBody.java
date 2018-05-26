package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.KamikazeModel;
import com.mygdx.model.entities.ZigZagModel;

public class KamikazeBody extends EnemyBody {
	
	private KamikazeModel model;
	
	public KamikazeBody(World world, KamikazeModel model){
		super(world, model);
		createFixture(body, new float[]{12,28, 15,28, 19,32, 19,42, 13,43}, 50, 50, 0);
		body.setGravityScale(0);
		body.setLinearVelocity(-0.8f, 0f);
		this.model = model;
		body.setTransform(model.getXCoord(), model.getYCoord(), 0);
	}
	
	@Override
	public void update(){
		model.setYCoord(body.getPosition().y);
		model.setXCoord(body.getPosition().x);
	}

}