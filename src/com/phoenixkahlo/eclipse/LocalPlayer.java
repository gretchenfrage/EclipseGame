package com.phoenixkahlo.eclipse;

import static org.newdawn.slick.Input.*;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class LocalPlayer extends Human {
	
	private Eclipse eclipse;
	private float viewAngle = 0;
	private double platformPreAngle;
	
	public LocalPlayer(Eclipse eclipse) {
		super(eclipse.getWorld());
		this.eclipse = eclipse;
	}
	
	public Vector2 getTranslation(GameContainer container, int pixelsPerMeter) {
		return getWorldCenter().multiply(pixelsPerMeter).multiply(-1).add(container.getWidth() / 2, container.getHeight() / 2);
	}
	
	public float getRotation() {
		return 0;//viewAngle;
	}

	@Override
	public void preUpdate(int delta) {
		super.preUpdate(delta);
		
		//TODO: make this nicer
		if (getPlatform() != null) platformPreAngle = getPlatform().toBody().getTransform().getRotation();
		
		GameContainer container = eclipse.getContainer();
		Input input = container.getInput();
		
		boolean sprinting = input.isKeyDown(KEY_LSHIFT);
		
		Vector2 direction = new Vector2(0, 0);
		if (input.isKeyDown(KEY_A)) direction.x -= 1;
		if (input.isKeyDown(KEY_D)) direction.x += 1;
		if (input.isKeyDown(KEY_W)) direction.y -= 1;
		if (input.isKeyDown(KEY_S)) direction.y += 1;
		
		setSprinting(sprinting);
		setDirection(direction);
		
		pointTowardsMouse(container.getInput(), eclipse.getTranslation(), eclipse.getPixelsPerMeter());
	}
	
	@Override
	public void postUpdate(int delta) {
		super.postUpdate(delta);
		if (onPlatform()) viewAngle -= getPlatform().toBody().getTransform().getRotation() - platformPreAngle;
	}
	

}
