package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GalaxyWars;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GalaxyWars.WIDTH;
		config.height = GalaxyWars.HEIGHT;
		config.resizable = false;
		config.forceExit = true;
		new LwjglApplication(new GalaxyWars(), config);
	}
}
