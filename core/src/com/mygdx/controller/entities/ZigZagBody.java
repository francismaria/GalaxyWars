package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.ZigZagModel;

public class ZigZagBody extends EnemyBody{
	
	private ZigZagModel model;
	
	public ZigZagBody(World world, ZigZagModel model){
		super(world, model);
		createFixture(body);
		body.setLinearVelocity(-0.8f, 0f);
		this.model = model;
	}
	
	public void update(){
		model.setYCoord(body.getPosition().y);
		model.setXCoord(body.getPosition().x);
	}
}