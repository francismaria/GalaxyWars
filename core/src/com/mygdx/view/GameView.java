package com.mygdx.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.mygdx.model.entities.EnemyModel;
import com.mygdx.model.entities.ExplosionModel;
import com.mygdx.model.entities.KamikazeModel;
import com.mygdx.model.entities.ShooterModel;
import com.mygdx.model.entities.SpaceShipModel;
import com.mygdx.model.entities.ZigZagModel;
import com.mygdx.view.entities.ExplosionView;
import com.mygdx.view.entities.KamikazeView;
import com.mygdx.view.entities.ShooterView;
import com.mygdx.view.entities.SpaceShipView;
import com.mygdx.view.entities.ZigZagView;

public class GameView extends ScreenAdapter {
	
	/**
	 * The game itself
	 */
	private GalaxyWars game;
	
	/**
	 * Camera declaration
	 */
	private OrthographicCamera box2DCamera;
	
	/**
	 * Debug camera renderer (only used for debug purposes in the development phase)
	 */
	private Box2DDebugRenderer debugRenderer;
	
	/**
	 * All explosions views
	 */
	private List<ExplosionView> explosions = new ArrayList<ExplosionView>();

	/**
	 * Class constructor which calls the camera initializer
	 * @param game the game itself
	 */
	public GameView(GalaxyWars game){
		this.game = game;
		initCamera();
	}
	
	/**
	 * Initializes the camera settings
	 */
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
        
        if(game.isOver()){
        	//showFinishedGameScreen();
        }
        else if(!game.isPaused())
        	showRunningGame(delta);
        else
        	showPausedScreen();	     
       
        game.getSpriteBatch().end();
        debugRenderer.render(GameController.getInstance().getWorld(), box2DCamera.combined);
	}
	
	/**
	 * Displays the correct screen when the game is paused
	 */
	private void showPausedScreen(){
		Texture menuTitle = game.getAssetManager().get("menu-title.png", Texture.class);
		game.getSpriteBatch().draw(menuTitle, 290, 300);
	}
	
	/**
	 * Draws the game elements
	 * @param delta
	 */
	private void showRunningGame(float delta){
		
		drawSpaceShip(delta);
		drawEnemies(delta);
		getExplosions();
		drawExplosions(delta);
	}
	
	/**
	 * Draws the explosions and deletes the ones that are over
	 * @param delta
	 */
	private void drawExplosions(float delta){
		
		for(Iterator<ExplosionView> iterator = explosions.iterator(); iterator.hasNext(); ){
			ExplosionView explosion = iterator.next();
			explosion.update(delta);
			if(!explosion.isFinished()){
				explosion.draw(game.getSpriteBatch());
			}else{
				iterator.remove();
			}
		}
	}
	
	/**
	 * Gets the explosions from the controller and creates a new view for each one of them
	 */
	private void getExplosions(){
		
		List<ExplosionModel> explosionsModel = GameController.getInstance().getExplosions();
		
		for(ExplosionModel model : explosionsModel){
			explosions.add(new ExplosionView(game, model));
			System.out.println( "EXPLOSION" + model.getXCoord());
		}
		
		GameController.getInstance().clearExplosions();
	}
	
	/**
	 * Draws the enemies
	 * @param delta
	 */
	private void drawEnemies(float delta){
		
		List<EnemyModel> models = GameController.getInstance().getEnemies();
		
		for(EnemyModel model : models){
			if(model instanceof ZigZagModel){
				drawZigZag((ZigZagModel)model);
			}
			else if(model instanceof ShooterModel){
				drawShooter((ShooterModel)model);
			}
			else if(model instanceof KamikazeModel){
				drawKamikaze((KamikazeModel)model);
			}
		}
	}
	
	/**
	 * Draws the enemy of type "zig-zag".
	 * @param model ziz-zag enemy model.
	 */
	private void drawZigZag(ZigZagModel model){
		ZigZagView zigzag = new ZigZagView(game, (ZigZagModel)model);
		zigzag.draw(game.getSpriteBatch());
	}
	
	/**
	 * Draws the enemy of type "shooter".
	 * @param model shooter enemy model.
	 */
	private void drawShooter(ShooterModel model){
		ShooterView shooter = new ShooterView(game, model);
		shooter.draw(game.getSpriteBatch());
	}
	
	/**
	 * Draws the enemy of type "kamikaze".
	 * @param model kamikaze enemy model.
	 */
	private void drawKamikaze(KamikazeModel model){
		KamikazeView zigzag = new KamikazeView(game, model);
		zigzag.draw(game.getSpriteBatch());
	}
	
	/**
	 * Draws the spaceship
	 * @param delta
	 */
	private void drawSpaceShip(float delta){
		SpaceShipModel model = GameModel.getInstance().getSpaceShipModel();
		GameController.getInstance().update(delta);
		
		SpaceShipView ship = new SpaceShipView(game, model);
		ship.draw(game.getSpriteBatch());
	}
	
	/**
	 * Draws the game background
	 */
	private void drawBackground(){
		Texture background = game.getAssetManager().get("space-background.png", Texture.class);
		game.getSpriteBatch().draw(background, 0, 0, 0, 0, GalaxyWars.WIDTH, GalaxyWars.HEIGHT);
	}
	
	/**
	 * Responsible for all keyboard and mouse inputs in the game
	 */
	private void handleInputs(){
		
		if(game.isRunning()){
			handleRunningGameInputs();
		}
		else{
			handlePausedGameInputs();
		}
	}	
	
	/**
	 * Handles the user inputs while the game is running
	 */
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
	
	/**
	 * Handles the user inputs while the game is paused
	 */
	private void handlePausedGameInputs(){
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			game.setRunning();
		}
	}
}
