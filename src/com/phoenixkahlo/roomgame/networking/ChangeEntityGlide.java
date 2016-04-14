package com.phoenixkahlo.roomgame.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.newdawn.slick.SlickException;

import com.phoenixkahlo.roomgame.client.Client;
import com.phoenixkahlo.roomgame.client.entities.GlidingEntity;
import com.phoenixkahlo.roomgame.networking.core.StreamUtils;

/**
 * Directive for client to change the movement path of a GlidingEntity
 */
public class ChangeEntityGlide extends SendablePhysicsChangeDirective {
	
	private float x;
	private float y;
	private float xVel;
	private float yVel;
	private String id;
	
	public ChangeEntityGlide(int time, float x, float y, float xVel, float yVel, String id) {
		super(time);
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
		this.id = id;
	}
	
	public ChangeEntityGlide(InputStream in) throws IOException {
		super(in);
		x = StreamUtils.readFloat(in);
		y = StreamUtils.readFloat(in);
		xVel = StreamUtils.readFloat(in);
		yVel = StreamUtils.readFloat(in);
		id = StreamUtils.readString(in);
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
		super.write(out);
		StreamUtils.writeFloat(x, out);
		StreamUtils.writeFloat(y, out);
		StreamUtils.writeFloat(xVel, out);
		StreamUtils.writeFloat(yVel, out);
		StreamUtils.writeString(id, out);
	}
	
}
