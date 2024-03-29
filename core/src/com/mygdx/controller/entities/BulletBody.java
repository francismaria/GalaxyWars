package com.mygdx.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.model.GameModel;
import com.mygdx.model.entities.BulletModel;

public class BulletBody extends EntityBody {

	/**
	 * The bullet model.
	 */
	private BulletModel model;

	/**
	 * The sound fx when the bullet is launched.
	 */
	private static Sound shootingFX;
	
	/**
	 * Constructor of the class.
	 * @param world the game world.
	 * @param model the bullet model.
	 */
	public BulletBody(World world, BulletModel model){
		super(world, model, "Kinematic");
		createFixture(body, new float[]{4,5, 4,2, 11,2, 11,5}, 15, 7, 0, BULLET_BODY, (short) (SPACESHIP_BODY | ENEMY_BODY));
		this.model = model;
		if(!GameModel.TESTING)
			shootingFX = Gdx.audio.newSound(Gdx.files.internal("sounds/shooting-sound.mp3"));
		body.setTransform(model.getXCoord(), model.getYCoord(), 0);
	}
	
	/**
	 * Launches the body with a given velocity.
	 * @param velocity the velocity the body has when launched.
	 */
	public void launch(Vector2 velocity){
		if(!GameModel.TESTING)
			shootingFX.play(0.4f);
		body.setLinearVelocity(velocity);
	}

	public void update() {
		model.setYCoord(body.getPosition().y);
		model.setXCoord(body.getPosition().x);
	}
	
	
}
