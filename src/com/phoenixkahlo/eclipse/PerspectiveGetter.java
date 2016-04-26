package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * Something that can have a visual viewing perspective.
 */
public interface PerspectiveGetter {

	Vector2 getPerspectivePosition();
	
	float getPerspectiveScale();
	
	float getPerspectiveAngle();
	
}
