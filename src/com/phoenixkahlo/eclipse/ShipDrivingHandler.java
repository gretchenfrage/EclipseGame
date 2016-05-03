package com.phoenixkahlo.eclipse;

import static org.newdawn.slick.Input.KEY_A;
import static org.newdawn.slick.Input.KEY_D;
import static org.newdawn.slick.Input.KEY_E;
import static org.newdawn.slick.Input.KEY_F;
import static org.newdawn.slick.Input.KEY_LSHIFT;
import static org.newdawn.slick.Input.KEY_Q;
import static org.newdawn.slick.Input.KEY_R;
import static org.newdawn.slick.Input.KEY_S;
import static org.newdawn.slick.Input.KEY_W;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Input;

/**
 * Drives a ship.
 */
public class ShipDrivingHandler implements PlayerInputHandler {

	private static final float PERSPECTIVE_SCALE_SPEED = 1.001f;
	private static final float PERSPECTIVE_SCALE_MIN = 1;
	private static final float PERSPECTIVE_SCALE_MAX = 6;
	
	private static final int STRAFE_LEFT = KEY_A;
	private static final int STRAFE_RIGHT = KEY_D;
	private static final int STRAFE_UP = KEY_W;
	private static final int STRAFE_DOWN = KEY_S;
	private static final int TURN_RIGHT = KEY_E;
	private static final int TURN_LEFT = KEY_Q;
	private static final int ZOOM_IN = KEY_R;
	private static final int ZOOM_OUT = KEY_F;
	private static final int DISMOUNT = KEY_LSHIFT;
	
	private Player player;
	private Eclipse eclipse;
	private Ship ship;
	
	private float perspectiveAngle = 0;
	private float perspectiveScale = 15;
	
	private double shipPreAngle;
	
	public ShipDrivingHandler(Player player, Eclipse eclipse, Ship ship) {
		this.player = player;
		this.eclipse = eclipse;
		this.ship = ship;
	}
	
	@Override
	public void preUpdate(int delta) {
		Input input = eclipse.getContainer().getInput();
		
		// Dismounting
		if (input.isKeyPressed(DISMOUNT)) {
			ship.dismount();
			return;
		}
		
		// Zooming
		if (input.isKeyDown(ZOOM_IN)) perspectiveScale *= Math.pow(PERSPECTIVE_SCALE_SPEED, delta);
		if (input.isKeyDown(ZOOM_OUT)) perspectiveScale /= Math.pow(PERSPECTIVE_SCALE_SPEED, delta);
		if (perspectiveScale < PERSPECTIVE_SCALE_MIN) perspectiveScale = PERSPECTIVE_SCALE_MIN;
		if (perspectiveScale > PERSPECTIVE_SCALE_MAX) perspectiveScale = PERSPECTIVE_SCALE_MAX;
		
		// Getting movement direction
		Vector2 direction = new Vector2(0, 0);
		if (input.isKeyDown(STRAFE_LEFT)) direction.x -= 1;
		if (input.isKeyDown(STRAFE_RIGHT)) direction.x += 1;
		if (input.isKeyDown(STRAFE_UP)) direction.y -= 1;
		if (input.isKeyDown(STRAFE_DOWN)) direction.y += 1;
		direction.rotate(-eclipse.getTransformer().getAngle());
		
		// Getting torqueing direction
		float spin = 0;
		if (input.isKeyDown(TURN_RIGHT)) spin++;
		if (input.isKeyDown(TURN_LEFT)) spin--;
		
		// Thrust
		ship.thrustLinear(direction);
		ship.thrustTorque(spin);
	}

	@Override
	public void postUpdate(int delta) {
		//player.translate(ship.getWorldPoint(ship.getHelmPosition()).subtract(player.getLocation()));
		player.setLocation(ship.getWorldPoint(ship.getHelmPosition()));
		player.setAngle(ship.getHelmAngle() + ship.getAngle());
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

}
