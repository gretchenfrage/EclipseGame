package com.phoenixkahlo.roomold;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class BasicItem implements Item {

	private Image image;
	private float angle = 0;
	private float zeroAngle = 0;
	
	protected void injectFields(Image image) {
		this.image = image;
	}
	
	protected void setZeroAngle(float zeroAngle) {
		this.zeroAngle = zeroAngle;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	@Override
	public void render(Graphics g, float x1, float y1, float x2, float y2) throws SlickException {
		g.rotate((x1 + x2) / 2, (y1 + y2) / 2, -(zeroAngle + angle));
		g.drawImage(image, x1, y1, x2, y2, 0, 0, image.getWidth(), image.getHeight());
		g.rotate((x1 + x2) / 2, (y1 + y2) / 2, zeroAngle + angle);
	}

}
