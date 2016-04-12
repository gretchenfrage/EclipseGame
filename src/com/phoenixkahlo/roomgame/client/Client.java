package com.phoenixkahlo.roomgame.client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.phoenixkahlo.roomgame.networking.ClientConnection;
import com.phoenixkahlo.roomgame.networking.RoomGameSendableCoder;
import com.phoenixkahlo.roomgame.networking.core.SendableCoder;
import com.phoenixkahlo.roomgame.utils.ResourceUtils;

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
	
	private int deltaSum = 0;
	
	private SendableCoder coder;
	private Background background;
	private ClientConnection connection;
	
	private Map<String, Entity> entities;
	private List<ClientPhysicsInjection> physicsQueue;
	
	public Client(String ip, int port) {
		super("Game");
		coder = new RoomGameSendableCoder();
		try {
			connection = new ClientConnection(new Socket(ip, port), this, coder);
		} catch (IOException e) {
			e.printStackTrace();
			disconnected();
		}
		entities = new HashMap<String, Entity>();
		physicsQueue = new ArrayList<ClientPhysicsInjection>();
	}
	
	public void addEntity(String name, Entity entity) {
		entities.put(name, entity);
	}
	
	public void queueDirective(ClientPhysicsInjection injection) {
		synchronized (physicsQueue) {
			physicsQueue.add(injection);
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		background.render(container, g);
		for (Entity entity : entities.values()) {
			entity.render(container, g);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		deltaSum += delta;
		
		synchronized (physicsQueue) {
			for (int i = physicsQueue.size() - 1; i >= 0; i--) {
				if (physicsQueue.get(i).isReady(deltaSum)) physicsQueue.remove(i).inject(this, deltaSum);
			}
		}
		
		for (Entity entity : entities.values()) {
			entity.update(container, delta);
		}
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		background = new Background(ResourceUtils.loadImage("sprites/stone_andesite_smooth"), 50);
	}
	
	public void disconnected() {
		System.exit(1);
	}
	
}
