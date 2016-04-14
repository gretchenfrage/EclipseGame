package old.com.phoenixkahlo.roomgame.networking;

import static old.com.phoenixkahlo.roomgame.networking.core.StreamUtils.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.newdawn.slick.SlickException;

import old.com.phoenixkahlo.roomgame.client.Client;
import old.com.phoenixkahlo.roomgame.client.entities.ThinClientEntity;

public class CreateThinClientEntity extends SendablePhysicsChangeDirective {

	private float x;
	private float y;
	private float imgWidth;
	private float imgHeight;
	private String id;
	private String imgName;
	
	public CreateThinClientEntity(int time, float x, float y, float imgWidth, float imgHeight,
			String id, String imgName) {
		super(time);
		this.x = x;
		this.y = y;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.id = id;
		this.imgName = imgName;
	}
	
	public CreateThinClientEntity(InputStream in) throws IOException {
		super(in);
		x = readFloat(in);
		y = readFloat(in);
		imgWidth = readFloat(in);
		imgHeight = readFloat(in);
		id = readString(in);
		imgName = readString(in);
	}
	
	@Override
	public void write(OutputStream out) throws IOException {
		super.write(out);
		writeFloat(x, out);
		writeFloat(y, out);
		writeFloat(imgWidth, out);
		writeFloat(imgHeight, out);
		writeString(id, out);
		writeString(imgName, out);
	}

	@Override
	public void implement(Client client, int delta) throws SlickException {
		client.addEntity(id, new ThinClientEntity(x, y, imgWidth, imgHeight, imgName));
	}
	
}
