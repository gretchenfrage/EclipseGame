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
	
	private static int PIXELS_PER_METER = 20;
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Eclipse(), 1920, 1080, true);
		container.setTargetFrameRate(60);
		container.setVSync(true);
		container.start();
	}
	
	private EclipseWorld world;
	private GameContainer container;
	private LocalPlayer player;
	
	private Vector2 translation;
		
	public Eclipse() {
		super("Eclipse");
		
		world = new EclipseWorld();
		
		world.add(new SpaceBackground(this), RenderLayer.Background);

		Ship ship = new BasicShip2();
		world.add(ship, RenderLayer.Ships);
		ship.applyForce(new Vector2(700_0400, 0));
		ship.applyTorque(500000);
		
		player = new LocalPlayer(this);
		world.add(player, RenderLayer.Humans);
	}
	
	public GameContainer getContainer() {
		return container;
	}
	
	public EclipseWorld getWorld() {
		return world;
	}
	
	public Vector2 getTranslation() {
		return translation;
	}
	
	public int getPixelsPerMeter() {
		return PIXELS_PER_METER;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		translation = player.getTranslation(container, getPixelsPerMeter());
		g.translate((float) translation.x, (float) translation.y);
		Vector2 position = player.getWorldCenter().multiply(getPixelsPerMeter());
		g.rotate((float) position.x, (float) position.y, (float) Math.toDegrees(player.getRotation()));
		
		world.render(container, g, getPixelsPerMeter());
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) container.exit();
		
		world.completeUpdate(delta);
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;

		world.init();
	}

}
