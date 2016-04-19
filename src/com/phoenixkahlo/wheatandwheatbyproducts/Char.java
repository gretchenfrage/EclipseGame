package com.phoenixkahlo.wheatandwheatbyproducts;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Char extends Body implements Renderable {
	
	public static final float RADIUS = 0.5f;
	
	private Image texture;
	
	public Char() {
		addFixture(new Circle(RADIUS));
		setMass(MassType.NORMAL);
	}
	
	@Override
	public void render(Graphics g, float pixelsPerMeter) throws SlickException {
		Vector2 center = getWorldCenter().multiply(pixelsPerMeter);
		Vector2 corner1 = center.copy().subtract(new Vector2(RADIUS, RADIUS).multiply(pixelsPerMeter));
		Vector2 corner2 = center.copy().add(new Vector2(RADIUS, RADIUS).multiply(pixelsPerMeter));
		g.setColor(Color.green);
		g.drawOval((float) corner1.x, (float) corner1.y, (float) (corner2.x - corner1.x), (float) (corner2.y - corner1.y));
		g.drawImage(
				texture, (float) corner1.x, (float) corner1.y, (float) corner2.x, (float) corner2.y,
				0, 0, texture.getWidth(), texture.getHeight()
				);
	}
	
	@Override
	public void init() throws SlickException {
		texture = ResourceUtils.loadImage("sprites/wheat");
	}

}
