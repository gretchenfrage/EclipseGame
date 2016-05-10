package com.phoenixkahlo.eclipseold;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class BasicEclipse extends BasicGame {

	public static void main(String[] args) throws SlickException {
		System.out.println("creating");
		AppGameContainer container = new AppGameContainer(new BasicEclipse());
		//container.setDisplayMode(container.getScreenWidth(), container.getScreenHeight(), true);
		container.setDisplayMode(500, 500, false);
		container.setTargetFrameRate(60);
		container.setVSync(true);
		container.setShowFPS(false);
		container.setMinimumLogicUpdateInterval(1000 / 30);
		container.start();
	}
	
	private EclipseWorld world = new EclipseWorld();
	private Player self;
	
	public BasicEclipse() {
		super("Eclipse");
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		self = new LocalPlayer(world, new SlickInputContext(container.getInput()), container);
		world.add(self);
		
		world.init();
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		self.getTransformer().transform(g);
		world.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) container.exit();
		
		world.completeUpdate(delta);
	}

}
