package com.phoenixkahlo.roomgame.client;

import org.newdawn.slick.SlickException;

import com.phoenixkahlo.roomgame.utils.ResourceUtils;

public class Player extends GlidingEntity {

	public Player(float x, float y, float angle) {
		super(x, y, angle, 0, 0, -1, -1, 50, 50);
	}

	@Override
	public void init() throws SlickException {
		injectImage(ResourceUtils.loadImage("sprites/wheat"));
	}
	
}
