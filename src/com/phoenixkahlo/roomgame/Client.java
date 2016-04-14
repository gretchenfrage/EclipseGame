package com.phoenixkahlo.roomgame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.phoenixkahlo.roomgame.graphics.Background;
import com.phoenixkahlo.roomgame.graphics.ResourceUtils;

public class Client extends BasicGame {

	/*
	 * args = {} or {port, ip}
	 */
	public static void main(String[] args) {
		if (args.length < 2)
			new Client(/*"24.183.99.218"*/"localhost", 39450).start();
		else
			new Client(args[0], Integer.parseInt(args[1])).start();
	}

	public void start() {
		try {
			AppGameContainer container = new AppGameContainer(this, 700, 700, false);
			container.setTargetFrameRate(60);
			container.setVSync(true);
			container.setShowFPS(false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private Background background;
	
	public Client(String ip, int port) {
		super("Game");
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		background = new Background(ResourceUtils.loadImage("sprites/stone_andesite_smooth"), 50);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		background.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
	}
}
