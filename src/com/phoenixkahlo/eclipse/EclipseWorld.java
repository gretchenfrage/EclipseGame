package com.phoenixkahlo.eclipse;

import java.util.Stack;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class EclipseWorld extends World implements Renderable {
	
	private Stack<Renderable> toInit = new Stack<Renderable>();
	
	public EclipseWorld() {
		setGravity(ZERO_GRAVITY);
	}
	
	@Override
	public void addBody(Body body) {
		if (body instanceof Renderable) {
			if (toInit == null)
				try {
					((Renderable) body).init();
				} catch (SlickException e) {
					e.printStackTrace();
				}
			else
				toInit.push((Renderable) body);
		}
		super.addBody(body);
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		for (Body body : getBodies()) {
			if (body instanceof Renderable) ((Renderable) body).render(container, g);
		}
	}

	public void update(int delta) {
		for (Body body : getBodies()) {
			if (body instanceof Updatable) ((Updatable) body).preUpdate(delta);
		}
		super.update(delta / 1000f);
		for (Body body : getBodies()) {
			if (body instanceof Updatable) ((Updatable) body).postUpdate(delta);
		}
	}

	@Override
	public void init() {
		while (!toInit.isEmpty()) {
			try {
				toInit.pop().init();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		toInit = null;
	}
	
}