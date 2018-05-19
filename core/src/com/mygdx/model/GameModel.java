package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.GalaxyWars;
import com.mygdx.model.entities.BulletModel;
import com.mygdx.model.entities.EnemyModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;

public class GameModel {
	
	private static GameModel instance;
	
	private SpaceShipModel spaceship;
	
	private List<EnemyModel> enemies = new ArrayList<EnemyModel>();
	
	private List<BulletModel> bullets = new ArrayList<BulletModel>();
	
	private static final int ENEMIES_COUNT = 6;
	
	private static final int BULLET_COUNT = 50;
	
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
		createBullets();
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
	
	/**
	 * Creates the bullets used by the spaceship model
	 */
	private void createBullets(){
		
		for(int i = 0; i < BULLET_COUNT; i++){
			bullets.add(new BulletModel());
		}
	}
	
	/**
	 * Creates the game enemies (the models)
	 */
	private void createEnemies(){
		
		enemies.add(new ZigZagModel());
		
		
		//criar outros
	}
	
	public SpaceShipModel getSpaceShipModel(){
		return spaceship;
	}
	
	public List<EnemyModel> getEnemies(){
		return enemies;
	}
	
	public List<BulletModel> getBullets(){
		return bullets;
	}
}
