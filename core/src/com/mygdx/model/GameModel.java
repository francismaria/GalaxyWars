package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.BulletModel;
import com.mygdx.model.entities.EnemyModel;
import com.mygdx.model.entities.EntityModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;

public class GameModel {
	
	/**
	 * Singleton instance for the class.
	 */
	private static GameModel instance;
	
	/**
	 * Duration of each level of the game. 
	 */
	private static int LEVEL_INTERVAL;
	
	/**
	 * Maximum interval of time between the creation of enemies
	 */
	public static int MAX_ENEMY_INTERVAL;
	
	/**
	 * The user (spaceship) model
	 */
	private SpaceShipModel spaceship;
	
	/**
	 * List of the current enemies flying through the air
	 */
	private List<EnemyModel> enemies = new ArrayList<EnemyModel>();
	
	/**
	 * Pool of enemies 
	 */
	Pool<ZigZagModel> zigzagPool = new Pool<ZigZagModel>(){
		@Override
		protected ZigZagModel newObject(){
			return new ZigZagModel();
		}
	};
	
	/**
	 * Pool of bullets
	 */
	Pool<BulletModel> bulletPool = new Pool<BulletModel>(){
		@Override
		protected BulletModel newObject(){
			return new BulletModel();
		}
	};
	
	/**
	 * List of the current bullets flying through the air
	 */
	private List<BulletModel> bullets = new ArrayList<BulletModel>();
	
	/**
	 * Game window in meters
	 */
	public static final float HEIGHT_LIMIT = GalaxyWars.HEIGHT / GalaxyWars.PIXEL_TO_METER;
	
	/**
	 * Game window in meters
	 */
	public static final float WIDTH_LIMIT = GalaxyWars.WIDTH / GalaxyWars.PIXEL_TO_METER;
	
	/**
	 * Class constructor
	 */
	private GameModel(){
		spaceship = new SpaceShipModel();
	}
	
	/**
	 * Singleton implementation of the class
	 * @return the singleton model
	 */
	public static GameModel getInstance(){
		if(instance == null){
			instance = new GameModel();
		}
		return instance;
	}
	
	/**
	 * Pool model function which gets/creates a bullet everytime the user fires 
	 * @param initialPos
	 */
	public BulletModel createBullet(Vector2 initialPos){
		
		BulletModel bullet = bulletPool.obtain();
		
		bullet.setInitialPosition(initialPos);
		bullet.setToNotRemove();
		bullets.add(bullet);
		
		return bullet;
	}
	
	public EnemyModel createEnemy(){
		EnemyModel enemy = null;
		int index = 1;//getRandomNumber(3); 
		Vector2 initialPos = new Vector2(WIDTH_LIMIT, getRandomNumber((int)HEIGHT_LIMIT));
		
		switch(index){
			case 1:
				enemy = zigzagPool.obtain();
				break;
			/*case 2:
				// enemy = new KamikazeModel();
				break;
			case 3:
				// enemy = new ShooterModel();
				break;*/
			default:
				//break;
				return null;
		}
		
		enemy.setInitialPos(initialPos);
		enemy.setToNotRemove();
		enemies.add(enemy);
		
		return enemy;
	}
	
	/**
	 * Removes a given entity object from its belonging pool.
	 * @param model representing the entity
	 */
	public void removeEntity(EntityModel model){
		
		if(model instanceof EnemyModel){
			//enemies.remove((EnemyModel)model);
			//enemiesPool.free()
			removeEnemy((EnemyModel)model);
		}
		else if(model instanceof BulletModel){
			/*bullets.remove((BulletModel)model);
			bulletPool.free((BulletModel)model);*/
			removeBullet((BulletModel)model);
		}
	}
	
	private void removeEnemy(EnemyModel enemy){
		enemies.remove(enemy);
		
		if(enemy instanceof ZigZagModel){
			zigzagPool.free((ZigZagModel)enemy);
		}
	}
	
	private void removeBullet(BulletModel bullet){
		bullets.remove(bullet);
		bulletPool.free(bullet);
	}
	
	
	/**
	 * Gets the spaceship model
	 * @return spaceship model
	 */
	public SpaceShipModel getSpaceShipModel(){
		return spaceship;
	}
	
	/**
	 * Gets the enemies models
	 * @return list of enemies models
	 */
	public List<EnemyModel> getEnemies(){
		return enemies;
	}
	
	/**
	 * Gets the current flying bullets models
	 * @return list of the current flying bullets models
	 */
	public List<BulletModel> getBullets(){
		return bullets;
	}
	
	public static int getRandomNumber(int maxLimit){
		Random rand = new Random();
		return rand.nextInt(maxLimit) + 1;
	}
}
