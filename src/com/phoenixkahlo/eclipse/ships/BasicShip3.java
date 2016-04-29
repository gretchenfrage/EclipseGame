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
import com.phoenixkahlo.eclipse.ResourceUtils;
import com.phoenixkahlo.eclipse.Ship;

public class BasicShip3 extends Ship {

	private static Image texture;
	private List<Convex> floor = new ArrayList<Convex>();
	
	public BasicShip3(Eclipse eclipse) {
		super(eclipse);
		
		Convex c1 = new Rectangle(1, 40);
		c1.translate(-4.5, 0);
		addFixture(c1);
		Convex c2 = new Rectangle(1, 40);
		c2.translate(4.5, 0);
		addFixture(c2);
		Convex c3 = new Rectangle(10, 1);
		c3.translate(0, -19.5);
		addFixture(c3);
		
		floor.add(new Rectangle(10, 40));
		
		setMass(new Mass(new Vector2(0, 0), 10_000, 5_000));
	}
	
	@Override
	public void init() throws SlickException {
		if (texture == null) texture = ResourceUtils.loadImage("ships/BasicShip3");
		injectTexture(texture, 10, 40);
	}

	@Override
	protected List<Convex> getFloor() {
		return floor;
	}

}
