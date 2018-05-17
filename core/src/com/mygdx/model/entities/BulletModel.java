package com.mygdx.model.entities;

public class BulletModel extends EntityModel{
	
	private boolean fired;
	
	public BulletModel(){
		super();
		fired = false;
	}
	
	public boolean wasFired(){
		return fired;
	}
	
	
}
