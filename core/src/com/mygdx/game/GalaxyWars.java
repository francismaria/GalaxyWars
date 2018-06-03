package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.model.GameModel;
import com.mygdx.utils.Scoreboard;
import com.mygdx.utils.Utils;
import com.mygdx.view.FinishedGameScreen;
import com.mygdx.view.GameView;
import com.mygdx.view.MenuScreen;

public class GalaxyWars extends Game {
	
	/**
	 * The three possible game difficulties.
	 * @author Francisco / Dinis
	 *
	 */
	public enum Difficulty{
		EASY, MEDIUM, HARD
	}
	
	/**
	 * The sprite batch.
	 */
	private SpriteBatch batch;
	
	/**
	 * The asset manager where the elements will be kept.
	 */
	private AssetManager assetManager;
	
	/**
	 * The game score.
	 */
	private Scoreboard score;
	
	/**
	 * Control variable which stores if the game is or not running.
	 */
	private boolean running;
	
	/**
	 * Control variable which stores if the game is or not paused.
	 */
	private boolean paused;
	
	/**
	 * Control variable which stores of the game is or not over.
	 */
	private static boolean over;
	
	/**
	 * The game difficulty chosen.
	 */
	public static Difficulty difficulty;
	
	/**
	 * Saves the highscores.
	 */
	public static Preferences prefs;
	
	/**
	 * The width of the window.
	 */
	public static final int WIDTH = 850;
	
	/**
	 * The height of the window.
	 */
	public static final int HEIGHT = 500;
	
	/**
	 * Pixel to meter converter factor.
	 */
	public static final float PIXEL_TO_METER = 100;
	
	@Override 
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		prefs = Gdx.app.getPreferences("GalaxyWars");
		initPreferences();
		loadAssets();
		drawMenu();
	}
	
	/**
	 * Initializes the preferences.
	 */
	private void initPreferences(){
		if(!prefs.contains("highScore")) {
		    prefs.putInteger("highScore", 0);
		}
		if(!prefs.contains("username")){
			prefs.putString("username", "");
		}
	}
	
	/**
	 * Sets the new highscore of the game.
	 */
	public void setHighScore(int time, String username){
		prefs.putInteger("time", time);
		prefs.putString("username", username);
		prefs.flush();
	}
	
	public int getHighScoreTime(){
		return prefs.getInteger("time");
	}
	
	public String getHighScoreUsername(){
		return prefs.getString("username");
	}
	
	/**
	 * Initializes the control variables and creates the main menu.
	 */
	public void drawMenu(){
		running = false;
		paused = false;
		over = false;
		setScreen(new MenuScreen(this));
	}
	
	/**
	 * Sets the finished game menu.
	 */
	public void finishedGame(){
		setScreen(new FinishedGameScreen(this));
	}
	
	/**
	 * Initializes score board and sets the game screen.
	 * @param difficulty
	 */
	public void startGame(Difficulty difficulty){
		this.difficulty = difficulty;
		score = new Scoreboard();
		setRunning();
		setScreen(new GameView(this));
	}
	
	/**
	 * Exits the game.
	 */
	public void exitGame(){
		Gdx.app.exit();
	}
	
	/**
	 * Loads all assets of the game.
	 */
	private void loadAssets(){

		loadMainMenuScreenElements();
		loadDifficultMenuScreenElements();
		loadRunningGameElements();
		loadPausedGameScreenElements();
		loadFinishedGameScreenElements();
		loadSounds();
		
		assetManager.finishLoading();
	}
	
	/**
	 * Loads main menu elements.
	 */
	private void loadMainMenuScreenElements(){
		assetManager.load("space-background.png", Texture.class);
		assetManager.load("title.png", Texture.class);
		assetManager.load("play-button.png", Texture.class);
		assetManager.load("play-button-hover.png", Texture.class);
		assetManager.load("quit-button.png", Texture.class);
		assetManager.load("quit-button-hover.png", Texture.class);
	}
	
	/**
	 * Loads difficult menu elements.
	 */
	private void loadDifficultMenuScreenElements(){		
		assetManager.load("difficulty-title.png", Texture.class);
		assetManager.load("easy-button.png", Texture.class);
		assetManager.load("medium-button.png", Texture.class);
		assetManager.load("hard-button.png", Texture.class);
		assetManager.load("easy-button-hover.png", Texture.class);
		assetManager.load("medium-button-hover.png", Texture.class);
		assetManager.load("hard-button-hover.png", Texture.class);
	}
	
	/**
	 * Loads running game elements.
	 */
	private void loadRunningGameElements(){
		assetManager.load("space-ship.png", Texture.class);
		assetManager.load("explosion-steps.png", Texture.class);
		assetManager.load("bullet.png", Texture.class);
		assetManager.load("zigzag.png", Texture.class);
		assetManager.load("shooter.png", Texture.class);
		assetManager.load("kamikaze.png", Texture.class);
	}
	
	/**
	 * Loads paused game elements.
	 */
	private void loadPausedGameScreenElements(){
		assetManager.load("menu-title.png", Texture.class);
		assetManager.load("resume-button.png", Texture.class);
		assetManager.load("quit-game-button.png", Texture.class);
	}
	
	/**
	 * Loads finish game elements.
	 */
	private void loadFinishedGameScreenElements(){
		assetManager.load("game-over.png", Texture.class);
		assetManager.load("savescore-button.png", Texture.class);
		assetManager.load("savescore-button-hover.png", Texture.class);
		assetManager.load("return-button.png", Texture.class);
		assetManager.load("return-button-hover.png", Texture.class);
	}
	
	/**
	 * Loads sounds.
	 */
	private void loadSounds(){
		assetManager.load("sounds/action-music.ogg", Sound.class);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}
	
	/**
	 * Returns the asset manager.
	 * @return asset manager.
	 */
	public AssetManager getAssetManager(){
		return assetManager;
	}
	
	/**
	 * Returns the sprite batch.
	 * @return sprite batch.
	 */
	public SpriteBatch getSpriteBatch(){
		return batch;
	}
	
	/**
	 * Returns the game difficulty chosen.
	 * @return difficulty chosen.
	 */
	public static Difficulty getDifficulty(){
		return difficulty;
	}
	
	/**
	 * Returns the game score.
	 * @return score.
	 */
	public Scoreboard getScoreboard(){
		return score;
	}
	
	/**
	 * Checks if game is still running.
	 * @return true or false depending if the game is or not running.
	 */
	public boolean isRunning(){
		return running;
	}
	
	/**
	 * Checks if game is still paused.
	 * @return true or false depending if the game is or not paused.
	 */
	public boolean isPaused(){
		return paused;
	}
	
	/**
	 * Checks if game is still over.
	 * @return true or false depending if the game is or not over.
	 */
	public static boolean isOver(){
		return over;
	}
	
	/**
	 * Sets the game to paused : updates the game control variables.
	 */
	public void setPaused(){
		this.paused = true;
		this.running = false;
	}
	
	/**
	 * Sets the game over.
	 */
	public static void setGameOver(){
		over = true;
	}
	 
	/**
	 * Updates the control variables to running mode and initializes the game logic difficulty.
	 */
	public void setRunning(){
		this.running = true;
		this.paused = false;
		GameModel.setMaxEnemyInterval(difficulty);	//Interval of the enemies created in ms

	}
}
