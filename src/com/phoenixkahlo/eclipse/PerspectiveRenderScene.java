package com.phoenixkahlo.eclipse;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class PerspectiveRenderScene implements RenderScene {

	private Vector2 location;
	private float angle;
	private float scale;
	private List<Renderable> renderables = new ArrayList<Renderable>();
	
	public PerspectiveRenderScene() {
		this(new Vector2(0, 0), 0, 1);
	}
	
	public PerspectiveRenderScene(Vector2 location, float rotation, float scale) {
		this.location = location;
		this.angle = rotation;
		this.scale = scale;
	}
	
	public void addRenderable(Renderable renderable) {
		renderables.add(renderable);
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		transform(container, g);
		
	}
	
	private void transform(GameContainer container, Graphics g) {
		g.resetTransform();
		g.translate(container.getWidth() / 2, container.getHeight() / 2);
		g.scale(scale, scale);
		g.translate((float) -location.x, (float) -location.y);
		g.rotate((float) location.x, (float) location.y, (float) Math.toDegrees(angle));
	}
	
}
