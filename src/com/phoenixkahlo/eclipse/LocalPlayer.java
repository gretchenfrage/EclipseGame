package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * The player controlled by this client.
 */
@Deprecated
public class LocalPlayer extends WalkingBody implements Player {
	
	private PlayerInputHandler defaultInputHandler;
	private PlayerInputHandler inputHandler;
	
	public LocalPlayer(Eclipse eclipse) {
		super(eclipse.getWorld());
		
		defaultInputHandler = new PlayerMovementHandler(this, eclipse);
		inputHandler = defaultInputHandler;
	}
	
	@Override
	public void preUpdate(int delta) {
		super.preUpdate(delta);
		
		inputHandler.preUpdate(delta);
	}
	
	public void setInputHandler(PlayerInputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}
	
	public void resetInputHandler() {
		this.inputHandler = defaultInputHandler;
	}
	
	@Override
	public void postUpdate(int delta) {
		super.postUpdate(delta);
		inputHandler.postUpdate(delta);
	}

	public Vector2 getPerspectivePosition() {
		return inputHandler.getPerspectivePosition();
	}

	public float getPerspectiveScale() {
		return inputHandler.getPerspectiveScale();
	}

	public float getPerspectiveAngle() {
		return inputHandler.getPerspectiveAngle();
	}

	@Override
	public Vector2 getLocation() {
		return getWorldCenter();
	}

	@Override
	public void setLocation(Vector2 location) {
		translate(getLocation().subtract(location));
	}

}
