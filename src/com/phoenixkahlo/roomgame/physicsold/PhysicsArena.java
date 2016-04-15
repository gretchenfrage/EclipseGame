package com.phoenixkahlo.roomgame.physicsold;

import java.util.ArrayList;
import java.util.List;

import com.phoenixkahlo.roomgame.geometry.Point;

/**
 * A physics box with rectangular borders upon which objects slide
 */
public class PhysicsArena implements PhysicsBox {

	private class ContainedEntity {
		
		PhysicsEntity wrappedEntity;
		float x;
		float y;
		float xVel;
		float yVel;
		
		ContainedEntity(PhysicsEntity entity, float x, float y, float xVel, float yVel) {
			this.wrappedEntity = entity;
			this.x = x;
			this.y = y;
			this.xVel = xVel;
			this.yVel = yVel;
		}
		
	}
	
	private float width;
	private float height;
	
	private List<ContainedEntity> entities = new ArrayList<ContainedEntity>();
	
	public PhysicsArena(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public void add(PhysicsEntity entity, float x, float y, float xVel, float yVel) {
		entities.add(new ContainedEntity(entity, x, y, xVel, yVel));
	}
	
	@Override
	public void remove(PhysicsEntity entity) {
		entities.remove(getEntry(entity));
	}
	
	/**
	 * Called from the server/client
	 * @param delta time in milliseconds since the last update
	 */
	public void update(int delta) {
		for (ContainedEntity entity : entities) {
			entity.wrappedEntity.update(this, delta);
			
			entity.xVel += entity.wrappedEntity.getXAcceleration();
			entity.yVel += entity.wrappedEntity.getYAcceleration();
			
			for (Point point : entity.wrappedEntity.getCorners()) {
				float potentialX = entity.x + point.x() + entity.xVel;
				float potentialY = entity.y + point.y() + entity.yVel;
				
				if (potentialX < 0)
					entity.xVel = 0 - (entity.x + point.x());
				else if (potentialX > width)
					entity.xVel = width - (entity.x + point.x());
				
				if (potentialY < 0)
					entity.yVel = 0 - (entity.y + point.y());
				else if (potentialY > height)
					entity.yVel = height - (entity.y + point.y());
			}
			
			entity.x += entity.xVel;
			entity.y += entity.yVel;
		}
	}
	
	private ContainedEntity getEntry(PhysicsEntity search) {
		for (ContainedEntity entity : entities) {
			if (entity.wrappedEntity == search) return entity;
		}
		return null;
	}
	
	@Override
	public boolean contains(PhysicsEntity entity) {
		return getEntry(entity) != null;
	}

	@Override
	public float xOf(PhysicsEntity entity) {
		return getEntry(entity).x;
	}

	@Override
	public float yOf(PhysicsEntity entity) {
		return getEntry(entity).y;
	}

	@Override
	public float xVelOf(PhysicsEntity entity) {
		return getEntry(entity).xVel;
	}

	@Override
	public float yVelOf(PhysicsEntity entity) {
		return getEntry(entity).yVel;
	}

}
