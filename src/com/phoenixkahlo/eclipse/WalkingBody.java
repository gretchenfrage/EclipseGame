package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A body with walking/sprinting/thrusting movement. Has fixed angular velocity.
 */
public class WalkingBody extends StandingBody {

	private static final double RADIUS = 0.5;
	private static final double WALK_VELOCITY = 12;
	private static final double SPRINT_VELOCITY = 24;
	private static final double THRUST_FORCE = 0.3;
	private static final double SPRINT_THRUST_FORCE = 0.8;

	private static Image texture;
	
	private Vector2 direction = new Vector2(0, 0);
	private boolean sprinting = false;
	
	public WalkingBody(EclipseWorld world) {
		super(world);
		addFixture(new Circle(RADIUS));
		setMass(MassType.FIXED_ANGULAR_VELOCITY);
		setBaseAngle((float) Math.toRadians(45));
	}
	
	public void setDirection(double direction) {
		this.direction = new Vector2(Math.cos(direction), Math.sin(direction));
	}
	
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	
	public void setSprinting(boolean sprinting) {
		this.sprinting = sprinting;
	}
	
	@Override
	public void postUpdate(int delta) {
		super.postUpdate(delta);
		if (!onPlatform()) applyForce( // Thrust if in space.
				direction.copy().multiply((sprinting ? SPRINT_THRUST_FORCE : THRUST_FORCE) * delta)
				);
	}

	@Override
	protected Vector2 getTargetLinearVelocity() {
		return super.getTargetLinearVelocity().add(
				direction.copy().multiply(sprinting ? SPRINT_VELOCITY : WALK_VELOCITY)
				);
	}
	
	@Override
	public void init() throws SlickException {
		if (texture == null) texture = ResourceUtils.loadImage("minecraft/wheat");
		injectTexture(texture, (float) RADIUS * 2, (float) RADIUS * 2);
	}

}
