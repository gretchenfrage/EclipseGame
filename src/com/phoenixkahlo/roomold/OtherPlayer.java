package com.phoenixkahlo.roomold;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class OtherPlayer extends BasicEntity {

	private String id;
	private float initX;
	private float initY;
	
	public OtherPlayer(String id, float initX, float initY) {
		this.id = id;
		this.initX = initX;
		this.initY = initY;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		injectFields(ResourceUtils.loadImage("resources/images/wheat.png"), initX, initY, 50, 50);
	}

}
