package old.com.phoenixkahlo.roomgame.client.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class BasicEntity implements ClientEntity {

	private Image image;
	private float x;
	private float y;
	private float angle;
	private float imgCenterX;
	private float imgCenterY;
	private float imgWidth;
	private float imgHeight;
	private float zeroAngle = 0;
	
	/*
	 * if imgCenterX or imgCenterY are given a negative value, they will be set to the center of the image when injected
	 */
	public BasicEntity(float x, float y, float angle, float imgCenterX, float imgCenterY, float imgWidth, float imgHeight) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.imgCenterX = imgCenterX;
		this.imgCenterY = imgCenterY;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}
	
	public BasicEntity(float x, float y, float imgWidth, float imgHeight) {
		this(x, y, 0, -1, -1, imgWidth, imgHeight);
	}
	
	protected void setZeroAngle(float zeroAngle) {
		this.zeroAngle = zeroAngle;
	}
	
	protected void injectImage(Image image) {
		this.image = image;
		if (imgCenterX < 0) imgCenterX = image.getWidth() / 2;
		if (imgCenterY < 0) imgCenterY = image.getHeight() / 2;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.rotate(x, y, -(angle + zeroAngle));
		g.drawImage(image, x - imgCenterX / image.getWidth() * imgWidth, y - imgCenterY / image.getHeight() * imgHeight,
				x - imgCenterX / image.getWidth() * imgWidth + imgWidth, y - imgCenterY / image.getHeight() * imgHeight + imgHeight,
				0, 0, image.getWidth(), image.getHeight());
		g.rotate(x, y, angle + zeroAngle);
	}
	
	public void pointToMouse(Input input) {
		setAngle((float) (Math.atan2(input.getMouseX() - getX(), input.getMouseY() - getY()) / Math.PI * 180) - 90);
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getAngle() {
		return angle;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}

	@Override
	public void init() throws SlickException {}

	@Override
	public void update(int delta) throws SlickException {}

}
