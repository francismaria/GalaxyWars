package com.mygdx.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.SpaceShipModel;

public class SpaceShipBody extends EntityBody{
	
	private SpaceShipModel model;
	
	public SpaceShipBody(World world, SpaceShipModel model){
		super(world, model, "Dynamic");
		createFixture(body);
		this.model = model;
	}
	
	public void update(){
		model.setYCoord(body.getPosition().y);
		model.setXCoord(body.getPosition().x);
	}
	
	public void jump(){
		body.applyLinearImpulse(new Vector2(0, 1f), body.getWorldCenter(), true);
	}

}
