package com.phoenixkahlo.roomgame.graphiclogic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Entity {

	void init(GameContainer container) throws SlickException;

	void render(GameContainer container, Graphics g) throws SlickException;

	void update(GameContainer container, int delta) throws SlickException;
	
}
