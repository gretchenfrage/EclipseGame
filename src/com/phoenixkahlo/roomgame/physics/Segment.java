package com.phoenixkahlo.roomgame.physics;

import static java.lang.Float.NaN;

/**
 * An immutable line segment. 
 * Equation is encapsulated.
 */
public class Segment {

	/**
	 * Segment is:
	 * 	y=ax+b
	 * 	min < x < max
	 * Unless b == NaN in which case segment is:
	 * 	x = a
	 * 	min < y < max
	 */
	private float a;
	private float b;
	private float min;
	private float max;
	
	/**
	 * Non-vertical segment
	 */
	public Segment(float a, float b, float xMin, float xMax) {
		constructNonVertically(a, b, xMin, xMax);
	}
	
	private void constructNonVertically(float a, float b, float xMin, float xMax) {
		this.a = a;
		this.b = b;
		this.min = xMin;
		this.max = xMax;
	}
	
	/**
	 * Vertical segment
	 */
	public Segment(float x, float yMin, float yMax) {
		constructVertically(x, yMin, yMax);
	}
	
	private void constructVertically(float x, float yMin, float yMax) {
		this.a = x;
		this.b = NaN;
		this.min = yMin;
		this.max = yMax;
	}
	
	public Segment(Point a, Point b) {
		if (a.x() = b.x()) {
			if (a.y() > b.y())
				this(a.x(), b.y(), a.y());
			else
				this(a.x(), a.y(), b.y());
		}
	}
	
	public boolean contains(Point point) {
		if (b != NaN)
			return point.x() * a + b == point.y() && point.x() > min && point.x() < max;
		else
			return point.x() == a && point.y() > min && point.y() < max;
	}
	
	/**
	 * Returns null if lines have 0 or infinite intersections
	 */
	public Point intersection(Segment segment) {
		if (segment.a == a) return null;
		
	}
	
}
