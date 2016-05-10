package com.phoenixkahlo.eclipseold;

/**
 * Handles the movement of a Player based on an InputContext.
 */
public interface PlayerMovementHandler {

	void preUpdate(int delta, InputContext input);
	
	void postUpdate(int delta, InputContext input);
	
}
