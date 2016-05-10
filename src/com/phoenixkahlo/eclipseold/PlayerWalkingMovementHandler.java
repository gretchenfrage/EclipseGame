package com.phoenixkahlo.eclipseold;

import org.dyn4j.geometry.Vector2;

/**
 * PlayerMovementHandler for the player walking around normally.
 */
public class PlayerWalkingMovementHandler implements PlayerMovementHandler {
	
	private Player player;
	
	public PlayerWalkingMovementHandler(Player player) {
		this.player = player;
	}
	
	@Override
	public void preUpdate(int delta, InputContext input) {
		boolean sprinting = input.isKeyDown(InputKey.Sprint);
		player.setSprinting(sprinting);

		Vector2 direction = new Vector2(0, 0);
		if (input.isKeyDown(InputKey.WalkLeft)) direction.x -= 1;
		if (input.isKeyDown(InputKey.WalkRight)) direction.x += 1;
		if (input.isKeyDown(InputKey.WalkUp)) direction.y -= 1;
		if (input.isKeyDown(InputKey.WalkDown)) direction.y += 1;
		direction.rotate(-player.getPerspectiveHandler().getPerspectiveAngle());
		player.setDirection(direction);
		
		if (input.isKeyDown(InputKey.Use))
			player.activateUseable(player.getLocation());
	}

	@Override
	public void postUpdate(int delta, InputContext input) {}

}
