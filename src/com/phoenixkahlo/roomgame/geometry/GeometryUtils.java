package com.phoenixkahlo.roomgame.geometry;

import java.util.ArrayList;
import java.util.List;

public class GeometryUtils {

	private GeometryUtils() {}
	
	public static List<Segment> loop(List<Point> points) {
		List<Segment> out = new ArrayList<Segment>();
		for (int i = 0; i < points.size(); i++) {
			out.add(new Segment(points.get(i), points.get((i + 1) % points.size())));
		}
		return out;
	}
	
}
