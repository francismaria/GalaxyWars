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
	
	private GalaxyWars game;
	
	private Texture playButtonTxt;
	
	private static final int PLAY_BUTTON_DIM = 150;
	
	public MenuScreen(GalaxyWars game){
		this.game = game;
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
        
        //game.getSpriteBatch().draw(playButtonTxt, 275, 250, PLAY_BUTTON_DIM, PLAY_BUTTON_DIM);
        drawBackground();
        drawButtons();
        //drawMenu();
        
        game.getSpriteBatch().end();
		
	}

	private void drawBackground(){	
		Texture background = game.getAssetManager().get("space-background.png", Texture.class);
		Texture gameTitle = game.getAssetManager().get("title.png", Texture.class);
		game.getSpriteBatch().draw(background, 0, 0, 0, 0, GalaxyWars.WIDTH, GalaxyWars.HEIGHT);
		game.getSpriteBatch().draw(gameTitle, 100, 300);
	}
	
	private void drawButtons(){
		Texture playButton = game.getAssetManager().get("play-button.png", Texture.class);
		Texture quitButton = game.getAssetManager().get("quit-button.png", Texture.class);
		game.getSpriteBatch().draw(playButton, 260, 200);
		game.getSpriteBatch().draw(quitButton, 260, 50);
	}
	
	private void handleInputs(){
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){
			game.startGame(Difficulty.EASY);
		/*	if(isPlayBtnPressed()){
				
			}*/
		}
	}
	
	private boolean isPlayBtnPressed(){
		return false;
		//if()
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
