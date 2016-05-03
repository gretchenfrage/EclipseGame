package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * Handles the viewing perspective of a Player based on an InputContext.
 */
public interface PlayerPerspectiveHandler {
	
	void preUpdate(int delta, InputContext input);
	
	void postUpdate(int delta, InputContext input);
	
	Vector2 getPerspectivePosition();
	
	float getPerspectiveScale();
	
	float getPerspectiveAngle();
	
}
