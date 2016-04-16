package physicstest;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class RoomGame extends BasicGame {

	public static final int WIDTH = 900;
	public static final int HEIGHT = WIDTH;
	
	public static final int PIXELS_PER_METER = WIDTH / 100;
	
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new RoomGame(), WIDTH, HEIGHT, false);
			container.setTargetFrameRate(60);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private PhysicsArena box;
	
	public RoomGame() {
		super("Bad And Naughty Rigid Solids Are Placed In The Random Acceleration Chamber To Atone For Their Sins");
		
		box = new PhysicsArena(100, 100);
		box.setGravity(World.EARTH_GRAVITY.multiply(-1));
		for (int i = 0; i < 1000; i++) {
			Entity e = new SquareEntity((float) Math.random() * 2, (float) Math.random() * 2);
			e.setLocation(new Vector2(Math.random() * 8 + 1, Math.random() * 98 + 1));
			box.add(e);
		}
		
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		box.render(g, PIXELS_PER_METER);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		box.update(delta);
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {}

}
