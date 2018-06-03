package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.ShooterModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;

public class ShooterBody extends EnemyBody {
	
	/**
	 * The body model.
	 */
	private ShooterModel model;
	
	/**
	 * Constructor for the class.
	 * Initializes the fixture and set its velocity.
	 * @param world
	 * @param model
	 */
	public ShooterBody(World world, ShooterModel model){
		super(world, model);
		createFixture(body, new float[]{2,21, 22,13, 55,13, 77,21, 60,26, 50,32, 27,32, 16,26}, 75, 42, 0,  (ENEMY_BODY), (short) (SPACESHIP_BODY | BULLET_BODY));
		body.setGravityScale(0);
		body.setLinearVelocity(model.getVelocityX(), model.getVelocityY());
		this.model = model;
		body.setTransform(model.getXCoord(), model.getYCoord(), 0);
	}
	
	/**
	 * Updates the spaceship model depending on the spaceship model.
	 * Checks if it has to shoot.
	 * @param delta
	 * @param spaceship the spaceship model.
	 * @return if it has or not to shoot.
	 */
	public boolean update(float delta, SpaceShipModel spaceship) {
		model.update(delta, spaceship);
		boolean shoot = model.isToShoot();
		return shoot;
	}
	
	@Override
	public void update(float delta){}
	
	/**
	 * Gets the shooter body model.
	 * @return the shooter model.
	 */
	public ShooterModel getModel(){
		return model;
	}
}
