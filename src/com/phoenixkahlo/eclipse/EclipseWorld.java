package com.phoenixkahlo.eclipse;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.World;

public class EclipseWorld {

	private World world = new World();
	private List<Ship> ships = new ArrayList<Ship>();
	
	public void addShip(Ship ship) {
		ships.add(ship);
	}
	
}
