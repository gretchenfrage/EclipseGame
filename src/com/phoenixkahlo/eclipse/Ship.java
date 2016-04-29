package com.phoenixkahlo.eclipse;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Vector2;

/**
 * Abstract implementation of a ship.
 */
public abstract class Ship extends TextureBody implements Platform, WorldInitializer {
	
	private Eclipse eclipse;
	
	/**
	 * Will hold a reference if someone is driving the ship, otherwise will be null.
	 */
	private LocalPlayer driver = null;
	
	public Ship(Eclipse eclipse) {
		this.eclipse = eclipse;
	}
	
	@Override
	public void worldInit(EclipseWorld world) {
		world.addUseable(new Useable() {
			
			@Override
			public boolean atLocation(Vector2 position) {
				return localAreaContains(getHelm(), position) && driver == null;
			}
			
			@Override
			public void use(LocalPlayer player) {
				driver = player;
				player.setInputHandler(new ShipDrivingHandler(player, eclipse, Ship.this));
			}
			
		});
	}
	
	/**
	 * The canonical way of disconnecting a player from the ship.
	 */
	public void dismount() {
		driver.resetInputHandler();
		driver = null;
	}

	/**
	 * A list of convex polygons composing the floor area of this ship, on which bodies can rest.
	 * The origin of these polygons is the world position of this ship.
	 */
	protected abstract List<Convex> getFloor();

	protected List<Convex> getHelm() {return new ArrayList<Convex>();}
	
	/**
	 * May return null if getHelm() returns an empty ArrayList
	 */
	public Vector2 getHelmPosition() {return null;}
	
	public float getHelmAngle() {return 0;}
	
	@Override
	public boolean standingOn(Vector2 position) {
		return localAreaContains(getFloor(), position);
	}
	
	@Override
	public Body toBody() {
		return this;
	}
	
	private boolean localAreaContains(List<Convex> localArea, Vector2 worldPosition) {
		worldPosition.subtract(getWorldCenter());
		worldPosition.rotate(-getAngle());
		for (Convex convex : localArea) {
			if (convex.contains(worldPosition)) return true;
		}
		return false;
	}
	
}
