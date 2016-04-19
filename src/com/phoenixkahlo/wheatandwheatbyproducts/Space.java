package com.phoenixkahlo.wheatandwheatbyproducts;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.phoenixkahlo.eclipse.ResourceUtils;

public class Space extends RenderableWorld {

	private Image background;	
	
	public Space() {
		setGravity(ZERO_GRAVITY);
	}
	
	@Override
	public void init() throws SlickException {
		super.init();
		background = ResourceUtils.loadImage("gui/space2");
	}
	
	@Override
	public void render(Graphics g, float pixelsPerMeter) throws SlickException {
		for (int x = -20; x < 20; x++) {
			for (int y = -20; y < 20; y++) {
				g.drawImage(background, background.getWidth() / 6 * x, background.getHeight() / 6 * y,
						background.getWidth() / 6 * (x + 1), background.getHeight() / 6 * (y + 1), 0, 0,
						background.getWidth(), background.getHeight());
			}
		}
		super.render(g, pixelsPerMeter);
	}
	
}
