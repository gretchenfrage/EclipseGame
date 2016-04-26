package com.phoenixkahlo.eclipse;

import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Vector2;

/**
 * A ship that is a TextureBody and a Platform
 */
public abstract class Ship extends TextureBody implements Platform {
	
	/**
	 * A list of convex polygons composing the floor area of this ship, which which bodies can rest on.
	 * The origin of these polygons is the world position of this ship.
	 */
	protected abstract List<Convex> getFloor();

	@Override
	public boolean standingOn(Vector2 position) {
		position.subtract(getWorldCenter());
		for (Convex convex : getFloor()) {
			if (convex.contains(position)) return true;
		}
		return false;
	}
	
	@Override
	public Body toBody() {
		return this;
	}
	
}
