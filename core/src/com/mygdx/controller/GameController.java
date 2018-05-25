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
import com.mygdx.model.entities.ExplosionModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;

public class GameController implements ContactListener{
	
	/**
	 * Singleton instance for the class
	 */
	private static GameController instance;
	
	/**
	 * The world of the game
	 */
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
	/*private List<BulletBody> bulletsBodies = new ArrayList<BulletBody>();*/
	
	/**
	 * Represents all of the bodies that upon collision have to be removed in the next step.
	 */
	private List<Body> removeBodies = new ArrayList<Body>();
	
	/**
	 * List of all explosions occurring at an exact time
	 */
	private List<ExplosionModel> explosions = new ArrayList<ExplosionModel>();
	
	/**
	 * Constructor of the class.
	 * Initializes the world with -9.8N of gravity.
	 * It also creates all the bodies present in the game.
	 */
	private GameController(){
		
		world = new World(new Vector2(0, -1f), true);
		
		spaceshipBody = new SpaceShipBody(world, GameModel.getInstance().getSpaceShipModel());
		//createBulletsBodies();
		createEnemiesBodies();
		
		world.setContactListener(this);
	}
	/*
	/**
	 * Creates the bodies of the bullets given its models
	 */
	/*private void createBulletsBodies(){
		
		List<BulletModel> bulletsModels = GameModel.getInstance().getBullets();
	
		for(BulletModel model : bulletsModels){
			bulletsBodies.add(new BulletBody(world, model));
		}
	}*/
	
	/**
	 * Creates the bodies of the enemies given its models
	 */
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
			removeBodies.add(bodyB);
			removeBodies.add(bodyA);
			//explosions.add(new ExplosionModel(posX, posY));
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
	
	/**
	 * @brief Returns the world of the game
	 * @return world
	 */
	public World getWorld(){
		return world;
	}
	
	/**
	 * @brief Updates world and its bodies
	 * @param delta
	 */
	public void update(float delta){
		
		world.step(1f/60f, 6, 2);
		
		checkLimitPositions(spaceshipBody);
		spaceshipBody.update();
		
		for(EnemyBody enemy : enemiesBodies){
			checkLimitPositions(enemy);
			enemy.update();
		} 
		/*
		for(BulletBody bullet : bulletsBodies){
			checkLimitPositions(bullet);
			bullet.update();
		}*/
		
		removeBodies();	
	}
	
	/**
	 * Redirects the given body to its specific treatment
	 * @param body 
	 */
	private void checkLimitPositions(EntityBody body){
		
		float x = body.getBody().getPosition().x;
		float y = body.getBody().getPosition().y;
		
		if(body instanceof SpaceShipBody){
			checkSpaceshipLimits((SpaceShipBody)body, x, y);
		}
		else if(body instanceof EnemyBody){
			checkEnemyLimits((EnemyBody)body, x, y);
		}
		else if(body instanceof BulletBody){
			//checkBulletLimits((BulletBody)body, x, y);
		}
	}
	
	/**
	 * Checks if the given enemy body is inside the game window. If not restores its position
	 * @param body
	 * @param x width position of the body in the window
	 * @param y height position of the body in the window
	 */
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
	
	/**
	 * Checks if the bullet body is inside the game window. If not removes it from the game.
	 * @param body the bullet body
	 * @param x width position of the body in the window
	 * @param y height position of the body in the window
	 */
	/*private void checkBulletLimits(BulletBody body, float x, float y){
		System.out.println("BULLET LIMIT + " + x);
		if(x > GameModel.WIDTH_LIMIT){
			System.out.println("REMOVED");
			removeBodies.add(body.getBody());
		}
	}*/
	
	/**
	 * Checks if the spaceship body is inside the game window. If not restores its position
	 * @param body
	 * @param x width position of the body in the window
	 * @param y height position of the body in the window
	 */
	private void checkSpaceshipLimits(SpaceShipBody body, float x, float y){
		
		Vector2 restorePos = new Vector2();
		
		if(y < 0){
			restorePos.x = x; restorePos.y = 0+0.01f;
			body.getBody().setTransform(restorePos, 0);
			body.getBody().applyLinearImpulse(new Vector2(0, 0.1f), body.getBody().getWorldCenter(), true);
		}
		else if(y > 4){
			restorePos.x = x; restorePos.y = 4-0.01f;
			body.getBody().setTransform(restorePos, 0);
			body.getBody().applyLinearImpulse(new Vector2(0, -0.05f), body.getBody().getWorldCenter(), true);
		} 
	}
	
	/**
	 * Calls the _jump function of the spaceship body
	 */
	public void jumpSpaceShip(){
		spaceshipBody.jump();
	}
	
	/**
	 * This function fires a bullet through the space ship. 
	 * Gets the bullet model from the GameModel pool of BulletModel objects
	 */
	public void shootSpaceShipBullet(){
		
		BulletModel model = GameModel.getInstance().createBullet(new Vector2
				(spaceshipBody.getBody().getPosition().x+1,
				spaceshipBody.getBody().getPosition().y+0.5f));
		
		BulletBody bullet = new BulletBody(world, model);
		bullet.launch();
		
	}
	
	/**
	 * Removes the bodies that are waiting to be destroyed.
	 */
	public void removeBodies(){
		
		for(Body body : removeBodies){
			world.destroyBody(body);
		}
		removeBodies.clear();
	}
	
	/**
	 * Gets explosions
	 * @return list of models of the explosions
	 */
	public List<ExplosionModel> getExplosions(){
		return explosions;
	}
	
	/**
	 * Clears out explosions
	 */
	public void removeExplosions(){
		explosions.clear();
	}
}
