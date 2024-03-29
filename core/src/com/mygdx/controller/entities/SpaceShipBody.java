package com.mygdx.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.view.GameView.Movement;

public class SpaceShipBody extends EntityBody{
	
	/**
	 * The spaceship model.
	 */
	private SpaceShipModel model;
	
	/**
	 * Constructor of the class.
	 * Initializes the spaceship body fixtures.
	 * @param world the game world.
	 * @param model the spaceship model.
	 */
	public SpaceShipBody(World world, SpaceShipModel model){
		super(world, model, "Dynamic");
		//up
		createFixture(body, new float[]{6,60, 76,56, 92,57, 81,63, 60,63, 28,79}, 100, 100, 1f, ENEMY_BODY, (short) (SPACESHIP_BODY | BULLET_BODY));
		//middle
		createFixture(body, new float[]{6,60, 6,36, 76,41, 76,56}, 100, 100, 1f, ENEMY_BODY, (short) (SPACESHIP_BODY | BULLET_BODY));
		//bottom
		createFixture(body, new float[]{6,36, 28,18, 60,33, 82,33, 92,39, 76,41}, 100, 100, 1f, ENEMY_BODY, (short) (SPACESHIP_BODY | BULLET_BODY));
		this.model = model;
	}
	
	/**
	 * Applies a force caracterizing the jump depending on its direction.
	 * @param move jump direction (up/down).
	 */
	public void jump(Movement move){
		if(move.equals(Movement.UP))
			body.applyLinearImpulse(new Vector2(0, 0.1f), body.getWorldCenter(), true);
		else
			body.applyLinearImpulse(new Vector2(0, -0.05f), body.getWorldCenter(), true);
	}
	
	/**
	 * Gets the spaceship model.
	 * @return the spaceship model.
	 */
	public SpaceShipModel getModel(){
		return model;
	}

}
