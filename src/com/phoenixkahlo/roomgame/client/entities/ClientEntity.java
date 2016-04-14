package com.phoenixkahlo.roomgame.client.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface ClientEntity {

	void init() throws SlickException;

	void render(GameContainer container, Graphics g) throws SlickException;

	void update(int delta) throws SlickException;
	
}
