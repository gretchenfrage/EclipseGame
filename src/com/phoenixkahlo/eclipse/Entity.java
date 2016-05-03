package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Input;

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
	
	void pointTowardsMouse(Input input, PerspectiveTransformer transformer);
	
}