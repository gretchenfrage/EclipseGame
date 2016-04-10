package com.phoenixkahlo.roomold;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Room extends BasicGame {
	
	private static AppGameContainer container;
	
	public static void main(String[] args) {
		try {
			container = new AppGameContainer(new Room(), 750, 750, false);
			container.setTargetFrameRate(120);
			container.setVSync(true);
			container.start();
		} catch (SlickException e) {
			System.err.println("Failed to setup AppGameContainer");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Room() {
		super("A Room");
	}
	
	private Image background;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	@Override
	public void init(GameContainer container) throws SlickException {
		background = ResourceUtils.loadImage("resources/images/minecraft/prismarine_bricks.png");
		
		entities.add(new Player(this));
		
		for (Entity entity : entities) {
			entity.init(container);
		}
	}

	public void addEntity(Entity entity) throws SlickException {
		entities.add(entity);
		entity.init(container);
	}

	public Entity removeEntity(int entity) {
		return entities.remove(entity);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		final int BACKGROUND_TILE_SIZE = container.getWidth() / 15;
		for (int x = 0; x < container.getWidth(); x += BACKGROUND_TILE_SIZE) {
			for (int y = 0; y < container.getHeight(); y += BACKGROUND_TILE_SIZE) {
				g.drawImage(background, x, y, x + BACKGROUND_TILE_SIZE, y + BACKGROUND_TILE_SIZE, 0, 0, background.getWidth(), background.getHeight());
			}
		}
		
		for (Entity entity : entities) {
			entity.render(container, g);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (int i = entities.size() - 1; i >= 0; i--) {
			entities.get(i).update(container, delta);
		}
	}

}
