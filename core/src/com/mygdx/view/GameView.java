package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.controller.GameController;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.GameModel;
import com.mygdx.model.entities.SpaceShipModel;

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
        
        drawBackground();
        
        if(!game.isPaused())
        	showRunningGame(delta);
        else
        	showPausedScreen();	
        
        game.getSpriteBatch().end();
	}
	
	private void showPausedScreen(){
		System.out.println("Paused screen");
	}
	
	private void showRunningGame(float delta){ 
		System.out.println("Running screen");
		
		drawSpaceShip(delta);
	}
	
	private void drawSpaceShip(float delta){
		SpaceShipModel model = GameModel.getInstance().getSpaceShipModel();
		//model.update();
		GameController.getInstance().update(delta);
		
		SpaceShipView ship = new SpaceShipView(game, model);
		ship.draw(game.getSpriteBatch());
	}
	
	private void drawBackground(){
		Texture background = game.getAssetManager().get("space-background.png", Texture.class);
		game.getSpriteBatch().draw(background, 0, 0, 0, 0, GalaxyWars.WIDTH, GalaxyWars.HEIGHT);
	}
	
	private void handleInputs(){
		
		if(game.isRunning()){
			handleRunningGameInputs();
		}
		else{
			handlePausedGameInputs();
		}
	}	
	
	private void handleRunningGameInputs(){
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			game.setPaused();
		}
		else if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			GameController.getInstance().jumpSpaceShip();
		}
	}
	
	private void handlePausedGameInputs(){
		
	}
}
