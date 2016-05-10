package com.phoenixkahlo.eclipse;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

public class EntityBody extends Body implements Entity {

	@Override
	public Vector2 getLocation() {
		return getWorldCenter();
	}

	@Override
	public void setLocation(Vector2 location) {
		translate(getLocation().subtract(location));
	}

	@Override
	public double getAngle() {
		return getTransform().getRotation();
	}

	@Override
	public void setAngle(double angle) {
		getTransform().setRotation(angle);
	}

	@Override
	public void giveForce(Vector2 force) {
		applyForce(force);
	}

	@Override
	public void giveTorque(double torque) {
		applyTorque(torque);
	}

}
