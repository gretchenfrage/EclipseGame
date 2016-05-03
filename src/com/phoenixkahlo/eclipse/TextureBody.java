package com.phoenixkahlo.eclipse;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * A body that is rendered as an image at a certain angle and size.
 */
public abstract class TextureBody extends Body implements Entity, Renderable {

	private double baseAngle = 0;
	private Image texture;
	private float textureWidth;
	private float textureHeight;
	private EclipseWorld world;
	
	public TextureBody(EclipseWorld world) {
		this.world = world;
	}
	
	/**
	 * Expected to be called in init() call of non-abstract subclass.
	 */
	protected void injectTexture(Image texture, float textureWidth, float textureHeight) {
		this.texture = texture;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}
	
	/**
	 * Rendering angle will always be offset by base angle.
	 */
	protected void setBaseAngle(double baseAngle) {
		double angle = getAngle();
		this.baseAngle = baseAngle;
		setAngle(angle);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) {
		assert texture != null : "Texture not injected";
		
		Vector2 center = getWorldCenter();
		Vector2 c1 = center.copy().subtract(new Vector2(textureWidth / 2, textureHeight / 2));
		Vector2 c2 = center.copy().add(new Vector2(textureWidth / 2, textureHeight / 2));
		
		g.rotate((float) center.x, (float) center.y, (float) Math.toDegrees(getAngle() + baseAngle));
		g.drawImage(
				texture,
				(float) c1.x, (float) c1.y, (float) c2.x, (float) c2.y,
				0, 0, texture.getWidth(), texture.getHeight()
				);
		g.rotate((float) center.x, (float) center.y, (float) -Math.toDegrees(getAngle() + baseAngle));
	}	
	
	@Override
	public double getAngle() {
		return getTransform().getRotation();
	}
	
	@Override
	public void rotate(double theta) {
		getTransform().rotate(theta, getWorldCenter());
	}
	
	@Override
	public void setAngle(double theta) {
		rotate(theta - getAngle());
	}
	
	@Override
	public void pointTowards(Vector2 point) {
		point.subtract(getWorldCenter());
		setAngle(Math.atan2(point.y, point.x));
	}
	
	@Override
	public void pointTowardsMouse(InputContext input, PerspectiveTransformer transformer) {
		pointTowards(transformer.screenToWorld(input.getMousePosition()));
	}

	@Override
	public Vector2 getLocation() {
		return getWorldCenter();
	}
	
	@Override
	public void setLocation(Vector2 location) {
		translate(location.subtract(getLocation()));
	}

	@Override
	public EclipseWorld getWorld() {
		return world;
	}

}
