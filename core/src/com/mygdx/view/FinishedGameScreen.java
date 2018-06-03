package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.GalaxyWars;
import com.mygdx.utils.Utils;

public class FinishedGameScreen implements Screen, TextInputListener {
	
	/**
	 * The game itself.
	 */
	private GalaxyWars game;
	
	/**
	 * The timer font.
	 */
	private BitmapFont timerFont;
	
	/**
	 * The enemies counter font.
	 */
	private BitmapFont enemiesFont;
	
	/**
	 * The user username.
	 */
	private String username;
	
	/**
	 * The return button coordinates limits.
	 */
	private static final int[] RETURN_BUTTON_LIMITS = new int[]{315, 515, GalaxyWars.HEIGHT-146, GalaxyWars.HEIGHT-50};
	
	/**
	 * The save score button coordinates limits.
	 */
	private static final int[] SAVESCORE_BUTTON_LIMITS = new int[]{280, 515, GalaxyWars.HEIGHT-251, GalaxyWars.HEIGHT-155};
	
	/**
	 * Constructor of the class.
	 * @param game the game itself.
	 */
	public FinishedGameScreen(GalaxyWars game){
		this.game = game;
		initFonts();
	}
	
	/**
	 * Initializes the fonts used to keep score.
	 */
	private void initFonts(){
		timerFont = new BitmapFont();
		enemiesFont = new BitmapFont();
		enemiesFont.setColor(Color.WHITE);
		timerFont.setColor(Color.WHITE);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        
        handleInputs();
        
        game.getSpriteBatch().begin();
        
        drawBackground();
        drawScoreboard();
        drawButtons();
        
        game.getSpriteBatch().end();
	}
	
	/**
	 * Handles user inputs.
	 */
	private void handleInputs(){
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){
			if(Utils.isInBtnArea(RETURN_BUTTON_LIMITS)){
				game.drawMenu();
			} else if(Utils.isInBtnArea(SAVESCORE_BUTTON_LIMITS)){
				Gdx.input.getTextInput(this, "Save your score!", "", "Insert your username");
			}
		}
		
	}
	
	/**
	 * Draws the game scoreboard.
	 */
	private void drawScoreboard(){
		enemiesFont.draw(game.getSpriteBatch(), "ENEMIES SHOT\n"+game.getScoreboard().getEnemiesShot(), GalaxyWars.WIDTH-400, GalaxyWars.HEIGHT-200);
		timerFont.draw(game.getSpriteBatch(), "TIME ELAPSED\n"+game.getScoreboard().getTime(), GalaxyWars.WIDTH-575, GalaxyWars.HEIGHT-200);
	}
	
	/**
	 * Draws the menu buttons.
	 */
	private void drawButtons(){
		Texture savescoreButton = null, returnButton = null;
		
		if(Utils.isInBtnArea(RETURN_BUTTON_LIMITS))
			returnButton = game.getAssetManager().get("return-button-hover.png", Texture.class);
		else
			returnButton = game.getAssetManager().get("return-button.png", Texture.class);
		
		if(Utils.isInBtnArea(SAVESCORE_BUTTON_LIMITS))
			savescoreButton = game.getAssetManager().get("savescore-button-hover.png", Texture.class);
		else
			savescoreButton = game.getAssetManager().get("savescore-button.png", Texture.class);
			
		game.getSpriteBatch().draw(returnButton, 315, 50);
		game.getSpriteBatch().draw(savescoreButton, 280, 155);
	}
	
	/**
	 * Draws the background.
	 */
	private void drawBackground(){	
		Texture background = game.getAssetManager().get("space-background.png", Texture.class);
		Texture gameOver = game.getAssetManager().get("game-over.png", Texture.class);

		game.getSpriteBatch().draw(background, 0, 0, 0, 0, GalaxyWars.WIDTH, GalaxyWars.HEIGHT);
		game.getSpriteBatch().draw(gameOver, 240, 350);
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

	@Override
	public void input(String text) {
		this.username = text;
		//save in server
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
}
