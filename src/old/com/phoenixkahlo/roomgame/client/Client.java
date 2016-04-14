package old.com.phoenixkahlo.roomgame.client;

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

import old.com.phoenixkahlo.roomgame.client.entities.ClientEntity;
import old.com.phoenixkahlo.roomgame.client.entities.ResourceUtils;
import old.com.phoenixkahlo.roomgame.networking.ClientConnection;
import old.com.phoenixkahlo.roomgame.networking.PhysicsChangeDirective;
import old.com.phoenixkahlo.roomgame.networking.RoomGameSendableCoder;
import old.com.phoenixkahlo.roomgame.networking.core.SendableCoder;

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
	
	private int time = 0;
	
	private SendableCoder coder;
	private Background background;
	private ClientConnection connection;
	
	private Map<String, ClientEntity> entities;
	private List<PhysicsChangeDirective> physicsDirectives;
	
	public Client(String ip, int port) {
		super("Game");
		coder = new RoomGameSendableCoder();
		try {
			connection = new ClientConnection(new Socket(ip, port), this, coder);
		} catch (IOException e) {
			e.printStackTrace();
			disconnected();
		}
		entities = new HashMap<String, ClientEntity>();
		physicsDirectives = new ArrayList<PhysicsChangeDirective>();
	}
	
	public synchronized void addEntity(String name, ClientEntity entity) {
		entities.put(name, entity);
	}
	
	public ClientEntity getEntity(String name) {
		return entities.get(name);
	}
	
	public synchronized void queueDirective(PhysicsChangeDirective directive) {
		physicsDirectives.add(directive);
		
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		background.render(container, g);
		for (ClientEntity entity : entities.values()) {
			entity.render(container, g);
		}
	}

	@Override
	public synchronized void update(GameContainer container, int delta) throws SlickException {
		time += delta;
		
		for (int i = physicsDirectives.size() - 1; i >= 0; i--) {
			if (physicsDirectives.get(i).getTime() >= time)
				physicsDirectives.remove(i).implement(this, time - physicsDirectives.get(i).getTime());
		}
		
		for (ClientEntity entity : entities.values()) {
			entity.update(delta);
		}
	}
	
	public synchronized void setTime(int time) {
		this.time = time;
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		background = new Background(ResourceUtils.loadImage("sprites/stone_andesite_smooth"), 50);
	}
	
	public void disconnected() {
		System.exit(1);
	}
	
}
