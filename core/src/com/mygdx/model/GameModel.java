package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.model.entities.EnemyModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;

public class GameModel {
	
	private static GameModel instance;
	
	private static final int ENEMIES_COUNT = 6;
	
	private SpaceShipModel spaceship;
	
	private List<EnemyModel> enemies = new ArrayList<EnemyModel>();
	
	private GameModel(){
		spaceship = new SpaceShipModel(); 
		createEnemies();
	}
	
	private void createEnemies(){
		
		enemies.add(new ZigZagModel());
		
		//criar outros
	}
	
	public static GameModel getInstance(){
		if(instance == null){
			instance = new GameModel();
		}
		return instance;
	}
	
	public SpaceShipModel getSpaceShipModel(){
		return spaceship;
	}
	
	public List<EnemyModel> getEnemies(){
		return enemies;
	}
	
	public void update(float delta){
		spaceship.updatePosition();
	}
	
}
