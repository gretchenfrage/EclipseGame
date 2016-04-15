package com.phoenixkahlo.roomgame.geometry;

import java.util.List;

/**
 * Polygon with coordinates relative only to itself
 */
public interface Polygon {

	List<Point> corners();
	
	List<Segment> sides();
	
}
