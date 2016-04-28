package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * A TextureBody that will stand on Platforms.
 */
public abstract class StandingBody extends TextureBody implements Updatable {
	
	private EclipseWorld world;
	private Platform preUpdatePlatform;
	private Platform postUpdatePlatform;
	
	public StandingBody(EclipseWorld world) {
		this.world = world;
	}
	
	@Override
	public void preUpdate(int delta) {
		preUpdatePlatform = world.standingOn(getWorldCenter());
	}
	
	protected double getVelocityChangeWeight() {
		return 1;
	}
	
	protected Vector2 getTargetLinearVelocity() {
		return getPlatform().toBody().getLinearVelocity(getWorldCenter());
	}

	/**
	 * Only expected to work correctly when called after Standing.pre/postUpdate() in a given update cycle.
	 */
	protected boolean onPlatform() {
		return preUpdatePlatform == postUpdatePlatform && preUpdatePlatform != null;
	}
	
	/**
	 * Only expected to work correctly if onPlatform() == true in this update cycle.
	 */
	protected Platform getPlatform() {
		return preUpdatePlatform;
	}
	
	/**
	 * Has same limit as getPlatform().
	 */
	protected double getPlatformAngle() {
		return getPlatform().toBody().getTransform().getRotation();
	}
	
	@Override
	public void postUpdate(int delta) {
		postUpdatePlatform = world.standingOn(getWorldCenter());
		if (onPlatform()) {
			Vector2 vector = getTargetLinearVelocity(); // The ultimate target velocity
			vector.multiply(getVelocityChangeWeight() * delta);
			vector.add(getLinearVelocity());
			vector.multiply(1 / (1 + getVelocityChangeWeight() * delta)); // The velocity to achieve in this tick
			vector.subtract(getLinearVelocity()); // The change of velocity to achieve
			vector.multiply(getMass().getMass()); // The force to apply
			applyImpulse(vector);
			
			
			
			vector.multiply(-1); // The inverse force to apply to the platform
			getPlatform().toBody().applyImpulse(vector, getWorldCenter());
		}
	}

}
