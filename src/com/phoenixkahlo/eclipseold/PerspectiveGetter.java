package com.phoenixkahlo.eclipseold;

import org.dyn4j.geometry.Vector2;

/**
 * Something that can give a viewing perspective.
 */
public interface PerspectiveGetter {

	Vector2 getPerspectivePosition();

	float getPerspectiveScale();

	float getPerspectiveAngle();
	
}
