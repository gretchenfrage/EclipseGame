package com.phoenixkahlo.eclipse;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Entity {

	void init();
	
	void render(GameContainer container, Graphics g);
	
	void update(int delta);
	
}
