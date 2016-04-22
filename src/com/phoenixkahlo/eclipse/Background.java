package com.phoenixkahlo.eclipse;

import java.util.Random;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {

	private Image texture;
	private double width;
	private double height;
	
	private Eclipse eclipse;
	
	public Background(Image texture, double width, Eclipse eclipse) {
		this.texture = texture;
		this.width = width;
		this.height = width / texture.getWidth() * texture.getHeight();
		this.eclipse = eclipse;
	}
	
	public void render(GameContainer container, Graphics g, int pixelsPerMeter) throws SlickException {
		Vector2 min = eclipse.getTranslation().copy().multiply(-1);
		Vector2 max = min.copy().add(container.getWidth(), container.getHeight());
		
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
	}
	
}
