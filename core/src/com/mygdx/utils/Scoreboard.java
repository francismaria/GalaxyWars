package com.mygdx.utils;

public class Scoreboard {
	
	/**
	 * The time the user has passed playing the game.
	 */
	private int time;
	
	/**
	 * Number of enemies shot.
	 */
	private int enemiesShot;
	
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
		this.enemiesShot = enemiesShot;
	}
	
	/**
	 * Gets the time.
	 * @return
	 */
	public String getTime(){
		return Integer.toString(time);
	}
	
	/**
	 * Gets the enemies shot.
	 * @return
	 */
	public String getEnemiesShot(){
		return Integer.toString(enemiesShot);
	}
}
