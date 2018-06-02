package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GalaxyWars;

public class FinishedGameScreen implements Screen {
	
	private GalaxyWars game;
	
	private BitmapFont timerFont;
	
	private BitmapFont enemiesFont;
	
	public FinishedGameScreen(GalaxyWars game){
		this.game = game;
		initFonts();
	}
	
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
        
        //handleInputs();
        
        game.getSpriteBatch().begin();
        
        drawBackground();
        drawScoreboard();
        drawButtons();
        
        game.getSpriteBatch().end();
		
	}
	
	private void drawScoreboard(){
		enemiesFont.draw(game.getSpriteBatch(), "ENEMIES SHOT\n"+game.getScoreboard().getEnemiesShot(), GalaxyWars.WIDTH-400, GalaxyWars.HEIGHT-200);
		timerFont.draw(game.getSpriteBatch(), "TIME ELAPSED\n"+game.getScoreboard().getTime(), GalaxyWars.WIDTH-575, GalaxyWars.HEIGHT-200);
	}
	
	private void drawButtons(){
		
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
}
