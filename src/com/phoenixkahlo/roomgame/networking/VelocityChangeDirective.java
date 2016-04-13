package com.phoenixkahlo.roomgame.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.newdawn.slick.SlickException;

import com.phoenixkahlo.roomgame.client.Client;
import com.phoenixkahlo.roomgame.client.GlidingEntity;
import com.phoenixkahlo.roomgame.client.PhysicsChangeDirective;
import com.phoenixkahlo.roomgame.networking.core.Sendable;
import com.phoenixkahlo.roomgame.networking.core.StreamUtils;

public class VelocityChangeDirective implements PhysicsChangeDirective, Sendable {

	private int time;
	private float x;
	private float y;
	private float xVel;
	private float yVel;
	private String id;
	
	public VelocityChangeDirective(int time, float x, float y, float xVel, float yVel, String id) {
		this.time = time;
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
		this.id = id;
	}
	
	public VelocityChangeDirective(InputStream in) throws IOException {
		time = StreamUtils.readInt(in);
		x = StreamUtils.readFloat(in);
		y = StreamUtils.readFloat(in);
		xVel = StreamUtils.readFloat(in);
		yVel = StreamUtils.readFloat(in);
		id = StreamUtils.readString(in);
	}
	
	@Override
	public int getTime() {
		return time;
	}

	@Override
	public void implement(Client client, int delta) throws SlickException {
		GlidingEntity entity = (GlidingEntity) client.getEntity(id);
		entity.setX(x);
		entity.setY(y);
		entity.setXVel(xVel);
		entity.setYVel(yVel);
		entity.update(delta);
	}

	@Override
	public void write(OutputStream out) throws IOException {
		StreamUtils.writeInt(time, out);
		StreamUtils.writeFloat(x, out);
		StreamUtils.writeFloat(y, out);
		StreamUtils.writeFloat(xVel, out);
		StreamUtils.writeFloat(yVel, out);
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
