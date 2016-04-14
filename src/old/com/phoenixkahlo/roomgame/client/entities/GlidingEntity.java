package old.com.phoenixkahlo.roomgame.client.entities;

import org.newdawn.slick.SlickException;

public class GlidingEntity extends BasicEntity {
	
	private float xVel;
	private float yVel;
	
	public GlidingEntity(float x, float y, float angle, float xVel, float yVel, float imgCenterX,
			float imgCenterY, float imgWidth, float imgHeight) {
		super(x, y, angle, imgCenterX, imgCenterY, imgWidth, imgHeight);
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	public GlidingEntity(float x, float y, float angle, float speed, float imgCenterX,
			float imgCenterY, float imgWidth, float imgHeight) {
		super(x, y, angle, imgCenterX, imgCenterY, imgWidth, imgHeight);
		setVel(angle, speed);
	}
	
	public void setXVel(float xVel) {
		this.xVel = xVel;
	}
	
	public void setYVel(float yVel) {
		this.yVel = yVel;
	}
	
	public void setVel(float angle, float speed) {
		xVel = (float) Math.cos(Math.toRadians(angle)) * speed;
		yVel = (float) Math.sin(Math.toRadians(angle)) * speed;
	}
	
	@Override
	public void update(int delta) throws SlickException {
		setX(getX() + xVel * delta);
		setY(getY() + yVel * delta);
	}
	
}
