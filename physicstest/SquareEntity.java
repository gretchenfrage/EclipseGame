package physicstest;
import java.util.Random;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SquareEntity implements Entity {

	private float width;
	private float height;
	private Body body;
	private Color color = new Color(0xFF000000 | new Random().nextInt());
	
	public SquareEntity(float width, float height) {
		this.width = width;
		this.height = height;
		body = new Body();
		body.addFixture(new Rectangle(width, height));
		body.setMass(MassType.NORMAL);
	}

	@Override
	public void render(Graphics g, float pixelsPerMeter) throws SlickException {
		// Create vectors for rectangle corners
		Vector2[] corners = new Vector2[4];
		corners[0] = new Vector2(-width / 2, -height / 2);
		corners[1] = new Vector2(width / 2, -height / 2);
		corners[2] = new Vector2(width / 2, height / 2);
		corners[3] = new Vector2(-width / 2, height / 2);
		// Rotate vectors to angle of entity
		for (int i = 0; i < 4; i++) {
			corners[i].rotate(body.getTransform().getRotation());
		}
		// Translate vectors to position of entity
		for (int i = 0; i < 4; i++) {
			corners[i].add(body.getWorldCenter());
		}
		// Scale vectors from meters position to pixels position
		for (int i = 0; i < 4; i++) {
			corners[i].multiply(pixelsPerMeter);
		}
		// Render rectangle
		g.setColor(color);
		for (int i = 0; i < 4; i++) {
			g.drawLine((float) corners[i].x, (float) corners[i].y, (float) corners[(i + 1) % 4].x, (float) corners[(i + 1) % 4].y);
		}
	}

	@Override
	public void update(int delta) throws SlickException {
		if (Math.random() < 0.0001) {
			applyForce(new Vector2(Math.random() * 1000000, Math.random() * 1000000));
		}
	}

	@Override
	public Body getBody() {
		return body;
	}

	@Override
	public void applyForce(Vector2 force) {
		body.applyForce(force);
	}

	@Override
	public void applyForce(Vector2 force, Vector2 point) {
		body.applyForce(force, point);
	}

	@Override
	public void applyTorque(double torque) {
		body.applyTorque(torque);
	}

	@Override
	public void setLocation(Vector2 location) {
		body.translate(location.subtract(body.getWorldCenter()));
	}
	
	@Override
	public void setMass(MassType type) {
		body.setMass(type);
	}
	
	@Override
	public void setMassType(MassType type) {
		body.setMassType(type);
	}

}
