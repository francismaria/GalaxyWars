package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GalaxyWars;
import com.mygdx.game.model.GameModel;

public class GameView extends ScreenAdapter{
	
	private GalaxyWars game;
	private  OrthographicCamera camera;
	
	public static final float PIXEL_TO_METER = 0.04f;
	public static final float VIEWPORT_WIDTH = 20;
	public static final float ARENA_WIDTH = 50;
	public static final float ARENA_HEIGHT = 100;
	
	public GameView(GalaxyWars game){
		this.game = game;
		
		loadAssets();
		createCamera();
	}
	
	private void loadAssets(){
		game.getAssetManager().load("space.png", Texture.class);
		
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

        
        game.getBatch().begin();
        drawBackground();
        //drawEntities();
        game.getBatch().end();
	}
	
	private void drawBackground(){
		Texture background = game.getAssetManager().get("space.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, 0, 0, (int)(ARENA_WIDTH / PIXEL_TO_METER), (int) (ARENA_HEIGHT / PIXEL_TO_METER));
	}
	
	private void drawEntities(){
        ShipModel ship = GameModel.getInstance().getShip();
        EntityView view = ViewFactory.makeView(game, ship);
        view.update(ship);
        view.draw(game.getBatch());
	}
}
