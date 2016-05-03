package com.phoenixkahlo.eclipse;

/**
 * Handles the movement of a Player based on an InputContext.
 */
public interface PlayerMovementHandler {

	/**
	 * Expected to be called in PreUpdate stage.
	 */
	void update(InputContext input);
	
}
