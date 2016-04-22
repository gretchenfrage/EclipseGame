package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.phoenixkahlo.eclipse.ships.BasicShip2;

public class Eclipse extends BasicGame {
	
	private static  int PIXELS_PER_METER = 20;
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Eclipse(), 1280, 800, true);
		container.setTargetFrameRate(60);
		container.setVSync(true);
		container.start();
	}
	
	private EclipseWorld world;
	private InputHandler handler;
	private Background background;
	
	private Vector2 translation;
	
	public Eclipse() {
		super("Eclipse");
		world = new EclipseWorld();

		Ship ship = new BasicShip2();
		world.addBody(ship);
		ship.applyForce(new Vector2(500_0000, 0));
		ship.applyTorque(50000);
		
		Human player = new Human(world);
		world.addBody(player);
		
		handler = new InputHandler(player, this);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		translation = handler.getTranslation(container, PIXELS_PER_METER);
		g.translate((float) translation.x, (float) translation.y);
		
		background.render(container, g, PIXELS_PER_METER);
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
		background = new Background(ResourceUtils.loadImage("gui/space2"), 1000, this);
	}

	public Vector2 getTranslation() {
		return translation;
	}

}
