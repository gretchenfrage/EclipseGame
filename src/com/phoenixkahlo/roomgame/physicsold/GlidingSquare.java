package com.phoenixkahlo.roomgame.physicsold;

import com.phoenixkahlo.roomgame.geometry.Square;

/**
 * A square that attempts to glide at a constant velocity
 */
public class GlidingSquare extends BasicPhysicsEntity {

	private float targetXVel;
	private float targetYVel;

	public GlidingSquare(float size, float targetXVel, float targetYVel) {
		super(new Square(size));
		this.targetXVel = targetXVel;
		this.targetYVel = targetYVel;
	}
	
	@Override
	public void update(PhysicsBox box, int delta) {
		setXAcceleration(targetXVel - box.xVelOf(this));
		setYAcceleration(targetYVel - box.yVelOf(this));
	}
	
}
