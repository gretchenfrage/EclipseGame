package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;

/**
 * The player controlled by this client.
 */
@Deprecated
public class LocalPlayerOld extends WalkingBody implements Player {
	
	private PlayerInputHandler defaultInputHandler;
	private PlayerInputHandler inputHandler;
	
	public LocalPlayerOld(Eclipse eclipse) {
		super(eclipse.getWorld());
		
		defaultInputHandler = new PlayerWalkingHandlerOld(this, eclipse);
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
	public void setMovementHandler(PlayerMovementHandler handler) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	//public void setPerspetiveHandler(PlayerPerspectiveHandler handler) {
		// TODO Auto-generated method stub
		
	//}

	@Override
	public void resetPerspectiveHandler() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateUseable(Vector2 location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PerspectiveTransformer getTransformer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetMovementHandler() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPerspectiveHandler(PlayerPerspectiveHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PlayerPerspectiveHandler getPerspectiveHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
