package com.phoenixkahlo.roomgame.graphiclogic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import static org.newdawn.slick.Input.*;

public class Player extends GlidingEntity {
	
	public Player() {
		super(0, 0, 0, 0, 0, -1, -1, 50, 50);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input in = container.getInput();
		if (in.isKeyPressed(KEY_W)) setYVel(yVel);
		super.update(container, delta);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		super.init(container);
	}
	
}
