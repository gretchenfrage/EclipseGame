package com.phoenixkahlo.eclipse;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Eclipse extends BasicGame {
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Eclipse(), 700, 700, false);
		container.setTargetFrameRate(60);
		container.start();
	}
	
	private EclipseWorld world;
	
	public Eclipse() {
		super("Eclipse");
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		
	}

}
