package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * Something that can be used by players.
 */
public interface Useable {

	boolean atLocation(Vector2 position);
	
	void use(Player player);
	
}
