package com.phoenixkahlo.roomgame.physicsold;

import java.util.List;

import com.phoenixkahlo.roomgame.geometry.Point;
import com.phoenixkahlo.roomgame.geometry.Segment;

/**
 * A rigid solid that can move and bounce around and what not
 */
public interface PhysicsEntity {

	List<Point> getCorners();
	
	List<Segment> getSides();
	
	float getXAcceleration();
	
	float getYAcceleration();
	
	/**
	 * Called by the physics box so it can change acceleration or whatnot
	 * @param delta time in milliseconds since the last update
	 */
	void update(PhysicsBox box, int delta);
	
	void clipCollision(PhysicsBox box);
	
	void entityCollision(PhysicsBox box, PhysicsEntity entity);
	
}