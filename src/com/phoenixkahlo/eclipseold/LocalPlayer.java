package com.phoenixkahlo.eclipseold;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class LocalPlayer extends WalkingBody implements Player {

	private PlayerMovementHandler defaultMovementHandler;
	private PlayerPerspectiveHandler defaultPerspectiveHandler;
	private PlayerMovementHandler movementHandler;
	private PlayerPerspectiveHandler perspectiveHandler;
	private PerspectiveTransformer transformer;
	private InputContext input;
	
	public LocalPlayer(EclipseWorld world, InputContext input, GameContainer container) {
		super(world);
		this.input = input;
		
		defaultMovementHandler = new PlayerWalkingMovementHandler(this);
		defaultPerspectiveHandler = new PlayerWalkingPerspectiveHandler(this);
		setMovementHandler(defaultMovementHandler);
		setPerspectiveHandler(defaultPerspectiveHandler);
		
		transformer = new PerspectiveTransformer(container);
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
	public void init() throws SlickException {
		super.init();
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
	
	private Vector2 pos;
	
	@Override
	public void preUpdate(int delta) {
		super.preUpdate(delta);
		movementHandler.preUpdate(delta, input);
		perspectiveHandler.preUpdate(delta, input);
		
		pos = getLocation();
	}

	@Override
	public void postUpdate(int delta) {
		super.postUpdate(delta);
		movementHandler.postUpdate(delta, input);
		perspectiveHandler.postUpdate(delta, input);
		
		transformer.setAngle(perspectiveHandler.getPerspectiveAngle());
		transformer.setPosition(perspectiveHandler.getPerspectivePosition());
		transformer.setScale(perspectiveHandler.getPerspectiveScale());
		
		pos = getLocation().subtract(pos);
	}

}
