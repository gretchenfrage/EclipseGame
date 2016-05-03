package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * Represents a Body that is a player capable of doing the things that a player could do,
 * such as driving ships, taking damage, etc. Could be a connected client, a NPC, etc.
 */
public interface Player extends StandingEntity {
	
	void setMovementHandler(PlayerMovementHandler handler);
	
	void resetMovementHandler();

	void setPerspectiveHandler(PlayerPerspectiveHandler handler);
	
	void resetPerspectiveHandler();
	
	void setSprinting(boolean sprinting);

	void setDirection(Vector2 direction);

	void activateUseable(Vector2 location);
	
	PlayerPerspectiveHandler getPerspectiveHandler();

	PerspectiveTransformer getTransformer();
	
}