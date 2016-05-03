package com.phoenixkahlo.eclipse;

import static org.newdawn.slick.Input.*;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Input;

/**
 * Has the player move and turn about. Is owned by the player
 */
public class PlayerMovementHandler implements PlayerInputHandler {

	private static final float PERSPECTIVE_ROTATION_SPEED = 0.0025f;
	private static final float PERSPECTIVE_SCALE_SPEED = 1.001f;
	
	private static final float PERSPECTIVE_SCALE_MIN = 1f;
	private static final float PERSPECTIVE_SCALE_MAX = 22;
	
	private static final int STRAFE_LEFT = KEY_A;
	private static final int STRAFE_RIGHT = KEY_D;
	private static final int STRAFE_UP = KEY_W;
	private static final int STRAFE_DOWN = KEY_S;
	private static final int TURN_RIGHT = KEY_E;
	private static final int TURN_LEFT = KEY_Q;
	private static final int ZOOM_IN = KEY_R;
	private static final int ZOOM_OUT = KEY_F;
	private static final int USE = KEY_SPACE;
	private static final int ALIGN = KEY_T;
	private static final int SPRINT = KEY_LSHIFT;
	
	private Player player;
	private Eclipse eclipse;
	
	private float perspectiveAngle = 0;
	private float perspectiveScale = 15;
	
	private double platformPreUpdateAngle;
	
	private boolean sprinting;
	private Vector2 direction;
	
	public PlayerMovementHandler(Player player, Eclipse eclipse) {
		this.player = player;
		this.eclipse = eclipse;
	}

	@Override
	public void preUpdate(int delta) {
		if (player.getPlatform() != null) // If standing on a platform, save its angle.
			platformPreUpdateAngle = player.getPlatformAngle();
		
		Input input = eclipse.getContainer().getInput();
		
		sprinting = input.isKeyDown(SPRINT);
		
		direction = new Vector2(0, 0);
		if (input.isKeyDown(STRAFE_LEFT)) direction.x -= 1;
		if (input.isKeyDown(STRAFE_RIGHT)) direction.x += 1;
		if (input.isKeyDown(STRAFE_UP)) direction.y -= 1;
		if (input.isKeyDown(STRAFE_DOWN)) direction.y += 1;
		
		direction.rotate(-eclipse.getTransformer().getAngle());
		
		//player.setSprinting(sprinting);
		//player.setDirection(direction);
		
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
	}

	@Override
	public void postUpdate(int delta) {
		if (player.onPlatform())
			perspectiveAngle -= player.getPlatformAngle() - platformPreUpdateAngle;
	}
	
	@Override
	public Vector2 getPerspectivePosition() {
		return player.getLocation();
	}
	
	@Override
	public float getPerspectiveScale() {
		return perspectiveScale;
	}
	
	@Override
	public float getPerspectiveAngle() {
		return perspectiveAngle;
	}

	/**
	 * Transient Updatable to smoothly align LocalPlayer with it's Platform.
	 */
	private class PerspectiveAligner implements Updatable {

		static final float ROTATION_SPEED = 0.01f;
		
		@Override
		public void preUpdate(int delta) {
			if (!player.onPlatform()) {
				eclipse.getWorld().remove(this);
				return;
			}
			
			float target = (float) -player.getPlatformAngle();
			perspectiveAngle %= Math.PI * 2;
			
			if (perspectiveAngle < target) {
				perspectiveAngle += ROTATION_SPEED * delta;
				if (perspectiveAngle > target) perspectiveAngle = target;
			} else {
				perspectiveAngle -= ROTATION_SPEED * delta;
				if (perspectiveAngle < target) perspectiveAngle = target;
			}
			
			if (perspectiveAngle == target) {
				eclipse.getWorld().remove(this);
				return;
			}
		}
		
		@Override
		public void postUpdate(int delta) {}
		
	}
	
}
