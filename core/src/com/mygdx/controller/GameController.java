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
import com.badlogic.gdx.utils.Array;
import com.mygdx.controller.entities.BulletBody;
import com.mygdx.controller.entities.EnemyBody;
import com.mygdx.controller.entities.KamikazeBody;
import com.mygdx.controller.entities.ShooterBody;
import com.mygdx.controller.entities.SpaceShipBody;
import com.mygdx.controller.entities.ZigZagBody;
import com.mygdx.model.GameModel;
import com.mygdx.model.entities.BulletModel;
import com.mygdx.model.entities.EnemyModel;
import com.mygdx.model.entities.EntityModel;
import com.mygdx.model.entities.ExplosionModel;
import com.mygdx.model.entities.KamikazeModel;
import com.mygdx.model.entities.ShooterModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;

public class GameController implements ContactListener{
	
	/**
	 * Singleton instance for the class.
	 */
	private static GameController instance;
	
	/**
	 * The world of the game.
	 */
	private final World world;
	
	/**
	 * The body representing the spaceship (user).
	 */
	private final SpaceShipBody spaceshipBody;
	
	/**
	 * The list of active enemies bodies.
	 */
	private List<EnemyBody> enemiesBodies = new ArrayList<EnemyBody>();
	
	/**
	 * List of all explosions occurring at an exact time.
	 */
	private List<ExplosionModel> explosions = new ArrayList<ExplosionModel>();
	
	/**
	 * The time of the next enemy body creation.
	 */
	private float nextEnemyCreation;
	
	/**
	 * Time passed since the last enemy creation (diff time).
	 */
	private float timePassedEnemyCreation;
	
	/**
	 * Constructor of the class.
	 * Initializes the world with gravity.
	 * It also creates all the bodies present in the game.
	 */
	private GameController(){
		
		world = new World(new Vector2(0, -0.2f), true);
		
		spaceshipBody = new SpaceShipBody(world, GameModel.getInstance().getSpaceShipModel());
		timePassedEnemyCreation = 0;
		nextEnemyCreation = GameModel.getRandomNumberInt(GameModel.MAX_ENEMY_INTERVAL);

		world.setContactListener(this);
	}
	
	/**
	 * Singleton implementation of the class.
	 * @return the singleton model.
	 */
	public static GameController getInstance(){
		if(instance == null)
			instance = new GameController();
		return instance;
	}
	
	/**
	 * Returns the world of the game.
	 * @return world
	 */
	public World getWorld(){
		return world;
	}
	
	/**
	 * Updates world and its bodies.
	 * @param delta
	 */
	public void update(float delta){
		
		world.step(1f/60f, 6, 2);
		
		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		
		checkBodiesPositionWindow(bodies);
		
		updateBodies(bodies, delta);
		createRandomEnemy(delta);

		removeBodies();	
	}
	 
	@Override
	public void beginContact(Contact contact) {
		
		Body bodyA = contact.getFixtureA().getBody();
		Body bodyB = contact.getFixtureB().getBody();
		
		if(bodyA.getUserData() instanceof SpaceShipModel && bodyB.getUserData() instanceof EnemyModel){
			// GAME OVER!!!
		}
		else if(bodyA.getUserData() instanceof EnemyModel && bodyB.getUserData() instanceof BulletModel){
			enemyBulletCollision((EnemyModel)bodyA.getUserData(),(BulletModel)bodyB.getUserData());
		}
		else if(bodyA.getUserData() instanceof BulletModel && bodyB.getUserData() instanceof EnemyModel){		
			enemyBulletCollision((EnemyModel)bodyB.getUserData(), (BulletModel)bodyA.getUserData());
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
	 * Flags both the enemy and the bullet to be removed and creates a new explosion model.
	 * @param enemy enemy to be removed.
	 * @param bullet bullet to be removed.
	 */
	private void enemyBulletCollision(EnemyModel enemy, BulletModel bullet){
		enemy.setToRemove();
		bullet.setToRemove();
		explosions.add(new ExplosionModel(enemy.getXCoord(), enemy.getYCoord()));
	}
	
	private void updateBodies(Array<Body> bodies, float delta){

		for(EnemyBody body : enemiesBodies){
			if(body instanceof ZigZagBody)
				((ZigZagBody)body).update(delta);
			else if(body instanceof ShooterBody){
				ShooterBody shooter = (ShooterBody)body;
				if(shooter.update(delta, spaceshipBody.getModel())){
					shootEnemyBullet(spaceshipBody.getModel(), shooter.getModel());
					shooter.getModel().resetShooting();
				}
			}
		}
	}
	
	private void shootEnemyBullet(SpaceShipModel spaceship, ShooterModel enemy){
		
		if(enemy.isToRemove()) return;
		
		BulletModel model = GameModel.getInstance().createBullet(new Vector2
				(enemy.getXCoord()-0.18f, enemy.getYCoord()+0.2f));
		
		BulletBody bullet = new BulletBody(world, model);
		bullet.launch(enemy.getForce(spaceship));
	}
	
	/**
	 * Checks if the interval between the creation of the enemies is passed.
	 * If true it generates a new enemy whose type is generated randomly. 
	 * @param delta
	 */
	private void createRandomEnemy(float delta){
		
		timePassedEnemyCreation += delta*1000;  //in ms
		
		if(timePassedEnemyCreation >= nextEnemyCreation){
			createEnemyBody();
			timePassedEnemyCreation = 0;
			nextEnemyCreation = GameModel.getRandomNumberInt(GameModel.MAX_ENEMY_INTERVAL);
		}
	}
	
	/**
	 * Creates the body of the enemy given the model that was randomly generated.
	 */
	private void createEnemyBody(){
		EnemyBody body = null;
		EnemyModel model = GameModel.getInstance().createEnemy();
		
		if(model instanceof ZigZagModel){
			body = new ZigZagBody(world, (ZigZagModel)model);
		}
		else if(model instanceof KamikazeModel){
			body = new KamikazeBody(world, (KamikazeModel)model);
		}
		else if(model instanceof ShooterModel){
			body = new ShooterBody(world, (ShooterModel)model);
		}
		enemiesBodies.add(body);
	}
	
	/**
	 * Goes through all the bodies in the world and checks if they are inside the game window.
	 * If not flags them to be removed.
	 * @param bodies all of the bodies in the world
	 */
	private void checkBodiesPositionWindow(Array<Body> bodies){
		
		for(Body body : bodies){
			EntityModel model = (EntityModel)body.getUserData();
			
			if(model instanceof SpaceShipModel){
				checkSpaceshipPosition(body, (SpaceShipModel) model);
			}
			else if(model instanceof BulletModel){
				checkBulletPosition(body, (BulletModel)model);
			}
			else if(model instanceof EnemyModel){
				checkEnemyPosition(body, (EnemyModel)model);
			}
			
			model.setXCoord(body.getPosition().x);
			model.setYCoord(body.getPosition().y);
		}
	}
	
	/**
	 * Checks if the enemy is inside the game window.
	 * @param body the enemy body
	 * @param model the enemy model
	 */
	private void checkEnemyPosition(Body body, EnemyModel model){
		
		if(body.getPosition().x < 0 || body.getPosition().y < 0 || body.getPosition().y > GameModel.HEIGHT_LIMIT)
			((EnemyModel)body.getUserData()).setToRemove();
	}
	
	/**
	 * Checks if the bullet is inside the game window.
	 * @param body the bullet body
	 * @param model the bullet model
	 */
	private void checkBulletPosition(Body body, BulletModel model){
		
		if(body.getPosition().x > GameModel.WIDTH_LIMIT){
			((BulletModel)body.getUserData()).setToRemove();
		}
	}
	
	/**
	 * Checks if spaceship is inside the game window.
	 * If not it restores its position to the nearest position inside the game window.
	 * @param body the spaceship body.
	 * @param model the spaceship model.
	 */
	private void checkSpaceshipPosition(Body body, SpaceShipModel model){
		
		Vector2 restorePos = new Vector2();
		float xPos = body.getPosition().x, yPos = body.getPosition().y;
		
		if(yPos > 4.2f){
			restorePos.x = xPos; restorePos.y = 4.20f-0.01f;
			body.setTransform(restorePos, 0);
			body.applyLinearImpulse(new Vector2(0, -0.05f), body.getWorldCenter(), true);
		}
		else if(yPos < 0){
			restorePos.x = xPos; restorePos.y = 0+0.01f;
			body.setTransform(restorePos, 0);
			body.applyLinearImpulse(new Vector2(0, 0.1f), body.getWorldCenter(), true);
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
		bullet.launch(new Vector2(4f, 0));
	}
	
	/**
	 * Removes the bodies that are waiting to be destroyed.
	 */
	public void removeBodies(){
		
		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		
		for(Body body : bodies){
			if(((EntityModel)body.getUserData()).isToRemove()){ 
				GameModel.getInstance().removeEntity((EntityModel) body.getUserData());
				world.destroyBody(body);
			}
		}		
	}
	
	/**
	 * Gets explosions.
	 * @return list of models of the explosions
	 */
	public List<ExplosionModel> getExplosions(){
		return explosions;
	}
	
	/**
	 * Clears out explosions.
	 */
	public void clearExplosions(){
		explosions.clear();
	}
	
	/**
	 * Returns all of the enemies models present in the game.
	 * @return list of all active enemies models.
	 */
	public List<EnemyModel> getEnemies(){
		
		List<EnemyModel> models = new ArrayList<EnemyModel>();
		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		
		for(Body body : bodies){
			EntityModel model = (EntityModel)body.getUserData();
			if(model instanceof EnemyModel){
				models.add((EnemyModel)model);
			}
		}
		return models;
	}

	/**
	 * Returns all of the bullets models present in the game.
	 * @return list of all active bullets models.
	 */
	public List<BulletModel> getBullets() {
		
		List<BulletModel> models = new ArrayList<BulletModel>();
		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);
		
		for(Body body : bodies){
			EntityModel model = (EntityModel)body.getUserData();
			if(model instanceof BulletModel){
				models.add((BulletModel)model);
			}
		}
		return models;
	}
	
}
