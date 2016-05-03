package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

public class LocalPlayer extends WalkingBody implements Player {

	private PlayerMovementHandler defaultMovementHandler;
	private PlayerPerspectiveHandler defaultPerspectiveHandler;
	private PlayerMovementHandler movementHandler;
	private PlayerPerspectiveHandler perspectiveHandler;
	private PerspectiveTransformer transformer;
	private InputContext input;
	
	public LocalPlayer(EclipseWorld world, InputContext input) {
		super(world);
		this.input = input;
	}

	@Override
	public void setMovementHandler(PlayerMovementHandler handler) {
		movementHandler = handler;
	}

	@Override
	public void resetMovementHandler() {
		setMovementHandler(defaultMovementHandler);
	}

	@Override
	public void setPerspectiveHandler(PlayerPerspectiveHandler handler) {
		perspectiveHandler = handler;
	}

	@Override
	public void resetPerspectiveHandler() {
		setPerspectiveHandler(defaultPerspectiveHandler);
	}

	@Override
	public void activateUseable(Vector2 location) {
		//TODO: make better
		getWorld().activateUseable(this);
	}

	@Override
	public PlayerPerspectiveHandler getPerspectiveHandler() {
		return perspectiveHandler;
	}

	@Override
	public PerspectiveTransformer getTransformer() {
		return transformer;
	}
	
	@Override
	public void preUpdate(int delta) {
		super.preUpdate(delta);
		movementHandler.preUpdate(delta, input);
		perspectiveHandler.preUpdate(delta, input);
	}

	@Override
	public void postUpdate(int delta) {
		super.postUpdate(delta);
		movementHandler.postUpdate(delta, input);
		perspectiveHandler.postUpdate(delta, input);
	}

}
