package com.phoenixkahlo.spacegame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Background {

	private Image image;
	private float size;
	
	public Background(Image image, float size) {
		this.image = image;
		this.size = size;
	}
	
	public void render(int x1, int y1, int x2, int y2, Graphics g) {
		for (int x = x1; x < x2; x += size) {
			for (int y = y1; y < y2; y += size) {
				g.drawImage(image, x, y, x + size, y + size, 0, 0, image.getWidth(), image.getHeight());
			}
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		render(0, 0, container.getWidth(), container.getHeight(), g);
	}
	
}
