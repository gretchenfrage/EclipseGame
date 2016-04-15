package com.phoenixkahlo.roomgame.physics;

import com.phoenixkahlo.roomgame.geometry.Polygon;


/**
 * A moving object within a physics box.
 * @param <E> the type of physicsbox this belongs to
 */
public interface Entity<E> {

	void init(E box);
	
	/**
	 * Called at the beginning of each update
	 */
	void update(E box, int delta);
	
	void clipCollide(ClipCollisionEvent event);
	
	void entityCollide(EntityCollisionEvent event);
	
	/**
	 * @return how much this entity wants to move in this update
	 */
	float getXMovement();
	
	float getYMovement();
	
	Polygon getShape();
	
}
