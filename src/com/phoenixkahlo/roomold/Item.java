package com.phoenixkahlo.roomold;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Item {

	void init(GameContainer container) throws SlickException;

	void render(Graphics g, float x1, float y1, float x2, float y2) throws SlickException;

	void activate(float x, float y, Player player, Room game) throws SlickException;
	
}
