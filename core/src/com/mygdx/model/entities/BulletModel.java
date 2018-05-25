package com.mygdx.model.entities;

import com.badlogic.gdx.math.Vector2;

public class BulletModel extends EntityModel{
	
	private boolean fired;
	
	public BulletModel(){
		super();
		fired = false;
	}
	
	public boolean wasFired(){
		return fired;
	}
	
	public void setFired(){
		fired = true;
	}
	
	public void setInitialPosition(Vector2 initialPos){
		setXCoord(initialPos.x);
		setYCoord(initialPos.y);
	}
}
