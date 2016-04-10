package com.phoenixkahlo.roomold;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class BasicEntity implements Entity {

	private Image image;
	private float x;
	private float y;
	private float imgCenterX;
	private float imgCenterY;
	private float angle;
	private float imgWidth;
	private float imgHeight;
	
	private float zeroAngle = 0;
	
	protected void injectFields(Image image, float x, float y, float imgCenterX, float imgCenterY, float angle, float imgWidth, float imgHeight) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.imgCenterX = imgCenterX;
		this.imgCenterY = imgCenterY;
		this.angle = angle;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}
	
	protected void injectFields(Image image, float x, float y, float imgWidth, float imgHeight) {
		injectFields(image, x, y, image.getWidth() / 2, image.getHeight() / 2, 0, imgWidth, imgHeight);
	}
	
	protected void setZeroAngle(float zeroAngle) {
		this.zeroAngle = zeroAngle;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.rotate(x, y, -(angle + zeroAngle));
		g.drawImage(image, x - imgCenterX / image.getWidth() * imgWidth, y - imgCenterY / image.getHeight() * imgHeight,
				x - imgCenterX / image.getWidth() * imgWidth + imgWidth, y - imgCenterY / image.getHeight() * imgHeight + imgHeight,
				0, 0, image.getWidth(), image.getHeight());
		g.rotate(x, y, angle + zeroAngle);
	}
	
	public void pointToMouse(Input input) {
		setAngle((float) (Math.atan2(input.getMouseX() - getX(), input.getMouseY() - getY()) / Math.PI * 180) - 90);
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getAngle() {
		return angle;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}

	@Override
	public void init(GameContainer container) throws SlickException {}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {}

}
