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

/**
 * The prismarine square ship.
 */
public class BasicShip1 extends Ship {

	private static Image texture;
	private List<Convex> floor = new ArrayList<Convex>();
	
	public BasicShip1(Eclipse eclipse) {
		super(eclipse);
		
		setMass(new Mass(new Vector2(), 5000, 5000));
		floor.add(new Rectangle(10, 10));
		Rectangle wall = new org.dyn4j.geometry.Rectangle(0.1, 10);
			wall.translate(-5, 0);
		addFixture(wall);
	}
	
	@Override
	protected List<Convex> getFloor() {
		return floor;
	}
	
	@Override
	public void init() throws SlickException {
		if (texture == null) texture = ResourceUtils.loadImage("ships/BasicShip1");
		injectTexture(texture, 10, 10);
	}

}
