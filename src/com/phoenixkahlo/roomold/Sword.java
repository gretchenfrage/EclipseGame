package com.phoenixkahlo.roomold;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sword extends BasicItem {

	private Sound[] swingSounds;
	
	@Override
	public void init(GameContainer container) throws SlickException {
		injectFields(ResourceUtils.loadImage("resources/images/minecraft/iron_sword.png"));
		swingSounds = new Sound[2];
		swingSounds[0] = ResourceUtils.loadSound("resources/audio/sword_swing_1.wav");
		swingSounds[1] = ResourceUtils.loadSound("resources/audio/sword_swing_2.wav");
	}

	@Override
	public void activate(float x, float y, Player player, Room game) {
		swingSounds[(int) (Math.random() * swingSounds.length)].play(1, 0.5f);;
	}

}
