package com.phoenixkahlo.roomgame.geometry;

import static java.lang.Float.NaN;

/**
 * An immutable line segment. 
 * Equation is encapsulated.
 */
public class Segment {

	/**
	 * Segment is:
	 * 	{y=ax+b, min < x < max}
	 * Unless Float.isNaN(b) in which case segment is:
	 * 	{x = a, min < y < max}
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
	
	/**
	 * Segment between two points
	 */
	public Segment(Point p1, Point p2) {
		if (p1.x() == p2.x()) {
			if (p1.y() < p2.y())
				constructVertically(p1.x(), p1.y(), p2.y());
			else
				constructVertically(p1.x(), p2.y(), p1.y());
		} else {
			float a = (p1.y() - p2.y()) / (p1.x() - p2.x());
			float b = p1.y() - p1.x() * a;
			if (p1.x() < p2.x())
				constructNonVertically(a, b, p1.x(), p2.x());
			else
				constructNonVertically(a, b, p2.x(), p1.x());
		}
	}
	
	public Point p1() {
		if (isVertical())
			return new Point(a, min);
		else
			return new Point(min, min * a + b);
	}
	
	public Point p2() {
		if (isVertical())
			return new Point(a, max);
		else
			return new Point(max, max * a + b);
	}
	
	public boolean isVertical() {
		return Float.isNaN(b);
	}
	
	/**
	 * Returns null if there are 0 or infinite intersections
	 */
	public Point intersection(Segment other) {
		if (isVertical() && other.isVertical()) {
			return null;
		} else if (isVertical() || other.isVertical()) { // Exactly 1 segment is vertical
			// The lines have 1 intersection
			Segment vert;
			Segment nonVert;
			if (isVertical()) {
				vert = this;
				nonVert = other;
			} else { // The other segment is the vertical one
				vert = other;
				nonVert = this;
			}
			float x = vert.p1().x();
			float y = x * nonVert.a + nonVert.b;
			Point point = new Point(x, y);
			if (contains(point) && other.contains(point))
				return point;
			else
				return null;
		} else { // Neither segments are vertical
			if (b == other.b) return null;
			// The lines have 1 intersection
			float x =  (b - other.b) / (other.a - a);
			float y = x * a + b;
			Point point = new Point(x, y);
			System.out.println(point);
			if (contains(point) && other.contains(point))
				return point;
			else
				return null;
		}
	}
	
	public boolean contains(Point point) {
		if (isVertical())
			return point.x() == a && point.y() > min && point.y() < max;
		else
			return point.x() * a + b == point.y() && point.x() > min && point.x() < max;
	}
	
	@Override
	public String toString() {
		return isVertical() ? "{y=" + a + "x+" + b + ", " + min + "<x<" + max + "}" : "{x=" + a + ", " + min + "<y<" + max +"}";
	}
	
}
