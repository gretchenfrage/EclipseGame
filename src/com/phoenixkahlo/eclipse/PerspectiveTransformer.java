package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Transforms the Graphics context each frame to show the desired perspective, and provides
 * data derived from that transformation.
 */
public class PerspectiveTransformer {

	private GameContainer container;
	
	private Vector2 position;
	private float scale;
	private float angle;
	
	public PerspectiveTransformer(GameContainer container) {
		this.container = container;
		
		position = new Vector2(0, 0);
		scale = 1;
		angle = 0;
	}
	
	public void transform(Graphics g) {
		g.translate(container.getWidth() / 2, container.getHeight() / 2);
		g.scale(scale, scale);
		g.translate((float) -position.x, (float) -position.y);
		g.rotate((float) position.x, (float) position.y, (float) Math.toDegrees(angle));
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	private Vector2[] transformedCorners() {
		Vector2[] out = {
				new Vector2(-container.getWidth() / 2, -container.getHeight() / 2),
				new Vector2(container.getWidth() / 2, -container.getHeight() / 2),
				new Vector2(container.getWidth() / 2, container.getHeight() / 2),
				new Vector2(-container.getWidth() / 2, container.getHeight() / 2)
				};
		for (Vector2 v : out) {
			v.rotate(angle);
			v.multiply(scale);
			v.add(position);
		}
		return out;
	}
	
	private double[] transformedCornersX() {
		Vector2[] vectors = transformedCorners();
		double[] out = new double[4];
		for (int i = 0; i < 4; i++) {
			out[i] = vectors[i].x;
		}
		return out;
	}
	
	private double[] transformedCornersY() {
		Vector2[] vectors = transformedCorners();
		double[] out = new double[4];
		for (int i = 0; i < 4; i++) {
			out[i] = vectors[i].y;
		}
		return out;
	}
	
	public float minX() {
		return (float) MathUtils.min(transformedCornersX());
	}
	
	public float maxX() {
		return (float) MathUtils.max(transformedCornersX());
	}
	
	public float minY() {
		return (float) MathUtils.min(transformedCornersY());
	}
	
	public float maxY() {
		return (float) MathUtils.max(transformedCornersY());
	}
	
	public Vector2 minVisisble() {
		return new Vector2(minX(), minY());
	}
	
	public Vector2 maxVisible() {
		return new Vector2(maxX(), maxY());
	}
	
}
