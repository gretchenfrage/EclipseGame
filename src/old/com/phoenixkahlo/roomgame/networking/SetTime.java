package old.com.phoenixkahlo.roomgame.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import old.com.phoenixkahlo.roomgame.client.Client;
import old.com.phoenixkahlo.roomgame.networking.core.Sendable;
import old.com.phoenixkahlo.roomgame.networking.core.StreamUtils;

public class SetTime implements Sendable {

	private int time;
	
	public SetTime(int time) {
		this.time = time;
	}
	
	public SetTime(InputStream in) throws IOException {
		time = StreamUtils.readInt(in);
	}
	
	@Override
	public void write(OutputStream out) throws IOException {
		StreamUtils.writeInt(time, out);
	}

	@Override
	public void effectClient(Client client) {
		client.setTime(time);
	}

	@Override
	public void effectServer(ServerConnection connection) {
		throw new RuntimeException();
	}

}
