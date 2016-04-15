package com.phoenixkahlo.roomgame.testing;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.phoenixkahlo.roomgame.geometry.Point;
import com.phoenixkahlo.roomgame.geometry.Segment;

public class GeometryTest extends BasicGame {

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new GeometryTest(), 500, 500, false).start();
	}
	
	public GeometryTest() {
		super("");
	}

	int time = 0;
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.drawOval(100, 100, 300, 300);
		Point p1 = new Point(400, 250);
		drawPoint(g, p1);
		for (float f = 10; f < 5000; f++) {
			Point p2 = p1.rotate(250, 250, time / f);
			drawPoint(g, p2);
		}
		/*
		Segment s1 = new Segment(new Point(100, 100), new Point(100, 300));
		Segment s2 = new Segment(new Point(10, 50), new Point(300, 400));
		drawSegment(g, s1);
		drawSegment(g, s2);
		Point p1 = s1.intersection(s2);
		if (p1 != null)
			drawPoint(g, p1);
		else
			System.out.println(p1);
		*/
	}
	
	public static void drawPoint(Graphics g, Point p) {
		g.setColor(Color.blue);
		g.drawOval(p.x() - 10, p.y() - 10, 20, 20);
	}
	
	public static void drawSegment(Graphics g, Segment l) {
		g.setColor(Color.green);
		g.drawLine(l.p1().x(), l.p1().y(), l.p2().x(), l.p2().y());
		drawPoint(g, l.p1());
		drawPoint(g, l.p2());
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setTargetFrameRate(60);
	}

	@Override
	public void update(GameContainer container, int delta) {
		time += delta;
	}
	
}
