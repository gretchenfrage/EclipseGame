package com.phoenixkahlo.eclipseold;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A background that renders a tiling image of space.
 */
public class SpaceBackground implements Renderable {

	private static Image texture;
	
	private static Image mars;
	
	private double width = 500;
	private double height;
	
	private Player player;
	
	public SpaceBackground(Player player) {
		this.player = player;
	}
	
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		Vector2 min = player.getTransformer().minVisisble();
		Vector2 max = player.getTransformer().maxVisible();
		
		min = new Vector2(
				Math.floor(min.x / width) * width,
				Math.floor(min.y / height) * height
				);
		
		for (double x = min.x; x < max.x; x += width) {
			for (double y = min.y; y < max.y; y += height) {
				g.drawImage(
						texture,
						(float) x, (float) y, (float) (x + width), (float) (y + height),
						0, 0, texture.getWidth(), texture.getHeight()
						);
			}
		}
		
		g.drawImage(mars, 15, 15, 40, 40, 0, 0, mars.getWidth(), mars.getHeight());
	}

	@Override
	public void init() throws SlickException {
		if (texture == null) texture = ResourceUtils.loadImage("gui/space2");
		this.height = width / texture.getWidth() * texture.getHeight();
		
		if (mars == null) mars = ResourceUtils.loadImage("gui/mars");
	}
	
}
