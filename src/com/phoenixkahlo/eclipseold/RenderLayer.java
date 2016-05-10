package com.phoenixkahlo.eclipseold;

/**
 * Denotes layers of Renderables that the EclipseWorld will render in order as listed here.
 */
public enum RenderLayer {

	/**
	 * The background, such as space.
	 */
	Background,
	
	/**
	 * All the ships and platforms.
	 */
	Ships,
	
	/**
	 * All the humans and human-like entities.
	 */
	Humans;
	
}
