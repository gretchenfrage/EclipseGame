package com.phoenixkahlo.eclipse;

import static org.newdawn.slick.Input.*;

import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class InputHandler {
	
	private Eclipse eclipse;
	private Human player;
		
	public InputHandler(Human player, Eclipse eclipse) {
		this.player = player;
		this.eclipse = eclipse;
	}
	
	public void update(Input input, int delta) {
		boolean sprinting = input.isKeyDown(KEY_LSHIFT);
		
		Vector2 direction = new Vector2(0, 0);
		if (input.isKeyDown(KEY_A)) direction.x -= 1;
		if (input.isKeyDown(KEY_D)) direction.x += 1;
		if (input.isKeyDown(KEY_W)) direction.y -= 1;
		if (input.isKeyDown(KEY_S)) direction.y += 1;
		
		player.setDirection(direction);
		player.setSprinting(sprinting);
	}
	
	public Vector2 getTranslation(GameContainer container, int pixelsPerMeter) {
		Vector2 trans = player != null ? player.getWorldCenter() : new Vector2(0, 0);
		trans.multiply(pixelsPerMeter).multiply(-1).add(container.getWidth() / 2, container.getHeight() / 2);
		return trans;
	}
	
	/**
	 * The first thing to be rendered
	 */
	public void preRender(GameContainer container, Graphics g, int pixelsPerMeter) {
		player.pointTowardsMouse(container.getInput(), eclipse.getTranslation(), pixelsPerMeter);
	}
	
	/**
	 * The last thing to be rendered
	 */
	public void postRender(GameContainer container, Graphics g, int pixelsPerMeter) {
		
	}
	
}
