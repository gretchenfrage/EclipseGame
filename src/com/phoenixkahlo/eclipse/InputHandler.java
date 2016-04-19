package com.phoenixkahlo.eclipse;

import static org.newdawn.slick.Input.*;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class InputHandler {
	
	private static final float LINEAR_DAMPING = 20f;
	private static final float WALK_FORCE = 30f + LINEAR_DAMPING;
	private static final float SPRINT_FORCE = 50f + LINEAR_DAMPING;
	private static final float WALK_VELOCITY_LIMIT = 8f;
	private static final float SPRINT_VELOCITY_LIMIT = 16f;
			
	private Human player;
	
	public void setPlayer(Human player) {
		this.player = player;
	}
	
	public void update(Input input, int delta) {
		player.setLinearDamping(LINEAR_DAMPING);
		
		boolean sprinting = input.isKeyDown(KEY_LSHIFT);
		
		Vector2 force = new Vector2(0, 0);
		if (input.isKeyDown(KEY_A)) force.x -= 1;
		if (input.isKeyDown(KEY_D)) force.x += 1;
		if (input.isKeyDown(KEY_W)) force.y -= 1;
		if (input.isKeyDown(KEY_S)) force.y += 1;
		force.multiply((sprinting ? SPRINT_FORCE : WALK_FORCE) * delta);
		
		Vector2 velocity = player.getLinearVelocity();
		float velocityLimit = sprinting ? SPRINT_VELOCITY_LIMIT : WALK_VELOCITY_LIMIT;
		if (velocity.x > velocityLimit && force.x > 0) force.x = 0;
		if (velocity.x < -velocityLimit && force.x < 0) force.x = 0;
		if (velocity.y > velocityLimit && force.y > 0) force.y = 0;
		if (velocity.y < -velocityLimit && force.y < 0) force.y = 0;
		
		player.applyForce(force);
		
		player.getFixture(0).getShape().rotate(0.1f);
	}
	
	/**
	 * The first thing to be rendered
	 */
	public void preRender(GameContainer container, Graphics g, int pixelsPerMeter) {
		Vector2 center = /*player != null*/false ? player.getWorldCenter() : new Vector2(0, 0);
		center.multiply(pixelsPerMeter);
		g.translate(
				(float) (container.getWidth() / 2 -center.x),
				(float) (container.getHeight() / 2 -center.y)
				);
		g.scale(pixelsPerMeter, pixelsPerMeter);
	}
	
	/**
	 * The last thing to be rendered
	 */
	public void postRender(GameContainer container, Graphics g, int pixelsPerMeter) {
		
	}
	
}
