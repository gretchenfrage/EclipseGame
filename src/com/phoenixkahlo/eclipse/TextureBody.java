package com.phoenixkahlo.eclipse;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 * Body that is rendered as an image
 */
public abstract class TextureBody extends Body implements Renderable {

	private double baseAngle = 0;
	private Image texture;
	private float textureWidth;
	private float textureHeight;
	
	protected void injectTexture(Image texture, float textureWidth, float textureHeight) {
		this.texture = texture;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}
	
	protected void setBaseAngle(double baseAngle) {
		double angle = getAngle();
		this.baseAngle = baseAngle;
		setAngle(angle);
	}
	
	@Override
	public void render(GameContainer container, Graphics g, int pixelsPerMeter) {
		assert texture != null : "Texture not injected";
		Vector2 center = getWorldCenter().multiply(pixelsPerMeter);
		Vector2 corner1 = center.copy().subtract(
				new Vector2(textureWidth / 2, textureHeight / 2).multiply(pixelsPerMeter)
				);
		Vector2 corner2 = center.copy().add(
				new Vector2(textureWidth / 2, textureHeight / 2).multiply(pixelsPerMeter)
				);
		g.rotate((float) center.x, (float) center.y, (float) Math.toDegrees(getAngle() + baseAngle));
		g.drawImage(
				texture,
				(float) corner1.x, (float) corner1.y, (float) corner2.x, (float) corner2.y,
				0, 0, texture.getWidth(), texture.getHeight()
				);
		g.rotate((float) center.x, (float) center.y, (float) Math.toDegrees(-(getAngle() + baseAngle)));
	}	
	
	public double getAngle() {
		return getTransform().getRotation();
	}
	
	public void rotate(double theta) {
		getTransform().rotate(theta, getWorldCenter());
	}
	
	public void setAngle(double theta) {
		rotate(theta - getAngle());
	}
	
	public void pointTowards(Vector2 point) {
		point.subtract(getWorldCenter());
		setAngle(Math.atan2(point.y, point.x));
	}
	
	/**
	 * Points towards the pixel on the screen
	 * @param point the coordinates of the pixel to point towards
	 * @param pixelOrigin the pixel coordinates of the world origin
	 */
	public void pointTowardsPixel(Vector2 point, Vector2 pixelOrigin, int pixelsPerMeter) {
		pointTowards(point.subtract(pixelOrigin).multiply(1d / pixelsPerMeter));
	}
	
	public void pointTowardsMouse(Input input, Vector2 pixelOrigin, int pixelsPerMeter) {
		pointTowardsPixel(new Vector2(
				input.getMouseX(), input.getMouseY()),
				pixelOrigin,
				pixelsPerMeter
				);
	}

}
