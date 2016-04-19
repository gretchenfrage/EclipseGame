package com.phoenixkahlo.eclipse;

/**
 * Indicates a body that will be updated before and after the EclipseWorld calls the World.update method
 */
public interface Updatable {

	void preUpdate(int delta);
	
	void postUpdate(int delta);
	
}
