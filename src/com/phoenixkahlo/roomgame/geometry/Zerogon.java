package com.phoenixkahlo.roomgame.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * A polygon with 0 sides. Thus a point. Definitely still a polygon.
 */
public class Zerogon implements Polygon {
	
	List<Point> corners = new ArrayList<Point>();
	List<Segment> sides = new ArrayList<Segment>();
	
	public Zerogon() {
		corners.add(new Point(0, 0));		
	}

	@Override
	public List<Point> corners() {
		return corners;
	}

	@Override
	public List<Segment> sides() {
		return sides;
	}

}
