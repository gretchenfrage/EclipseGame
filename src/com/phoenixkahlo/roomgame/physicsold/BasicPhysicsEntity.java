package com.phoenixkahlo.roomgame.physicsold;

import java.util.List;

import com.phoenixkahlo.roomgame.geometry.Point;
import com.phoenixkahlo.roomgame.geometry.Polygon;
import com.phoenixkahlo.roomgame.geometry.Segment;

public abstract class BasicPhysicsEntity implements PhysicsEntity {

	private Polygon shape;
	private float xAcceleration;
	private float yAcceleration;
	
	public BasicPhysicsEntity(Polygon shape) {
		this.shape = shape;
	}
	
	protected void setXAcceleration(float xAcceleration) {
		this.xAcceleration = xAcceleration;
	}
	
	protected void setYAcceleration(float yAcceleration) {
		this.yAcceleration = yAcceleration;
	}
	
	@Override
	public List<Point> getCorners() {
		return shape.corners();
	}

	@Override
	public List<Segment> getSides() {
		return shape.sides();
	}

	@Override
	public float getXAcceleration() {
		return xAcceleration;
	}

	@Override
	public float getYAcceleration() {
		return yAcceleration;
	}
	
	@Override
	public void clipCollision(PhysicsBox box) {}
	
	@Override
	public void entityCollision(PhysicsBox box, PhysicsEntity entity) {}
	

}
