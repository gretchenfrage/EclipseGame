package com.phoenixkahlo.eclipse;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.phoenixkahlo.eclipse.ships.BasicShip2;

/**
 * The eclipse game.
 */
public class Eclipse extends BasicGame {
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Eclipse());
		//container.setDisplayMode(container.getScreenWidth(), container.getScreenHeight(), true);
		container.setDisplayMode(500, 500, false);
		container.setTargetFrameRate(60);
		container.setVSync(true);
		container.setShowFPS(false);
		container.start();
	}
	
	private GameContainer container;
	private EclipseWorld world;
	private LocalPlayer player;
	private PerspectiveTransformer transformer;
			
	public Eclipse() {
		super("Eclipse");
		
		world = new EclipseWorld();
		world.add(new SpaceBackground(this), RenderLayer.Background);

		player = new LocalPlayer(this);
		world.add(player, RenderLayer.Humans);
		
		// Arbitrary initialization section
		
		Ship ship = new BasicShip2(this);
		world.add(ship, RenderLayer.Ships);
		ship.applyForce(new Vector2(1400_0400, 1400_0400));
		ship.applyTorque(500000);
	}
	
	public EclipseWorld getWorld() {
		return world;
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		transformer.setAngle(player.getPerspectiveAngle());
		transformer.setScale(player.getPerspectiveScale());
		transformer.setPosition(player.getPerspectivePosition());
		
		transformer.transform(g);
		
		world.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) container.exit();
		if (container.getInput().isKeyPressed(Input.KEY_GRAVE)) container.exit();
		
		world.completeUpdate(delta);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		world.init();
		transformer = new PerspectiveTransformer(container);
	}

	public GameContainer getContainer() {
		return container;
	}
	
	public PerspectiveTransformer getTransformer() {
		return transformer;
	}

}
