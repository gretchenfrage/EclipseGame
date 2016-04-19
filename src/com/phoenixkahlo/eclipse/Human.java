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

	private static final float RADIUS = 0.75f;
	
	private static Image texture;
	
	public Human() {
		addFixture(new Circle(RADIUS));
		setMass(MassType.NORMAL);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) {
		Vector2 coords = getWorldCenter();
		Vector2f p1 = new Vector2f(coords.subtract(RADIUS, RADIUS));
		Vector2f p2 = new Vector2f(coords.add(RADIUS, RADIUS));
		g.drawImage(texture, p1.x, p1.y, p2.x, p2.y, 0, 0, texture.getWidth(), texture.getHeight());
	}

	@Override
	public void init() throws SlickException {
		if (texture == null) texture = ResourceUtils.loadImage("sprites/wheat");
	}

}
