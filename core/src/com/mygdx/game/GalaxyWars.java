package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	private Difficulty difficulty;
	
	public static final int WIDTH = 720;
	public static final int HEIGHT = 480;
	
	@Override 
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		running = false;
		paused = false;
		loadAssets();
		setScreen(new MenuScreen(this));
	}
	
	public void startGame(Difficulty difficulty){
		this.difficulty = difficulty;
		setScreen(new GameView(this));
	}
	
	private void loadAssets(){
		assetManager.load("play-button.png", Texture.class);
		assetManager.load("space-background.png", Texture.class);
		assetManager.load("space-ship.png", Texture.class);
		
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
	
	public boolean isRunning(){
		return running;
	}
	
	public boolean isPaused(){
		return paused;
	}
	
	public void setPaused(){
		this.paused = true;
		this.running = false;
	}
	
	public void setRunning(){
		this.running = true;
		this.paused = false;
	}
}
