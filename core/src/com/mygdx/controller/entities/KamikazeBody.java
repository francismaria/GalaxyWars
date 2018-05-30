package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.KamikazeModel;
import com.mygdx.model.entities.ZigZagModel;

public class KamikazeBody extends EnemyBody {
	
	private KamikazeModel model;
	
	public KamikazeBody(World world, KamikazeModel model){
		super(world, model);
		createFixture(body, new float[]{1,31, 20,22, 41,22, 59,31, 44,36, 35,42, 27,42, 18,36}, 60, 60, 0);
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
