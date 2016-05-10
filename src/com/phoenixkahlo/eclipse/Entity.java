package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

public interface Entity {

	Vector2 getLocation();
	
	void setLocation(Vector2 location);
	
	double getAngle();
	
	void setAngle(double angle);
	
	Vector2 getLinearVelocity();
	
	void setLinearVelocity(Vector2 velocity);
	
	double getAngularVelocity();
	
	void setAngularVelocity(double velocity);
	
	void giveForce(Vector2 force);
	
	void giveTorque(double torque);
	
}
