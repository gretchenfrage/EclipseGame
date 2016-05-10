package com.phoenixkahlo.eclipseold;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

/**
 * A body that other bodies can stand on and move with, such as a ship.
 */
public interface Platform {

	/**
	 * Whether the point at the position is resting on this Platform.
	 */
	boolean standingOn(Vector2 position);
	
	Body toBody();
	
}
