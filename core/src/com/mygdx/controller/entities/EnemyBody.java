package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.EntityModel;

public abstract class EnemyBody extends EntityBody{
	
	public EnemyBody(World world, EntityModel model){
		super(world, model, "Dynamic");	
	}
	
	public abstract void update(float delta);
}	
