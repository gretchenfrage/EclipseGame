package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Input;

/**
 * Represents a Body that is a player capable of doing the things that a player could do,
 * such as driving ships, taking damage, etc. Could be a connected client, a NPC, etc.
 */
public interface Player {

	Vector2 getLocation();
	
	double getAngle();
	
	void setLocation(Vector2 location);
	
	void setAngle(double angle);
	
	void setInputHandler(PlayerInputHandler handler);
	
	void resetInputHandler();
	
	Platform getPlatform();

	double getPlatformAngle();

	boolean onPlatform();

	void pointTowardsMouse(Input input, PerspectiveTransformer transformer);
	
}