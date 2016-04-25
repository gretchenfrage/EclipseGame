package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

public interface Useable {

	boolean atLocation(Vector2 position);
	
	void use(LocalPlayer player);
	
}
