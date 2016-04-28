package com.phoenixkahlo.eclipse;

import static org.newdawn.slick.Input.*;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Input;

/**
 * The player controlled by this client.
 */
public class LocalPlayer extends Human implements PerspectiveGetter {
	
	private static final float PERSPECTIVE_ROTATION_SPEED = 0.0025f;
	private static final float PERSPECTIVE_SCALE_SPEED = 1.001f;
	
	private static final float PERSPECTIVE_SCALE_MIN = 1f;
	private static final float PERSPECTIVE_SCALE_MAX = 22;
	
	private Eclipse eclipse;
	
	private float perspectiveAngle = 0;
	private float perspectiveScale = 15;
	
	private double platformPreUpdateAngle;

	public LocalPlayer(Eclipse eclipse) {
		super(eclipse.getWorld());
		this.eclipse = eclipse;
	}
	
	@Override
	public void preUpdate(int delta) {
		super.preUpdate(delta);
		
		if (getPlatform() != null) // If standing on a platform, save its angle.
			platformPreUpdateAngle = getPlatformAngle();
		
		Input input = eclipse.getContainer().getInput();
		
		boolean sprinting = input.isKeyDown(KEY_LSHIFT);
		
		Vector2 direction = new Vector2(0, 0);
		if (input.isKeyDown(KEY_A)) direction.x -= 1;
		if (input.isKeyDown(KEY_D)) direction.x += 1;
		if (input.isKeyDown(KEY_W)) direction.y -= 1;
		if (input.isKeyDown(KEY_S)) direction.y += 1;
		
		direction.rotate(-eclipse.getTransformer().getAngle());
		
		setSprinting(sprinting);
		setDirection(direction);
		
		if (onPlatform() && input.isKeyDown(KEY_T))
			//perspectiveAngle = (float) -getPlatformAngle();
			eclipse.getWorld().addUpdatable(new PerspectiveAligner());
		else {
			if (input.isKeyDown(KEY_Q)) perspectiveAngle += PERSPECTIVE_ROTATION_SPEED * delta;
			if (input.isKeyDown(KEY_E)) perspectiveAngle -= PERSPECTIVE_ROTATION_SPEED * delta;
		}
		
		if (input.isKeyDown(KEY_R)) perspectiveScale *= Math.pow(PERSPECTIVE_SCALE_SPEED, delta);
		if (input.isKeyDown(KEY_F)) perspectiveScale /= Math.pow(PERSPECTIVE_SCALE_SPEED, delta);
		if (perspectiveScale < PERSPECTIVE_SCALE_MIN) perspectiveScale = PERSPECTIVE_SCALE_MIN;
		if (perspectiveScale > PERSPECTIVE_SCALE_MAX) perspectiveScale = PERSPECTIVE_SCALE_MAX;
		
		pointTowardsMouse(input, eclipse.getTransformer());
	}
	
	@Override
	public void postUpdate(int delta) {
		super.postUpdate(delta);
		if (onPlatform())
			perspectiveAngle -= getPlatformAngle() - platformPreUpdateAngle;
	}

	@Override
	public Vector2 getPerspectivePosition() {
		return getWorldCenter();
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
			if (!onPlatform()) {
				eclipse.getWorld().remove(this);
				return;
			}
			
			float target = (float) -getPlatformAngle();
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
