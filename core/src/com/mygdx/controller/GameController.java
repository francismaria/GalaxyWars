package com.mygdx.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.controller.entities.SpaceShipBody;
import com.mygdx.model.GameModel;

public class GameController implements ContactListener{
	
	private static GameController instance;
	
	private final World world;
	
	private final SpaceShipBody spaceshipBody;
	
	private GameController(){
		world = new World(new Vector2(0, -9.8f), true);
		
		spaceshipBody = new SpaceShipBody(world, GameModel.getInstance().getSpaceShipModel());
		
		world.setContactListener(this);
	}
	
	public static GameController getInstance(){
		if(instance == null)
			instance = new GameController();
		return instance;
	}

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
	}
	
	public World getWorld(){
		return world;
	}
	
	public void update(float delta){
		world.step(1f/60f, 6, 2);
		spaceshipBody.update();
		//GameModel.getInstance().update(delta);
	}
	
	public void jumpSpaceShip(){
		spaceshipBody.jump();
	}
}
