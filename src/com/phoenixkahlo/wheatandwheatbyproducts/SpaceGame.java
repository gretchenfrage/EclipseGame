package com.phoenixkahlo.wheatandwheatbyproducts;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SpaceGame extends BasicGame {

	public static final int WINDOW_SIZE = 700;
	public static final float PIXELS_PER_METER = 50;
	
	/*
	 * args = {} or {port, ip}
	 */
	public static void main(String[] args) {
		if (args.length < 2)
			new SpaceGame(/*"24.183.99.218"*/"localhost", 39450).start();
		else
			new SpaceGame(args[0], Integer.parseInt(args[1])).start();
	}

	public void start() {
		try {
			AppGameContainer container = new AppGameContainer(this, WINDOW_SIZE, WINDOW_SIZE, false);
			container.setTargetFrameRate(60);
			container.setVSync(true);
			container.setShowFPS(false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private RenderableWorld world;
	private MovementController movement;
	
	public SpaceGame(String ip, int port) {
		super("Wheat");
		
		world = new Space();
		
		Char c = new Char();
		world.addBody(c);
		c.applyForce(new Vector2(100, 100));
		
		//movement = new MovementController(c, 0.1f, 0.2f);
		movement = new MovementController(c, 3);
		
		Char d = new Char();
		world.addBody(d);
		d.translate(new Vector2(8.3f, 8).subtract(d.getWorldCenter()));
		d.applyForce(new Vector2(-10, -10));
		d.getFixture(0).setRestitution(8f);
		
		Body e = new IndentationParticle();
		world.addBody(e);
		e.translate(new Vector2(5, 2));
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) container.exit();
		
		world.update(delta / 1000f);
		movement.update(container.getInput());
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		world.init();
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		Vector2 perspective = world.getBody(0).getWorldCenter().multiply(PIXELS_PER_METER).subtract(WINDOW_SIZE / 2, WINDOW_SIZE / 2);
		g.translate((float) -perspective.x, (float) -perspective.y);
		world.render(g, PIXELS_PER_METER);
	}
}
