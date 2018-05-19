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
import com.mygdx.controller.entities.BulletBody;
import com.mygdx.controller.entities.EnemyBody;
import com.mygdx.controller.entities.EntityBody;
import com.mygdx.controller.entities.SpaceShipBody;
import com.mygdx.controller.entities.ZigZagBody;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.GameModel;
import com.mygdx.model.entities.BulletModel;
import com.mygdx.model.entities.EnemyModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;

public class GameController implements ContactListener{
	
	private static GameController instance;
	
	private final World world;
	
	/**
	 * The body representing the spaceship (user)
	 */
	private final SpaceShipBody spaceshipBody;
	
	/**
	 * All of the bodies representing the enemies
	 */
	private List<EnemyBody> enemiesBodies = new ArrayList<EnemyBody>();
	
	/**
	 * All of the bodies representing the bullets
	 */
	private List<BulletBody> bulletsBodies = new ArrayList<BulletBody>();
	
	/**
	 * Represents all of the bodies that upon collision have to be removed in the next step.
	 */
	private List<Body> removeBodies = new ArrayList<Body>();
	
	/**
	 * Constructor of the class.
	 * Initializes the world with -9.8N of gravity.
	 * It also creates all the bodies present in the game.
	 */
	private GameController(){
		world = new World(new Vector2(0, -1f), true);
		
		spaceshipBody = new SpaceShipBody(world, GameModel.getInstance().getSpaceShipModel());
		createBulletsBodies();
		createEnemiesBodies();
		
		world.setContactListener(this);
	}
	
	private void createBulletsBodies(){
		
		List<BulletModel> bulletsModels = GameModel.getInstance().getBullets();
	
		for(BulletModel model : bulletsModels){
			bulletsBodies.add(new BulletBody(world, model));
		}
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
		
		if(bodyA.getUserData() instanceof SpaceShipModel &&
				bodyB.getUserData() instanceof EnemyModel){
			System.out.println("Enemies collision");
		}
		else if(bodyA.getUserData() instanceof BulletModel &&
				bodyB.getUserData() instanceof EnemyModel){
			removeBodies.add(bodyB); removeBodies.add(bodyA);
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
		
		checkLimitPositions(spaceshipBody);
		spaceshipBody.update();
		
		for(EnemyBody enemy : enemiesBodies){
			checkLimitPositions(enemy);
			enemy.update();
		}
		
		removeBodies();	
	}
	
	private void checkLimitPositions(EntityBody body){
		
		float x = body.getBody().getPosition().x;
		float y = body.getBody().getPosition().y;
		
		if(body instanceof SpaceShipBody){
			checkSpaceshipLimits((SpaceShipBody)body, x, y);
		}
		else if(body instanceof EnemyBody){
			checkEnemyLimits((EnemyBody)body, x, y);
		}
	}
	
	private void checkEnemyLimits(EnemyBody body, float x, float y){
		
		Vector2 restorePos = new Vector2();
		float limitWidth = GameModel.WIDTH_LIMIT, limitHeight = GameModel.HEIGHT_LIMIT;
		
		if(x < 0){
			restorePos.x = limitWidth; restorePos.y = y;
			body.getBody().setTransform(restorePos, 0);
		}
		else if(x > limitWidth){
			restorePos.x = 0; restorePos.y = y;
			body.getBody().setTransform(restorePos, 0);
		}
		else if(y < 0){
			restorePos.x = x; restorePos.y = limitHeight;
			body.getBody().setTransform(restorePos, 0);
		}
		else if(y > limitHeight){
			restorePos.x = x; restorePos.y = 0;
			body.getBody().setTransform(restorePos, 0);
		}
	}
	
	private void checkSpaceshipLimits(SpaceShipBody body, float x, float y){
		
		Vector2 restorePos = new Vector2();
		
		if(y < 0){
			restorePos.x = x; restorePos.y = 0.2f;
			body.getBody().setTransform(restorePos, 0);
		}
		else if(y > 4){
			restorePos.x = x; restorePos.y = 4-0.01f;
			body.getBody().setTransform(restorePos, 0);
			body.getBody().applyLinearImpulse(new Vector2(0, -0.05f), body.getBody().getWorldCenter(), true);
		} 
	}
	
	public void jumpSpaceShip(){
		spaceshipBody.jump();
	}
	
	public void shootSpaceShipBullet(){
		
		int i = 0;
		List<BulletModel> bulletsModels = GameModel.getInstance().getBullets();
		
		for(BulletModel model : bulletsModels){
			if(!model.wasFired()){
				bulletsBodies.get(i).launch(new Vector2(spaceshipBody.getBody().getPosition().x+1,
						spaceshipBody.getBody().getPosition().y+0.5f));
				model.setFired();
				break;
			}
			i++;
		}
	}
	
	public void removeBodies(){
		
		for(Body body : removeBodies){
			world.destroyBody(body);
		}
		removeBodies.clear();
	}
}
