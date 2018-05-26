package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

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
	 * Singleton instance for the class
	 */
	private static GameModel instance;
	
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
	Pool<EnemyModel> enemyPool = new Pool<EnemyModel>(){
		@Override
		protected EnemyModel newObject(){
			//switch()
			return null;
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
		createEnemies();
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
	
	/*public EnemyModel createEnemy(){
		
	}*/
	
	
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
		
		Vector2 initialPos = new Vector2();
		EnemyModel enemy = enemyPool.obtain();
		
		//enemy.setInitialPosition(initialPos);
		return null;
	}
	
	/**
	 * Creates the game enemies (the models)
	 */
	private void createEnemies(){
		
		enemies.add(new ZigZagModel());
		
		
		//criar outros
	}
	
	/**
	 * Removes a given entity object from its belonging pool.
	 * @param model representing the entity
	 */
	public void removeEntity(EntityModel model){
		
		if(model instanceof EnemyModel){
			enemies.remove((EnemyModel)model);
		}
		else if(model instanceof BulletModel){
			bullets.remove((BulletModel)model);
			bulletPool.free((BulletModel)model);
		}
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
}
