package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

public class PlayerWalkingPerspectiveHandler implements PlayerPerspectiveHandler {

	private static final float PERSPECTIVE_ROTATION_RATE = 0.0025f;
	private static final float PERSPECTIVE_SCALE_RATE = 1.001f;
	private static final float PERSPECTIVE_SCALE_MIN = 1f;
	private static final float PERSPECTIVE_SCALE_MAX = 22;
	
	private Player player;
	
	private Vector2 position;
	private float scale;
	private float angle;
	
	private double platformPreUpdateAngle; // Used for making player rotate with platform.
	
	public PlayerWalkingPerspectiveHandler(Player player) {
		this.player = player;
	}
	
	@Override
	public void preUpdate(int delta, InputContext input) {
		if (player.getPlatform() != null)
			platformPreUpdateAngle = player.getPlatformAngle();
		
		if (player.getPlatform() != null && input.isKeyDown(InputKey.ShipAlign)) {
			player.getWorld().addUpdatable(new PerspectiveAligner());
		} else {
			if (input.isKeyDown(InputKey.TurnLeft)) angle += PERSPECTIVE_ROTATION_RATE * delta;
			if (input.isKeyDown(InputKey.TurnRight)) angle -= PERSPECTIVE_ROTATION_RATE * delta;
		}
		
		if (input.isKeyDown(InputKey.ZoomIn)) scale *= Math.pow(PERSPECTIVE_SCALE_RATE, delta);
		if (input.isKeyDown(InputKey.ZoomOut)) scale /= Math.pow(PERSPECTIVE_SCALE_RATE, delta);
		if (scale < PERSPECTIVE_SCALE_MIN) scale = PERSPECTIVE_SCALE_MIN;
		if (scale > PERSPECTIVE_SCALE_MAX) scale = PERSPECTIVE_SCALE_MAX;
		
		player.pointTowardsMouse(input, player.getTransformer());
	}
	
	@Override
	public void postUpdate(int delta, InputContext input) {
		if (player.onPlatform())
			angle -= player.getPlatformAngle() - platformPreUpdateAngle;
	}

	@Override
	public Vector2 getPerspectivePosition() {
		return position;
	}

	@Override
	public float getPerspectiveScale() {
		return scale;
	}

	@Override
	public float getPerspectiveAngle() {
		return angle;
	}
	
	/**
	 * Transient Updatable to smoothly align LocalPlayerOld with it's Platform.
	 */
	private class PerspectiveAligner implements Updatable {

		static final float ROTATION_SPEED = 0.01f;
		
		@Override
		public void preUpdate(int delta) {
			if (!player.onPlatform()) {
				remove();
				return;
			} else {
				float targetAngle = (float) (-player.getPlatformAngle() % Math.PI * 2);
				angle %= Math.PI * 2;
				if (angle < targetAngle) {
					angle += ROTATION_SPEED * delta;
					if (angle > targetAngle) angle = targetAngle;
				} else {
					angle -= ROTATION_SPEED * delta;
					if (angle < targetAngle) angle = targetAngle;
				}
				angle %= Math.PI * 2;
				if (angle == targetAngle) {
					remove();
					return;
				}
			}
		}
		
		void remove() {
			player.getWorld().remove(this);
		}
		
		@Override
		public void postUpdate(int delta) {}
		
	}

}
