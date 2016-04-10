package com.phoenixkahlo.roomold;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Arrow extends BasicEntity {
	
	private static final float SPEED = 1;
	
	private float x;
	private float y;
	private float angle;
	
	public Arrow(float x, float y, float angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		injectFields(ResourceUtils.loadImage("resources/images/minecraft/arrow.png"),
				x, y, 300, 30, angle, 50, 50);
		setZeroAngle(-45);
		setAngle(angle);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		setX((float) (getX() + Math.cos(Math.toRadians(angle)) * SPEED * delta));
		setY((float) (getY() - Math.sin(Math.toRadians(angle)) * SPEED * delta));
	}

}
