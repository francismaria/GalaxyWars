package com.mygdx.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.SpaceShipModel;

public class SpaceShipBody extends EntityBody{
	
	private SpaceShipModel model;
	
	public SpaceShipBody(World world, SpaceShipModel model){
		super(world, model, "Dynamic");
		//up
		createFixture(body, new float[]{6,60, 76,56, 92,57, 81,63, 60,63, 28,79}, 100, 100, 1f);
		//middle
		createFixture(body, new float[]{6,60, 6,36, 76,41, 76,56}, 100, 100, 1f);
		//bottom
		createFixture(body, new float[]{6,36, 28,18, 60,33, 82,33, 92,39, 76,41}, 100, 100, 1f);
		this.model = model;
	}
	/*
	@Override
	public void update(){
		model.setYCoord(body.getPosition().y);
		model.setXCoord(body.getPosition().x);
	}*/
	
	public void jump(){
		
		body.applyLinearImpulse(new Vector2(0, 0.05f), body.getWorldCenter(), true);
	}

}
