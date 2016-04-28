package com.phoenixkahlo.eclipse;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * The Eclipse world that holds all the dyn4j-Bodies, Platforms, Renderables, Updatables, Useables.
 */
public class EclipseWorld extends World implements Renderable, Updatable {
	
	private List<Platform> platforms = new ArrayList<Platform>();
	private List<Updatable> updatables = new ArrayList<Updatable>();
	private List<Useable> useables = new ArrayList<Useable>();
	
	private Map<RenderLayer, List<Renderable>> renderLayers;
	private RenderLayer defaultLayer = RenderLayer.Humans;
	
	private Stack<Renderable> toInit = new Stack<Renderable>();
	
	public EclipseWorld() {
		setGravity(ZERO_GRAVITY);
		
		renderLayers = new EnumMap<RenderLayer, List<Renderable>>(RenderLayer.class);
		for (RenderLayer layer : RenderLayer.values()) {
			renderLayers.put(layer, new ArrayList<Renderable>());
		}
	}
	
	public void add(Object object, RenderLayer layer) {
		if (object instanceof Body) addBody((Body) object);
		if (object instanceof Platform) addPlatform((Platform) object);
		if (object instanceof Renderable) addRenderable((Renderable) object, layer);
		if (object instanceof Updatable) addUpdatable((Updatable) object);
		if (object instanceof Useable) addUseable((Useable) object);
	}
	
	public void add(Object object) {
		add(object, defaultLayer);
	}
	
	public void remove(Object object) {
		if (object instanceof Body) removeBody((Body) object);
		platforms.remove(object);
		for (RenderLayer layer : RenderLayer.values()) renderLayers.get(layer).remove(object);
		updatables.remove(object);
	}
	
	public void completeUpdate(int delta) {
		preUpdate(delta);
		update(delta / 1000d, Integer.MAX_VALUE);
		postUpdate(delta);
	}
	
	public void addPlatform(Platform platform) {
		platforms.add(platform);
	}
	
	public void addRenderable(Renderable renderable, RenderLayer layer) {
		renderLayers.get(layer).add(renderable);
		if (toInit == null)
			try {
				renderable.init();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		else
			toInit.push(renderable);
	}
	
	public void addUpdatable(Updatable updatable) {
		updatables.add(updatable);
	}
	
	public void addUseable(Useable useable) {
		useables.add(useable);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for (RenderLayer layer : RenderLayer.values()) {
			/*for (Renderable renderable : renderLayers.get(layer)) {
				renderable.render(container, g);
			}*/
			for (int i = renderLayers.get(layer).size() - 1; i >= 0; i--) {
				renderLayers.get(layer).get(i).render(container, g);
			}
		}
	}
	
	@Override
	public void preUpdate(int delta) {
		/*for (Updatable updatable : updatables) {
			updatable.preUpdate(delta);
		}*/
		for (int i = updatables.size() - 1; i >= 0; i--) {
			updatables.get(i).preUpdate(delta);
		}
	}
	
	@Override
	public void postUpdate(int delta) {
		/*for (Updatable updatable : updatables) {
			updatable.postUpdate(delta);
		}*/
		for (int i = updatables.size() - 1; i >= 0; i--) {
			updatables.get(i).postUpdate(delta);
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
	
	/**
	 * Returns what Platform, if any, the position is resting on, or null if none.
	 */
	public Platform standingOn(Vector2 position) {
		for (Platform platform : platforms) {
			if (platform.standingOn(position)) return platform;
		}
		return null;
	}
	
	public void activateUseable(LocalPlayer player) {
		for (Useable useable : useables) {
			if (useable.atLocation(player.getWorldCenter())) {
				useable.use(player);
				return;
			}
		}
	}
	
}
