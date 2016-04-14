package com.phoenixkahlo.roomgame.physics;

import org.newdawn.slick.GameContainer;

public class BorderPhysicsBox implements PhysicsBox {

	private int width;
	private int height;
	
	public BorderPhysicsBox(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public BorderPhysicsBox(GameContainer container) {
		width = container.getWidth();
		height = container.getHeight();
	}

	@Override
	public boolean xLegal(float x, float y) {
		return x >= 0 && x < width;
	}

	@Override
	public boolean yLegal(float x, float y) {
		return y >= 0 && y < height;
	}
	
}
