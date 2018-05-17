package com.mygdx.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.BulletModel;

public class BulletBody extends EntityBody {

	private BulletModel model;
	
	public BulletBody(World world, BulletModel model){
		super(world, model, "Kinematic");
		createBoxFixture(body, 25, 25);
		this.model = model;
	}
	
	public void launch(float initialPosX, float initialPosY){
		
		body.applyLinearImpulse(new Vector2(0.2f, 0), body.getWorldCenter(), true);
	}

	@Override
	public void update() {
		model.setYCoord(body.getPosition().y);
		model.setXCoord(body.getPosition().x);
	}
	
	
}
