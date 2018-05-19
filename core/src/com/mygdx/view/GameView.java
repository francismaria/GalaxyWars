package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.controller.GameController;
import com.mygdx.game.GalaxyWars;
import com.mygdx.model.GameModel;
import com.mygdx.model.entities.SpaceShipModel;

public class GameView extends ScreenAdapter {
	
	private GalaxyWars game;
	
	private OrthographicCamera box2DCamera;
	private Box2DDebugRenderer debugRenderer;

	public GameView(GalaxyWars game){
		this.game = game;
		initCamera();
	}
	
	private void initCamera(){
		box2DCamera = new OrthographicCamera(GalaxyWars.WIDTH / GalaxyWars.PIXEL_TO_METER, GalaxyWars.WIDTH / GalaxyWars.PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
		box2DCamera.position.set(box2DCamera.viewportWidth / 2f, box2DCamera.viewportHeight / 2f, 0);
		box2DCamera.update();
		debugRenderer = new Box2DDebugRenderer();
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
        debugRenderer.render(GameController.getInstance().getWorld(), box2DCamera.combined);
	}
	
	private void showPausedScreen(){
		System.out.println("Paused screen");
	}
	
	private void showRunningGame(float delta){
		
		drawSpaceShip(delta);
		drawEnemies(delta);
	}
	
	private void drawEnemies(float delta){
		
		
	}
	
	private void drawSpaceShip(float delta){
		SpaceShipModel model = GameModel.getInstance().getSpaceShipModel();
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
		else if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			GameController.getInstance().shootSpaceShipBullet();
			System.out.println("SHOOOT");
		}
	}
	
	private void handlePausedGameInputs(){
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			game.setRunning();
		}
	}
}
