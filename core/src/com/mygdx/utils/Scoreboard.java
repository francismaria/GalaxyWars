package com.mygdx.utils;

public class Scoreboard {
	
	/**
	 * The time the user has passed playing the game.
	 */
	private int time;
	
	/**
	 * Constructor of the class.
	 */
	public Scoreboard(){
		
	}
	
	/**
	 * Sets the score.
	 * @param time
	 * @param enemiesShot
	 */
	public void set(int time, int enemiesShot){
		this.time = time;
	}
	
	/**
	 * Returns time integer.
	 * @return
	 */
	public int getTimeInt(){
		return time;
	}
	
	/**
	 * Gets the time.
	 * @return
	 */
	public String getTime(){
		return Integer.toString(time);
	}
	
}
