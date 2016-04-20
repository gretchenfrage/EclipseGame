package com.phoenixkahlo.eclipse;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Human extends Body implements Renderable{

	private static final double RADIUS = 0.75f;
	private static final double BASE_ANGLE = 45;
	
	private static Image texture;
	
	public Human() {
		addFixture(new Circle(RADIUS));
		setMass(MassType.NORMAL);
	}
	
	@Override
	public void render(GameContainer container, Graphics g, int pixelsPerMeter) {
		Vector2 center = getWorldCenter().multiply(pixelsPerMeter);
		Vector2 p1 = center.copy().subtract(new Vector2(RADIUS, RADIUS).multiply(pixelsPerMeter));
		Vector2 p2 = center.copy().add(new Vector2(RADIUS, RADIUS).multiply(pixelsPerMeter));
		g.rotate((float) center.x, (float) center.y, (float) Math.toDegrees(-getAngle()));
		g.drawImage(
				texture,
				(float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y,
				0, 0, texture.getWidth(), texture.getHeight()
				);
		g.rotate((float) center.x, (float) center.y, (float) Math.toDegrees(getAngle()));
	}
	
	public double getAngle() {
		return getTransform().getRotation();
	}
	
	public void rotate(double theta) {
		getTransform().rotate(theta, getWorldCenter());
	}
	
	public void setAngle(double theta) {
		rotate(-theta - getAngle());
	}

	@Override
	public void init() throws SlickException {
		if (texture == null) texture = ResourceUtils.loadImage("sprites/wheat");
	}

}
