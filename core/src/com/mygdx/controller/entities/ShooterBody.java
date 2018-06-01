package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.ShooterModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;

public class ShooterBody extends EnemyBody {
	
	private ShooterModel model;
	
	public ShooterBody(World world, ShooterModel model){
		super(world, model);
		createFixture(body, new float[]{2,21, 22,13, 55,13, 77,21, 60,26, 50,32, 27,32, 16,26}, 75, 42, 0);
		body.setGravityScale(0);
		body.setLinearVelocity(-1f, 0f);
		this.model = model;
		body.setTransform(model.getXCoord(), model.getYCoord(), 0);
	}

	public boolean update(float delta, SpaceShipModel spaceship) {
		model.update(delta, spaceship);
		boolean shoot = model.isToShoot();
		System.out.println("BODY:  " + body.getPosition().y + "  " + model.getYCoord()); 
		return shoot;
	}
	
	@Override
	public void update(float delta){}
	
	public ShooterModel getModel(){
		return model;
	}
}
