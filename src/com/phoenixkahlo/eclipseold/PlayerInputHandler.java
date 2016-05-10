package com.phoenixkahlo.eclipseold;

import org.dyn4j.geometry.Vector2;

/**
 * Is owned by the player to handle input and perspective.
 */
@Deprecated
public interface PlayerInputHandler extends Updatable {

	Vector2 getPerspectivePosition();
	
	float getPerspectiveScale();
	
	float getPerspectiveAngle();
	
}
