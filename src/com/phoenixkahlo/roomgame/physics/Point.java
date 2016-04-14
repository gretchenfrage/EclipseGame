package com.phoenixkahlo.roomgame.physics;

/**
 * Immutable point in 2d space
 */
public class Point {

	private final float x;
	private final float y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float x() {
		return x;
	}
	
	public float y() {
		return y;
	}
	
}
