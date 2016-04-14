package com.phoenixkahlo.roomgame.client.entities;

import org.newdawn.slick.SlickException;

public class ThinClientEntity extends GlidingEntity {

	private String imageName;

	public ThinClientEntity(float x, float y, float imgCenterX, float imgCenterY, float imgWidth,
			float imgHeight, String imageName) {
		super(x, y, 0, 0, 0, imgCenterX, imgCenterY, imgWidth, imgHeight);
		this.imageName = imageName;
	}
	
	public ThinClientEntity(float x, float y, float imgWidth, float imgHeight, String imageName) {
		this(x, y, -1, -1, imgWidth, imgHeight, imageName);
	}

	@Override
	public void init() throws SlickException {
		injectImage(ResourceUtils.loadImage(imageName));
	}
	
}
