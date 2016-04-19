package com.phoenixkahlo.wheatandwheatbyproducts;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Renderable {

	void render(Graphics g, float pixelsPerMeter) throws SlickException;
	
	void init() throws SlickException;
	
}
