package com.phoenixkahlo.roomgame.physics;

import com.phoenixkahlo.roomgame.geometry.Polygon;

public class BasicEntity<E extends GeneralPhysicsBox> implements Entity<E> {

	private Polygon shape;
	
	public BasicEntity(Polygon shape) {
		this.shape = shape;
	}
	
	@Override
	public void init(E box) {
		
	}
	
	@Override
	public void update(E box, int delta) {
		
	}
	
	@Override
	public void clipCollide(ClipCollisionEvent event) {
		
	}

	@Override
	public void entityCollide(EntityCollisionEvent event) {
		
	}

	@Override
	public float getXMovement() {
		
	}

	@Override
	public float getYMovement() {
		
	}
	
	@Override
	public Polygon getShape() {
		return shape;
	}

}
