package com.phoenixkahlo.roomgame.geometry;

import java.util.ArrayList;
import java.util.List;

public class SimplePolygon implements Polygon {

	/**
	 * originalPoints is not rotated, corners and sides are.
	 */
	List<Point> originalPoints;
	List<Point> corners;
	List<Segment> sides;
	
	public void injectGeometry(List<Point> points) {
		originalPoints = points;
	}
	
	public void setAngle(float angle) {
		corners = new ArrayList<Point>();
		for (Point point : originalPoints) {
			corners.add(point.rotate(angle));
		}
		sides = GeometryUtils.loop(corners);
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
