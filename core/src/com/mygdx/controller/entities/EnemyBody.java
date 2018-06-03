package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.EntityModel;

public abstract class EnemyBody extends EntityBody{
	
	/**
	 * Class constructor.
	 * Redirects it to its super class.
	 * @param world the game world.
	 * @param model the enemy model.
	 */
	public EnemyBody(World world, EntityModel model){
		super(world, model, "Dynamic");	
	}
	
	/**
	 * Update function.
	 * @param delta
	 */
	public abstract void update(float delta);
}	
