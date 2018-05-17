package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

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
	
	private GameModel(){
		spaceship = new SpaceShipModel();
		createBullets();
		createEnemies();
	}
	
	public static GameModel getInstance(){
		if(instance == null){
			instance = new GameModel();
		}
		return instance;
	}
	
	private void createBullets(){
		
		for(int i = 0; i < BULLET_COUNT; i++){
			bullets.add(new BulletModel());
		}
	}
	
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
