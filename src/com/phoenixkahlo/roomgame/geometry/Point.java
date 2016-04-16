package com.phoenixkahlo.roomgame.geometry;

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
	
	public Point rotate(float cx, float cy, float angle) {
		float s = (float) Math.sin(Math.toRadians(angle));
		float c = (float) Math.cos(Math.toRadians(angle));
		return new Point(
				(x - cx) * c - (y - cy) * s + cx,
				(x - cx) * s + (y - cy) * c + cy
				);
	}
	
	public Point rotate(float angle) {
		return rotate(0, 0, angle);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
