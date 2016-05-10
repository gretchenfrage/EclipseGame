package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

public interface Platform extends Entity {

	void restingOn(Vector2 location);
	
	/**
	 * Takes the position start from the latest preUpdate, and
	 * returns what force was applied to it by movement of the platform.
	 */
	Vector2 updateForce(Vector2 start);
	
}
