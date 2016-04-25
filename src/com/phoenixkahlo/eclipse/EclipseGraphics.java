package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * The eclipse graphics context that encapsulates a Slick graphics context.
 */
public class EclipseGraphics {

	private GameContainer container;
	private Graphics graphics;
	
	private Vector2 position;
	private float scale;
	private float angle;
	
	public EclipseGraphics(GameContainer container, Graphics graphics, Vector2 position, float scale, float angle) {
		this.container = container;
		this.graphics = graphics;
		this.position = position;
		this.scale = scale;
		this.angle = angle;
		transform();
	}
	
	public EclipseGraphics(GameContainer container, Graphics graphics) {
		this(container, graphics, new Vector2(0, 0), 1, 0);
	}

	public Vector2 getPosition() {
		return position;
	}

	public float getScale() {
		return scale;
	}

	public float getAngle() {
		return angle;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
		transform();
	}

	public void setScale(float scale) {
		this.scale = scale;
		transform();
	}

	public void setAngle(float angle) {
		this.angle = angle;
		transform();
	}
	
	private void transform() {
		graphics.resetTransform();
		graphics.translate(container.getWidth() / 2, container.getHeight() / 2);
		graphics.translate((float) -position.x, (float) -position.y);
		graphics.rotate((float) position.x, (float) position.y, (float) -Math.toDegrees(angle));
	}
	
	private void scale(Vector2 vector) {
		vector.x = (vector.x - position.x) * scale + position.x;
		vector.y = (vector.y - position.y) * scale + position.y;
	}
	
	public void drawImage(Image image, Vector2 center, Vector2 size, float angle) {
		scale(center);
		size.multiply(scale);
		size.multiply(0.5);
		Vector2 c1 = center.copy().subtract(size);
		Vector2 c2 = center.copy().add(size);
		graphics.rotate((float) center.x, (float) center.y, (float) -Math.toDegrees(angle));
		graphics.drawImage(image, (float) c1.x, (float) c1.y, (float) c2.x, (float) c2.y, 0, 0, image.getWidth(), image.getHeight());
		graphics.rotate((float) center.x, (float) center.y, (float) Math.toDegrees(angle));
	}
	
}
