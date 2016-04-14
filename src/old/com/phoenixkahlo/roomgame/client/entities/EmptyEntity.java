package old.com.phoenixkahlo.roomgame.client.entities;

import org.newdawn.slick.SlickException;

/**
 * An entity that's really just a means of displaying an image at a certain place.
 */
public class EmptyEntity extends BasicEntity {

	private String imgName;
	
	public EmptyEntity(String imgName, float x, float y, float angle, float imgCenterX, float imgCenterY,
			float imgWidth, float imgHeight) {
		super(x, y, angle, imgCenterX, imgCenterY, imgWidth, imgHeight);
		this.imgName = imgName;
	}
	
	public EmptyEntity(String imgName, float x, float y, float imgWidth, float imgHeight) {
		this(imgName, x, y, 0, -1, -1, imgWidth, imgHeight);
	}

	@Override
	public void init() throws SlickException {
		injectImage(ResourceUtils.loadImage(imgName));
	}

}
