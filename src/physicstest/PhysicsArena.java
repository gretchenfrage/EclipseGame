package physicstest;
import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class PhysicsArena {
	
	private World world = new World();
	private List<Entity> entities = new ArrayList<Entity>();
	
	/**
	 * @param width width in meters
	 * @param height height in meters
	 */
	public PhysicsArena(float width, float height) {
		world.setGravity(World.ZERO_GRAVITY);
		// Create borders
		Entity borderN = new SquareEntity(width + 20, 10);
		borderN.setMassType(MassType.INFINITE);
		borderN.setLocation(new Vector2(width / 2, -5));
		add(borderN);
		Entity borderS = new SquareEntity(width + 20, 10);
		borderS.setMassType(MassType.INFINITE);
		borderS.setLocation(new Vector2(width / 2, height + 5));
		add(borderS);
		Entity borderW = new SquareEntity(10, height + 20);
		borderW.setMassType(MassType.INFINITE);
		borderW.setLocation(new Vector2(-5, height / 2));
		add(borderW);
		Entity borderE = new SquareEntity(10, height + 20);
		borderE.setMassType(MassType.INFINITE);
		borderE.setLocation(new Vector2(width + 5, height / 2));
		add(borderE);
	}
	
	public void render(Graphics g, float pixelsPerMeter) throws SlickException {
		for (Entity entity : entities) {
			entity.render(g, pixelsPerMeter);
		}
	}

	public void update(int delta) throws SlickException {
		for (Entity entity : entities) {
			entity.update(delta);
		}
		world.update(delta / 1000f);
	}
	
	public void add(Entity entity) {
		entities.add(entity);
		world.addBody(entity.getBody());
	}
	
	public void setGravity(Vector2 gravity) {
		world.setGravity(gravity);
	}
	
}
