package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.GalaxyWars;
import com.mygdx.game.GalaxyWars.Difficulty;

public class MenuScreen implements Screen {
	
	/**
	 * The game itself.
	 */
	private GalaxyWars game;
	
	/**
	 * Variable that acknowledges if user has or not passed the main menu.
	 */
	private boolean difficultyMenu;
	
	// -----------------------> [ xmin, xmax, ymin, ymax ]
	
	private static final int XMIN = 0;
	private static final int XMAX = 1;
	private static final int YMIN = 2;
	private static final int YMAX = 3;
	
	/**
	 * Array which defines the limits of the play button.
	 */
	private static final int[] playBtnLimits = new int[]{260, 565, GalaxyWars.HEIGHT-315, GalaxyWars.HEIGHT-200};
	
	/**
	 * Array which defines the limits of the quit button.
	 */
	private static final int[] quitBtnLimits = new int[]{260, 565, GalaxyWars.HEIGHT-167, GalaxyWars.HEIGHT-50};
	
	/**
	 * Creates the menu screen
	 * @param game the game class
	 */
	public MenuScreen(GalaxyWars game){
		this.game = game;
		this.difficultyMenu = false;
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
		
		if(isPlayBtnArea()){
			playButton = game.getAssetManager().get("play-button-hover.png", Texture.class);
		} else{
			playButton = game.getAssetManager().get("play-button.png", Texture.class);
		}
		
		if(isQuitBtnArea()){
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
		
		easyButton = game.getAssetManager().get("easy-button.png", Texture.class);
		mediumButton = game.getAssetManager().get("medium-button.png", Texture.class);
		hardButton = game.getAssetManager().get("hard-button.png", Texture.class);
		
		game.getSpriteBatch().draw(difficultyTitle, 190, 330);
		game.getSpriteBatch().draw(easyButton, 315, 230);
		game.getSpriteBatch().draw(mediumButton, 275, 125);
		game.getSpriteBatch().draw(hardButton, 315, 20);
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
		
		/*DEBUGGGGG
		 * int cursorXpos = Gdx.input.getX(), cursorYpos = Gdx.input.getY();
		
		if(isPlayBtnArea(cursorXpos, cursorYpos)) System.out.println("PLAY BUTTON" + cursorXpos + " " + cursorYpos);*/
	
		if(Gdx.input.isButtonPressed(Buttons.LEFT) && !difficultyMenu){
			if(isPlayBtnArea()){		
				//game.startGame(Difficulty.EASY);
				difficultyMenu = true;
			}
			else if(isQuitBtnArea()){
				game.exitGame();
			}
		}
		else if(Gdx.input.isButtonPressed(Buttons.LEFT) && difficultyMenu){
			
		}
	}
	
	/**
	 * Checks if cursor is inside the play button area.
	 * @return true or false depending if the cursor is or not inside the button area.
	 */
	private boolean isPlayBtnArea(){
		int xPos = Gdx.input.getX(), yPos = Gdx.input.getY();
		
		if(xPos > playBtnLimits[XMIN] && xPos < playBtnLimits[XMAX] &&
				yPos > playBtnLimits[YMIN] && yPos < playBtnLimits[YMAX])
			return true;
		return false;
	}
	
	/**
	 * Checks if cursor is inside the quit button area.
	 * @return true or false depending if the cursor is or not inside the button area.
	 */
	private boolean isQuitBtnArea(){
		int xPos = Gdx.input.getX(), yPos = Gdx.input.getY();
		
		if(xPos > quitBtnLimits[XMIN] && xPos < quitBtnLimits[XMAX] &&
				yPos > quitBtnLimits[YMIN] && yPos < quitBtnLimits[YMAX])
			return true;
		return false;
	}
	
	/**
	 * Handle inputs of the difficulty-choosing menu.
	 */
	private void handleDifficultyInputs(){
		
		
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
