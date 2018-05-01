package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.SpaceShipModel;

public class SpaceShipBody extends EntityBody{
	
	private SpaceShipModel model;
	
	public SpaceShipBody(World world, SpaceShipModel model){
		super(world, model);
		this.model = model;
	}
	
	public void update(){
		model.setYCoord(body.getPosition().y);
	}

}
