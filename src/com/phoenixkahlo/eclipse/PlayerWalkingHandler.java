package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Input;

import com.phoenixkahlo.eclipse.PlayerWalkingHandlerOld.PerspectiveAligner;

/**
 * PlayerMovementHandler for the player walking around normally.
 */
public class PlayerWalkingHandler implements PlayerMovementHandler {

	private static final float PERSPECTIVE_ROTATION_RATE = 0.0025f;
	private static final float PERSPECTIVE_SCALE_RATE = 1.001f;
	private static final float PERSPECTIVE_SCALE_MIN = 1f;
	private static final float PERSPECTIVE_SCALE_MAX = 22;
	
	private Player player;
	
	private double platformPreUpdateAngle; // Used for making player rotate with platform.
	
	public PlayerWalkingHandler(Player player) {
		this.player = player;
	}
	
	@Override
	public void update(InputContext input) {
		if (player.getPlatform() != null)
			platformPreUpdateAngle = player.getPlatformAngle();
	
		boolean sprinting = input.isKeyDown(InputKey.Sprint);
		player.setSprinting(sprinting);

		Vector2 direction = new Vector2(0, 0);
		if (input.isKeyDown(InputKey.WalkLeft)) direction.x -= 1;
		if (input.isKeyDown(InputKey.WalkRight)) direction.x += 1;
		if (input.isKeyDown(InputKey.WalkUp)) direction.y -= 1;
		if (input.isKeyDown(InputKey.WalkDown)) direction.y += 1;
		direction.rotate(-player.getPerspectiveAngle());
		player.setDirection(direction);
		
		if (player.getPlatform() != null && input.isKeyDown(InputKey.ShipAlign)) {
			
		} else {
			if (input.isKeyDown(key))
		}
		/*
		Input input = eclipse.getContainer().getInput();
		
		
		
		//direction.rotate(-eclipse.getTransformer().getAngle());
		
		player.setSprinting(sprinting);
		player.setDirection(direction);
		
		if (player.onPlatform() && input.isKeyDown(ALIGN))
			eclipse.getWorld().addUpdatable(new PerspectiveAligner());
		else {
			if (input.isKeyDown(TURN_LEFT)) perspectiveAngle += PERSPECTIVE_ROTATION_SPEED * delta;
			if (input.isKeyDown(TURN_RIGHT)) perspectiveAngle -= PERSPECTIVE_ROTATION_SPEED * delta;
		}
		
		if (input.isKeyDown(ZOOM_IN)) perspectiveScale *= Math.pow(PERSPECTIVE_SCALE_SPEED, delta);
		if (input.isKeyDown(ZOOM_OUT)) perspectiveScale /= Math.pow(PERSPECTIVE_SCALE_SPEED, delta);
		if (perspectiveScale < PERSPECTIVE_SCALE_MIN) perspectiveScale = PERSPECTIVE_SCALE_MIN;
		if (perspectiveScale > PERSPECTIVE_SCALE_MAX) perspectiveScale = PERSPECTIVE_SCALE_MAX;
		
		if (input.isKeyPressed(USE)) {
			eclipse.getWorld().activateUseable(player);
		}
		
		player.pointTowardsMouse(input, eclipse.getTransformer());
		 */
	}

}
