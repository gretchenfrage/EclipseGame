package com.phoenixkahlo.roomgame.geometry;

import java.util.ArrayList;
import java.util.List;

public class Square extends SimplePolygon {
	
	public Square(float size) {
		List<Point> corners = new ArrayList<Point>();
		corners.add(new Point(-size / 2, -size / 2));
		corners.add(new Point(size / 2, -size / 2));
		corners.add(new Point(size / 2, size / 2));
		corners.add(new Point(-size / 2, size / 2));
		injectGeometry(corners);
	}

}
