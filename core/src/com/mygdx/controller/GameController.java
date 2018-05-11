package com.mygdx.controller;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.controller.entities.EnemyBody;
import com.mygdx.controller.entities.SpaceShipBody;
import com.mygdx.controller.entities.ZigZagBody;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.GameModel;
import com.mygdx.model.entities.EnemyModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;

public class GameController implements ContactListener{
	
	private static GameController instance;
	
	private final World world;
	
	private final SpaceShipBody spaceshipBody;
	private List<EnemyBody> enemiesBodies = new ArrayList<EnemyBody>();
	
	private GameController(){
		world = new World(new Vector2(0, -1), true);
		
		spaceshipBody = new SpaceShipBody(world, GameModel.getInstance().getSpaceShipModel());
		createEnemiesBodies();
		
		world.setContactListener(this);
	}
	
	private void createEnemiesBodies(){
		
		List<EnemyModel> enemiesModels = GameModel.getInstance().getEnemies();
		
		for(EnemyModel model : enemiesModels){
			if(model.getType() == EnemyModel.EnemyType.ZIGZAG){
				enemiesBodies.add(new ZigZagBody(world, (ZigZagModel)model));
			}
			else if(model.getType() == EnemyModel.EnemyType.KAMIKAZE){
				
			}
			else if(model.getType() == EnemyModel.EnemyType.SHOOTER){
				
			}
		}
	}
	
	public static GameController getInstance(){
		if(instance == null)
			instance = new GameController();
		return instance;
	}

	@Override
	public void beginContact(Contact contact) {
		
		Body bodyA = contact.getFixtureA().getBody();
		Body bodyB = contact.getFixtureB().getBody();
		
		if(bodyA.getUserData() instanceof SpaceShipModel && bodyB.getUserData() instanceof EnemyModel){
			System.out.println("Enemies collision");
			//take life from spaceship
		}
		
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
		
		for(EnemyBody enemy : enemiesBodies){
			checkLimitPositions(enemy);
			enemy.update();
		}
	}
	
	private void checkLimitPositions(EnemyBody enemy){
		
		float x = enemy.getBody().getPosition().x;
		float y = enemy.getBody().getPosition().y;
		Vector2 restorePos = new Vector2();
		
		if(x < 0){
			restorePos.x = GalaxyWars.WIDTH; restorePos.y = y;
			enemy.getBody().setTransform(restorePos, 0);
		}
		else if(x > GalaxyWars.WIDTH){
			restorePos.x = 0; restorePos.y = y;
			enemy.getBody().setTransform(restorePos, 0);
		}
		else if(y < 0){
			restorePos.x = x; restorePos.y = GalaxyWars.HEIGHT;
			enemy.getBody().setTransform(restorePos, 0);
		}
		else if(y > GalaxyWars.HEIGHT){
			restorePos.x = x; restorePos.y = 0;
			enemy.getBody().setTransform(restorePos, 0);
		}
	}
	
	public void jumpSpaceShip(){
		spaceshipBody.jump();
	}
}
