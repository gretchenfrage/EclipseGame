package com.phoenixkahlo.roomgame.physicsold;

public interface PhysicsBox {

	boolean contains(PhysicsEntity entity);
	
	float xOf(PhysicsEntity entity);
	
	float yOf(PhysicsEntity entity);
	
	float xVelOf(PhysicsEntity entity);
	
	float yVelOf(PhysicsEntity entity);

	void remove(PhysicsEntity entity);
	
}
