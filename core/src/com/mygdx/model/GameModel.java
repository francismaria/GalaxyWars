package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.GalaxyWars;
import com.mygdx.game.GalaxyWars.Difficulty;
import com.mygdx.model.entities.BulletModel;
import com.mygdx.model.entities.EnemyModel;
import com.mygdx.model.entities.EntityModel;
import com.mygdx.model.entities.KamikazeModel;
import com.mygdx.model.entities.ShooterModel;
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
	 * The game difficulty.
	 */
	public static Difficulty difficulty;
	
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
	 * Pool of "zig-zag" enemies 
	 */
	Pool<ZigZagModel> zigzagPool = new Pool<ZigZagModel>(){
		@Override
		protected ZigZagModel newObject(){
			return new ZigZagModel();
		}
	};
	
	/**
	 * Pool of "kamikaze" enemies
	 */
	Pool<KamikazeModel> kamikazePool = new Pool<KamikazeModel>(){
		@Override
		protected KamikazeModel newObject(){
			return new KamikazeModel();
		}
	};
	
	/**
	 * Pool of "shooter" enemies
	 */
	Pool<ShooterModel> shooterPool = new Pool<ShooterModel>(){
		@Override
		protected ShooterModel newObject(){
			return new ShooterModel();
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
	 * Controls the testing option. Necessary for libgdx dependencies mock-ups.
	 */
	public static boolean TESTING = false;
	
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
		Vector2 initialPos = new Vector2(WIDTH_LIMIT, getRandomNumberFloat(HEIGHT_LIMIT));
		
		switch(getRandomNumberInt(3)){
			case 1:
				System.out.println("ZIGZAG");
				enemy = zigzagPool.obtain();
				break;
			case 2:
				System.out.println("KAMIKAZE");
				enemy = kamikazePool.obtain();
				break;
			case 3:
				System.out.println("SHOOTER");
				enemy = shooterPool.obtain();  
				break;
			default:
				return null;
		}
		
		enemy.setInitialPos(initialPos);
		enemy.setToNotRemove();
		enemies.add(enemy);
		
		return enemy;
	}
	
	/**
	 * Removes a given entity object from its belonging pool.
	 * @param model representing the entity.
	 */
	public void removeEntity(EntityModel model){
		
		if(model instanceof EnemyModel){
			removeEnemy((EnemyModel)model);
		}
		else if(model instanceof BulletModel){
			removeBullet((BulletModel)model);
		}
	}
	
	/**
	 * Removes a given enemy from its belonging pool.
	 * @param enemy the enemy to be removed.
	 */
	private void removeEnemy(EnemyModel enemy){
		enemies.remove(enemy);
		
		if(enemy instanceof ZigZagModel){
			zigzagPool.free((ZigZagModel)enemy);
		}
		else if(enemy instanceof KamikazeModel){
			kamikazePool.free((KamikazeModel)enemy);
		}
		else if(enemy instanceof ShooterModel){
			shooterPool.free((ShooterModel)enemy);
		}
	}
	
	/**
	 * Removes a given bullet from its belonging pool.
	 * @param bullet the bullet to be removed.
	 */
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
	
	/**
	 * Sets the maximum enemy creation interval depending on its difficulty.
	 * @param difficulty the game difficulty.
	 */
	public static void setMaxEnemyInterval(Difficulty gameDifficulty){
		difficulty = gameDifficulty;
		if(difficulty.equals(Difficulty.EASY)){
			MAX_ENEMY_INTERVAL = 10000;
		}
		else if(difficulty.equals(Difficulty.MEDIUM)){
			MAX_ENEMY_INTERVAL = 7000;
		}
		else if(difficulty.equals(Difficulty.HARD)){
			MAX_ENEMY_INTERVAL = 3000;
		}
	}
	
	/**
	 * Util function to get a random integer from 1 to the maxLimit parameter.
	 * @param maxLimit max number.
	 * @return random integer between 1 and maxLimit.
	 */
	public static int getRandomNumberInt(int maxLimit){
		Random rand = new Random();
		return rand.nextInt(maxLimit) + 1;
	}
	
	/**
	 * Util function to get a random float from 1 to the maxLimit parameter.
	 * @param maxLimit max number.
	 * @return random float between 1 and maxLimit.
	 */
	public static float getRandomNumberFloat(float maxLimit){
		Random rand = new Random();
		return 1 + rand.nextFloat() * (maxLimit - 1);
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY. Necessary due to libgdx constraints on dependencies.
	 */
	public static void setTestingOption(){
		TESTING = true;
	}
	
	public void removeAll(){
		enemies.clear();
		bullets.clear();
	}
}
