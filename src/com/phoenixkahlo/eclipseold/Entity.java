package com.phoenixkahlo.eclipseold;

import org.dyn4j.geometry.Vector2;

/**
 * An Eclipse entity which may be implemented through a Body.
 */
public interface Entity {

	Vector2 getLocation();
	
	void setLocation(Vector2 location);
	
	double getAngle();

	void rotate(double angle);
	
	void setAngle(double angle);
	
	public void pointTowards(Vector2 point);
	
	void pointTowardsMouse(InputContext input, PerspectiveTransformer transformer);
	
	EclipseWorld getWorld();
	
}
