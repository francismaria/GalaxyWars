package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.model.GameModel;
import com.mygdx.view.GameView;
import com.mygdx.view.MenuScreen;

public class GalaxyWars extends Game {
	
	public enum Difficulty{
		EASY, MEDIUM, HARD
	}
	
	private SpriteBatch batch;
	private AssetManager assetManager;
	
	private boolean running;
	private boolean paused;
	
	private static boolean over;
	
	public static Difficulty difficulty;
	
	public static final int WIDTH = 850;
	public static final int HEIGHT = 500;
	public static final float PIXEL_TO_METER = 100;
	
	@Override 
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		running = false;
		paused = false;
		over = false;
		loadAssets();
		drawMenu();
	}
	
	public void drawMenu(){
		setScreen(new MenuScreen(this));
	}
	
	public void startGame(Difficulty difficulty){
		this.difficulty = difficulty;
		setRunning();
		setScreen(new GameView(this));
	}
	
	public void exitGame(){
		Gdx.app.exit();
	}
	
	private void loadAssets(){
		assetManager.load("play-button.png", Texture.class);
		assetManager.load("play-button-hover.png", Texture.class);
		assetManager.load("quit-button.png", Texture.class);
		assetManager.load("quit-button-hover.png", Texture.class);
		assetManager.load("easy-button.png", Texture.class);
		assetManager.load("medium-button.png", Texture.class);
		assetManager.load("hard-button.png", Texture.class);
		assetManager.load("easy-button-hover.png", Texture.class);
		assetManager.load("medium-button-hover.png", Texture.class);
		assetManager.load("hard-button-hover.png", Texture.class);
		assetManager.load("space-background.png", Texture.class);
		assetManager.load("space-ship.png", Texture.class);
		assetManager.load("title.png", Texture.class);
		assetManager.load("menu-title.png", Texture.class);
		assetManager.load("difficulty-title.png", Texture.class);
		assetManager.load("explosion-steps.png", Texture.class);
		assetManager.load("bullet.png", Texture.class);
		assetManager.load("zigzag.png", Texture.class);
		assetManager.load("shooter.png", Texture.class);
		assetManager.load("kamikaze.png", Texture.class);
		
		//assetManager.load("resume-button.png", Texture.class);
		assetManager.load("quit-game-button.png", Texture.class);
		
		assetManager.finishLoading();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}
	
	public AssetManager getAssetManager(){
		return assetManager;
	}
	
	public SpriteBatch getSpriteBatch(){
		return batch;
	}
	
	public Difficulty getDifficulty(){
		return difficulty;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public boolean isPaused(){
		return paused;
	}
	
	public static boolean isOver(){
		return over;
	}
	
	public void setPaused(){
		this.paused = true;
		this.running = false;
	}
	
	public static void setGameOver(){
		over = true;
	}
	
	public void setRunning(){
		this.running = true;
		this.paused = false;
													//Interval of the enemies created in ms
		if(difficulty.equals(Difficulty.EASY)){
			GameModel.MAX_ENEMY_INTERVAL = 10000;
		}
		else if(difficulty.equals(Difficulty.MEDIUM)){
			GameModel.MAX_ENEMY_INTERVAL = 7000;
		}
		else if(difficulty.equals(Difficulty.HARD)){
			GameModel.MAX_ENEMY_INTERVAL = 3000;
		}
	}
}
