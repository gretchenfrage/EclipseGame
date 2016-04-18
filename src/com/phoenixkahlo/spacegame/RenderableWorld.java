package com.phoenixkahlo.spacegame;

import java.util.Stack;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class RenderableWorld extends World implements Renderable {

	/**
	 * After RenderableWorld.init() is called, toInit will be set to null,
	 * and Renderables will be initialized without delay
	 */
	private Stack<Renderable> toInit = new Stack<Renderable>();
	
	@Override
	public void addBody(Body body) {
		super.addBody(body);
		if (body instanceof Renderable) {
			if (toInit == null) {
				try {
					((Renderable) body).init();
				} catch (SlickException e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			} else {
				toInit.push((Renderable) body);
			}
		}
	}
	
	@Override
	public void render(Graphics g, float pixelsPerMeter) throws SlickException {
		for (Body body : getBodies()) {
			if (body instanceof Renderable) ((Renderable) body).render(g, pixelsPerMeter);
		}
	}

	@Override
	public void init() throws SlickException {
		while (!toInit.isEmpty()) toInit.pop().init();
		toInit = null;
	}

}
