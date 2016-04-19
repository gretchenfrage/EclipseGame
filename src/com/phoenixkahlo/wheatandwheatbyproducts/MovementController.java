package com.phoenixkahlo.wheatandwheatbyproducts;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Input;

import static org.newdawn.slick.Input.*;

public class MovementController {

	private Body body;
	private float force;
	
	public MovementController(Body body, float force) {
		this.body = body;
		this.force = force;
	}
	
	public void update(Input input) {
		float multiplier = input.isKeyDown(KEY_LSHIFT) ? 4 : 1;
		if (input.isKeyDown(KEY_W)) body.applyForce(new Vector2(0, -force * multiplier));
		if (input.isKeyDown(KEY_S)) body.applyForce(new Vector2(0, force * multiplier));
		if (input.isKeyDown(KEY_A)) body.applyForce(new Vector2(-force * multiplier, 0));
		if (input.isKeyDown(KEY_D)) body.applyForce(new Vector2(force * multiplier, 0));
		if (input.isKeyDown(KEY_SPACE)) body.setLinearDamping(10); else body.setLinearDamping(0);
	}
	
}
