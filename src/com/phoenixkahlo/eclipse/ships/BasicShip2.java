package com.phoenixkahlo.eclipse.ships;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.phoenixkahlo.eclipse.Eclipse;
import com.phoenixkahlo.eclipse.EclipseWorld;
import com.phoenixkahlo.eclipse.ResourceUtils;
import com.phoenixkahlo.eclipse.Ship;

/**
 * Small generic engine-testing ship.
 */
public class BasicShip2 extends Ship {
	
	private static Image texture;
	private List<Convex> floor;
	private List<Convex> helm;

	public BasicShip2(EclipseWorld world) {
		super(world);
		
		floor = new ArrayList<Convex>();
		floor.add(new Rectangle(10, 10));
		
		helm = new ArrayList<Convex>();
		Convex h1 = new Rectangle(2, 2);
		h1.translate(0, -3);
		helm.add(h1);
		
		Convex c1 = new Rectangle(1, 10);
		c1.translate(-4.5, 0);
		addFixture(c1);
		Convex c2 = new Rectangle(1, 10);
		c2.translate(4.5, 0);
		addFixture(c2);
		Convex c3 = new Rectangle(2, 1);
		c3.translate(-3, 4.5);
		addFixture(c3);
		Convex c4 = new Rectangle(2, 1);
		c4.translate(3, 4.5);
		addFixture(c4);
		Convex c5 = new Rectangle(8, 1);
		c5.translate(0, -4.5);
		addFixture(c5);
		
		setMass(new Mass(new Vector2(0, 0), 10_000, 5_000));
	}
	
	@Override
	public Vector2 getHelmPosition() {
		return new Vector2(0, -3);
	}

	@Override
	public float getHelmAngle() {
		return (float) -(Math.PI / 2);
	}

	@Override
	public void init() throws SlickException {
		if (texture == null) texture = ResourceUtils.loadImage("ships/BasicShip2");
		injectTexture(texture, 10, 10);
	}

	@Override
	protected List<Convex> getFloor() {
		return floor;
	}
	
	@Override
	protected List<Convex> getHelm() {
		return helm;
	}

}
