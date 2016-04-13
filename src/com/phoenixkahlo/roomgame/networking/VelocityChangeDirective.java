package com.phoenixkahlo.roomgame.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.newdawn.slick.SlickException;

import com.phoenixkahlo.roomgame.client.Client;
import com.phoenixkahlo.roomgame.client.PhysicsChangeDirective;
import com.phoenixkahlo.roomgame.networking.core.StreamUtils;
import com.phoenixkahlo.roomgame.networking.core.Sendable;

public class VelocityChangeDirective implements PhysicsChangeDirective, Sendable {

	private int time;
	private String id;
	
	public VelocityChangeDirective(int time, String id) {
		this.time = time;
		this.id = id;
	}
	
	public VelocityChangeDirective(InputStream in) throws IOException {
		time = StreamUtils.readInt(in);
		id = StreamUtils.readString(in);
	}
	
	@Override
	public int getTime() {
		return time;
	}

	@Override
	public void implement(Client client, int delta) throws SlickException {
		client.getEntity(id).update(delta);
	}

	@Override
	public void write(OutputStream out) throws IOException {
		StreamUtils.writeInt(time, out);
		StreamUtils.writeString(id, out);
	}

	@Override
	public void effectClient(Client client) {
		client.queueDirective(this);
	}

	@Override
	public void effectServer(ServerConnection connection) {
		throw new RuntimeException();
	}

}
