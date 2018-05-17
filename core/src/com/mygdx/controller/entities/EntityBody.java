package com.mygdx.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.EntityModel;

public abstract class EntityBody {

	final Body body;
	
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
	
	public final void createFixture(Body body, float[] vertices, int width,  int height, float restitution){
		PolygonShape shape = new PolygonShape();
		
		for(int i = 0; i < vertices.length; i++){
			vertices[i] /= GalaxyWars.PIXEL_TO_METER;
		}
		
        shape.set(vertices);
		//shape.setAsBox(50 / GalaxyWars.PIXEL_TO_METER, 50 / GalaxyWars.PIXEL_TO_METER);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1;
		fixtureDef.restitution = restitution;
		
		Fixture fixture = body.createFixture(fixtureDef);
		shape.dispose();
	}
	
	public final void createBoxFixture(Body body, int width, int height){
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / GalaxyWars.PIXEL_TO_METER, height / GalaxyWars.PIXEL_TO_METER);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1;
		
		Fixture fixture = body.createFixture(fixtureDef);
		shape.dispose();
	}
	
	public Body getBody(){
		return body;
	}
	
	public abstract void update();
}
