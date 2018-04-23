package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GalaxyWars;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.model.entities.SpaceShip;
import com.mygdx.game.view.entities.EntityView;
import com.mygdx.game.view.entities.SpaceShipView;

public class GameView extends ScreenAdapter{
	
	private GalaxyWars game;
	private  OrthographicCamera camera;
	
	public static final float PIXEL_TO_METER = 0.02f;
	public static final float VIEWPORT_WIDTH = 20;
	public static final float ARENA_WIDTH = 720;
	public static final float ARENA_HEIGHT = 480;
	
	public GameView(GalaxyWars game){
		this.game = game;
		
		loadAssets();
		createCamera();
	}
	
	private void loadAssets(){
		game.getAssetManager().load("space.png", Texture.class);
		game.getAssetManager().load("spaceship.png", Texture.class);
		
		game.getAssetManager().finishLoading();
	}
	
	private void createCamera(){
		 camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
	
	     camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
	     camera.update();   
	}
	
	@Override
	public void render(float delta){
		
        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        inputHandler(delta);
        
        game.getBatch().begin();
        drawBackground();
        drawEntities();
        game.getBatch().end();
	}
	
	private void inputHandler(float delta){
		
		 if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
	          	Gdx.app.log("PRESSED", "LEFT BUTTON");
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
	        	Gdx.app.log("PRESSED", "RIGHT BUTTON");
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
	        	Gdx.app.log("PRESSED", "UP BUTTON");
	        }
	        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
	        	Gdx.app.log("PRESSED", "SPACE BUTTON");
	        }
	}
	
	private void drawBackground(){
		Texture background = game.getAssetManager().get("space.png", Texture.class);
        game.getBatch().draw(background, 0, 0, ARENA_WIDTH, ARENA_HEIGHT);
        
	}
	
	private void drawEntities(){
        SpaceShip ship = GameModel.getInstance().getSpaceShip();
        EntityView view = new SpaceShipView(game);
        view.update(ship);
        view.draw(game.getBatch());
	}
}
