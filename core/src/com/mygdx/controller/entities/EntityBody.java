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
	
	public EntityBody(World world, EntityModel model){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(model.getXCoord() / GalaxyWars.PIXEL_TO_METER, model.getYCoord()/ GalaxyWars.PIXEL_TO_METER);
		
		body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 / GalaxyWars.PIXEL_TO_METER, 50 / GalaxyWars.PIXEL_TO_METER);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1;
		
		Fixture fixture = body.createFixture(fixtureDef);
		
		shape.dispose();
	}
	
	
	//public final void createFixture(Body body, float[] vertexes,)
}
