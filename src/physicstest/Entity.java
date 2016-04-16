package physicstest;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Entity {
	
	void render(Graphics g, float pixelsPerMeter) throws SlickException;
	
	void update(int delta) throws SlickException;
	
	Body getBody();
	
	void applyForce(Vector2 force);
	
	void applyForce(Vector2 force, Vector2 point);

	void applyTorque(double torque);
	
	/**
	 * @param location in meters
	 */
	void setLocation(Vector2 location);

	void setMass(MassType type);

	void setMassType(MassType type);
	
}
