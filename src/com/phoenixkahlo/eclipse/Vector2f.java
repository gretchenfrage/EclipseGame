package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * Thin float vector for easier usage of Vector2s in scenarios requiring floats
 */
public class Vector2f {

	public float x;
	public float y;
	
	public Vector2f(Vector2 vector) {
		x = (float) vector.x;
		y = (float) vector.y;
	}
	
}
