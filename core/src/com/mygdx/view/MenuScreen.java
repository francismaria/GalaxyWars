package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.GalaxyWars;
import com.mygdx.game.GalaxyWars.Difficulty;
import com.mygdx.utils.Utils;

public class MenuScreen implements Screen {
	
	/**
	 * The game itself.
	 */
	private GalaxyWars game;
	
	/**
	 * Variable that acknowledges if user has or not passed the main menu.
	 */
	private boolean difficultyMenu;
	
	/**
	 * Time control variable to a button be clicked only once.
	 */
	private float timeElapsedtoClick;
	
	/**
	 * Array which defines the limits of the play button.
	 */
	private static final int[] playBtnLimits = new int[]{260, 565, GalaxyWars.HEIGHT-315, GalaxyWars.HEIGHT-200};
	
	/**
	 * Array which defines the limits of the quit button.
	 */
	private static final int[] quitBtnLimits = new int[]{260, 565, GalaxyWars.HEIGHT-167, GalaxyWars.HEIGHT-50};
	
	/**
	 * Array which defines the limits of the easy button.
	 */
	private static final int[] easyBtnLimits = new int[]{55, 255, GalaxyWars.HEIGHT-260, GalaxyWars.HEIGHT-150};
	
	/**
	 * Array which defines the limits of the medium button.
	 */
	private static final int[] mediumBtnLimits = new int[]{275, 545, GalaxyWars.HEIGHT-260, GalaxyWars.HEIGHT-150};
	
	/**
	 * Array which defines the limits of the hard button.
	 */
	private static final int[] hardBtnLimits = new int[]{595, 795, GalaxyWars.HEIGHT-260, GalaxyWars.HEIGHT-150};
	
	/**
	 * Sound of the menu background music.
	 */
	private static final Sound backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("sounds/Cyber-Dream-Loop.mp3"));
	
	/**
	 * Creates the menu screen
	 * @param game the game class
	 */
	public MenuScreen(GalaxyWars game){
		this.game = game;
		this.difficultyMenu = false;
		timeElapsedtoClick = 0;
		backgroundMusic.loop();
	}
	
	@Override
	public void show(){
		
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        
        handleInputs();
        
        game.getSpriteBatch().begin();
        
        drawBackground();
        
        if(difficultyMenu){
        	drawDifficultyButtons();
        } else {
        	drawMainButtons();
        }
        
        game.getSpriteBatch().end();
		
	}
	
	/**
	 * Draws the background.
	 */
	private void drawBackground(){	
		Texture background = game.getAssetManager().get("space-background.png", Texture.class);
		game.getSpriteBatch().draw(background, 0, 0, 0, 0, GalaxyWars.WIDTH, GalaxyWars.HEIGHT);
	}
	
	/**
	 * Draws the buttons of the main menu and the game title.
	 */
	private void drawMainButtons(){
		Texture playButton, quitButton;
		Texture gameTitle = game.getAssetManager().get("title.png", Texture.class);
		
		if(isInBtnArea(playBtnLimits)){
			playButton = game.getAssetManager().get("play-button-hover.png", Texture.class);
		} else{
			playButton = game.getAssetManager().get("play-button.png", Texture.class);
		}
		
		if(isInBtnArea(quitBtnLimits)){
			quitButton = game.getAssetManager().get("quit-button-hover.png", Texture.class);
		} else{
			quitButton = game.getAssetManager().get("quit-button.png", Texture.class);
		}
		
		game.getSpriteBatch().draw(gameTitle, 100, 300);
		game.getSpriteBatch().draw(playButton, 260, 200);
		game.getSpriteBatch().draw(quitButton, 260, 50);
	}
	
	/**
	 * Draws the difficulty menu buttons and the difficulty menu title.
	 */
	private void drawDifficultyButtons(){
		Texture easyButton, mediumButton, hardButton;
		Texture difficultyTitle = game.getAssetManager().get("difficulty-title.png", Texture.class);
		
		if(isInBtnArea(easyBtnLimits)){
			easyButton = game.getAssetManager().get("easy-button-hover.png", Texture.class);
		} else{
			easyButton = game.getAssetManager().get("easy-button.png", Texture.class);
		}
		
		if(isInBtnArea(mediumBtnLimits)){
			mediumButton = game.getAssetManager().get("medium-button-hover.png", Texture.class);
		} else {
			mediumButton = game.getAssetManager().get("medium-button.png", Texture.class);
		}
		
		if(isInBtnArea(hardBtnLimits)){
			hardButton = game.getAssetManager().get("hard-button-hover.png", Texture.class);
		} else{
			hardButton = game.getAssetManager().get("hard-button.png", Texture.class);	
		}
		
		game.getSpriteBatch().draw(difficultyTitle, 190, 330);
		game.getSpriteBatch().draw(easyButton, 55, 150);
		game.getSpriteBatch().draw(mediumButton, 275, 150);
		game.getSpriteBatch().draw(hardButton, 595, 150);
	}
	
	/**
	 * Redirects input-checking to correct screen handler.
	 */
	private void handleInputs(){
		
		if(!difficultyMenu){
			handleMainInputs();
		} else {
			handleDifficultyInputs();
		}
	}
	
	/**
	 * Handles inputs of the main screen.
	 */
	private void handleMainInputs(){
	
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){
			if(isInBtnArea(playBtnLimits)){
				Utils.buttonClickSound.play(0.1f);
				difficultyMenu = true;
			}
			else if(isInBtnArea(quitBtnLimits)){
				Utils.buttonClickSound.play(0.1f);
				game.exitGame();
			}
		}
	}
	
	/**
	 * Checks if the cursor is inside a certain button area.
	 * @param btnLimits array with the values of button.
	 * @return true or false depending if the cursor is or not inside the button area.
	 */
	private boolean isInBtnArea(int[] btnLimits){
 		int xPos = Gdx.input.getX(), yPos = Gdx.input.getY();
	
		if(xPos > btnLimits[Utils.XMIN] && xPos < btnLimits[Utils.XMAX] &&
				yPos > btnLimits[Utils.YMIN] && yPos < btnLimits[Utils.YMAX])
			return true;
		return false;
	}

	/**
	 * Handles inputs of the difficulty-choosing menu.
	 */
	private void handleDifficultyInputs(){
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){
			if(isInBtnArea(easyBtnLimits)) {
				backgroundMusic.stop();
				game.startGame(Difficulty.EASY);
			}
			else if(isInBtnArea(mediumBtnLimits)){
				backgroundMusic.stop();
				game.startGame(Difficulty.MEDIUM);
			}
			else if(isInBtnArea(hardBtnLimits)) {
				backgroundMusic.stop();
				game.startGame(Difficulty.HARD);
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
