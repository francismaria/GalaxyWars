package com.mygdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Utils {

	/**
	 * Variable to represent the button minimun x coordinate limit.
	 */
	public static final int XMIN = 0;
	
	/**
	 * Variable to represent the button maximum x coordinate limit.
	 */
	public static final int XMAX = 1;
	
	/**
	 * Variable to represent the button minimun y coordinate limit.
	 */
	public static final int YMIN = 2;
	
	/**
	 * Variable to represent the button maximum y coordinate limit.
	 */
	public static final int YMAX = 3;
	
	/**
	 * Checks if the cursor is inside a certain button area.
	 * @param btnLimits array with the values of button.
	 * @return true or false depending if the cursor is or not inside the button area.
	 */
	public static boolean isInBtnArea(int[] btnLimits){
 		int xPos = Gdx.input.getX(), yPos = Gdx.input.getY();
 		
		if(xPos > btnLimits[Utils.XMIN] && xPos < btnLimits[Utils.XMAX] &&
				yPos > btnLimits[Utils.YMIN] && yPos < btnLimits[Utils.YMAX])
			return true;
		return false;
	}
	
	public static void waitClick(){
		float counter = 0, totalWait = 4f;
		
		while(counter < totalWait){
			counter += 0.167f;
		}
	}
	
	public static final Sound buttonClickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/beep-button.mp3"));

}
