package com.mygdx.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.BulletModel;

public class BulletBody extends EntityBody {

	private BulletModel model;
	
	public BulletBody(World world, BulletModel model){
		super(world, model, "Kinematic");
		createFixture(body, new float[]{4,5, 4,2, 11,2, 11,5}, 15, 7, 0);
		this.model = model;
		body.setTransform(model.getXCoord(), model.getYCoord(), 0);
	}
	
	public void launch(Vector2 initialPos){
		moveToSpaceshipPos(initialPos); 
		body.setLinearVelocity(new Vector2(4f, 0));
	}
	
	public void launch(){
		body.setLinearVelocity(new Vector2(4f, 0));
	}
	
	private void moveToSpaceshipPos(Vector2 spaceshipPos){
		body.setTransform(spaceshipPos, 0);
		update();
	}

	public void update() {
		model.setYCoord(body.getPosition().y);
		model.setXCoord(body.getPosition().x);
	}
	
	
}
