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

	final Body body;
	
	protected static final short SPACESHIP_BODY = 0x0001;
	
	protected static final short ENEMY_BODY = 0x0002;
	
	protected static final short BULLET_BODY = 0x0002;
	
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
	
	public Body getBody(){
		return body;
	}
}
