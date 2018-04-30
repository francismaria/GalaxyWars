package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.GalaxyWars;

public class GameView extends ScreenAdapter {
	
	private GalaxyWars game;
	
	public GameView(GalaxyWars game){
		this.game = game;
	}
	
	@Override
	public void render(float delta){
        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        
        game.getSpriteBatch().begin();
        
        handleInputs();

        if(!game.isPaused())
        	showRunningGame();
        else
        	showPausedScreen();	
        
        game.getSpriteBatch().end();
	}
	
	private void showPausedScreen(){
		System.out.println("Paused screen");
	}
	
	private void showRunningGame(){
		System.out.println("Running screen");
	}
	
	private void handleInputs(){
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			game.setPaused();
		}
	}	
}
