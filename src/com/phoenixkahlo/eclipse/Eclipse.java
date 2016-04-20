package com.phoenixkahlo.eclipse;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Eclipse extends BasicGame {
	
	private static final int PIXELS_PER_METER = 20;
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Eclipse(), 700, 700, false);
		container.setTargetFrameRate(60);
		container.start();
	}
	
	private EclipseWorld world;
	private InputHandler handler;
	
	public Eclipse() {
		super("Eclipse");
		world = new EclipseWorld();
		handler = new InputHandler();
		
		Human player = new Human();
		world.addBody(player);
		handler.setPlayer(player);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		handler.preRender(container, g, PIXELS_PER_METER);
		world.render(container, g, PIXELS_PER_METER);
		handler.postRender(container, g, PIXELS_PER_METER);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) container.exit();
		
		handler.update(container.getInput(), delta);
		world.update(delta);
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		world.init();
	}

}
