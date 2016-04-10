package com.phoenixkahlo.roomold;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Bow extends BasicItem {

	private Sound fireSound;
	
	@Override
	public void init(GameContainer container) throws SlickException {
		injectFields(ResourceUtils.loadImage("resources/images/minecraft/bow_standby.png"));
		fireSound = ResourceUtils.loadSound("resources/audio/bowfire.wav");
	}

	@Override
	public void activate(float x, float y, Player player, Room game) throws SlickException {
		game.addEntity(new Arrow(player.getX(), player.getY(), player.getAngle()));
		fireSound.play();
	}

}
