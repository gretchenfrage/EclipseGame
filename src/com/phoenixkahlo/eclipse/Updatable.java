package com.phoenixkahlo.eclipse;

public interface Updatable {

	void preUpdate(int delta);
	
	void postUpdate(int delta);
	
}
