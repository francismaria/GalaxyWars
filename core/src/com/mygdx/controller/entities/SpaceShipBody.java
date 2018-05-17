package com.mygdx.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.SpaceShipModel;

public class SpaceShipBody extends EntityBody{
	
	private SpaceShipModel model;
	
	public SpaceShipBody(World world, SpaceShipModel model){
		super(world, model, "Dynamic");				
		
		// down pipe
		createFixture(body, new float[]{5,38, 5,42, 22,44.5f, 23.5f,36.5f}, 50, 50, 1f);
		// up pipe
		createFixture(body, new float[]{5,61, 5,66, 23.5f,66, 22,60}, 50, 50, 1f);
		// main body
		createFixture(body, new float[]{22,44.5f, 22,60, 75.5f,60, 75.5f,44.5f}, 50, 50, 1f);
		// down winglet
		createFixture(body, new float[]{23.5f,36.5f, 23.5f,34, 17,33.3f, 30.5f,33.3f, 40,40.5f}, 50, 50, 1f);
		
		/*createFixture(body, new float[]{32,12, 31,46, 34,51, 40,51, 43,46, 41,12}, 50, 50, 1f);*/
		this.model = model;
	}
	
	@Override
	public void update(){
		model.setYCoord(body.getPosition().y);
		model.setXCoord(body.getPosition().x);
	}
	
	public void jump(){
		
		body.applyLinearImpulse(new Vector2(0, 0.05f), body.getWorldCenter(), true);
	}

}
