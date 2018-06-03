package com.mygdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Utils {
	
	/**
	 * Variable to wait 1s to click.
	 */
	public static final float MIN_TIME_TO_CLICK = 1f;
	
	public static final int XMIN = 0;
	
	public static final int XMAX = 1;
	
	public static final int YMIN = 2;
	
	public static final int YMAX = 3;
	
	public static final Sound buttonClickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/beep-button.mp3"));

}
