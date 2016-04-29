package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * Is owned by the player to handle input and perspective.
 */
public interface PlayerInputHandler extends Updatable {

	Vector2 getPerspectivePosition();
	
	float getPerspectiveScale();
	
	float getPerspectiveAngle();
	
}
