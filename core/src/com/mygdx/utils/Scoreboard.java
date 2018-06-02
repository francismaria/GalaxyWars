package com.mygdx.utils;

public class Scoreboard {
	
	private int time;
	private int enemiesShot;
	
	public Scoreboard(){
		
	}
	
	public void set(int time, int enemiesShot){
		this.time = time;
		this.enemiesShot = enemiesShot;
	}
	
	public String getTime(){
		return Integer.toString(time);
	}
	
	public String getEnemiesShot(){
		return Integer.toString(enemiesShot);
	}
}
