package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpaceBackground implements Renderable {

	private static Image texture;
	
	private double width;
	private double height;
	
	private Eclipse eclipse;
	
	public SpaceBackground(Eclipse eclipse) {
		this.eclipse = eclipse;
	}
	
	@Override
	public void render(GameContainer container, Graphics g, int pixelsPerMeter) throws SlickException {
		Vector2 min = eclipse.getTranslation().copy().multiply(-1);
		Vector2 max = min.copy().add(container.getWidth(), container.getHeight());
		
		min = new Vector2(
				Math.floor(min.x / width) * width,
				Math.floor(min.y / height) * height
				);
		
		// [lenny_face.png]
		min.subtract(1000, 1000);
		max.add(1000, 1000);
		
		for (double x = min.x; x < max.x; x += width) {
			for (double y = min.y; y < max.y; y += height) {
				g.drawImage(
						texture,
						(float) x, (float) y, (float) (x + width), (float) (y + height),
						0, 0, texture.getWidth(), texture.getHeight()
						);
			}
		}
	}

	@Override
	public void init() throws SlickException {
		if (texture == null) texture = ResourceUtils.loadImage("gui/space2");
		this.width = 1000;
		this.height = width / texture.getWidth() * texture.getHeight();
	}
	
}
