package com.phoenixkahlo.roomgame.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.phoenixkahlo.roomgame.client.Client;
import com.phoenixkahlo.roomgame.networking.core.Sendable;
import com.phoenixkahlo.roomgame.networking.core.StreamUtils;

public abstract class SendablePhysicsChangeDirective implements PhysicsChangeDirective, Sendable {

	private int time;
	
	public SendablePhysicsChangeDirective(int time) {
		this.time = time;
	}
	
	public SendablePhysicsChangeDirective(InputStream in) throws IOException {
		time = StreamUtils.readInt(in);
	}
	
	@Override
	public int getTime() {
		return time;
	}

	@Override
	public void effectClient(Client client) {
		client.queueDirective(this);
	}

	@Override
	public void effectServer(ServerConnection connection) {
		throw new RuntimeException();
	}
	
	@Override
	public void write(OutputStream out) throws IOException {
		StreamUtils.writeInt(time, out);
	}

}
