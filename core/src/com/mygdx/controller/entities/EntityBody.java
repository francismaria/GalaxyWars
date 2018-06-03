package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.BulletModel;
import com.mygdx.model.entities.EntityModel;

public abstract class EntityBody {

	/**
	 * The body itself.
	 */
	final Body body;
	
	/**
	 * Category bits for the spaceship body.
	 */
	protected static final short SPACESHIP_BODY = 0x0001;
	
	/**
	 * Category bits for the enemies bodies.
	 */
	protected static final short ENEMY_BODY = 0x0002;
	
	/**
	 * Category bits for the bullets bodies.
	 */
	protected static final short BULLET_BODY = 0x0002;
	
	/**
	 * Constructor of the class.
	 * @param world the game world.
	 * @param model the body model.
	 * @param bodyType the body type.
	 */
	public EntityBody(World world, EntityModel model, String bodyType){
		BodyDef bodyDef = new BodyDef();
		
		if(bodyType.equals("Dynamic"))
			bodyDef.type = BodyDef.BodyType.DynamicBody;
		else if(bodyType.equals("Kinematic"))
			bodyDef.type = BodyDef.BodyType.KinematicBody;
		else
			bodyDef.type = BodyDef.BodyType.StaticBody;
		
		bodyDef.position.set(model.getXCoord() / GalaxyWars.PIXEL_TO_METER, model.getYCoord()/ GalaxyWars.PIXEL_TO_METER);
		
		body = world.createBody(bodyDef);
		body.setUserData(model);
	}
	
	/**
	 * Creates a fixture. 
	 * Credits to arestivo.
	 * @param body
	 * @param vertices
	 * @param width
	 * @param height
	 * @param restitution
	 * @param category
	 * @param mask
	 */
	public final void createFixture(Body body, float[] vertices, int width,  int height, float restitution, short category, short mask){
		PolygonShape shape = new PolygonShape();
		
		for(int i = 0; i < vertices.length; i++){
			vertices[i] /= GalaxyWars.PIXEL_TO_METER;
		}
		
        shape.set(vertices);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1;
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.maskBits = mask;
		fixtureDef.restitution = restitution;
		
		Fixture fixture = body.createFixture(fixtureDef);
		shape.dispose();
	}
	
	/**
	 * Returns the body itself.
	 * @return the body.
	 */
	public Body getBody(){
		return body;
	}
}
