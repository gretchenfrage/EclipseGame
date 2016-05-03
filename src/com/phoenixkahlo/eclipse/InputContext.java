package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * A context of input.
 */
public interface InputContext {
	
	/**
	 * Returns if key is currently held down.
	 */
	boolean isKeyDown(InputKey key);
	
	Vector2 getMousePosition();
	
}
